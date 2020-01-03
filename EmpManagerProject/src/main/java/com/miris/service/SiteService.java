package com.miris.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.SiteDAO;
import com.miris.vo.SiteVO;

@Service
public class SiteService {
	@Autowired
	private SiteDAO sdao;
	
	public List<SiteVO> siteAllList(){
		return sdao.siteAllList();
	}
}
