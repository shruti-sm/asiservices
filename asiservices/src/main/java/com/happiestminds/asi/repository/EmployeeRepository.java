package com.happiestminds.asi.repository;

import org.springframework.stereotype.Repository;

import com.happiestminds.asi.dao.CommonDAOImpl;
import com.happiestminds.asi.domain.Employee;

/**
 * 
 * @author shruti.mishra
 *
 */
@Repository
public class EmployeeRepository extends CommonDAOImpl<Employee> {

    public EmployeeRepository() {
        setClazz(Employee.class);
    }
}
