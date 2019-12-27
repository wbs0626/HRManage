package com.miris.manager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miris.service.HistoryService;
import com.miris.service.LocService;
import com.miris.service.MonthlyHisService;
import com.miris.vo.MonthVO;
import com.miris.vo.MonthlyRateVO;

@Controller
public class TableController {
	@Autowired
	private LocService ls;
	@Autowired
	private HistoryService hs;
	@Autowired
	private MonthlyHisService ms;
	
	@RequestMapping("tables/tables.do")
	public String empAllocate(Model model) {
//		int maxLoc = ls.locCount();
//		List<LocVO> locList = ls.locAllInfo();
//		
//		model.addAttribute("maxLoc", maxLoc);
//		model.addAttribute("locList", locList);
		
		return "tables/tables";
	}
	
	@RequestMapping("tables/inputCurrentState.do")
	public String currentState(Model model) {

		MonthVO mvo = new MonthVO();
		
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		int nYear = calendar.get(Calendar.YEAR);
		int nMonth = calendar.get(Calendar.MONTH) + 1;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("baseYear", nYear);
		map.put("baseMonth", nMonth);
		
		MonthlyRateVO rvo = hs.operationRate(map);
		
		int total = rvo.getMTotal();
		int possible = rvo.getMPossible();
		double tmp = ((double)possible / (double)total) * 100.0;
		double operRate = (Math.round(tmp * 100) / 100.0);
		
		rvo.setRate(operRate);
		
		mvo.setBaseYear(nYear);
		mvo.setBaseMonth(nMonth);
		
		List<MonthVO> mList = ms.monEmpDateSearch(mvo);
		
		
		// 초기값 설정
		model.addAttribute("rvo", rvo);
		
		
		return "tables/inputCurrentState";
	}
	
	@RequestMapping("empIns.do")
	public String empIns() {
		
		
		return "empIns";
	}
}
