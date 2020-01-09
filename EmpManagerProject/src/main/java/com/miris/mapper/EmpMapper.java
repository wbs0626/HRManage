package com.miris.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.miris.dto.EmpDataTableDTO;
import com.miris.vo.EmpVO;
import com.miris.vo.HistoryVO;
import com.miris.vo.MonthVO;

public interface EmpMapper {
	// 직원 총 수
	@Select("SELECT count(*) FROM emp")
	public int empCount();

	@Select("SELECT * FROM emp WHERE ID=#{id} ")
	public EmpVO empDetailInfo(EmpVO vo);
	
	// 직원 내역 출력
	@Select("SELECT id, pwd, emp_name, rank, section, depart_id, TO_CHAR(entry_date, 'YYYY-MM-DD') entry_date, TO_CHAR(retire_date, 'YYYY-MM-DD') retire_date, emp_remarks "
			+ "FROM emp")
	public List<EmpVO> empList();
	//TO_CHAR(dh.history_time, 'YYYY-MM-DD')
	
	// 직원 테이블 출력용(날짜)
	@Select("SELECT e.id, e.emp_name, e.rank, d.depart_name, dh.state, l.loc_addr, to_char(dh.history_time, 'YYYY.MM.DD hh24:mm') htime " + 
			"FROM emp e LEFT OUTER JOIN dailyhistory dh " + 
			"ON e.id = dh.id AND dh.history_date = #{htime} " +
			"LEFT OUTER JOIN departs d " +
			"ON e.depart_id = d.depart_id " +
			"LEFT OUTER JOIN loc l " + 
			"ON dh.loc_name = l.loc_name")
	public List<EmpDataTableDTO> EmpTableList(EmpDataTableDTO tvo);
	
	@Select("select count(*) " + 
			"FROM emp e, departs dep, dailyhistory dh, loc l " + 
			"WHERE e.depart_id = dep.depart_id " + 
			"AND e.id(+) = dh.id " + 
			"AND dh.loc_name = l.loc_name(+) " + 
			"ORDER BY dh.history_time")
	public int EmpTableCount();
	
	// 직원 이름으로 검색
	@Select("SELECT e.id, e.emp_name, e.rank, d.depart_name, dh.state, l.loc_addr, to_char(dh.history_time, 'YYYY.MM.DD hh24:mm') htime " + 
			"FROM emp e JOIN dailyhistory dh " + 
			"ON e.id = dh.id AND e.emp_name LIKE '%'||#{emp_name}||'%' " +
			"JOIN departs d "
			+ "ON e.depart_id = d.depart_id "
			+ "LEFT OUTER JOIN loc l "
			+ "ON dh.loc_name = l.loc_name")
	public List<EmpDataTableDTO> empNameSearch(EmpDataTableDTO tvo);
	
	// 직원 명 검색(직원 관리)
	@Select("SELECT id, pwd, emp_name, rank, section, depart_id, TO_CHAR(entry_date, 'YYYY-MM-DD') entry_date, TO_CHAR(retire_date, 'YYYY-MM-DD') retire_date, emp_remarks "
			+ "FROM emp "
			+ "WHERE emp_name LIKE '%'||#{emp_name}||'%'")
	public List<EmpVO> empNameSearch2(String name);
	
	// 직원 이름+날짜
	@Select("SELECT e.id, e.emp_name, e.rank, d.depart_name, dh.state, l.loc_addr, to_char(dh.history_time, 'YYYY.MM.DD hh24:mm') htime " + 
			"FROM emp e JOIN dailyhistory dh " + 
			"ON e.id = dh.id AND e.emp_name= #{emp_name} AND dh.history_date = #{htime} " +
			"JOIN departs d "
			+ "ON e.depart_id = d.depart_id "
			+ "LEFT OUTER JOIN loc l "
			+ "ON dh.loc_name = l.loc_name")
	public List<EmpDataTableDTO> empNameDateSearch(EmpDataTableDTO tvo);
	
	// 부서명 검색
	@Select("SELECT e.id, e.emp_name, e.rank, d.depart_name, dh.state, l.loc_addr, to_char(dh.history_time, 'YYYY.MM.DD hh24:mm') htime " + 
			"FROM emp e JOIN dailyhistory dh " + 
			"ON e.id = dh.id " +
			"JOIN departs d "
			+ "ON e.depart_id = d.depart_id AND d.depart_name = #{depart_name} "
			+ "LEFT OUTER JOIN loc l "
			+ "ON dh.loc_name = l.loc_name")
	public List<EmpDataTableDTO> empDepartSearch(EmpDataTableDTO tvo);
	
	// 이름 + 부서명 검색
	@Select("SELECT e.id, e.emp_name, e.rank, d.depart_name, dh.state, l.loc_addr, to_char(dh.history_time, 'YYYY.MM.DD hh24:mm') htime " + 
			"FROM emp e JOIN dailyhistory dh " + 
			"ON e.id = dh.id " +
			"JOIN departs d "
			+ "ON e.depart_id = d.depart_id AND d.depart_name = #{depart_name} "
			+ "LEFT OUTER JOIN loc l "
			+ "ON dh.loc_name = l.loc_name")
	public List<EmpDataTableDTO> empNameDepartSearch(EmpDataTableDTO tvo);
	
	// 직원 정보(부서 명 포함)
	@Select("SELECT e.id, e.section, d.depart_name, e.emp_name, e.rank, e.emp_remarks as month_remarks "
			+ "FROM emp e JOIN departs d "
			+ "ON e.depart_id = d.depart_id "
			+ "AND e.id=#{id}")
	public MonthVO empInfo(String id);
	
	// 직급 목록
	@Select("SELECT DISTINCT(rank) FROM emp")
	public List<EmpVO> rankList();
	
	// 직원 정보 수정
	@Update("UPDATE emp SET "
			+ "section = #{section}, "
			+ "depart_id = #{depart_id}, "
			+ "rank = #{rank}, "
			+ "emp_remarks = #{emp_remarks} "
			+ "WHERE id = #{id}")
	public void empUpdate(EmpVO vo);
	
	// 직원 등록
	@Insert("INSERT INTO emp (id, section, emp_name, depart_id, rank, emp_remarks, entry_date, retire_date) "
			+ "VALUES (#{id}, #{section}, #{emp_name}, #{depart_id}, "
			+ "#{rank}, #{emp_remarks}, #{entry_date}, #{retire_date})")
	public void empInsert(EmpVO vo);
	
	// 직원 삭제
	@Delete("DELETE FROM emp WHERE id = #{id}")
	public void empDelete(String id);
	
	@Select("SELECT count(*) FROM emp WHERE emp_name = #{emp_name}")
	public int duplicateNameChk(EmpVO vo);
	
	// (ID 확인)
	@Select("SELECT count(*) FROM emp WHERE id=#{id}")
	public int loginIdCheck(EmpVO vo);
	
}
