package com.miris.mapper;

import org.apache.ibatis.annotations.Select;

import com.miris.vo.RankVO;

import java.util.*;

public interface RankMapper {
	@Select("SELECT * FROM ranktable "
			+ "ORDER BY rank_rate")
	public List<RankVO> rankAllFind();
}
