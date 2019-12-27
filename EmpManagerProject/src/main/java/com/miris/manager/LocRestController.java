package com.miris.manager;

import java.util.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miris.service.LocService;
import com.miris.vo.LocVO;

@RestController
public class LocRestController {
	@Autowired
	private LocService ls;
	
	@RequestMapping("loc/locInsert_ok.do")
	public String locInsert_ok(LocVO vo) {
		String msg = "";

		//System.out.println("vo 값 : " + ToStringBuilder.reflectionToString(vo));
		
		try {	// 같은 이름이 이미 있으면 FAIL
			if(ls.locInsert(vo)) {
				msg = "OK";
			} else {
				msg = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@RequestMapping("loc/locDelete_ok.do")
	public String locDel_ok(String name) {
		String msg = "";
		
		//System.out.println("들어온 name값: " + name);
		try {
			if(ls.locDelete(name)) {
				msg = "OK";
			} else {
				msg = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("loc/locSearch_ok.do")
	public LocVO locSearch_ok(String name) {
		LocVO searchData = new LocVO();
		
		System.out.println("들어온 name값: " + name);
		searchData = ls.locSearch(name);
		System.out.println("searchData 값 : " + ToStringBuilder.reflectionToString(searchData));
		
		return searchData;
	}
}
