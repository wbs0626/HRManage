package com.miris.manager;

import java.util.List;

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
	public String departInfo(Model model) {
		List<DepartVO> depList = ds.deptList();
		
		model.addAttribute("depList", depList);
		
		return "departs/departs";
	}
}
