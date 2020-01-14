package com.miris.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.mapper.RankMapper;
import com.miris.vo.RankVO;

@Repository
public class RankDAO implements RankMapper{
	@Autowired 
	private RankMapper mapper;
	
	public List<RankVO> rankAllFind() {
		return mapper.rankAllFind();
	}
	
}
