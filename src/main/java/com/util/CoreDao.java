package com.util;


import java.util.List;

import org.hibernate.Session;

import static com.util.HibernateUtil.*;


public interface CoreDao <P, I> {
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	Integer insert(P pojo);

	Integer update(P pojo);

	P getOneById(I id);
	
	List<P> getAll();

}
