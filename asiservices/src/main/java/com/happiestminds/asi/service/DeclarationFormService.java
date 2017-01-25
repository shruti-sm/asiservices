package com.happiestminds.asi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.asi.beans.GraphEntry;
import com.happiestminds.asi.beans.ReportEntry;
import com.happiestminds.asi.constant.Status;
import com.happiestminds.asi.dao.FetchEntitities;
import com.happiestminds.asi.domain.DeclarationForm;
import com.happiestminds.asi.domain.Employee;
import com.happiestminds.asi.domain.Office;
import com.happiestminds.asi.domain.Project;
import com.happiestminds.asi.exception.AsiException;
import com.happiestminds.asi.repository.DeclarationFormRepository;
import com.happiestminds.asi.repository.EmployeeRepository;
import com.happiestminds.asi.repository.OfficeRepository;
import com.happiestminds.asi.repository.ProjectRepository;
import com.happiestminds.asi.util.CommonUtil;
import com.happiestminds.asi.vo.DeclarationFormDTO;

/**
 * 
 * @author shruti.mishra
 *
 */
@Service
public class DeclarationFormService {

	@Autowired
	private DeclarationFormRepository formRepository;
	
	@Autowired
	private EmployeeRepository empRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private OfficeRepository officeRepository;
	@Autowired
    Mapper mapper;
	
	@Transactional
	public Long save(DeclarationFormDTO form) throws AsiException, ParseException {

		return createDeclarationFormEntity(form);
	}

	@Transactional
	public List<DeclarationFormDTO> findFormsByEmpId(Long empId) {

		return mapForms(formRepository.findByCriteria(Restrictions.eq("employee.id", empId)));
	}
	
	@Transactional
	public List<DeclarationFormDTO> findFormByEmpIdInDuration(Long empId, Date startDate, Date endDate) {

		return mapForms(findFormByEmpIdDuration(empId, startDate, endDate));
	}
	@Transactional
	public List<DeclarationFormDTO> findFormByEmpIdCodeUserNameInDuration(String identifier, Date startDate, Date endDate) {

		Map<String, String> aliases = new HashMap<String, String>();
		aliases.put("employee", "emp");
		
		Criterion crit1 = Restrictions.eq("emp.empCode", identifier);
		Criterion crit2 = Restrictions.eq("emp.emailId", identifier);
		
		return mapForms(formRepository.findByCriteria(aliases, Restrictions.or(crit1, crit2), Restrictions.ge("leavingDateTime", startDate), 
				Restrictions.le("leavingDateTime", endDate)));
		
	}
	/**
	 * Retrieves current day's records only
	 * @return
	 */
	@Transactional 
	public List<DeclarationFormDTO> findFormsByDuration(Date startDate, Date endDate) {
		
		return mapForms(formRepository.findByCriteria(Restrictions.ge("leavingDateTime", startDate), 
				Restrictions.le("leavingDateTime", endDate)));
		
	}
	
	@Transactional
	public DeclarationFormDTO updateDeclarationForm(DeclarationFormDTO form) throws ParseException {
		
		List<DeclarationForm> dForms = findFormByEmpIdDuration(form.getEmpId(), CommonUtil.makeDate(null, 0, 0, 0, 0), 
				CommonUtil.makeDate(null, 23, 59, 59, 999));
		if(dForms != null && !dForms.isEmpty()) {
			DeclarationForm dForm = dForms.get(0);
			if(form.getExpectedArrivalDateTime() != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				dForm.setExpectedArrivalDateTime(format.parse(form.getExpectedArrivalDateTime()));
			}
			if(form.getStatus() != null) {
				dForm.setStatus(form.getStatus());
			}
			if(form.getRemarks() != null) {
				dForm.setRemarks(form.getRemarks());
			}
			return mapper.map(dForm, DeclarationFormDTO.class); 
		} else {
			return null;
		}
		
	}
	
