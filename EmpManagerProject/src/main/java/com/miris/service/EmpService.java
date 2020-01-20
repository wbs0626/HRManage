package com.miris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

import com.miris.dao.EmpDAO;
import com.miris.dto.EmpDataTableDTO;
import com.miris.vo.EmpVO;
import com.miris.vo.MonthVO;
import com.miris.vo.Pagination;

@Service
public class EmpService {

	final static int LOGIN = 1;
	final static int PWD_INCORRECT = 2;
	final static int NO_ID = 3;

	@Autowired
	private EmpDAO edao;

	public List<EmpVO> empAllList() {
		return edao.empList();
	}

	public int duplicateNameChk(EmpVO vo) {
		int result = edao.duplicateNameChk(vo);
		
		return result;
	}

	public EmpVO empDetailInfo(EmpVO vo) {
		return edao.empDetailInfo(vo);
	}

	public List<EmpDataTableDTO> empTableList(EmpDataTableDTO tvo) {
		return edao.EmpTableList(tvo);
	}

	public int empCount() {
		return edao.empCount();
	}
	
	public int empTableCount() {
		return edao.EmpTableCount();
	}
	
	public List<EmpVO> rankList() {
		return edao.rankList();
	}

	public List<EmpDataTableDTO> empNameSearch(EmpDataTableDTO tvo) {
		return edao.empNameSearch(tvo);
	}

	public List<EmpDataTableDTO> empNameDateSearch(EmpDataTableDTO tvo) {
		return edao.empNameDateSearch(tvo);
	}

	public List<EmpDataTableDTO> empDepartSearch(EmpDataTableDTO tvo) {
		return edao.empDepartSearch(tvo);
	}

	public List<EmpDataTableDTO> empNameDepartSearch(EmpDataTableDTO tvo) {
		return edao.empNameDepartSearch(tvo);
	}
	
	public MonthVO empInfo(String id) {
		if(id == null) {
			id = "";
		}
		
		return edao.empInfo(id);
	}
	
	public void empUpdate(EmpVO vo) {
		edao.empUpdate(vo);
	}
	
	public void empDetailUpdate(EmpVO vo) {
		edao.empDetailUpdate(vo);
	}
	
	public boolean empInsert(EmpVO vo) {
		boolean isSuccess = false;
		
		if(edao.loginIdCheck(vo) == 0) {
			edao.empInsert(vo);
			isSuccess = true;
		}
		
		return isSuccess;
	}
	
	public boolean empDelete(EmpVO vo) {
		boolean isSuccess = false;

		if(edao.loginIdCheck(vo) == 1) {
			edao.empDelete(vo.getId());
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public List<EmpVO> empNameSearch2(String name){
		return edao.empNameSearch2(name);
	}
	
	public EmpVO empInfoFind(EmpVO vo) {
		return edao.empInfoFind(vo);
	}
	
	public EmpVO empInfo2(String id) {
		return edao.empInfo2(id);
	}
	
	public List<EmpVO> empPaging(Pagination pa) {
		
		return edao.empPaging(pa);
	}
}
