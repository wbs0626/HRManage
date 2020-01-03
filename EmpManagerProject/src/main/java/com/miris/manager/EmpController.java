package com.miris.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miris.dto.DailyEmpCountDTO;
import com.miris.service.EmpService;
import com.miris.service.HistoryService;
import com.miris.service.MonthlyHisService;
import com.miris.vo.EmpVO;
import com.miris.vo.HistoryVO;

@Controller
public class EmpController {
	@Autowired
	private HistoryService hs;
	@Autowired
	private MonthlyHisService ms;
	@Autowired
	private EmpService es;
	
	// 30일 현황 창
	@RequestMapping("empLog.do")
	public String empLog(String id, Model model) {
		List<HistoryVO> empLogList = hs.historyList(id);
		
		DailyEmpCountDTO empStateList = hs.empLogCount(id);
		
		model.addAttribute("empLogList", empLogList);
		model.addAttribute("empStateList", empStateList);
		return "empLog";
	}
	
	// 인력 관리 화면
	@RequestMapping("emp/empManage.do")
	public String empManage(Model model) {
		List<EmpVO> elist = es.empAllList();
		
		System.out.println(elist);
		
		model.addAttribute(elist);
		
		return "emp/empManage";
	}
	
	
}
