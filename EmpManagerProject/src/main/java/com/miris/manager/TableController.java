package com.miris.manager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miris.service.*;
import com.miris.vo.*;

@Controller
public class TableController {
	@Autowired
	private LocService ls;
	@Autowired
	private HistoryService hs;
	@Autowired
	private MonthlyHisService ms;
	@Autowired
	private DeptService ds;
	@Autowired
	private EmpService es;
	@Autowired
	private BusinessService bs;
	@Autowired
	private SiteService ss;
	
	@RequestMapping("tables/tables.do")
	public String empAllocate(Model model, HttpSession session) {
//		int maxLoc = ls.locCount();
//		List<LocVO> locList = ls.locAllInfo();
//		
//		model.addAttribute("maxLoc", maxLoc);
//		model.addAttribute("locList", locList);
		
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "tables/tables";
		}
	}
	
	@RequestMapping("tables/inputCurrentState.do")
	public String currentState(Model model, HttpSession session) {

		MonthVO mvo = new MonthVO();
		
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		int nYear = calendar.get(Calendar.YEAR);
		int nMonth = calendar.get(Calendar.MONTH) + 1;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("baseYear", nYear);
		map.put("baseMonth", nMonth);
		
		MonthlyRateVO rvo = hs.operationRate(map);
		
		int input1 = rvo.getMInput1();
		int possible = rvo.getMPossible();
		double tmp = ((double)input1 / (double)possible) * 100.0;
		double operRate = (Math.round(tmp * 100) / 100.0);
		
		rvo.setRate(operRate);
		mvo.setBaseYear(nYear);
		mvo.setBaseMonth(nMonth);

		List<MonthVO> mList = ms.monEmpDateSearch(mvo);
		// List<MonthVO> mList = ms.monEmpAllList();
		
		// 초기값 설정
		model.addAttribute("rvo", rvo);
		model.addAttribute("mList", mList);
		model.addAttribute("nYear", nYear);
		model.addAttribute("nMonth", nMonth);
		
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "tables/inputCurrentState";
		}
	}
	
	// 인력 등록 창
	@RequestMapping("empIns.do")
	public String empIns(Model model, HttpSession session, String id) {
		List<DepartVO> dvo = ds.deptList();
		List<EmpVO> evo = es.rankList();
		
		MonthVO mvo = es.empInfo(id);
		
		model.addAttribute("mvo", mvo);
		model.addAttribute("dvo", dvo);
		model.addAttribute("evo", evo);
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "empIns";
		}
	}
	
	// 인력 연간 정보
	@RequestMapping("empYearHistory.do")
	public String empYearHis(Model model, HttpSession session, String id, String baseYear, String baseMonth) {
		MonthVO mvo = es.empInfo(id);
		List<MonthEmpLogVO> logList = ms.yearHistoryList(id); 
		
		model.addAttribute("empId", id);
		model.addAttribute("info", mvo);
		model.addAttribute("logList", logList);
		model.addAttribute("baseYear", baseYear);
		model.addAttribute("baseMonth", baseMonth);
		
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "empYearHistory";
		}
	}
	
	// 현황 등록 창
	@RequestMapping("empStateIns.do")
	public String empStateIns(Model model, HttpSession session, String id, String baseYear, String baseMonth) {
		MonthVO mvo = new MonthVO();
		mvo.setId(id);
		mvo.setBaseYear(Integer.parseInt(baseYear));
		mvo.setBaseMonth(Integer.parseInt(baseMonth));

		EmpDetailVO evo = ms.empDetailLog(mvo);
		//System.out.println(evo);
		
		List<MonthEmpLogVO> logList = ms.yearHistoryList(id);
		List<BusinessVO> blist = bs.businessAllList();
		List<SiteVO> slist = ss.siteAllList();
		
		model.addAttribute("info", evo);
		model.addAttribute("logList", logList);
		model.addAttribute("blist", blist);
		model.addAttribute("slist", slist);
		
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "empStateIns";
		}
	}
}
