package com.miris.manager;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miris.service.BusinessService;
import com.miris.vo.BusinessVO;

@RestController
public class TaskRestController {
	@Autowired
	private BusinessService bs;
	
	@RequestMapping("task/taskFindByName.do")
	public List<BusinessVO> taskFindByName(String name) {
		
		List<BusinessVO> list = bs.busiFindByName(name);
		System.out.println("업무 리스트 : " + list);
		
		return list;
	}
	
	@RequestMapping("task/taskInsert_ok.do")
	public String taskInsert_ok(BusinessVO vo) {
		String msg = "";
		
		System.out.println("들어온 값 : " + vo);
		
		try {	// 같은 이름이 이미 있으면 FAIL
			if(bs.busiIns(vo)) {
				msg = "OK";
			} else {
				msg = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping("task/taskDelete_ok.do")
	public String taskDelete_ok(BusinessVO vo) {
		String msg = "";
		
		System.out.println(vo);
		
		try {	// 같은 ID 없으면 FAIL
			if(bs.busiDel(vo)) {
				msg = "OK";
			} else {
				msg = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("task/taskUpdate_ok")
	public String taskUpdate_ok(BusinessVO vo) {
		String msg = "";
		System.out.println(vo);
		
		try {	
			if(bs.busiUpd(vo)) {
				msg = "OK";
			} else {
				msg = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
