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
	
	@RequestMapping("dep/depSearch_ok.do")
	public DepartVO deptSearchInfo(String name) {
		DepartVO vo = ds.deptNameSearch(name);
		
		return vo;
	}
}
