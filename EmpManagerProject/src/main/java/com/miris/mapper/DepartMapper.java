package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.miris.vo.DepartVO;

public interface DepartMapper {
	// 부서 전체 내역
	@Select("SELECT depart_id, depart_name, TO_CHAR(reg_date, 'YYYY-MM-DD') reg_date, departs_remarks FROM departs")
	public List<DepartVO> deptList();
	
	// 부서 이름 찾기
	@Select("SELECT depart_id, depart_name FROM departs WHERE depart_id = #{depart_id}")
	public DepartVO deptNameSearch(int depart_id);
	
	// 부서 검색 by 이름
	@Select("SELECT depart_id, depart_name, TO_CHAR(reg_date, 'YYYY-MM-DD') reg_date, departs_remarks FROM departs WHERE depart_name LIKE '%'||#{depart_name}||'%'")
	public List<DepartVO> deptFindByName(String depart_name);
	
	// 부서 정보 검색
	@Select("SELECT depart_id, depart_name, TO_CHAR(reg_date, 'YYYY-MM-DD') reg_date, departs_remarks "
			+ "FROM departs "
			+ "WHERE depart_id = #{depart_id}")
	public DepartVO deptFindInfo(DepartVO vo);
	
	// 중복 검사
	@Select("SELECT count(*) FROM departs WHERE depart_id = #{depart_id}")
	public int deptChkId(int depart_id);
	
	// 부서 삭제
	@Delete("DELETE FROM departs WHERE depart_id=#{depart_id}")
	public void deptDelete(int depart_id);
	
	// 부서 등록
	@Insert("INSERT INTO departs(depart_id, depart_name, departs_remarks) VALUES(#{depart_id}, #{depart_name}, #{departs_remarks})")
	public void deptInsert(DepartVO vo);
	
	// 부서 수정
	@Update("UPDATE departs SET "
			+ "depart_name = #{depart_name}, "
			+ "departs_remarks = #{departs_remarks} "
			+ "WHERE depart_id = #{depart_id}")
	public void deptUpdate(DepartVO vo);
}
