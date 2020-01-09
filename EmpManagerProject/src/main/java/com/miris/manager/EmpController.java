package com.miris.manager;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miris.dto.DailyEmpCountDTO;
import com.miris.service.DeptService;
import com.miris.service.EmpService;
import com.miris.service.HistoryService;
import com.miris.service.MonthlyHisService;
import com.miris.vo.DepartVO;
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
	@Autowired
	private DeptService ds;
	
	// 30일 현황 창
	@RequestMapping("empLog.do")
	public String empLog(String id, Model model, HttpSession session) {
		List<HistoryVO> empLogList = hs.historyList(id);
		
		DailyEmpCountDTO empStateList = hs.empLogCount(id);
		
		model.addAttribute("empLogList", empLogList);
		model.addAttribute("empStateList", empStateList);
		
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "empLog";
		}
	}
	
	// 인력 관리 화면
	@RequestMapping("emp/empManage.do")
	public String empManage(Model model, HttpSession session) {
		List<EmpVO> elist = es.empAllList();
		List<DepartVO> dvo = ds.deptList();
		//System.out.println("인력 관리 화면 값: " + elist);
		
		model.addAttribute("elist", elist);
		model.addAttribute("dvo", dvo);
		
		//System.out.println(dvo);
		
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "emp/empManage";
		}
	}
	
	
}
