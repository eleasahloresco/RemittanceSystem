package com.onb.yasah.service;

import java.util.List;

import com.onb.yasah.domain.Branch;
import com.onb.yasah.exceptions.RemittanceException;

public interface BranchService {

	void add(Branch branch) throws RemittanceException;

	void update(Branch branch);

	Branch get(long id);

	void delete(Branch branch);

	List<Branch> getAllBranches();

}