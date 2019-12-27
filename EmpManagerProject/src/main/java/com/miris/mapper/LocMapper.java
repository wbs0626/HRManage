package com.miris.mapper;

/*
 *	근무지 관련 내용 정리
 */

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.miris.vo.LocVO;

public interface LocMapper {
	// 전체 근무지 수
	@Select("SELECT count(*) FROM loc")
	public int locCount();
	
	// 전체 근무지 내역 출력
	@Select("SELECT * FROM loc")
	public List<LocVO> locAllInfo();
	
	// 근무지 이름으로 검색
	@Select("SELECT loc_name, loc_addr FROM loc WHERE loc_name=#{loc_name}")
	public LocVO locSearch(String name);
	
	// 근무지 삭제
	@Delete("DELETE FROM loc WHERE loc_name=#{loc_name}")
	public void locDelete(String name);
	
	// 근무지 등록
	@Insert("INSERT INTO loc VALUES(#{loc_name}, #{loc_addr})")
	public void locInsert(LocVO vo);
	
	// 근무지 중복 여부 확인
	@Select("SELECT COUNT(*) FROM loc WHERE loc_name=#{loc_name}")
	public int ChecklocName(String name);
	
}
