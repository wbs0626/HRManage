package com.miris.service;

import java.util.*;

import com.miris.vo.DepartVO;

public interface IDeptservice {
	
	public List<DepartVO> deptList();
	
	public DepartVO deptNameSearch(String depart_id);

	public boolean deptDelete(String depart_id);
	
	public boolean deptInsert(DepartVO vo);
	
}
