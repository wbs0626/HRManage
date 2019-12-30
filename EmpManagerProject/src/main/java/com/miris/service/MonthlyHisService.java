package com.miris.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.MonthlyHisDAO;
import com.miris.vo.MonthVO;

@Service
public class MonthlyHisService {
	@Autowired
	private MonthlyHisDAO mdao;

	public List<MonthVO> monEmpDateSearch(MonthVO mvo) {
		return mdao.monEmpDateSearch(mvo);
	}
	
	public List<MonthVO> monEmpDataFind(MonthVO mvo) {
		int section = mvo.getSection();
		String name = mvo.getEmp_name();
		String rank = mvo.getRank();
		String bname = mvo.getBusiness_name();
		
		if(section != 0 && name.isEmpty() == true && rank.equals("전체") && bname == null) {
			System.out.println("구분 검색");
			return mdao.monEmpSectionSearch(mvo);
		} else if(section == 0 && name.isEmpty() == false && rank.equals("전체") && bname == null) {
			System.out.println("이름 검색");
			return mdao.monEmpNameSearch(mvo);
		} else if (section == 0 && name.isEmpty() == true && !rank.equals("전체") && bname == null) {
			System.out.println("직위 검색");
			return mdao.monEmpRankSearch(mvo);
		} else if (section == 0 && name.isEmpty() == true && rank.equals("전체") && bname != null) {
			System.out.println("업무 검색");
			return mdao.monEmpBNameSearch(mvo);
		} else {
			System.out.println("날짜 검색");
			return mdao.monEmpDateSearch(mvo);
		}
		
	}
}
