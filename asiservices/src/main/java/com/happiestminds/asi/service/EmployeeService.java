package com.happiestminds.asi.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.asi.domain.DeclarationForm;
import com.happiestminds.asi.domain.Employee;
import com.happiestminds.asi.repository.EmployeeRepository;
import com.happiestminds.asi.util.HashUtils;
import com.happiestminds.asi.vo.DeclarationFormDTO;
import com.happiestminds.asi.vo.EmployeeDTO;

/**
 * 
 * @author shruti.mishra
 *
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;
	
	@Transactional
	public EmployeeDTO findById(Long id) {
		
		Employee employee = empRepository.findOne(id);
		EmployeeDTO emp = new EmployeeDTO();
		
		emp.setContactNumber(employee.getContactNumber());
		emp.setEmpCode(employee.getEmpCode());
		emp.setFirstName(employee.getFirstName());
		emp.setId(id);
		emp.setLastExpectedArrivalTime(employee.getLastExpectedArrivalTime());
		emp.setLastName(employee.getLastName());
		emp.setOfficeId(employee.getOffice().getId());
		emp.setProjectId(employee.getProject().getId());
		
		return emp;
	}
	
	@Transactional
	public Long checkLogin(String emailId, String password) {
		
		List<Employee> employees = empRepository.findByCriteria(Restrictions.eq("emailId", emailId));
		if(!emailId.isEmpty()) {
			Employee employee = employees.get(0);
			if(HashUtils.matches(password, employee.getPassword())) {
				return employee.getId();
			}
		} 
		return null;
	}
}
