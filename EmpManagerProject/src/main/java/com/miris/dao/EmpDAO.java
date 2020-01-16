package com.miris.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.dto.EmpDataTableDTO;
import com.miris.mapper.EmpMapper;
import com.miris.vo.EmpVO;
import com.miris.vo.MonthVO;

@Repository
public class EmpDAO implements EmpMapper{
	@Autowired
	private EmpMapper mapper;
	
	public int empCount() {
		return mapper.empCount();
	}
	
	public EmpVO empDetailInfo(EmpVO vo) {
		return mapper.empDetailInfo(vo);
	}

	public List<EmpDataTableDTO> EmpTableList(EmpDataTableDTO tvo) {
		return mapper.EmpTableList(tvo);
	}

	public List<EmpDataTableDTO> empNameSearch(EmpDataTableDTO tvo) {
		return mapper.empNameSearch(tvo);
	}

	public int loginIdCheck(EmpVO vo) {
		return mapper.loginIdCheck(vo);
	}

	public List<EmpVO> empList() {
		return mapper.empList();
	}
	
	public int EmpTableCount() {
		return mapper.EmpTableCount();
	}
	
	public int duplicateNameChk(EmpVO vo) {
		return mapper.duplicateNameChk(vo);
	}

	public List<EmpDataTableDTO> empNameDateSearch(EmpDataTableDTO tvo) {
		return mapper.empNameDateSearch(tvo);
	}

	public List<EmpDataTableDTO> empDepartSearch(EmpDataTableDTO tvo) {
		return mapper.empDepartSearch(tvo);
	}

	public List<EmpDataTableDTO> empNameDepartSearch(EmpDataTableDTO tvo) {
		return mapper.empNameDepartSearch(tvo);
	}

	public List<EmpVO> rankList() {
		return mapper.rankList();
	}

	public MonthVO empInfo(String id) {
		return mapper.empInfo(id);
	}

	public void empUpdate(EmpVO vo) {
		mapper.empUpdate(vo);
	}

	public void empInsert(EmpVO vo) {
		mapper.empInsert(vo);
	}

	public List<EmpVO> empNameSearch2(String name) {
		return mapper.empNameSearch2(name);
	}

	public void empDelete(String id) {
		mapper.empDelete(id);
	}
	
	public void empDetailUpdate(EmpVO vo) { 
		mapper.empDetailUpdate(vo);
	}

	public EmpVO empInfoFind(EmpVO vo) {
		return mapper.empInfoFind(vo);
	}

	public EmpVO empInfo2(String id) {
		return mapper.empInfo2(id);
	}
}
