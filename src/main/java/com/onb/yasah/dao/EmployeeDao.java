package com.onb.yasah.dao;

import com.onb.yasah.domain.Employee;

public interface EmployeeDao extends GenericDao<Employee, Long>{

	Employee getByEmployeeNo(Long employeeNo);

}
