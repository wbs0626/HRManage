package com.miris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miris.dao.RankDAO;
import com.miris.vo.RankVO;

import java.util.*;

@Service
public class RankService {
	@Autowired
	private RankDAO rdao;
	
	public List<RankVO> rankAllFind() {
		return rdao.rankAllFind();
	}
}
