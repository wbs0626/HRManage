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

	public DepartVO deptNameSearch(int depart_id) {
		return ddao.deptNameSearch(depart_id);
	}

	public boolean deptDelete(int depart_id) {
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
	
	public List<DepartVO> deptFindByName(String depart_name) {
		if(depart_name == null) {
			depart_name = "";
		}
		
		return ddao.deptFindByName(depart_name);
	}
	
}
