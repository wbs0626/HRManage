package com.miris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.AdminDAO;
import com.miris.vo.AdminVO;

import java.util.*;

import javax.servlet.http.HttpSession;

@Service
public class AdminService {
	@Autowired
	private AdminDAO adao;
	
	final static int LOGIN = 1;
	final static int PWD_INCORRECT = 2;
	final static int NO_ID = 3;
	
	public int login(AdminVO vo) {
		int result = 0;

		if (adao.loginIdCheck(vo) == 1) {
			if (adao.loginPwdCheck(vo) == 1) {
				result = LOGIN;
			} else {
				result = PWD_INCORRECT;
			}
		} else {
			result = NO_ID;
		}
		return result;
	}

}
