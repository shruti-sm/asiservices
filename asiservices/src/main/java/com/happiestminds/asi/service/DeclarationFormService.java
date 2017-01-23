package com.happiestminds.asi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		List<DeclarationFormDTO> formDTOs = new ArrayList<DeclarationFormDTO>();
		List<DeclarationForm> forms = formRepository.findByCriteria(Restrictions.eq("employee.id", id));
		for(DeclarationForm form: forms) {
			formDTOs.add(mapper.map(form, DeclarationFormDTO.class));
		}
		return formDTOs;
	}
	
	@Transactional
	
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
		
		System.out.println("dForm="+dForm);
		
		formRepository.save(dForm);
		
		//update last time interval in employee
		employee.setLastExpectedArrivalTime(CommonUtil.findTimeDuration(dForm.getLeavingDateTime(), dForm.getExpectedArrivalDateTime(), CommonUtil.MILLISECONDS));
		employee.setOffice(office);
		employee.setProject(project);
		empRepository.save(employee);
		
		return dForm.getId();
	}
}