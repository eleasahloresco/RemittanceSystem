package com.onb.yasah.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onb.yasah.domain.Branch;
import com.onb.yasah.domain.Employee;
import com.onb.yasah.exceptions.RemittanceException;
import com.onb.yasah.service.BranchService;
import com.onb.yasah.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;
	
	private BranchService branchService;

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}
	
	@RequestMapping(value = "/employee")
	public String showEmployeeForm() {
		return "employee";
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public String showAddEmployeeForm(@ModelAttribute("employeeModel") Employee employee, HttpServletRequest request) {
		
		List<Branch> branches = branchService.getAllBranches();
		request.setAttribute("branches", branches);
		
		return "addEmployee";
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employeeModel") Employee employee, BindingResult bindingResult, HttpServletRequest request) throws RemittanceException{
		
		List<Branch> branches = branchService.getAllBranches();
		request.setAttribute("branches", branches);
		
		if (employee.getEmployeeNo() == null || employee.getEmployeeNo().toString().isEmpty()) {
			bindingResult.rejectValue("employeeNo", "employeeNo.validation.error", null, "is empty");
		}
		if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
			bindingResult.rejectValue("firstName", "firstName.validation.error", null, "is empty");
		}
		if (employee.getLastName() == null || employee.getLastName().isEmpty()) {
			bindingResult.rejectValue("lastName", "lastName.validation.error", null, "is empty");
		}
		if (employee.getPosition() == null || employee.getPosition().isEmpty()) {
			bindingResult.rejectValue("position", "position.validation.error", null, "is empty");
		}
		
		if (bindingResult.hasErrors()){
			return "addEmployee";
		}
		
		try {
			employeeService.add(employee);
		} catch (RemittanceException e) {
			bindingResult.rejectValue("employeeNo", "employeeNo.validation.error", null, "employee no. already exist");
			return "addEmployee";
		}
		
		return "redirect:getAllEmployees";
		
	}
	
	@RequestMapping(value = "/getAllEmployees")
	public String showEmployeeList(Model model){
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		
		return "employeeList";
	}
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.GET)
	public String showUpdateEmployeeForm(@ModelAttribute("employeeModel") Employee employee, HttpServletRequest request,
			@RequestParam("employee.id") Long id) throws RemittanceException {
		
		Employee retrievedEmployee = employeeService.get(id);
		List<Branch> branches = branchService.getAllBranches();

		request.setAttribute("branches", branches);
		request.setAttribute("employee", retrievedEmployee);
		
		return "updateEmployee";
	}
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute("employeeModel") Employee employee, BindingResult bindingResult, HttpServletRequest request,
			@RequestParam("employee.id") Long id){
		employee.setId(id);
		List<Branch> branches = branchService.getAllBranches();
		request.setAttribute("branches", branches);
		
		
		if (employee.getEmployeeNo() == null || employee.getEmployeeNo().toString().isEmpty()) {
			bindingResult.rejectValue("employeeNo", "employeeNo.validation.error", null, "is empty");
		}
		if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
			bindingResult.rejectValue("firstName", "firstName.validation.error", null, "is empty");
		}
		if (employee.getLastName() == null || employee.getLastName().isEmpty()) {
			bindingResult.rejectValue("lastName", "lastName.validation.error", null, "is empty");
		}
		if (employee.getPosition() == null || employee.getPosition().isEmpty()) {
			bindingResult.rejectValue("position", "position.validation.error", null, "is empty");
		}
		
		if (bindingResult.hasErrors()){
			return "updateEmployee";
		}
		
		try {
			employeeService.update(employee);
		} catch (Exception e) {
			return "updateEmployee";
		}
		
		return "redirect:getAllEmployees";
		
	}
	
	
	@RequestMapping(value = "/delete")
	public String deleteEmployee(@RequestParam("employee.id") Long id) throws RemittanceException{
		Employee employee = employeeService.get(id);
		employeeService.delete(employee);
		
		return "redirect:getAllEmployees";
	}
	
}
