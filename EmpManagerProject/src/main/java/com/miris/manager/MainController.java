package com.miris.manager;

import java.util.*;

import javax.servlet.http.HttpSession;

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
	public String main_main(Model model, HttpSession session) {
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
		MonthlyRateVO mRateVO = hs.operationRate(map);
		
		int input1 = mRateVO.getMInput1();
		int possible = mRateVO.getMPossible();
		double tmp = ((double)input1 / (double)possible) * 100.0;
		double operRate = (Math.round(tmp * 100) / 100.0);
		
		mRateVO.setRate(operRate);
		
		String currentMonth = nYear + "-" + nMonth;
		String currentDate = nYear + "-" + nMonth + "-" + nDay;
		
		DailyEmpCountDTO hvo = hs.empStateCount(currentDate);
		
		String tempId = (String)session.getAttribute("userId");
		
		System.out.println("현재 세션 userId 값 : " + tempId);
		
		model.addAttribute("mvo", mRateVO);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("currentDate", currentDate);
		model.addAttribute("hvo", hvo);
		
		if(tempId == null || tempId.trim().equals("")) {
			return "redirect:login.do";
		} else {
			return "main";
		}
	}
}