	private List<DeclarationFormDTO> mapForms(List<DeclarationForm> forms) {
		List<DeclarationFormDTO> formDTOs = new ArrayList<DeclarationFormDTO>();
		for(DeclarationForm form: forms) {
			formDTOs.add(mapper.map(form, DeclarationFormDTO.class));
		}
		return formDTOs;
	}
	
	
	private Long createDeclarationFormEntity(DeclarationFormDTO form) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		DeclarationForm dForm = new DeclarationForm();
		
		Employee employee = empRepository.findOne(form.getEmpId());
		
		Project project = projectRepository.findOne(form.getProjectId());
		Office office = officeRepository.findOne(form.getOfficeId());
		
		
		dForm.setContactNumber(form.getContactNumber());
		dForm.setEmployee(employee);
		dForm.setExpectedArrivalDateTime(format.parse(form.getExpectedArrivalDateTime()));
		dForm.setLeavingDateTime(format.parse(form.getLeavingDateTime()));
		dForm.setOffice(office);
		dForm.setProject(project);
		dForm.setStatus(Status.PENDING);
		System.out.println("dForm="+dForm);
		
		formRepository.save(dForm);
		
		//update last time interval in employee
		employee.setLastExpectedArrivalTime(CommonUtil.findTimeDuration(dForm.getLeavingDateTime(), dForm.getExpectedArrivalDateTime(), CommonUtil.MILLISECONDS));
		employee.setOffice(office);
		employee.setProject(project);
		empRepository.save(employee);
		
		return dForm.getId();
	}
	
	@Transactional
	public List<GraphEntry> findFormCountsByDuration(String groupByField) {
		return findFormCountsByDuration(null, null, groupByField);
	}
	
	//Graph Services
	@SuppressWarnings("unchecked")
	@Transactional
	public List<GraphEntry> findFormCountsByDuration(Date startDate, Date endDate, String groupByField) {
		
		List<Criterion> criterions = new ArrayList<Criterion>();
		if(startDate != null && endDate != null) {
			criterions.add(Restrictions.ge("leavingDateTime", startDate));
			criterions.add(Restrictions.le("leavingDateTime", endDate));
		}
		List<Projection> projections = new ArrayList<Projection>();
		projections.add(Projections.alias(Projections.groupProperty(groupByField), "key"));
		projections.add(Projections.alias(Projections.count(groupByField), "count"));
		
		return (List<GraphEntry>) formRepository.findByCriteriaWIthAll(criterions, projections, null, null, null, null, null, GraphEntry.class);
	}
	
	//Reports
	@SuppressWarnings("unchecked")
	@Transactional
	public List<ReportEntry> findTopLateLeavers(Date startDate, Date endDate, int n) {
		
		Map<String, String> aliases = new HashMap<String, String>();
		aliases.put("employee", "emp");
		
		Order order = Order.desc("lateCount");
		FetchEntitities fetch = new FetchEntitities("employee", FetchMode.JOIN);
		
		List<Criterion> criterions = new ArrayList<Criterion>();
		if(startDate != null && endDate != null) {
			criterions.add(Restrictions.ge("leavingDateTime", startDate));
			criterions.add(Restrictions.le("leavingDateTime", endDate));
		}
		List<Projection> projections = new ArrayList<Projection>();
		projections.add(Projections.alias(Projections.groupProperty("emp.id"), "empId"));
		projections.add(Projections.alias(Projections.property("emp.empCode"), "empCode"));
		projections.add(Projections.alias(Projections.property("emp.emailId"), "emailId"));
		projections.add(Projections.alias(Projections.count("emp.id"), "lateCount"));
		
		return formRepository.findByCriteriaWIthAll(criterions, projections, aliases, order, fetch, 0, n, ReportEntry.class);
	}
	
	private List<DeclarationForm> findFormByEmpIdDuration(Long empId, Date startDate, Date endDate) {
		return formRepository.findByCriteria(Restrictions.eq("employee.id", empId), Restrictions.ge("leavingDateTime", startDate), 
				Restrictions.le("leavingDateTime", endDate));
	}
	
	
}