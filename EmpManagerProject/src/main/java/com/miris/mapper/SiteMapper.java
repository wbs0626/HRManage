package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.miris.vo.SiteVO;

public interface SiteMapper {
	// Site 전체 내역
	@Select("SELECT * FROM site")
	public List<SiteVO> siteAllList();
}
