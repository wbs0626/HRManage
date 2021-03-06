package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.miris.vo.EmpDetailVO;
import com.miris.vo.MonthEmpLogVO;
import com.miris.vo.MonthVO;
import com.miris.vo.Pagination;

public interface MonthlyHisMapper {
	
	public final String defStr = "SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear='2020' AND basemonth = '1' AND id = mh.id), 0) m1, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '2' AND id = mh.id), 0) m2, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '3' AND id = mh.id), 0) m3, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '4' AND id = mh.id), 0) m4, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '5' AND id = mh.id), 0) m5, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '6' AND id = mh.id), 0) m6, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '7' AND id = mh.id), 0) m7, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '8' AND id = mh.id), 0) m8, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '9' AND id = mh.id), 0) m9, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '10' AND id = mh.id), 0) m10, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '11' AND id = mh.id), 0) m11, "
			+ "nvl((SELECT CASE "
			+ "WHEN state = 1 AND exclusion_state = 1 THEN 1 "
			+ "WHEN state = 2 AND exclusion_state = 2 THEN 2 "
			+ "WHEN state is NULL OR exclusion_state is NULL THEN 0 "
			+ "ELSE 3 END "
			+ "FROM monthhistory "
			+ "WHERE baseyear=#{baseYear} AND basemonth = '12' AND id = mh.id), 0) m12,"
			+ "r.rank_rate "
			+ "FROM monthhistory mh RIGHT OUTER JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "JOIN ranktable r "
			+ "ON e.rank = r.rank_name ";
		
	// 월별 인력 투입 현황 (연도 + 월 검색)
	@Select("SELECT id, section, emp_name, rank, business_name, baseyear, basemonth, site_name, state, "
			+ "m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, rank_rate, month_remarks, B.rnum "
			+ "FROM ( "
			+ "SELECT A.id, A.section, A.emp_name, A.rank, A.business_name, A.baseyear, A.basemonth, "
				+ "A.site_name, A.state, "
				+ "A.m1, A.m2, A.m3, A.m4, A.m5, A.m6, A.m7, A.m8, A.m9, A.m10, A.m11, A.m12, A.rank_rate, A.month_remarks, rownum AS rnum "
			+ "FROM ( "
			+ defStr
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY mh.baseYear, mh.baseMonth, r.rank_rate "
			+ ") A "
			+ "WHERE rownum <= #{pa.end} "
			+ ") B "
			+ "WHERE B.rnum >= #{pa.start}")
	public List<MonthVO> monEmpDateSearch(MonthVO mvo);
	
