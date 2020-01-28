package com.miris.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
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
	@Autowired
	private RankService rs;
	
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
	public String currentState(Model model, HttpSession session, String page, MonthVO vo) {

		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		int nYear = calendar.get(Calendar.YEAR);
		int nMonth = calendar.get(Calendar.MONTH) + 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("baseYear", nYear);
		map.put("baseMonth", nMonth);

		MonthlyRateVO rvo = hs.operationRate(map);

		int input1 = rvo.getMInput1();
		int possible = rvo.getMPossible();
		double tmp = ((double) input1 / (double) possible) * 100.0;
		double operRate = (Math.round(tmp * 100) / 100.0);

		rvo.setRate(operRate);
		// mvo 데이터 설정
		int totalPage;
		int curPage;
		int totalCnt = es.empCount();

		if (page == null || page == "") {
			page = "1";
		}

		curPage = Integer.parseInt(page);

		Pagination pa = new Pagination(totalCnt, curPage);

		totalPage = pa.getTotalPage();
		curPage = pa.getCurPage();
		
		
		vo.setBaseYear(nYear);
		vo.setBaseMonth(nMonth);
		vo.setPa(pa);

		//System.out.println("\ncontroller에요: " + vo);
		
		List<MonthVO> mList = new ArrayList<MonthVO>();
		
		mList = ms.monEmpDateSearch(vo);
		
		List<LocVO> lList = ls.locAllInfo();
		List<SiteVO> sList = ss.siteAllList();
		List<RankVO> rList = rs.rankAllFind();
		// 초기값 설정
		model.addAttribute("rvo", rvo);
		model.addAttribute("mList", mList);
		model.addAttribute("lList", lList);
		model.addAttribute("sList", sList);
		model.addAttribute("rList", rList);
		model.addAttribute("nYear", nYear);
		model.addAttribute("nMonth", nMonth);
		model.addAttribute("curPage", curPage);
		model.addAttribute("totalPage", totalPage);

		String tempId = (String) session.getAttribute("userId");

		if (tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "tables/inputCurrentState";
		}

	}
	
	// 인력 정보 수정 창
	@RequestMapping("empInfoUpd.do")
	public String empIns(Model model, HttpSession session, String id) {
		List<DepartVO> dvo = ds.deptList();
		List<RankVO> rvo = rs.rankAllFind();
		
		MonthVO mvo = es.empInfo(id);
		
		model.addAttribute("mvo", mvo);
		model.addAttribute("dvo", dvo);
		model.addAttribute("rvo", rvo);
		String tempId = (String)session.getAttribute("userId");
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:../login.do";
		} else {
			return "empInfoUpd";
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
	public String empStateIns(Model model, HttpSession session, MonthVO mvo) {
		//MonthVO mvo = new MonthVO();
		EmpDetailVO evo = new EmpDetailVO();
		
		String id = mvo.getId();
		int baseYear = mvo.getBaseYear();
		int baseMonth = mvo.getBaseMonth();
		String business_name = mvo.getBusiness_name();
		
		if(business_name == null || business_name.equals("")) {
			mvo = es.empInfo(id);

			mvo.setBaseYear(baseYear);
			mvo.setBaseMonth(baseMonth);
			model.addAttribute("info", mvo);
			model.addAttribute("type", "newEmp");
			//System.out.println("직원 데이터 정보A: " + mvo);
		} else {
			evo = ms.empDetailLog(mvo);
			model.addAttribute("info", evo);
			model.addAttribute("type", "oldEmp");
			//System.out.println("직원 데이터 정보B: " + evo);
		}
		
		System.out.println("직원 정보: " + evo);
		
		List<MonthEmpLogVO> logList = ms.yearHistoryList(id);
		List<BusinessVO> blist = bs.businessAllList();
		List<SiteVO> slist = ss.siteAllList();
		
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
