package com.miris.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.dto.DailyEmpCountDTO;
import com.miris.mapper.HistoryMapper;
import com.miris.vo.HistoryVO;
import com.miris.vo.MonthEmpLogVO;
import com.miris.vo.MonthlyRateVO;

@Repository
public class HistoryDAO implements HistoryMapper{
	@Autowired
	private HistoryMapper mapper;
	
	public List<HistoryVO> historyList(String id) {
		return mapper.historyList(id);
	}

	public DailyEmpCountDTO empStateCount(String date) {
		return mapper.empStateCount(date);
	}

	public DailyEmpCountDTO empLogCount(String id) {
		return mapper.empLogCount(id);
	}

	public DailyEmpCountDTO empMonthState(String date) {
		return mapper.empMonthState(date);
	}

	public MonthlyRateVO operationRate(Map<String, Integer> map) {
		return mapper.operationRate(map);
	}

}
