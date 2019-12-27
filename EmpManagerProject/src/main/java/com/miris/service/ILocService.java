package com.miris.service;

import java.util.*;

import com.miris.vo.LocVO;

public interface ILocService {
	public List<LocVO> locAllInfo();
	
	public LocVO locSearch(String name);
	
	public boolean locDelete(String name);
	
	public boolean locInsert(LocVO vo);
	
	public int locCount();
}
