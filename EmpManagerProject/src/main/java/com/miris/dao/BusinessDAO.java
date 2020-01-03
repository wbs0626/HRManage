package com.miris.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.mapper.BusinessMapper;
import com.miris.vo.BusinessVO;

@Repository
public class BusinessDAO implements BusinessMapper {
	@Autowired
	private BusinessMapper mapper;
	
	public List<BusinessVO> businessAllList() {
		return mapper.businessAllList();
	}

}
