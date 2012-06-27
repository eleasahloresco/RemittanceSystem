package com.onb.yasah.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	private final Class<T> domainClass;
	
	@Autowired
	private SessionFactory sessionFactory;

	protected GenericDaoImpl(Class<T> domainClass){
		this.domainClass = domainClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	@Override
	public void add(T item) {
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void update(T item) {
		sessionFactory.getCurrentSession().update(item);
	}

	@Override
	public T get(ID id) {
		return (T) sessionFactory.getCurrentSession().get(domainClass, id);
	}

	@Override
	public void delete(T item) {
		sessionFactory.getCurrentSession().delete(item);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(domainClass);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByCriteria(String sqlQuery){
		Query query = sessionFactory.getCurrentSession().createQuery(sqlQuery);
		return query.list();
	}

}
