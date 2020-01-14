package com.miris.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.BusinessDAO;
import com.miris.dao.MonthlyHisDAO;
import com.miris.vo.EmpDetailVO;
import com.miris.vo.MonthEmpLogVO;
import com.miris.vo.MonthVO;

@Service
public class MonthlyHisService {
	@Autowired
	private MonthlyHisDAO mdao;
	@Autowired
	private BusinessDAO bdao;
	
	
	public List<MonthVO> monEmpDateSearch(MonthVO mvo) {	
		
		return mdao.monEmpDateSearch(mvo);
	}
	
	public List<MonthVO> monEmpDataFind(MonthVO mvo) {
		int section = mvo.getSection();
		String name = mvo.getEmp_name();
		String rank = mvo.getRank();
		String bname = mvo.getBusiness_name();
		
		if(section != 0 && name.isEmpty() == true && rank.equals("전체") && bname == "") {
			System.out.println("구분 검색");
			return mdao.monEmpSectionSearch(mvo);
		} else if(section == 0 && name.isEmpty() == false && rank.equals("전체") && bname == "") {
			System.out.println("이름 검색");
			return mdao.monEmpNameSearch(mvo);
		} else if (section == 0 && name.isEmpty() == true && !rank.equals("전체") && bname == "") {
			System.out.println("직위 검색");
			return mdao.monEmpRankSearch(mvo);
		} else if (section == 0 && name.isEmpty() == true && rank.equals("전체") && bname != "") {
			System.out.println("업무 검색");
			return mdao.monEmpBNameSearch(mvo);
		} else {
			System.out.println("날짜 검색");
			return mdao.monEmpDateSearch(mvo);
		}
	}
	
	public List<MonthEmpLogVO> yearHistoryList(String id) {
		if(id == null) {
			id = "";
		}
		
		return mdao.yearHistoryList(id);
	}
	
	public EmpDetailVO empDetailLog(MonthVO mvo) {
		return mdao.empDetailLog(mvo);
	}
	
	public boolean monthHisInsert(MonthVO mvo) {
		boolean isSuccess = false;
		
		String bname = mvo.getBusiness_name();
		int state = bdao.findBState(bname);
		
		mvo.setExclusion_state(state);
		
		if(mdao.monHisDupChk(mvo) != 1) {
			mdao.monthHisInsert(mvo);
			isSuccess = true;
		}
		
		return isSuccess;
	}
}
