package com.onb.yasah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onb.yasah.dao.EmployeeDao;
import com.onb.yasah.domain.Employee;
import com.onb.yasah.exceptions.RemittanceException;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;

	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public void add(Employee employee) throws RemittanceException{
		if(employee == null){
			throw new RemittanceException("Employee is empty!");
		}
		
		Employee retrievedEmployee = employeeDao.getByEmployeeNo(employee.getEmployeeNo());
		System.out.println(retrievedEmployee.getEmployeeNo());
		if(retrievedEmployee.getEmployeeNo() != null){
			throw new RemittanceException("Employee No. already exist!");
		}
			
		employeeDao.add(employee);
	}

	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Override
	public Employee get(long id) throws RemittanceException{
		return employeeDao.get(id);
	}

	@Override
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAll();
	}
	
	
}
