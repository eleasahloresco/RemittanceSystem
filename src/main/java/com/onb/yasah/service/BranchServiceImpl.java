package com.onb.yasah.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onb.yasah.dao.BranchDao;
import com.onb.yasah.domain.Branch;
import com.onb.yasah.exceptions.RemittanceException;

@Service("branchService")
@Transactional
public class BranchServiceImpl implements BranchService {

	private BranchDao branchDao;
	
	@Autowired
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}

	@Override
	public void add(Branch branch) throws RemittanceException {
		if(branch == null){
			throw new RemittanceException("Branch is empty");
		}
		
		Branch retrievedBranch = branchDao.getByBranchCode(branch.getCode());
		if(retrievedBranch.getCode() != null){
			throw new RemittanceException("Employee No. already exist");
		}
		
		branchDao.add(branch);
	}

	@Override
	public void update(Branch branch) {
		branchDao.update(branch);
	}

	@Override
	public Branch get(long id) {
		return branchDao.get(id);
	}

	@Override
	public void delete(Branch branch) {
		branchDao.delete(branch);
	}
	
	@Override
	public List<Branch> getAllBranches() {
		return branchDao.getAll();
	}

}