	// 구분(내부, 외부) 검색
	@Select(defStr
			+ "AND e.section = #{section}"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY mh.baseYear, mh.baseMonth, r.rank_rate")
	public List<MonthVO> monEmpSectionSearch(MonthVO mvo);
	
	// 성명 검색
	@Select(defStr
			+ "AND e.emp_name LIKE '%'||#{emp_name}||'%'"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY mh.baseYear, mh.baseMonth, r.rank_rate")
	public List<MonthVO> monEmpNameSearch(MonthVO mvo);
	
	// 직급 검색
	@Select(defStr
			+ "AND e.rank = #{rank}"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY mh.baseYear, mh.baseMonth, r.rank_rate")
	public List<MonthVO> monEmpRankSearch(MonthVO mvo);
	
	// 현 업무 검색
	@Select(defStr
			+ "AND mh.business_name LIKE '%'||#{business_name}||'%' "
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY mh.baseYear, mh.baseMonth, r.rank_rate")
	public List<MonthVO> monEmpBNameSearch(MonthVO mvo);
	
	// 검색 갯수 확인
	@Select("SELECT count(*) "
			+ "FROM monthhistory mh RIGHT OUTER JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "JOIN ranktable r "
			+ "ON e.rank = r.rank_name "
			+ "AND e.section = #{section} "
			+ "AND e.emp_name LIKE '%'||#{emp_name}||'%' "
			+ "AND e.rank LIKE '%'||#{rank}||'%' "
			+ "AND mh.business_name LIKE '%'||#{business_name}||'%' "
			+ "JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "AND s.site_name LIKE '%'||#{site_name}||'%' "
			+ "ORDER BY mh.baseYear, mh.baseMonth, r.rank_rate")
	public int cntFind(MonthVO vo);
	
	@Select("SELECT count(*) "
			+ "FROM monthhistory mh RIGHT OUTER JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "JOIN ranktable r "
			+ "ON e.rank = r.rank_name "
			+ "AND e.emp_name LIKE '%'||#{emp_name}||'%' "
			+ "AND e.rank LIKE '%'||#{rank}||'%' "
			+ "AND mh.business_name LIKE '%'||#{business_name}||'%' "
			+ "JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "AND s.site_name LIKE '%'||#{site_name}||'%' "
			+ "ORDER BY mh.baseYear, mh.baseMonth, r.rank_rate")
	public int cntAllFind(MonthVO vo);
	
	@Select("SELECT id, section, emp_name, rank, business_name, baseyear, basemonth, site_name, state, "
			+ "m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, rank_rate, month_remarks, B.rnum "
			+ "FROM ( "
			+ "SELECT A.id, A.section, A.emp_name, A.rank, A.business_name, A.baseyear, A.basemonth, "
				+ "A.site_name, A.state, "
				+ "A.m1, A.m2, A.m3, A.m4, A.m5, A.m6, A.m7, A.m8, A.m9, A.m10, A.m11, A.m12, A.rank_rate, A.month_remarks, rownum AS rnum "
			+ "FROM ( "
			+ defStr
			+ "AND e.section = #{section} "
			+ "AND e.emp_name LIKE '%'||#{emp_name}||'%' "
			+ "AND e.rank LIKE '%'||#{rank}||'%' "
			+ "AND mh.business_name LIKE '%'||#{business_name}||'%' "
			+ "JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "AND s.site_name LIKE '%'||#{site_name}||'%' "
			+ "ORDER BY mh.baseYear, mh.baseMonth, r.rank_rate "
			+ ") A "
			+ "WHERE rownum <= #{pa.end} "
			+ ") B "
			+ "WHERE B.rnum >= #{pa.start}")
	public List<MonthVO> monMultiFind(MonthVO mvo);
	
	@Select("SELECT id, section, emp_name, rank, business_name, baseyear, basemonth, site_name, state, "
			+ "m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, rank_rate, month_remarks, B.rnum "
			+ "FROM ( "
			+ "SELECT A.id, A.section, A.emp_name, A.rank, A.business_name, A.baseyear, A.basemonth, "
				+ "A.site_name, A.state, "
				+ "A.m1, A.m2, A.m3, A.m4, A.m5, A.m6, A.m7, A.m8, A.m9, A.m10, A.m11, A.m12, A.rank_rate, A.month_remarks, rownum AS rnum "
			+ "FROM ( "
			+ defStr
			+ "AND e.emp_name LIKE '%'||#{emp_name}||'%' "
			+ "AND e.rank LIKE '%'||#{rank}||'%' "
			+ "AND mh.business_name LIKE '%'||#{business_name}||'%' "
			+ "JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "AND s.site_name LIKE '%'||#{site_name}||'%' "
			+ "ORDER BY mh.baseYear, mh.baseMonth, r.rank_rate "
			+ ") A "
			+ "WHERE rownum <= #{pa.end} "
			+ ") B "
			+ "WHERE B.rnum >= #{pa.start}")
	public List<MonthVO> monMultiAllFind(MonthVO mvo);
	
	// -------------- 회원별 달마다 상태 구분 -----------

	// 월별 인력 투입 이력 (최근 1년)
	@Select("SELECT TO_CHAR((mh.baseyear||'.'||mh.basemonth )) as baseDate , mh.business_name, "
			+ "mh.exclusion_state, mh.month_remarks, mh.baseyear, mh.basemonth, mh.state, s.site_name "
			+ "FROM monthhistory mh "
			+ "JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.id = #{id} "
			+ "AND TO_DATE((mh.baseyear||'-'||mh.basemonth ) , 'YYYY-MM') BETWEEN to_char(sysdate - 365, 'YYYY-MM-DD') AND to_char(sysdate + 1, 'YYYY-MM-DD') "
			+ "AND mh.business_name IS NOT NULL "
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY mh.baseyear, mh.basemonth")
	public List<MonthEmpLogVO> yearHistoryList(String id);
	  
	// 상세 이력 조회 (단일 월)
	@Select("select e.id, e.section, d.depart_name, e.emp_name, e.rank, e.emp_remarks, "
			+ "mh.baseyear, mh.basemonth, mh.business_name, site_name, mh.state, mh.month_remarks "
			+ "FROM monthhistory mh "
			+ "JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.baseMonth = #{baseMonth} "
			+ "AND mh.id = #{id} "
			+ "JOIN departs d "
			+ "ON e.depart_id = d.depart_id "
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY mh.baseyear, mh.basemonth")
	public EmpDetailVO empDetailLog (MonthVO mvo);
	
	// 이력 중복 확인
	@Select("SELECT count(*) FROM monthhistory "
			+ "WHERE baseyear = #{baseYear} "
			+ "AND basemonth = #{baseMonth} "
			+ "AND id = #{id}")
	public int monHisDupChk(MonthVO mvo);
	
	// 월별 이력 추가
	@Insert("INSERT INTO monthhistory "
			+ "VALUES (#{id}, #{baseYear}, #{baseMonth}, #{business_name}, "
			+ "#{site_id}, #{state}, #{exclusion_state}, #{month_remarks})")
	public void monthHisInsert(MonthVO mvo);
	
	// 월별 이력 변경
	@Update("UPDATE monthhistory SET "
			+ "baseYear = #{baseYear}, "
			+ "baseMonth = #{baseMonth}, "
			+ "business_name = #{business_name}, "
			+ "site_id = #{site_id}, "
			+ "state = #{state}, "
			+ "exclusion_state = #{exclusion_state}, "
			+ "month_remarks = #{month_remarks} "
			+ "WHERE id = #{id} "
			+ "AND baseYear = #{baseYear} "
			+ "AND baseMonth = #{baseMonth}")
	public void monthHisUpdate(MonthVO mvo);
}
	
