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
		String site_name = mvo.getSite_name();
		System.out.println("현재 입력 값: " + mvo);
		
		if(rank.equals("전체")) {
			rank = "";
			mvo.setRank(rank);
		}
		
		/*
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
		*/
		
		if(section == 0 && name.isEmpty() == true && rank.equals("") && bname == "" && site_name == "") {
			System.out.println("날짜 검색");
			return mdao.monEmpDateSearch(mvo);
		} else if (section == 0 && (name.isEmpty() == false || !rank.equals("") || bname != "" || site_name !="")){
			System.out.println("전체 검색");
			return mdao.monMultiAllFind(mvo);
		} else {
			System.out.println("구분별 검색");
			System.out.println("VO값 : " + mvo);
			return mdao.monMultiFind(mvo);
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
	
	public int monthHisInsert(MonthVO mvo) {
		final int INSOK = 1;
		final int UPDOK = 2;
		final int FAIL = 3;
		
		String bname = mvo.getBusiness_name();
		int exState = bdao.findBState(bname);
		
		mvo.setExclusion_state(exState);
		
		try {
			if(mdao.monHisDupChk(mvo) == 0) {
				mdao.monthHisInsert(mvo);
				return INSOK;
			} else {
				mdao.monthHisUpdate(mvo);
				return UPDOK;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
	}
}
