package com.miris.manager;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miris.dto.DailyEmpCountDTO;
import com.miris.service.HistoryService;
import com.miris.service.LocService;
import com.miris.vo.HistoryVO;
import com.miris.vo.LocVO;
import com.miris.vo.MonthlyRateVO;

@Controller
public class MainController {
	@Autowired
	private HistoryService hs;
	@Autowired
	private LocService ls;
	

	@RequestMapping("main.do")
	public String main_main(Model model) {
		int nYear;
		int nMonth;
		int nDay;
		 
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		nYear = calendar.get(Calendar.YEAR);
		nMonth = calendar.get(Calendar.MONTH) + 1;
		nDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("baseYear", nYear);
		map.put("baseMonth", nMonth);
		System.out.println(map);
		MonthlyRateVO mRateVO = hs.operationRate(map);
		System.out.println("mRateVO 값 : " + mRateVO);
		
		int input1 = mRateVO.getMInput1();
		int possible = mRateVO.getMPossible();
		double tmp = ((double)input1 / (double)possible) * 100.0;
		double operRate = (Math.round(tmp * 100) / 100.0);
		
		mRateVO.setRate(operRate);
		
		String currentMonth = nYear + "-" + nMonth;
		String currentDate = nYear + "-" + nMonth + "-" + nDay;
		
		DailyEmpCountDTO hvo = hs.empStateCount(currentDate);
		
		model.addAttribute("mvo", mRateVO);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDate", currentDate);
		model.addAttribute("hvo", hvo);
		
		return "main";
	}
}
