package com.onb.yasah.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.onb.yasah.domain.Branch;
import com.onb.yasah.domain.Employee;
import com.onb.yasah.exceptions.RemittanceException;
import com.onb.yasah.service.BranchService;
import com.onb.yasah.service.EmployeeService;

public class App {
	public static void main( String[] args ) throws RemittanceException
    {
		
    	ApplicationContext appContext = 
    	  new ClassPathXmlApplicationContext("ApplicationContext.xml");
 
    	EmployeeService employeeService = (EmployeeService)appContext.getBean("employeeService");
 
    	Employee employee = new Employee();
		employee.setFirstName("Yasah");
		employee.setLastName("Loresco");	
		employeeService.add(employee);
		
    	System.out.println("Done");
    	
    	BranchService branchService = (BranchService) appContext.getBean("branchService");
    	
    	Branch branch = new Branch();
    	branch.setCode("branch101");
    	branch.setLocation("Makati");
    	branchService.add(branch);
    }
}
