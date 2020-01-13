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

	public DepartVO deptNameSearch(int depart_id) {
		return mapper.deptNameSearch(depart_id);
	}

	public int deptChkId(int depart_id) {
		return mapper.deptChkId(depart_id);
	}

	public void deptDelete(int depart_id) {
		mapper.deptDelete(depart_id);
	}

	public void deptInsert(DepartVO vo) {
		mapper.deptInsert(vo);
	}

	public List<DepartVO> deptFindByName(String depart_name) {
		return mapper.deptFindByName(depart_name);
	}
	
	public void deptUpdate(DepartVO vo) {
		mapper.deptUpdate(vo);
	}

	public DepartVO deptFindInfo(DepartVO vo) {
		return mapper.deptFindInfo(vo);
	}
}
