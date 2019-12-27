package com.miris.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.mapper.LocMapper;
import com.miris.vo.LocVO;

@Repository
public class LocDAO implements LocMapper {
	@Autowired
	private LocMapper mapper;
	
	public List<LocVO> locAllInfo() {
		return mapper.locAllInfo();
	}

	public LocVO locSearch(String name) {
		return mapper.locSearch(name);
	}

	public void locDelete(String name) {
		mapper.locDelete(name);
	}

	public void locInsert(LocVO vo) {
		mapper.locInsert(vo);
	}
	
	public int ChecklocName(String loc_name) {
		return mapper.ChecklocName(loc_name);
	}
	
	public int locCount() {
		return mapper.locCount();
	}

	
}
