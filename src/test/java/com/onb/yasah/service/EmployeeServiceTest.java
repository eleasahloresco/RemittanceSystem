package com.onb.yasah.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.runners.MockitoJUnitRunner;

import com.onb.yasah.dao.EmployeeDao;
import com.onb.yasah.domain.Branch;
import com.onb.yasah.domain.Employee;
import com.onb.yasah.exceptions.RemittanceException;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

	@Mock
	private EmployeeDao employeeDao;
	
	private Employee employee;
	
	@Before
	public void setUp() throws RemittanceException{
		employeeService.setEmployeeDao(employeeDao);
		employee = new Employee("Yasah", "Loresco", "ASE", new Long("3"), new Branch());
//		employeeService.add(employee);
	}
	
	@Test
	public void shouldVerifyThatAddEmployeeDaoWasCalled() throws RemittanceException {
		employeeService.add(employee);
		Mockito.verify(employeeDao).add(employee);
	}
	
	@Test(expected = MockitoException.class)
	public void shouldThrowExceptionWhenAddingEmptyEmployee() throws RemittanceException{
		Employee emptyEmployee = new Employee();
		Mockito.doThrow(new RemittanceException("Employee is empty!")).when(employeeDao).add(emptyEmployee);

		employeeService.add(emptyEmployee);
	}
	
	@Test
	public void shouldVerifyThatUpdateEmployeeDaoWasCalled() {
		employeeService.update(employee);
		
		Mockito.verify(employeeDao).update(employee);
	}
	
	@Test
	public void shouldVerifyThatGetEmployeeDaoWasCalled() throws RemittanceException{
		when(employeeDao.get(1l)).thenReturn(employee);
		Employee retrievedEmployee = employeeService.get(1l);
		
		Mockito.verify(employeeDao).get(1l);
		assertEquals("Yasah", employee.getFirstName());
	}
	
	@Test(expected = MockitoException.class)
	public void shouldThrowExceptionWhenRetrievingNonExistingEmployee() throws RemittanceException{
		Mockito.doThrow(new RemittanceException("Not Existing Employee!")).when(employeeDao).get(2l);
		
		employeeService.get(2l);
	}
	
	@Test
	public void shouldVerifyThatDeleteEmployeeDaoWasCalled() throws RemittanceException{
		employeeService.delete(employee);
		
		Mockito.verify(employeeDao).delete(employee);
	}
}
