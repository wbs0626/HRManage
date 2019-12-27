package com.miris.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miris.dto.DailyEmpCountDTO;
import com.miris.service.HistoryService;
import com.miris.vo.HistoryVO;

@Controller
public class EmpController {
	@Autowired
	private HistoryService hs;
	
	// 새창으로 값 줌
	@RequestMapping("empLog.do")
	public String empLog(String id, Model model) {
		List<HistoryVO> empLogList = hs.historyList(id);
		
		DailyEmpCountDTO empStateList = hs.empLogCount(id);
		
		model.addAttribute("empLogList", empLogList);
		model.addAttribute("empStateList", empStateList);
		return "empLog";
	}
	
	@RequestMapping("emp/empManage.do")
	public String empManage(Model model) {
		return "emp/empManage";
	}
}
