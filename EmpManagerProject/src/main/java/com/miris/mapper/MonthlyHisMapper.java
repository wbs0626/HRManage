package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.miris.vo.MonthEmpLogVO;
import com.miris.vo.MonthVO;

public interface MonthlyHisMapper {
	
	public final String defStr = "SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '1' AND id = mh.id), 0) m1, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '2' AND id = mh.id), 0) m2, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '3' AND id = mh.id), 0) m3, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '4' AND id = mh.id), 0) m4, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '5' AND id = mh.id), 0) m5, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '6' AND id = mh.id), 0) m6, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '7' AND id = mh.id), 0) m7, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '8' AND id = mh.id), 0) m8, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '9' AND id = mh.id), 0) m9, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '10' AND id = mh.id), 0) m10, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '11' AND id = mh.id), 0) m11, "
			+ "nvl((SELECT state FROM monthhistory WHERE baseyear = #{baseYear} AND basemonth = '12' AND id = mh.id), 0) m12 "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} ";
	/*
	 * // 월별 인력 투입 현황 전체
	 * 
	 * @Select("SELECT id, section, emp_name, rank, business_name, site_name, state, baseyear, basemonth, month_remarks, num "
	 * +
	 * "FROM (SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
	 * + "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks, rownum as num " +
	 * " FROM monthhistory mh JOIN emp e " + "ON mh.id = e.id " +
	 * "LEFT OUTER JOIN site s " + "ON mh.site_id = s.site_id " +
	 * "ORDER BY mh.baseyear, mh.basemonth)") public List<MonthVO> monEmpAllList();
	 * 
	 * // 전체 현황 수
	 * 
	 * @Select("SELECT count(*) " + "FROM monthhistory mh JOIN emp e " +
	 * "ON mh.id = e.id " + "LEFT OUTER JOIN site s " + "ON mh.site_id = s.site_id")
	 * public int monEmpTotalCount();
	 * 
	 * // 페이징 전체 내역
	 * 
	 * @Select("SELECT id, section, emp_name, rank, business_name, site_name, state, baseyear, basemonth, month_remarks, num "
	 * +
	 * "FROM (SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
	 * + "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks, rownum as num " +
	 * " FROM monthhistory mh JOIN emp e " + "ON mh.id = e.id " +
	 * "LEFT OUTER JOIN site s " + "ON mh.site_id = s.site_id " +
	 * "ORDER BY mh.baseyear, mh.basemonth) " +
	 * "WHERE num Between #{start} and #{end}") public List<MonthVO>
	 * monEmpTotalList(Map<String, Object> map);
	 * 
	 * // 페이징 용 (20개 씩)
	 * 
	 * @Select("SELECT Ceil(count(*)/20) " + "FROM monthhistory mh JOIN emp e " +
	 * "ON mh.id = e.id " + "LEFT OUTER JOIN site s " +
	 * "ON mh.site_id = s.site_id ") public int monEmpTotalPage();
	 */
	
	// 월별 인력 투입 현황 (연도 + 월 검색)
	@Select(defStr
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 1, 8, 9")
	public List<MonthVO> monEmpDateSearch(MonthVO mvo);
	
	// 구분(내부, 외부) 검색
	@Select(defStr
			+ "AND e.section = #{section}"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 8, 9")
	public List<MonthVO> monEmpSectionSearch(MonthVO mvo);
	
	// 성명 검색
	@Select(defStr
			+ "AND e.emp_name LIKE '%'||#{emp_name}||'%'"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 8, 9")
	public List<MonthVO> monEmpNameSearch(MonthVO mvo);
	
	// 직급 검색
	@Select(defStr
			+ "AND e.rank = #{rank}"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 8, 9")
	public List<MonthVO> monEmpRankSearch(MonthVO mvo);
	
	// 현업무 검색
	@Select(defStr
			+ "AND mh.business_name LIKE '%'||#{business_name}||'%'"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 8, 9")
	public List<MonthVO> monEmpBNameSearch(MonthVO mvo);
	
	// -------------- 회원별 달마다 상태 구분 -----------

	// 월별 인력 투입 이력 (최근 1년)
		@Select("SELECT TO_CHAR((mh.baseyear||'.'||mh.basemonth )) as baseDate , mh.business_name, "
				+ "mh.exclusion_state, mh.month_remarks, mh.baseyear, mh.basemonth "
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
	  
	
}
