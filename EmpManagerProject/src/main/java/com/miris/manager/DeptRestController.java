package com.miris.manager;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miris.service.DeptService;
import com.miris.vo.DepartVO;

@RestController
public class DeptRestController {
	@Autowired
	private DeptService ds;
	
	@RequestMapping("departs/depFindByName.do")
	public List<DepartVO> deptSearchInfo(String name) {
		List<DepartVO> list = ds.deptFindByName(name);
		
		System.out.println("부서 검색 리스트 : " + list);
		
		return list;
	}
	
	@RequestMapping("departs/depIns_ok.do")
	public String depIns_ok(DepartVO vo) {
		String msg = "";

		System.out.println("들어온 값 : " + vo);
		
		try {	// 같은 이름이 이미 있으면 FAIL
			if(ds.deptInsert(vo)) {
				msg = "OK";
			} else {
				msg = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("departs/depDel_ok.do")
	public String depDel_ok(int depart_id) {
		String msg = "";
		
		try {	// 같은 이름이 이미 있으면 FAIL
			if(ds.deptDelete(depart_id)) {
				msg = "OK";
			} else {
				msg = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("departs/depUpd_ok.do")
	public String depUpd_ok(DepartVO vo) {
		String msg = "";
		
		System.out.println("업데이트 정보: " + vo);
		
		try {
			if(ds.deptUpdate(vo)) {
				msg = "OK";
			} else {
				msg = "NO NAME";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
