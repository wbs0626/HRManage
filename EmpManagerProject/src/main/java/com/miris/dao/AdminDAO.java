package com.miris.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.miris.mapper.AdminMapper;
import com.miris.vo.AdminVO;

@Repository
public class AdminDAO implements AdminMapper{
	@Autowired
	private AdminMapper mapper;
		
	public int loginIdCheck(AdminVO vo) {
		return mapper.loginIdCheck(vo);
	}

	public int loginPwdCheck(AdminVO vo) {
		return mapper.loginPwdCheck(vo);
	}

	public List<AdminVO> adminAllList() {
		return mapper.adminAllList();
	}
	
	public AdminVO adminInfo(AdminVO vo) {
		return mapper.adminInfo(vo);
	}
}
