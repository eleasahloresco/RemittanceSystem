package com.onb.yasah.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.onb.yasah.domain.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Long> implements EmployeeDao {

	public EmployeeDaoImpl(){
		super(Employee.class);
	}
	
	@Override
	public Employee getByEmployeeNo(Long employeeNo){
		String sqlQuery = "from Employee employee where employee.employeeNo = :employeeNo";
		Query query = getSessionFactory().getCurrentSession().createQuery(sqlQuery);
		query.setParameter("employeeNo", employeeNo);
		
		if(query.list().size() == 0){
			return new Employee();
		}
		else{
			return (Employee) query.list().get(0);
		}
	}
	
}
