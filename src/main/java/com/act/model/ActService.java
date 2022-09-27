package com.act.model;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.act_rp.model.ActRpService;
import com.act_rp.model.ActRpVO;


public class ActService {
	
	private I_ActDAO dao;
	
	public ActService() {
		dao = new ActDAO();
	}
	
	// 創建揪團活動
	public Integer createAct(ActVO actVO) {
		actVO.setMen_no(actVO.getMen_no());
		actVO.setAct_type_no(actVO.getAct_type_no());
		actVO.setAct_title(actVO.getAct_title());
		actVO.setAct_content(actVO.getAct_content());
		actVO.setAct_current_count(actVO.getAct_current_count());
		actVO.setAct_min_count(actVO.getAct_min_count());
		actVO.setAct_max_count(actVO.getAct_max_count());
		actVO.setAct_enroll_begin(actVO.getAct_enroll_begin());
		actVO.setAct_enroll_end(actVO.getAct_enroll_end());
		actVO.setAct_start(actVO.getAct_start());			
		actVO.setAct_end(actVO.getAct_end());
		actVO.setAct_loc(actVO.getAct_loc());			
		actVO.setAct_pl(actVO.getAct_pl());
		
		Integer actNo = dao.insert(actVO);
		
		return actNo;
	}
	
	// 更新揪團活動
	public ActVO updateAct(ActVO actVO) {		
		actVO.setAct_type_no(actVO.getAct_type_no());
		actVO.setAct_title(actVO.getAct_title());
		actVO.setAct_content(actVO.getAct_content());
		actVO.setAct_min_count(actVO.getAct_min_count());
		actVO.setAct_max_count(actVO.getAct_max_count());
		actVO.setAct_enroll_begin(actVO.getAct_enroll_begin());
		actVO.setAct_enroll_end(actVO.getAct_enroll_end());
		actVO.setAct_start(actVO.getAct_start());			
		actVO.setAct_end(actVO.getAct_end());
		actVO.setAct_loc(actVO.getAct_loc());			
		actVO.setAct_pl(actVO.getAct_pl());
		
		dao.update(actVO);
		
		return actVO;			
	}
	
	public void updateActPeopleAmount(Integer act_no, Integer act_current_count) {
		ActVO actVO = new ActVO();
		actVO.setAct_no(act_no);
		actVO.setAct_current_count(act_current_count);
		dao.updateActPeopleAmount(actVO);
	}

	
	// 更新活動狀態
	public ActVO updateActStatus(Integer act_no, Integer act_status) {
		ActVO actVO = new ActVO();
		
		actVO.setAct_no(act_no);
		actVO.setAct_status(act_status);
				
		dao.updateActStatus(actVO);
		
		return actVO;
	}

	// 為 揪團活動 評價 增加 星數 & 評價人數
	public ActVO updateRateEval(Integer act_rate_sum, Integer eval_sum, Integer act_no) {
		ActVO actVO = new ActVO();		
		actVO.setAct_rate_sum(act_rate_sum);
		actVO.setEval_sum(eval_sum);
		actVO.setAct_no(act_no);
		
		dao.updateActStatus(actVO);
		
		return actVO;
	}
	
	// 取得所有揪團活動的所有資訊
	public List<ActVO> getAll() {
		
        List<ActVO> actList =  dao.getAll()
		.stream()
		.filter(act -> act.getAct_status() != 1)
		.collect(Collectors.toList());
        
		return actList;
	};
	
	// 取得 揪團活動舉辦者所有舉辦的活動 
	public List<ActVO> getHostAct(Integer memNo){
		return dao.getHostAct(memNo);
	}
	
	// 取得 特定種類 活動列表
	public List<ActVO> getAllActByType(Integer actTypeNo){
		return dao.findActByType(actTypeNo);
	}
	
	// 取得 活動報名起始日期之後活動列表 
	public List<ActVO> getActDateAfterEnrollBeginDate(LocalDateTime actEnrollBegin) {
		return dao.findActByAEB(actEnrollBegin);
	}
	
	// 取得 活動報名截止日期之後活動列表 
	public List<ActVO> getActDateAfterEnrollEndDate(LocalDateTime actEnrollEnd) {
		return dao.findActByAEE(actEnrollEnd);
	}
	
	// 取得 活動報名起訖日期區間的活動列表 
	public List<ActVO> getActDateBetweenEnrollDateAndEndDate(LocalDateTime actEnrollBegin, LocalDateTime actEnrollEnd) {
		return dao.findActByAEBE(actEnrollBegin, actEnrollEnd);
	}
	
	// 取得 活動開始結束日期區間的活動列表 
	public List<ActVO> getActBetweenStartDateAndEndDate(LocalDateTime actStart, LocalDateTime actEnd) {
		return dao.findActByASBE(actStart, actEnd);
	}
	
	// 取得 揪團活動 評價平均數(評價總星數 / 評價總人數) 的活動列表 
	public List<ActVO> getActAverageStar(Integer starAvg) {
		return dao.getActEvalAvg(starAvg);
	}
	
	// 取得 舉辦於特定縣市之活動列表
	public List<ActVO> getSpecificLocationOfAct(Integer actLoc) {
		return dao.getActLoc(actLoc);
	}
	
	// 取得 符合目前報名人數之活動列表
	public List<ActVO> getCurrentCountAct(Integer currentCount) {
		return dao.findActByCurrentCount(currentCount);
	}
	
	// 取得 報名人數最少限制之活動列表
	public List<ActVO> getMinNumberOfActParticipants(Integer minCount) {
		return dao.findActByMinCount(minCount);
	}
	
	// 取得 報名人數最多限制之活動列表
	public List<ActVO> getMaxNumberOfActParticipants(Integer maxCount) {
		return dao.findActByMaxCount(maxCount);
	}
	
	// 取得 報名人數最少限制 至 最多限制 區間之活動列表 
	public List<ActVO> getNumberOfActParticipantsBetweenMinAndMax(Integer minCount, Integer maxCount) {
		return dao.findPeriodCount(minCount, maxCount);
	}
		
	public List<ActVO> getOwnActParti(Integer memNo) {
		return dao.innerJoinAcrParti(memNo);
	}
	
}
