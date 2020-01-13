package com.miris.manager;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miris.service.EmpService;
import com.miris.vo.EmpVO;

@RestController
public class EmpRestController {
	@Autowired
	private EmpService es;
	
	@RequestMapping("emp/empFindByName.do")
	public List<EmpVO> empFindByName(String emp_name) {
		List<EmpVO> list = es.empNameSearch2(emp_name);
		
		//System.out.println("이름검색: " + list);
		
		return list;
	}
	
	@RequestMapping("emp/empInsert_ok.do")
	public String empInsert_ok(EmpVO vo) {
		String msg = "";
		
		System.out.println("들어온 값 : " + vo);
		
		try {	// 같은 이름이 이미 있으면 FAIL
			if(es.empInsert(vo)) {
				msg = "OK";
			} else {
				msg = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping("emp/empDelete_ok.do")
	public String empDelete_ok(EmpVO vo) {
		String msg = "";
		
		System.out.println(vo);
		
		try {	// 같은 ID 없으면 FAIL
			if(es.empDelete(vo)) {
				msg = "OK";
			} else {
				msg = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("emp/empUpd_ok.do")
	public String empUpd_ok(EmpVO vo) {
		String msg = "";
		
		try {
			es.empDetailUpdate(vo);
			msg = "OK";
		} catch (Exception e) {
			msg = "FAIL";
			e.printStackTrace();
		}
		return msg;
	}
}
