package com.onb.yasah.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.HashSet;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.onb.yasah.domain.Branch;
import com.onb.yasah.domain.Employee;

@ContextConfiguration(locations = { "classpath:ApplicationContext.xml" })
public class BranchDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private DataSource dataSource;

	@Autowired
	private BranchDao branchDao;
	
	private Branch branch;
	
	@Test
	public void shouldAddBranch(){
		branch = new Branch("Makati101", "Makati Branch", "Makati City", new HashSet<Employee>());
		branchDao.add(branch);
		
		assertNotNull(branch.getId());
	}
	
	@Test
	public void shouldUpdateBranch(){
		branch = branchDao.get(1l);
		branch.setName("New Name");
		branchDao.update(branch);
		
		Branch retrievedBranch = branchDao.get(1l);
		assertEquals(branch.getName(), retrievedBranch.getName());
	}
	
	@Test
	public void shouldGetBranch(){
		assertNotNull(branchDao.get(1l));
	}
	
	@Test
	public void shouldDeleteBranch(){
		branch = branchDao.get(1l);
		branchDao.delete(branch);
		
		assertNull(branchDao.get(1l));
	}
	
	@Before
	public void setUpMockDB() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(getDatabaseConnection(),	getDataSet());
	}
	
	private IDatabaseConnection getDatabaseConnection()	throws DatabaseUnitException, SQLException {
		IDatabaseConnection connection =  new DatabaseConnection(dataSource.getConnection());
		return connection;
	}

	private IDataSet getDataSet() throws MalformedURLException,	DataSetException {
		return new FlatXmlDataSetBuilder().build(new File("src/main/resources/dataset.xml"));
	}
}
