package com.miris.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.LocDAO;
import com.miris.vo.LocVO;

@Service
public class LocService implements ILocService{
	@Autowired
	private LocDAO ldao;
	
	public List<LocVO> locAllInfo() {
	
		return ldao.locAllInfo();
	}

	public LocVO locSearch(String name) {
		
		return ldao.locSearch(name);
	}

	public boolean locDelete(String name){
		boolean isSuccess = false;
		
		if(ldao.ChecklocName(name) != 0){
			ldao.locDelete(name);
			isSuccess = true;
		}
		return isSuccess;
	}

	public boolean locInsert(LocVO vo){
		boolean isSuccess = false;
		
		if(ldao.ChecklocName(vo.getLoc_name()) == 0) {
			ldao.locInsert(vo);
			isSuccess = true;
		}
		
		return isSuccess;
	}
	
	public int locCount() {
		return ldao.locCount();
	}
	
}
