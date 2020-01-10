package com.miris.manager;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miris.service.AdminService;
import com.miris.service.EmpService;
import com.miris.vo.AdminVO;
import com.miris.vo.EmpVO;

@RestController
public class LoginRestController {
	@Autowired
	private AdminService as;
	
	@RequestMapping("login_ok.do")
	public String login_ok(AdminVO vo, HttpSession session) {
		
		int result = as.login(vo);
		System.out.println("result : " + result);
		System.out.println("입력된 ID값 : " + vo.getId());
		System.out.println("입력된 pwd값 : " + vo.getPwd() +"\n");
		
		AdminVO tmp = as.adminInfo(vo);
		
		System.out.println("tmp 전체값: " + tmp);
		
		if(result == 1) {
			session.setAttribute("userId", tmp.getId());
			session.setAttribute("permit", tmp.getPermit());
			session.setMaxInactiveInterval(60 * 60);	// 세션 1시간 유지
		} 
		
		return String.valueOf(result);
	}
}
