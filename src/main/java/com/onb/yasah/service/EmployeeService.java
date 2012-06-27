package com.onb.yasah.service;

import java.util.List;

import com.onb.yasah.domain.Employee;
import com.onb.yasah.exceptions.RemittanceException;

public interface EmployeeService {

	void add(Employee employee) throws RemittanceException;

	void update(Employee employee);

	Employee get(long id) throws RemittanceException;

	void delete(Employee employee);

	List<Employee> getAllEmployees();

}
