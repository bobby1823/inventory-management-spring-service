package com.mindtree.GenericDao;

import java.util.List;

public interface GenericDao<E, K> {
	
	public void add(E entity);
	public void update(E entity);
	public void remove(K id);
	public List<E> getAll();

}
