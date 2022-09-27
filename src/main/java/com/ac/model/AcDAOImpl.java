package com.ac.model;

import java.util.List;

import org.hibernate.Transaction;


public class AcDAOImpl implements AcDAO {
	
	@Override
	public Integer insert(AcVO acVo) {
		acVo.setAc_update(0);
        getSession().save(acVo);
		return acVo.getAc_no();
	}

	@Override
	public Integer update(AcVO acVo) {
		AcVO ac = getSession().load(AcVO.class, acVo.getAc_no());
		ac.setAc_title(acVo.getAc_title());
		ac.setAc_content(acVo.getAc_content());
		ac.setAc_time(acVo.getAc_time());
		ac.setAc_type_no(acVo.getAc_type_no());
		getSession().update(ac);
		return 1;
	}

	@Override
	public AcVO getOneById(Integer id) {
		return getSession().get(AcVO.class, id);
	}

	@Override
	public List<AcVO> getAll() {
		final String hql = "FROM AcVO";
		return getSession().createQuery(hql, AcVO.class).list();
	}


}
