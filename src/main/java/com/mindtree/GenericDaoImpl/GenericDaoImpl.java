package com.mindtree.GenericDaoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.GenericDao.GenericDao;

@Repository
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {

	@Autowired 
	private SessionFactory sessionFactory;

	protected Class<? extends E> daoType;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
	}
	
	protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public void add(E entity) {
		currentSession().save(entity);
	}

	@Override
	public void update(E entity) {
		currentSession().saveOrUpdate(entity);
	}

	@Override
	public void remove(K id) {
		E entity = currentSession().byId(daoType).load(id);
		currentSession().delete(entity);		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<E> getAll() {
		return currentSession().createCriteria(daoType).list();
	}
	
	
	
}
