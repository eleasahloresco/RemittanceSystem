package com.onb.yasah.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.onb.yasah.domain.Branch;

@Repository("branchDao")
public class BranchDaoImpl extends GenericDaoImpl<Branch, Long> implements BranchDao {

	public BranchDaoImpl(){
		super(Branch.class);
	}

	@Override
	public Branch getByBranchCode(String branchCode){
		String sqlQuery = "from Branch branch where branch.code = :branchCode";
		Query query = getSessionFactory().getCurrentSession().createQuery(sqlQuery);
		query.setParameter("branchCode", branchCode);
		
		if(query.list().size() == 0){
			return new Branch();
		}
		else{
			return (Branch) query.list().get(0);
		}
	}

}
