package com.ac_reply.model;

import java.util.List;

import com.util.CoreDao;

public interface AcReplyDAO extends CoreDao<AcReplyVo, Integer> {

	List<AcReplyVo> getAllByAcNo(Integer acNo);
}
