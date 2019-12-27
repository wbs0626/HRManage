package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.miris.vo.MonthVO;

public interface MonthlyHisMapper {
	
	// 월별 인력 투입 현황 전체
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 1, 9")
	public List<MonthVO> monEmpAllList();
	
	// 월별 인력 투입 현황 (연도 검색)
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 1, 9")
	public List<MonthVO> monEmpYearSearch(MonthVO mvo);
	
	// 월별 인력 투입 현황 (연도 + 월 검색)
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 1, 9")
	public List<MonthVO> monEmpDateSearch(MonthVO mvo);
	
	// 구분(내부, 외부) 검색 + 연도
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND e.section = #{section}"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 1, 9")
	public List<MonthVO> monEmpSectionSearch(MonthVO mvo);
	
	// 구분(내부, 외부) 검색 + 연도 + 월
	@Select("SELECT e.id, e.section, e.emp_name, e.rank, mh.business_name, s.site_name, "
			+ "mh.state, mh.baseyear, mh.basemonth, mh.month_remarks "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} "
			+ "AND mh.basemonth = #{baseMonth} "
			+ "AND e.section = #{section}"
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id "
			+ "ORDER BY 1, 9")
	public List<MonthVO> monEmpDateSectionSearch(MonthVO mvo);
	
	
}
