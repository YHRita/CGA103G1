package com.ac_reply.model;

import java.util.List;

public class AcReplyDAOImpl implements AcReplyDAO {
	
	public static final String GET_ALL = "FROM AcReplyVo";
	
	public static final String GET_ALL_BY_AC_NO = "From AcReplyVo where ac_no=:ac_no";

	@Override
	public Integer insert(AcReplyVo acReplyVo) {
		getSession().save(acReplyVo);
		return 1;
	}

	@Override
	public Integer update(AcReplyVo acReplyVo) {
		AcReplyVo acReply = getSession().load(AcReplyVo.class, acReplyVo.getAc_reply_no());
		acReply.setAc_reply_update(acReplyVo.getAc_reply_update());
		return 1;
	}

	@Override
	public AcReplyVo getOneById(Integer id) {
		return getSession().get(AcReplyVo.class, id);
	}

	@Override
	public List<AcReplyVo> getAll() {		
		return getSession().createQuery(GET_ALL, AcReplyVo.class).list();
	}

	@Override
	public List<AcReplyVo> getAllByAcNo(Integer acNo) {
		return getSession()
				.createQuery(GET_ALL_BY_AC_NO, AcReplyVo.class)
				.setParameter("ac_no", acNo)
				.list();
	}


}
