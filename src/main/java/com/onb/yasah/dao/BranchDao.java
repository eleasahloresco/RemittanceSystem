package com.onb.yasah.dao;

import com.onb.yasah.domain.Branch;

public interface BranchDao extends GenericDao<Branch, Long> {

	Branch getByBranchCode(String branchCode);

}
