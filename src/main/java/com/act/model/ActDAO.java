package com.act.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActDAO implements I_ActDAO{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT = "insert into act( "
			+ "mem_no, act_type_no, act_title, "
			+ "act_content, "
			+ "act_current_count, act_min_count, act_max_count,  "
			+ "act_enroll_begin, act_enroll_end, act_start, act_end, "
			+ "act_loc, act_pl) "
			+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE = "update act set act_type_no = ?, act_title = ? "
			+ ", act_content = ?, act_min_count = ? "
			+ ", act_max_count = ?, act_enroll_begin = ?, act_enroll_end= ? "
			+ ", act_start = ?, act_end = ?, act_loc = ? "
			+ ", act_pl = ? where act_no = ? and mem_no = ?";
	
	private static final String UPDATE_ACT_STATUS = "update act set act_status = ? where act_no = ?";

	private static final String UPDATE_ACT_RATE_AND_EVAL = "update act set act_rate_sum = ?, act_eval_sum = ? where act_no = ?";
	
	private static final String GET_ALL = "select act_no "
			+ ", mem_no, act_type_no, act_title, act_content "
			+ ", act_current_count, act_max_count, act_min_count "
			+ ", act_enroll_begin, act_enroll_end, act_start "
			+ ", act_end, act_loc, act_pl, act_rate_sum "
			+ ", act_eval_sum, act_status  "
			+ "from act";
	
	private static final String GET_HOST_ACT = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where mem_no = ?";
	
	private static final String FIND_ACT_BY_TYPE = "select act_no, act_type_no, mem_no, act_title "
			+ ", act_content, act_current_count, act_min_count "
			+ ", act_max_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_type_no = ?";
	
	private static final String FIND_AEB = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_enroll_begin >= ?";
	
	private static final String FIND_AEE = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_enroll_end <= ?";
	
	private static final String FIND_AEBE = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_enroll_begin >= ? and act_enroll_end <= ? ";
	
	private static final String FIND_ASBE = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_start >= ? and act_end <= ? ";
	
	private static final String GET_ACT_EVAL_AVG = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum "
			+ ", act_eval_sum, act_status from act where (act_rate_sum / act_eval_sum) >= ?";

	private static final String GET_ACT_LOC = "select act_no, mem_no, act_type_no, act_title "
			+ ", act_content, act_current_count, act_max_count "
			+ ", act_min_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, (act_rate_sum / act_eval_sum) as StarAvg "
			+ ", act_status from act where act_loc = ?";

	private static final String FIND_ACT_BY_MIN_COUNT = "select act_no "
			+ ", mem_no, act_type_no, act_title, act_content "
			+ ", act_current_count, act_max_count, act_min_count "
			+ ", act_enroll_begin, act_enroll_end, act_start "
			+ ", act_end, act_loc, act_pl, (act_rate_sum / act_eval_sum) as avgStar, act_status "
			+ " from act where act_min_count >= ?";
		
	private static final String FIND_ACT_BY_MAX_COUNT = "select act_no "
			+ ", mem_no, act_type_no, act_title, act_content "
			+ ", act_current_count, act_max_count, act_min_count "
			+ ", act_enroll_begin, act_enroll_end, act_start "
			+ ", act_end, act_loc, act_pl, (act_rate_sum / act_eval_sum) as avgStar, act_status "
			+ "from act where act_max_count <= ?";

	
	private static final String FIND_PERIOD_COUNT = "select act_no "
			+ ", mem_no, act_type_no, act_title, act_content "
			+ ", act_current_count, act_max_count, act_min_count "
			+ ", act_enroll_begin, act_enroll_end, act_start "
			+ ", act_end, act_loc, act_pl, (act_rate_sum / act_eval_sum) as avgStar, act_status "
			+ "from act where act_min_count >= ? and act_max_count <= ?";
	
	private static final String FIND_ACC =  "select act_no, act_type_no, mem_no, act_title "
			+ ", act_content, act_current_count, act_min_count "
			+ ", act_max_count, act_enroll_begin, act_enroll_end "
			+ ", act_start, act_end, act_loc, act_pl, act_rate_sum"
			+ ", act_eval_sum, act_status from act where act_current_count = ?";
	
	private static final String UPDATE_RATE_SUM = "update act set act_rate_sum = ? where act_no = ?";
	
	private static final String UPDATE_EVAL_SUM = "update act set act_eval_sum = ? where act_no = ?";
	
	private static final String GET_OWN_JOIN_ACT 
	= "select * from act inner join act_participant as ap on  act.act_no=ap.act_no where ap.mem_no = ?";
	
	private static final String UPDATE_ACT_PEOPLE_AMOUNT 
	= "update act set act_current_count = ? where act_no = ?";
	
	private static final String GET_ACT_NO_CURRENT_COUNT =
			"select act_current_count from act where act_no = ?";
	// ========================================================================================== //
	
	
	@Override
	public Integer insert(ActVO actVO) {
		Integer actNo = 0;
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, actVO.getMen_no());
			ps.setInt(2, actVO.getAct_type_no());
			ps.setString(3, actVO.getAct_title());
			ps.setString(4, actVO.getAct_content());
			ps.setInt(5, actVO.getAct_current_count());
			ps.setInt(6, actVO.getAct_min_count());
			ps.setInt(7, actVO.getAct_max_count());
			ps.setObject(8, actVO.getAct_enroll_begin());
			ps.setObject(9, actVO.getAct_enroll_end());
			ps.setObject(10, actVO.getAct_start());			
			ps.setObject(11, actVO.getAct_end());
			ps.setInt(12, actVO.getAct_loc());			
			ps.setString(13, actVO.getAct_pl());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()) {		
				actNo = rs.getInt(1);
			}
			return actNo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public void update(ActVO actVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE)) {
			ps.setInt(1, actVO.getAct_type_no());
			ps.setString(2, actVO.getAct_title());
			ps.setString(3, actVO.getAct_content());
			ps.setInt(4, actVO.getAct_min_count());
			ps.setInt(5, actVO.getAct_max_count());
			ps.setObject(6, actVO.getAct_enroll_begin());
			ps.setObject(7, actVO.getAct_enroll_end());
			ps.setObject(8, actVO.getAct_start());			
			ps.setObject(9, actVO.getAct_end());
			ps.setInt(10, actVO.getAct_loc());			
			ps.setString(11, actVO.getAct_pl());
			ps.setInt(12, actVO.getAct_no());
			ps.setInt(13, actVO.getMen_no());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	

	@Override
	public void updateActStatus(ActVO actVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE_ACT_STATUS)) {
			ps.setInt(1, actVO.getAct_status());
			ps.setInt(2, actVO.getAct_no());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void updateRateEval(ActVO actVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE_ACT_RATE_AND_EVAL)) {
			ps.setInt(1, actVO.getAct_rate_sum());
			ps.setInt(2, actVO.getEval_sum());
			ps.setInt(3, actVO.getAct_no());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void updateActPeopleAmount(ActVO actVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE_ACT_PEOPLE_AMOUNT)) {
			ps.setInt(1, actVO.getAct_current_count());
			ps.setInt(2, actVO.getAct_no());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}


	@Override
	public List<ActVO> getAll() {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ALL);) {			
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer actTypeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, actTypeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}
	

	@Override
	public List<ActVO> getHostAct(Integer memNo) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_HOST_ACT);) {
			ps.setInt(1, memNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer actTypeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, actTypeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> findActByType(Integer actTypeNo) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_ACT_BY_TYPE)) {
			ps.setInt(1, actTypeNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> findActByAEB(LocalDateTime actEnrollBegin) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_AEB)) {
			ps.setObject(1, actEnrollBegin);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime act_EnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								act_EnrollBegin,
								actEnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}
	
	@Override
	public List<ActVO> findActByAEE(LocalDateTime actEnrollEnd) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_AEE)) {
			ps.setObject(1, actEnrollEnd);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime act_EnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								act_EnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> findActByAEBE(LocalDateTime actEnrollBegin, LocalDateTime actEnrollEnd) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_AEBE)) {
			ps.setObject(1, actEnrollBegin);
			ps.setObject(2, actEnrollEnd);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime act_EnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime act_EnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								act_EnrollBegin,
								act_EnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> findActByASBE(LocalDateTime actStart, LocalDateTime actEnd) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_ASBE)) {
				ps.setObject(1, actStart);
				ps.setObject(2, actEnd);
				ResultSet rs1 = ps.executeQuery();
				while (rs1.next()) {
					Integer actNo = rs1.getInt(1);
					Integer actMemNo = rs1.getInt(2);
					Integer act_typeNo = rs1.getInt(3);
					String actTitle = rs1.getString(4);
					String actContent = rs1.getString(5);
					Integer actCurrentCount = rs1.getInt(6);
					Integer actMinCount = rs1.getInt(7);
					Integer actMaxCount = rs1.getInt(8);
					LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
					LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
					LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
					LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
					Integer actLoc = rs1.getInt(13);
					String actPl = rs1.getString(14);
					Integer actRateSum = rs1.getInt(15);
					Integer actEvalSum = rs1.getInt(16);
					Integer actStatus = rs1.getInt(17);
					ActVO act = 
							new ActVO(actNo,
									actMemNo, act_typeNo, actTitle,
									actContent, actCurrentCount,
									actMinCount, actMaxCount,
									actEnrollBegin,
									actEnrollEnd, act_Start,
									act_End, actLoc,
									actPl,actRateSum,
									actEvalSum,actStatus
									);
					acts.add(act);
					}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> getActEvalAvg(Integer starAvg) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ACT_EVAL_AVG)) {
			ps.setInt(1, starAvg);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer act_RateSum = rs1.getInt(15);
				Integer act_EvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, actLoc,
								actPl,act_RateSum,
								act_EvalSum,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}

	@Override
	public List<ActVO> getActLoc(Integer actLoc) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ACT_LOC)) {
			ps.setInt(1, actLoc);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
//				Integer actRateSum = rs1.getInt(15);
//				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}
	
	@Override
	public List<ActVO> findActByMinCount(Integer minCount) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_ACT_BY_MIN_COUNT)) {
			ps.setInt(1, minCount);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}
	
	@Override
	public List<ActVO> findActByMaxCount(Integer maxCount) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_ACT_BY_MAX_COUNT)) {
			ps.setInt(1, maxCount);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return acts;
	}
	
	@Override
	public List<ActVO> findActByCurrentCount(Integer currentCount) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_ACC)) {
			ps.setInt(1, currentCount);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return acts;
	}

	@Override
	public List<ActVO> findPeriodCount(Integer minCount, Integer maxCount) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(FIND_PERIOD_COUNT)) {
			ps.setObject(1, minCount);
			ps.setObject(2, maxCount);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer act_typeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime act_Start = (LocalDateTime) rs1.getObject(11);
				LocalDateTime act_End = (LocalDateTime) rs1.getObject(12);
				Integer act_Loc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer starAvg = rs1.getInt(15);
				Integer actStatus = rs1.getInt(16);				
				ActVO act = 
						new ActVO(actNo,
								actMemNo, act_typeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, act_Start,
								act_End, act_Loc,
								actPl, starAvg,actStatus);
				acts.add(act);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return acts;
	}

	
	@Override
	public void updateRateSum(ActVO actVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE_RATE_SUM)) {
			ps.setInt(1, actVO.getAct_rate_sum());
			ps.setInt(2, actVO.getAct_no());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	
	@Override
	public void updateEvalSum(ActVO actVO) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(UPDATE_EVAL_SUM)) {
			ps.setInt(1, actVO.getEval_sum());
			ps.setInt(2, actVO.getAct_no());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<ActVO> innerJoinAcrParti(Integer memNo) {
		List<ActVO> acts = new ArrayList<ActVO>();
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_OWN_JOIN_ACT);) {	
			ps.setInt(1, memNo);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				Integer actNo = rs1.getInt(1);
				Integer actMemNo = rs1.getInt(2);
				Integer actTypeNo = rs1.getInt(3);
				String actTitle = rs1.getString(4);
				String actContent = rs1.getString(5);
				Integer actCurrentCount = rs1.getInt(6);
				Integer actMinCount = rs1.getInt(7);
				Integer actMaxCount = rs1.getInt(8);
				LocalDateTime actEnrollBegin = (LocalDateTime) rs1.getObject(9);
				LocalDateTime actEnrollEnd = (LocalDateTime) rs1.getObject(10);
				LocalDateTime actStart = (LocalDateTime) rs1.getObject(11);
				LocalDateTime actEnd = (LocalDateTime) rs1.getObject(12);
				Integer actLoc = rs1.getInt(13);
				String actPl = rs1.getString(14);
				Integer actRateSum = rs1.getInt(15);
				Integer actEvalSum = rs1.getInt(16);
				Integer actStatus = rs1.getInt(17);
				ActVO act = 
						new ActVO(actNo,
								actMemNo, actTypeNo, actTitle,
								actContent, actCurrentCount,
								actMinCount, actMaxCount,
								actEnrollBegin,
								actEnrollEnd, actStart,
								actEnd, actLoc,
								actPl,actRateSum,
								actEvalSum,actStatus
								);
				acts.add(act);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acts;
	}


	
	@Override
	public ActVO getActMaxAmount(Integer actNo) {
		try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_ACT_NO_CURRENT_COUNT)) {
			ActVO actVo = new ActVO();
			ps.setInt(1, actVo.getAct_no());			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				actVo.setAct_current_count(rs.getInt(1));
			}
			return actVo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;		
		}
	}
		
}
