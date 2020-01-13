package com.miris.manager;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miris.service.LocService;
import com.miris.vo.LocVO;

@Controller
public class LocController {
	@Autowired
	private LocService ls;
	
	@RequestMapping("loc/locinfo.do")
	public String locInfoView(Model model, HttpSession session) {
		// 근무지 리스트 출력
		List<LocVO> locList = ls.locAllInfo();
		model.addAttribute("locList", locList);
		
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "loc/locinfo";
		}
	}
	
	@RequestMapping("locIns.do")
	public String locIns(HttpSession session) {
		
		String permit = (String) session.getAttribute("permit");
		
		if(!permit.trim().equals("A")) {
			return "redirect:../login.do";
		} else {
			return "locIns";
		}
	}
	
	@RequestMapping("locUpd.do")
	public String locUpd(Model model, HttpSession session, LocVO vo) {
		
		LocVO lvo = ls.locSearch(vo.getLoc_name());
		
		model.addAttribute("lvo", lvo);
		
		String permit = (String) session.getAttribute("permit");
		
		if(!permit.trim().equals("A")) {
			return "redirect:../login.do";
		} else {
			return "locUpd";
		}
	}
}
