package com.happiestminds.asi.service;

import java.util.List;

import org.dozer.Mapper;
import org.hibernate.criterion.Criterion;
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
	
	@Autowired
	private Mapper mapper;
	
	@Transactional
	public EmployeeDTO findById(Long id) {
		return mapper.map(empRepository.findOne(id), EmployeeDTO.class);
	}
	
	@Transactional
	public Long checkLogin(String emailId, String password) {
		
		Criterion criterion = Restrictions.or(Restrictions.eq("emailId", emailId), Restrictions.eq("empCode", emailId));
		
		List<Employee> employees = empRepository.findByCriteria(criterion);
		if(!employees.isEmpty()) {
			Employee employee = employees.get(0);
			if(HashUtils.matches(password, employee.getPassword())) {
				return employee.getId();
			}
		} 
		return null;
	}
}
