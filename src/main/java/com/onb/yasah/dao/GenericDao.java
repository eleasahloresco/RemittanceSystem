package com.onb.yasah.dao;

import java.util.List;

public interface GenericDao<T, ID> {
	
	void add(T item);
	
	void update(T item);
	
	T get(ID id);
	
	void delete(T item);

	List<T> getAll();

	List<T> getByCriteria(String sqlQuery);
}
