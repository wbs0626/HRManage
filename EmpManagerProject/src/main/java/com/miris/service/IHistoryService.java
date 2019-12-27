package com.miris.service;

import java.util.List;
import java.util.Map;

import com.miris.dto.DailyEmpCountDTO;
import com.miris.vo.HistoryVO;
import com.miris.vo.MonthlyRateVO;

public interface IHistoryService {
	List<HistoryVO> historyList(String id);
	
	public DailyEmpCountDTO empStateCount(String date);
	
	public DailyEmpCountDTO empLogCount(String id);
	
	public MonthlyRateVO operationRate(Map<String, Integer> map);
}
