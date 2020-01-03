package com.miris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.BusinessDAO;
import com.miris.vo.BusinessVO;

@Service
public class BusinessService {
	@Autowired
	private BusinessDAO bdao;
	
	public List<BusinessVO> businessAllList() {
		return bdao.businessAllList();
	}
}
