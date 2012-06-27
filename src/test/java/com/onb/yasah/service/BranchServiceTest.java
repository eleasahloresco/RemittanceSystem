package com.onb.yasah.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.onb.yasah.dao.BranchDao;
import com.onb.yasah.domain.Branch;
import com.onb.yasah.domain.Employee;
import com.onb.yasah.exceptions.RemittanceException;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceTest {
	
	private BranchServiceImpl branchService = new BranchServiceImpl();
	
	@Mock
	private BranchDao branchDao;
	
	private Branch branch;

	@Before
	public void setUp() throws RemittanceException{
		branchService.setBranchDao(branchDao);
		branch = new Branch("branch101", "Makati Branch", "Makati city", new HashSet<Employee>());
//		branchService.add(branch);
	}
	
	@Test
	public void shouldVerifyThatAddBranchDaoWasCalled() throws RemittanceException{
		branchService.add(branch);
		Mockito.verify(branchDao).add(branch);
	}
	
	@Test
	public void shouldVerifyThatUpdateBranchDaoWasCalled() {
		branchService.update(branch);
		Mockito.verify(branchDao).update(branch);
	}
	
	@Test
	public void shouldVerifyThatGetBranchDaoWasCalled() {
		when(branchDao.get(1l)).thenReturn(branch);
		Branch retrievedBranch = branchService.get(1l);
		
		Mockito.verify(branchDao).get(1l);
		assertEquals("branch101", retrievedBranch.getCode());
	}
	
	@Test
	public void shouldVerifyThatDeleteBranchWasCalled(){
		branchService.delete(branch);
		Mockito.verify(branchDao).delete(branch);
	}
	
}
