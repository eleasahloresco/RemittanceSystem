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
import com.onb.yasah.service.BranchService;

@Controller
public class BranchController {

	private BranchService branchService;

	@Autowired
	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}
	
	@RequestMapping(value = "/branch")
	public String showBranchForm() {
		return "branch";
	}
	
	@RequestMapping(value = "/addBranch", method = RequestMethod.GET)
	public String showAddBranchForm(@ModelAttribute("branchModel") Branch branch, HttpServletRequest request) {
		List<Branch> branches = branchService.getAllBranches();
		request.setAttribute("branches", branches);
		
		return "addBranch";
	}
	
	@RequestMapping(value = "/addBranch", method = RequestMethod.POST)
	public String addBranch(@ModelAttribute("branchModel") Branch branch, BindingResult bindingResult, HttpServletRequest request) {
		List<Branch> branches = branchService.getAllBranches();
		request.setAttribute("branches", branches);
		
		if (branch.getCode() == null || branch.getCode().isEmpty()) {
			bindingResult.rejectValue("code", "code.validation.error", null, "is empty");
		}
		if (branch.getName() == null || branch.getName().isEmpty()) {
			bindingResult.rejectValue("name", "name.validation.error", null, "is empty");
		}
		if (branch.getLocation() == null || branch.getLocation().isEmpty()) {
			bindingResult.rejectValue("location", "location.validation.error", null, "is empty");
		}
		
		if (bindingResult.hasErrors()){
			return "addBranch";
		}
		
		try {
			branchService.add(branch);
		} catch (Exception e) {
			bindingResult.rejectValue("code", "code.validation.error", null, "branch code already exist");
			return "addBranch";
		}
		
		return "redirect:getAllBranches";
	}
	
	@RequestMapping(value = "/getAllBranches")
	public String showBranchList(Model model){
		List<Branch> branches = branchService.getAllBranches();
		model.addAttribute("branches", branches);
		
		return "branchList";
	}
	
	@RequestMapping(value = "/deleteBranch")
	public String deleteBranch(@RequestParam("branch.id") Long id) throws Exception{
		Branch branch = branchService.get(id);
		
		try{
			branchService.delete(branch);
		}catch(Exception e){
			return "redirect:getAllBranches";
		}
		
		return "redirect:getAllBranches";
	}
	
	@RequestMapping(value = "/updateBranch", method = RequestMethod.GET)
	public String showUpdateBranchForm(@ModelAttribute("branchModel") Branch branch, HttpServletRequest request,
			@RequestParam("branch.id") Long id) {
		
		Branch retrievedBranch = branchService.get(id);
		List<Branch> branches = branchService.getAllBranches();
		request.setAttribute("branches", branches);
		request.setAttribute("branch", retrievedBranch);
		
		return "updateBranch";
	}
	
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST)
	public String updateBranch(@ModelAttribute("branchModel") Branch branch, BindingResult bindingResult, HttpServletRequest request,
			@RequestParam("branch.id") Long id) {
		branch.setId(id);
		branch.setCode(branch.getCode());
		List<Branch> branches = branchService.getAllBranches();
		request.setAttribute("branches", branches);
		
		if (branch.getCode() == null || branch.getCode().isEmpty()) {
			bindingResult.rejectValue("code", "code.validation.error", null, "is empty");
		}
		if (branch.getName() == null || branch.getName().isEmpty()) {
			bindingResult.rejectValue("name", "name.validation.error", null, "is empty");
		}
		if (branch.getLocation() == null || branch.getLocation().isEmpty()) {
			bindingResult.rejectValue("location", "location.validation.error", null, "is empty");
		}
		
		if (bindingResult.hasErrors()){
			return "updateBranch";
		}
		
		try {
			branchService.update(branch);
		} catch (Exception e) {
			return "updateBranch";
		}
		
		return "redirect:getAllBranches";
	}
	
}
