package com.miris.manager;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miris.service.EmpService;
import com.miris.vo.EmpVO;

@RestController
public class LoginRestController {
	@Autowired
	private EmpService es;
	
	@RequestMapping("login_ok.do")
	public String login_ok(EmpVO vo, HttpSession session) {
		
		//System.out.println("vo 값 : " + ToStringBuilder.reflectionToString(vo));
		
		//vo = es.empDetailInfo(vo);
		
		int result = es.login(vo);
		System.out.println("result : " + result);
		System.out.println("입력된 ID값 : " + vo.getId());
		System.out.println("입력된 pwd값 : " + vo.getPwd());
		if(result == 1) {
			session.setAttribute("id", vo.getId());
		} 
		
		return String.valueOf(result);
	}
}
