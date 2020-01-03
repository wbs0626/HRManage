package com.miris.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.miris.mapper.SiteMapper;
import com.miris.vo.SiteVO;

@Repository
public class SiteDAO implements SiteMapper {
	@Autowired
	private SiteMapper mapper;
	
	public List<SiteVO> siteAllList() {
		return mapper.siteAllList();
	}

}
