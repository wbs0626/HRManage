package com.miris.manager;

import java.util.*;
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
	public String work(Model model) {
		List<BusinessVO> bvo = bs.businessAllList();
		
		model.addAttribute("bvo", bvo);
		
		return "task/task";
	}
}
