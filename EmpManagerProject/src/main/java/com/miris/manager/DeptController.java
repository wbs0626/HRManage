package com.miris.manager;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miris.service.DeptService;
import com.miris.vo.DepartVO;

@Controller
public class DeptController {
	@Autowired
	private DeptService ds;
	
	@RequestMapping("departs/departs")
	public String departInfo(Model model, HttpSession session) {
		List<DepartVO> depList = ds.deptList();
		
		String tempId = (String)session.getAttribute("userId");
		
		System.out.println("현재 세션 userId 값 : " + tempId);
		
		model.addAttribute("depList", depList);
		
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "departs/departs";
		}
		
	}
	
	@RequestMapping("departIns.do")
	public String departIns(Model model, HttpSession session) {
		
		String permit = (String)session.getAttribute("permit");
		
		if(permit == null || permit.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "departIns";
		}
	}
}
