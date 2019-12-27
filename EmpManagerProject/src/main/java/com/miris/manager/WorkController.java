package com.miris.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WorkController {
	
	@RequestMapping("work/work.do")
	public String work(Model model) {
		
		return "work/work";
	}
}
