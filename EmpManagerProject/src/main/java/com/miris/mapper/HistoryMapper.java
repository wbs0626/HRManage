package com.miris.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.miris.dto.DailyEmpCountDTO;
import com.miris.vo.HistoryVO;
import com.miris.vo.MonthlyRateVO;

public interface HistoryMapper {
	// 근무 이력 출력 (최근 30일)
	@Select("SELECT TO_CHAR(history_time, 'YYYY-MM-DD hh24:mm:ss') as history_time, state, loc_name "
			+ "FROM dailyhistory "
			+ "WHERE history_time BETWEEN to_char(sysdate - 30, 'YYYY-MM-DD') AND to_char(sysdate + 1, 'YYYY-MM-DD') "
			+ "AND id = #{id} "
			+ "ORDER BY history_time desc")
	public List<HistoryVO> historyList(String id);
	
	// 근무 이력 종합 (최근 30일)
	@Select("SELECT count(dh.id) as total, count(CASE WHEN state=1 THEN 1 END) as work, count(CASE WHEN state=2 THEN 1 END) as vacation, "
			+ "count(CASE WHEN state=3 THEN 1 END) as business_trip, count(CASE WHEN (state=4 OR state is null) THEN 1 END) as others "
			+ "FROM dailyhistory dh "
			+ "WHERE history_time BETWEEN to_char(sysdate - 30, 'YYYY-MM-DD') AND to_char(sysdate + 1, 'YYYY-MM-DD') "
			+ "AND id = #{id}")
	public DailyEmpCountDTO empLogCount(String id);
	
	// 근무 상태별 이력 출력(달로 통계)
	@Select("SELECT count(emp.id) total, count(CASE WHEN state=1 THEN 1 END) work, count(CASE WHEN state=2 THEN 1 END) vacation, "
			+ "count(CASE WHEN state=3 THEN 1 END) business_trip, count(CASE WHEN (state=4 OR state is null) THEN 1 END) others "
			+ "FROM dailyhistory dh RIGHT OUTER JOIN emp "
			+ "ON dh.id = emp.id "
			+ "AND TO_CHAR(dh.history_date, 'YYYY-MM') = #{hdate}")
	public DailyEmpCountDTO empMonthState(String date);
	
	// 날짜별 근무 인원 수
	@Select("SELECT count(emp.id) as total, count(CASE WHEN state=1 THEN 1 END) as work, count(CASE WHEN state=2 THEN 1 END) as vacation, "
			+ "count(CASE WHEN state=3 THEN 1 END) as business_trip, count(CASE WHEN (state=4 OR state is null) THEN 1 END) as others "
			+ "FROM dailyhistory dh RIGHT OUTER JOIN emp "
			+ "ON history_date = TO_DATE(#{date}, 'YYYY-MM-DD') "
			+ "AND dh.id = emp.id")
	public DailyEmpCountDTO empStateCount(String date);

	// 월별 인력 가동 현황
	@Select("select count(case WHEN NVL(mh.business_name, ' ') != ' ' THEN 1 END) mTotal, "
			+ "count(CASE WHEN(mh.business_name = '경영지원' OR mh.business_name = '영업' OR mh.business_name = '신사업 발굴') THEN 1 END) mExcept, "
			+ "count(CASE WHEN(mh.business_name != '경영지원' AND mh.business_name != '영업' AND mh.business_name != '신사업 발굴') THEN 1 END) mPossible, "
			+ "count(CASE WHEN(mh.business_name = '시스템운영' OR mh.business_name = '시금고운영') THEN 1 END) mInput1, "
			+ "count(CASE WHEN(mh.business_name = 'SI 사업 발주 대기') THEN 1 END) mWait "
			+ "FROM monthhistory mh JOIN emp e "
			+ "ON mh.id = e.id "
			+ "AND mh.baseyear = #{baseYear} AND mh.basemonth = #{baseMonth} "
			+ "LEFT OUTER JOIN site s "
			+ "ON mh.site_id = s.site_id")
	public MonthlyRateVO operationRate(Map<String, Integer> map);
}
