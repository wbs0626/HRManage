package com.miris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.MonthlyHisDAO;
import com.miris.vo.MonthVO;

@Service
public class MonthlyHisService implements IMonthlyHisService {
	@Autowired
	private MonthlyHisDAO mdao;
	
	public List<MonthVO> monEmpAllList() {
		return mdao.monEmpAllList();
	}
	
	/*
	 * public List<MonthVO> monEmpSearch(MonthVO mvo) { String id; int section;
	 * String name; String rank; String b_name; int year; int month; int state;
	 * 
	 * id = mvo.getId(); section = mvo.getSection(); name = mvo.getEmp_name(); rank
	 * = mvo.getRank(); b_name = mvo.getBusiness_name(); year = mvo.getBaseYear();
	 * month = mvo.getBaseMonth(); state = mvo.getState();
	 * 
	 * 
	 * 
	 * return null; }
	 */

//	public List<MonthVO> monEmpYearSearch(MonthVO mvo) {
//		return mdao.monEmpYearSearch(mvo);
//	}
//
//	public List<MonthVO> monEmpDateSearch(MonthVO mvo) {
//		return mdao.monEmpDateSearch(mvo);
//	}
//
//	public List<MonthVO> monEmpSectionSearch(MonthVO mvo) {
//		return mdao.monEmpSectionSearch(mvo);
//	}
//
//	public List<MonthVO> monEmpDateSectionSearch(MonthVO mvo) {
//		return mdao.monEmpDateSectionSearch(mvo);
//	}

}
