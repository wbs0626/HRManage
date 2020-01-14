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
	
	public List<BusinessVO> busiFindByName(String business_name) {
		return bdao.busiFindByName(business_name);
	}
	
	public boolean busiIns(BusinessVO vo) {
		boolean isSuccess = false;
		
		String business_name = vo.getBusiness_name();
		
		if(bdao.ChkBusiName(business_name) == 0) {
			bdao.busiIns(vo);
			isSuccess = true;
		}	
		return isSuccess;
	}
	
	public boolean busiDel(BusinessVO vo) {
		boolean isSuccess = false;
		
		String business_name = vo.getBusiness_name();
		
		if(bdao.ChkBusiName(business_name) == 1) {
			bdao.busiDel(business_name);
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public boolean busiUpd(BusinessVO vo) {
		boolean isSuccess = false;
		String business_name = vo.getBusiness_name();
		
		if(bdao.ChkBusiName(business_name) == 1) {
			bdao.busiUpd(vo);
			isSuccess = true;
		}
		return isSuccess;
	}
	
	public BusinessVO busiFind(BusinessVO vo) {
		return bdao.busiFind(vo);
	}
	
}
