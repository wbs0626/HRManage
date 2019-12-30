package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.miris.vo.MonthVO;

public interface MonthlyHisMapper {
	
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
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 8, 9")
	public List<MonthVO> monEmpDateSearch(MonthVO mvo);
	
	// 구분(내부, 외부) 검색
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "AND e.section = #{section}"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 8, 9")
	public List<MonthVO> monEmpSectionSearch(MonthVO mvo);
	
	// 성명 검색
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "AND e.emp_name LIKE '%'||#{emp_name}||'%'"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 8, 9")
	public List<MonthVO> monEmpNameSearch(MonthVO mvo);
	
	// 직급 검색
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "AND e.rank = #{rank}"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 8, 9")
	public List<MonthVO> monEmpRankSearch(MonthVO mvo);
	
	// 현업무 검색
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "AND e.emp_name LIKE '%'||#{business_name}||'%'"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 8, 9")
	public List<MonthVO> monEmpBNameSearch(MonthVO mvo);
	
	// -------------- 회원별 달마다 상태 구분 -----------

	
}
