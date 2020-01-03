package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.miris.vo.BusinessVO;

public interface BusinessMapper {
	
	// 업무 전체
	@Select("SELECT * FROM business")
	public List<BusinessVO> businessAllList();
	
}
