package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.miris.vo.BusinessVO;

public interface BusinessMapper {
	
	// 업무 전체
	@Select("SELECT * FROM business")
	public List<BusinessVO> businessAllList();
	
	@Select("SELECT * FROM business "
			+ "WHERE business_name LIKE '%'||#{business_name}||'%'")
	public List<BusinessVO> busiFindByName(String business_name);
	
	@Select("SELECT count (*) FROM business "
			+ "WHERE business_name = #{business_name}")
	public int ChkBusiName(String business_name);
	
	@Select("SELECT exclusion_state FROM business "
			+ "WHERE business_name = #{business_name}")
	public int findBState(String business_name);
	
	@Insert("INSERT INTO business "
			+ "VALUES(#{business_name}, #{exclusion_state} )")
	public void busiIns(BusinessVO vo);
	
	@Delete("DELETE FROM business "
			+ "WHERE business_name = #{business_name}")
	public void busiDel(String business_name);
}
