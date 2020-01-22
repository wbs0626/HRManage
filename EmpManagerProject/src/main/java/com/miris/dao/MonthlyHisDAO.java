package com.miris.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.mapper.MonthlyHisMapper;
import com.miris.vo.EmpDetailVO;
import com.miris.vo.MonthEmpLogVO;
import com.miris.vo.MonthVO;

/*
 * 	월별 인력 투입 현황 관련
 */

@Repository
public class MonthlyHisDAO implements MonthlyHisMapper {
	@Autowired
	private MonthlyHisMapper mapper;
	
	public List<MonthVO> monEmpDateSearch(MonthVO mvo) {
		return mapper.monEmpDateSearch(mvo);
	}

	public List<MonthVO> monEmpSectionSearch(MonthVO mvo) {
		return mapper.monEmpSectionSearch(mvo);
	}

	public List<MonthVO> monEmpNameSearch(MonthVO mvo) {
		return mapper.monEmpNameSearch(mvo);
	}

	public List<MonthVO> monEmpRankSearch(MonthVO mvo) {
		return mapper.monEmpRankSearch(mvo);
	}

	public List<MonthVO> monEmpBNameSearch(MonthVO mvo) {
		return mapper.monEmpBNameSearch(mvo);
	}
	
	public List<MonthEmpLogVO> yearHistoryList(String id) {
		return mapper.yearHistoryList(id);
	}

	public EmpDetailVO empDetailLog(MonthVO mvo) {
		return mapper.empDetailLog(mvo);
	}

	public void monthHisInsert(MonthVO mvo) {
		mapper.monthHisInsert(mvo);
	}

	public int monHisDupChk(MonthVO mvo) {
		return mapper.monHisDupChk(mvo);
	}

	public void monthHisUpdate(MonthVO mvo) {
		mapper.monthHisUpdate(mvo);
	}
	
	public List<MonthVO> monMultiFind(MonthVO mvo) {
		return mapper.monMultiFind(mvo);
	}

	public List<MonthVO> monMultiAllFind(MonthVO mvo) {
		return mapper.monMultiAllFind(mvo);
	}

	public int cntFind(MonthVO vo) {
		return mapper.cntFind(vo);
	}

	public int cntAllFind(MonthVO vo) {
		return mapper.cntAllFind(vo);
	}
}
