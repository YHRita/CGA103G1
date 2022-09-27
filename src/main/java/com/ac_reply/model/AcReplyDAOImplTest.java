package com.ac_reply.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.util.HibernateUtil;

public class AcReplyDAOImplTest implements AcReplyDAO {
	
	public static final String GET_ALL = "FROM AcReplyVo"; 
	
	@Override
	public Integer insert(AcReplyVo AcReplyVo) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			final Integer id = (Integer) session.save(AcReplyVo);
			transaction.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}		
	}

	@Override
	public Integer update(AcReplyVo AcReplyVo) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			AcReplyVo ac = session.get(AcReplyVo.class, AcReplyVo.getAc_reply_no());
			ac.setAc_reply_update(AcReplyVo.getAc_reply_update());
			transaction.commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}		
	}
	

	@Override
	public AcReplyVo getOneById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<?> query = session.createQuery("From AcReplyVo where ac_reply_no=:ac_reply_no");
            query.setParameter("ac_reply_no", id);
            AcReplyVo result = (AcReplyVo) query.uniqueResult();
			transaction.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}		
	}
	
	@Override
	public List<AcReplyVo> getAllByAcNo(Integer acNo) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<AcReplyVo> query = session
					.createQuery("From AcReplyVo where ac_no=:ac_no", AcReplyVo.class)
					.setParameter("ac_no", acNo);
            List<AcReplyVo> acReplylist = query.list();
			transaction.commit();
			return acReplylist;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}		
	}
	

	@Override
	public List<AcReplyVo> getAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Query<AcReplyVo> query = session.createQuery
					("FROM AcReplyVo", AcReplyVo.class);
			List<AcReplyVo> list = query.list();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}		
	}

	
	public static void main(String[] args) {
		AcReplyDAOImplTest acReplyDAOImplTest = new AcReplyDAOImplTest();
		AcReplyVo acReplyVo = new AcReplyVo();

		// GetAll		
//		acReplyDAOImplTest.getAll().forEach(System.out::println);
		
		// Get All By ac_no		
//		acReplyDAOImplTest.getAllByAcNo(1).forEach(System.out::println);
		
		// Get Specific One
//		System.out.println(acReplyDAOImplTest.getOneById(1));
		
		// Insert
//		acReplyVo.setAc_no(3);
//		acReplyVo.setMem_no(1);
//		acReplyVo.setReply_content("+!!!!!!!!@@@@@@@@+1");
//		acReplyVo.setReply_time(LocalDateTime.of(2022, 8, 23, 14, 30));
		// 資料庫需修改成 not null default 0
//		acReplyVo.setAc_reply_update(0);
//		System.out.println(acReplyDAOImplTest.insert(acReplyVo));
//			
		// Update
		acReplyVo.setAc_reply_no(6);
		// 資料庫需修改成 not null default 0
		acReplyVo.setAc_reply_update(1);
		System.out.println(acReplyDAOImplTest.update(acReplyVo));
		
		
	}



	

}
