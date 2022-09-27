package com.util;

import static com.util.HibernateUtil.*;

import org.hibernate.Session;
import org.hibernate.Transaction;

public interface CoreService {

	default Transaction beginTransacion() {
//		return getSessionFactory().getCurrentSession().beginTransaction();
		return getSession().beginTransaction();
	}
	
	default void commit() {
		getSession().getTransaction().commit();
//		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	}
	
	default void rollback() {
		getSession().getTransaction().rollback();
//		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
	}
	
	default Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	
}
