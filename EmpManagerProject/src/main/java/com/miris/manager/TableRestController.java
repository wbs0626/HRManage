package com.miris.manager;

import java.util.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.miris.dto.DailyEmpCountDTO;
import com.miris.dto.EmpDataTableDTO;
import com.miris.service.BusinessService;
import com.miris.service.DeptService;
import com.miris.service.EmpService;
import com.miris.service.HistoryService;
import com.miris.service.MonthlyHisService;
import com.miris.service.SiteService;
import com.miris.vo.BusinessVO;
import com.miris.vo.DepartVO;
import com.miris.vo.EmpDetailVO;
import com.miris.vo.EmpVO;
import com.miris.vo.MonthVO;
import com.miris.vo.MonthlyRateVO;
import com.miris.vo.SiteVO;

@RestController
public class TableRestController {
	@Autowired
	private EmpService es;
	@Autowired
	private HistoryService hs;
	@Autowired
	private MonthlyHisService ms;
	@Autowired
	private DeptService ds;
	@Autowired
	private SiteService ss;
	@Autowired
	private BusinessService bs;
	
	// 날짜별 근무 인원 현황
	@RequestMapping("tables/dailyEmpStateCount.do")
	public DailyEmpCountDTO dailyEmpStateCount(String htime, Model model) {
		DailyEmpCountDTO vo = new DailyEmpCountDTO();
		//System.out.println("hdate : " + htime);
		vo = hs.empStateCount(htime);

		return vo;
	}

	@RequestMapping("tables/empLogCount.do")
	public DailyEmpCountDTO empLogCount(EmpDataTableDTO dto) {
		DailyEmpCountDTO edto = new DailyEmpCountDTO();

		return edto;
	}

	// 근무 인원 상세 현황
	@RequestMapping("tables/empDataTable.do")
	public List<EmpDataTableDTO> empDataTable(String select, EmpDataTableDTO tvo) {
		List<EmpDataTableDTO> data = new ArrayList<EmpDataTableDTO>();

		// 차후 물어보고 추가할 부분
		String[] arr = { "전체" };

		//System.out.println("vo 값 : " + ToStringBuilder.reflectionToString(tvo));
		// == 쓰면 안됨 주소값 다름(문서 참조)
		if (select.equals(arr[0])) {
			if(tvo.getHtime().equals("") && tvo.getEmp_name().isEmpty() == false) {
				data = es.empNameSearch(tvo);
			} else if (tvo.getHtime().isEmpty() == false && tvo.getEmp_name().isEmpty() == true){
				data = es.empTableList(tvo);
			} else {
				data = es.empNameDateSearch(tvo);
			} 
		} else {
			data = es.empNameSearch(tvo);
		}

		return data;
	}

	// 인력 가동율 데이터
	@RequestMapping("tables/operation_rate.do")
	public MonthlyRateVO monthRate(MonthVO mvo) {
		MonthlyRateVO rvo = new MonthlyRateVO();
		
		//System.out.println(mvo);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("baseYear", mvo.getBaseYear());
		map.put("baseMonth", mvo.getBaseMonth());
		
		rvo = hs.operationRate(map);
		
		int input1 = rvo.getMInput1();
		int possible = rvo.getMPossible();
		double tmp = ((double)input1 / (double)possible) * 100.0;
		double operRate = (Math.round(tmp * 100) / 100.0);
		
		rvo.setRate(operRate);
		
		//System.out.println("RVO : " + rvo);
		
		return rvo;
	}
	
	@RequestMapping("tables/empMonthDataFind.do")
	public List<MonthVO> empMonthDataFind(MonthVO mvo) {
		List<MonthVO> list = new ArrayList<MonthVO>();
		
		String bname = mvo.getBusiness_name();
		bname.trim();
		mvo.setBusiness_name(bname);
		
		list = ms.monEmpDataFind(mvo);
		
		return list;
	}
	
	@RequestMapping("empUpd_ok.do")
	public String empInsOk(EmpVO vo) {
		String msg = "FAIL";
		
		//System.out.println(vo);
		
		try {
			es.empUpdate(vo);
			msg = "OK";
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping("getBsInfo.do")
	public List<BusinessVO> getBsInfo() {
		List<BusinessVO> bvo = bs.businessAllList();
		
		return bvo;
	}
	
	@RequestMapping("getSiteInfo.do")
	public List<SiteVO> getSiteInfo() {
		List<SiteVO> svo = ss.siteAllList();
		
		return svo;
	}
	
	@RequestMapping("addState.do")
	public String addState(MonthVO mvo) {
		
		String msg = "";
		
		try {
			ms.monthHisInsert(mvo);
			msg = "OK";
		} catch(Exception e) {
			msg = "FAIL";
			e.printStackTrace();
		}
		
		return msg;
	}
	
}
