package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.miris.vo.DepartVO;

public interface DepartMapper {
	// 부서 전체 내역
	@Select("SELECT * FROM departs")
	public List<DepartVO> deptList();
	
	// 부서 명 검색
	@Select("SELECT depart_id, depart_name FROM departs WHERE depart_id = #{depart_id}")
	public DepartVO deptNameSearch(String depart_id);
	
	// 중복 코드 검사
	@Select("SELECT count(*) FROM departs WHERE depart_id = #{depart_id}")
	public int deptChkId(String depart_id);
	
	// 부서 삭제
	@Delete("DELETE FROM departs WHERE depart_id=#{depart_id}")
	public void deptDelete(String depart_id);
	
	// 부서 등록
	@Insert("INSERT INTO departs VALUES(#{depart_id}, #{depart_name})")
	public void deptInsert(DepartVO vo);
}
