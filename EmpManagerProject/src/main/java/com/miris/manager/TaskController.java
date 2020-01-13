package com.miris.manager;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miris.service.BusinessService;
import com.miris.vo.BusinessVO;

@Controller
public class TaskController {
	@Autowired
	private BusinessService bs;
	
	@RequestMapping("task/task.do")
	public String task(Model model, HttpSession session) {
		List<BusinessVO> bvo = bs.businessAllList();
		
		model.addAttribute("bvo", bvo);
		
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "task/task";
		}
	}
	
	@RequestMapping("taskIns.do")
	public String taskIns(HttpSession session) {
		String permit = (String) session.getAttribute("permit");
		
		if(permit == null || permit.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "taskIns";
		}
		
	}
}
