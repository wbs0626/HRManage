package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.miris.vo.AdminVO;


public interface AdminMapper {
	@Select("SELECT count(*) FROM admin WHERE id=#{id}")
	public int loginIdCheck(AdminVO vo);
	
	@Select("SELECT count(*) FROM admin WHERE id=#{id} AND pwd=#{pwd}")
	public int loginPwdCheck(AdminVO vo);
	
	@Select("SELECT * FROM admin")
	public List<AdminVO> adminAllList();
	
	@Select("SELECT * FROM admin "
			+ "WHERE id = #{id}")
	public AdminVO adminInfo(AdminVO vo);
}
