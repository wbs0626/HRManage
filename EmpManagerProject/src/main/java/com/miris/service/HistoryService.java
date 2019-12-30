package com.miris.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.HistoryDAO;
import com.miris.dto.DailyEmpCountDTO;
import com.miris.vo.HistoryVO;
import com.miris.vo.MonthlyRateVO;

@Service
public class HistoryService {
	@Autowired
	private HistoryDAO hdao;
	
	public List<HistoryVO> historyList(String id) {
		
		return hdao.historyList(id); 
	}

	public DailyEmpCountDTO empStateCount(String date) {
		// 날짜값 받아옴
		return hdao.empStateCount(date);
	}

	public DailyEmpCountDTO empLogCount(String id) {
		
		return hdao.empLogCount(id);
	}

	public MonthlyRateVO operationRate(Map<String, Integer> map) {
		return hdao.operationRate(map);
	}
	
}
