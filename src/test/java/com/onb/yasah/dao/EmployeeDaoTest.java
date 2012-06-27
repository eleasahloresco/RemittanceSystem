package com.onb.yasah.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.onb.yasah.domain.Branch;
import com.onb.yasah.domain.Employee;

@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class EmployeeDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private DataSource dataSource;

	@Autowired
  	EmployeeDao employeeDao;
	
	private Employee employee;
	
	@Before
	public void setUpMockDB() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(getDatabaseConnection(),	getDataSet());
	}
	
	@Ignore //to be studied first
	@Test
	public void shouldMatchActualTableAndDataSetTable() throws Exception, DatabaseUnitException{
		IDataSet databaseDataSet = getDatabaseConnection().createDataSet();
		ITable actualTable = databaseDataSet.getTable("EMPLOYEE");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/main/resources/dataset.xml"));
		ITable expectedTable = expectedDataSet.getTable("Employee");
		
		assertEquals(expectedTable, actualTable);
	}
	
	@Test
	public void shouldAddEmployee(){
		employee = new Employee("Lorem", "Ipsum", "Architect", new Long("00119922"), new Branch());
		employeeDao.add(employee);
		
		assertNotNull(employee.getId());
	}
	
	@Test
	public void shouldGetEmployee(){
		Employee retrievedEmployee = employeeDao.get(1l);
		
		assertNotNull(retrievedEmployee.getId());
		assertNotNull(retrievedEmployee.getBranch());
	}
	

	@Test
	public void shoulUpdateEmployee(){
		employee = employeeDao.get(1l);
		employee.setFirstName("Yasah");
		employeeDao.update(employee);
		
		Employee retrievedEmployee = employeeDao.get(1l);
		assertEquals(employee.getFirstName(), retrievedEmployee.getFirstName());
	}
	
	@Test
	public void shouldDeleteEmployee(){
		employee = employeeDao.get(1l);
		employeeDao.delete(employee);
		
		assertNull(employeeDao.get(1l));
	}
	
	private IDatabaseConnection getDatabaseConnection()	throws DatabaseUnitException, SQLException {
		IDatabaseConnection connection =  new DatabaseConnection(dataSource.getConnection());
		return connection;
	}

	private IDataSet getDataSet() throws MalformedURLException,	DataSetException {
		return new FlatXmlDataSetBuilder().build(new File("src/main/resources/dataset.xml"));
	}
}
