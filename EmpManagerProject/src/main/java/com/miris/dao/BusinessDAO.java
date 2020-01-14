package com.miris.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.mapper.BusinessMapper;
import com.miris.vo.BusinessVO;

@Repository
public class BusinessDAO implements BusinessMapper {
	@Autowired
	private BusinessMapper mapper;
	
	public List<BusinessVO> businessAllList() {
		return mapper.businessAllList();
	}

	public List<BusinessVO> busiFindByName(String business_name) {
		return mapper.busiFindByName(business_name);
	}

	public int ChkBusiName(String business_name) {
		return mapper.ChkBusiName(business_name);
	}

	public void busiIns(BusinessVO vo) {
		mapper.busiIns(vo);
	}

	public void busiDel(String business_name) {
		mapper.busiDel(business_name);
	}

	public int findBState(String business_name) {
		return mapper.findBState(business_name);
	}
	
	public void busiUpd(BusinessVO vo) {
		mapper.busiUpd(vo);
	}
	
	public BusinessVO busiFind(BusinessVO vo) {
		return mapper.busiFind(vo);
	}
	
}
