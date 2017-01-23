package com.happiestminds.asi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.dozer.Mapper;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.asi.beans.GraphEntry;
import com.happiestminds.asi.constant.Status;
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
	public List<DeclarationFormDTO> findFormsByEmpId(Long id) {

		return mapForms(formRepository.findByCriteria(Restrictions.eq("employee.id", id)));
	}
	
	/**
	 * Retrievs current day's records only
	 * @return
	 */
	@Transactional 
	public List<DeclarationFormDTO> findFormsByDuration(Date startDate, Date endDate, String status) {
		
		return mapForms(formRepository.findByCriteria(Restrictions.ge("status", status),
				Restrictions.ge("leavingDateTime", startDate), 
				Restrictions.le("leavingDateTime", endDate)));
		
	}
	
	@Transactional
	public DeclarationFormDTO updateDeclarationForm(DeclarationFormDTO form) throws ParseException {
		
		DeclarationForm dForm = formRepository.findOne(form.getId());
		if(dForm != null) {
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
		
		Employee employee = empRepository.findOne(form.getId());
		employee.setId(form.getId());
		
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
	
	public List<GraphEntry> findFormCountsByDuration(String groupByField) {
		return findFormCountsByDuration(null, null, groupByField);
	}
	
	//Graph Services
	public List<GraphEntry> findFormCountsByDuration(Date startDate, Date endDate, String groupByField) {
		
		/* .add(Restrictions.ge("leavingDateTime", startDate))
		.add(Restrictions.le("leavingDateTime", endDate))      
        .setProjection(Projections.projectionList()
                .add(Projections.groupProperty(groupByField))
                .add(Projections.count(groupByField))         
        ).setResultTransformer(Transformers.aliasToBean(Entry.class));*/
		
		List<Criterion> criterions = new ArrayList<Criterion>();
		if(startDate != null && endDate != null) {
			criterions.add(Restrictions.ge("leavingDateTime", startDate));
			criterions.add(Restrictions.le("leavingDateTime", endDate));
		}
		List<Projection> projections = new ArrayList<Projection>();
		projections.add(Projections.groupProperty(groupByField).as("key"));
		projections.add(Projections.count(groupByField).as("value"));
		
		return formRepository.findGroupByColums(criterions, projections);
	}
}