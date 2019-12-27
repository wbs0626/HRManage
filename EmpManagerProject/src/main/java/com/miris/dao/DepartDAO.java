package com.miris.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.mapper.DepartMapper;
import com.miris.vo.DepartVO;

@Repository
public class DepartDAO implements DepartMapper{
	@Autowired
	private DepartMapper mapper;
	
	public List<DepartVO> deptList() {
		return mapper.deptList();
	}

	public DepartVO deptNameSearch(String depart_id) {
		return mapper.deptNameSearch(depart_id);
	}

	public int deptChkId(String depart_id) {
		return mapper.deptChkId(depart_id);
	}

	public void deptDelete(String depart_id) {
		mapper.deptDelete(depart_id);
	}

	public void deptInsert(DepartVO vo) {
		mapper.deptInsert(vo);
	}
}
