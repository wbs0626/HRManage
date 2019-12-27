package com.miris.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.mapper.MonthlyHisMapper;
import com.miris.vo.MonthVO;

@Repository
public class MonthlyHisDAO implements MonthlyHisMapper {
	@Autowired
	private MonthlyHisMapper mapper;
	
	public List<MonthVO> monEmpAllList() {
		return mapper.monEmpAllList();
	}
	
	public List<MonthVO> monEmpYearSearch(MonthVO mvo) {
		return mapper.monEmpYearSearch(mvo);
	}

	public List<MonthVO> monEmpDateSearch(MonthVO mvo) {
		return mapper.monEmpDateSearch(mvo);
	}

	public List<MonthVO> monEmpSectionSearch(MonthVO mvo) {
		return mapper.monEmpSectionSearch(mvo);
	}

	public List<MonthVO> monEmpDateSectionSearch(MonthVO mvo) {
		return mapper.monEmpDateSectionSearch(mvo);
	}

}
