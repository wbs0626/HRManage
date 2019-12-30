package com.miris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.DepartDAO;
import com.miris.vo.DepartVO;

@Service
public class DeptService {
	@Autowired
	private DepartDAO ddao;

	public List<DepartVO> deptList() {
		return ddao.deptList();
	}

	public DepartVO deptNameSearch(String depart_id) {
		return ddao.deptNameSearch(depart_id);
	}

	public boolean deptDelete(String depart_id) {
		boolean isSuccess = false;
		
		if(ddao.deptChkId(depart_id) != 0 ) {
			ddao.deptDelete(depart_id);
			isSuccess = true;
		}
		
		return isSuccess;
	}

	public boolean deptInsert(DepartVO vo) {
		boolean isSuccess = false;
		
		if(ddao.deptChkId(vo.getDepart_id()) == 0 ) {
			ddao.deptInsert(vo);
			isSuccess = true;
		}
		
		return isSuccess;
	}
	
	
}
