package com.miris.manager;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("login.do")
	public String LoginView(Model model, HttpSession session) {
		
		String userId = (String)session.getAttribute("userId");
		
		if(userId != null) {
			return "redirect:main.do";
		} else {
			return "login";
		}
	}
	
	@RequestMapping("logout.do")
	public String Logout(Model model, HttpSession session) {
		
		session.invalidate();
		
		return "redirect:login.do";
	}
} 





