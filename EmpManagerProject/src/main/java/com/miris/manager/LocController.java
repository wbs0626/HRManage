package com.miris.manager;

import java.util.List;

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
	public String locInfoView(Model model) {
		// 근무지 리스트 출력
		List<LocVO> locList = ls.locAllInfo();
		model.addAttribute("locList", locList);
		
		return "loc/locinfo";
	}
}
