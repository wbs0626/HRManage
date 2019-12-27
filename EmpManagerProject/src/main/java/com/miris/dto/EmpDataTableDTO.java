package com.miris.dto;

import java.util.Date;

/*
 * 	사원 현황 데이터 저장용 
 * 	-- history_time 이 date 형 변환이 정상적으로 이루어지지않아서 String 형으로 변경 (숫자값으로 들어옴)
 */

public class EmpDataTableDTO {
	private String id;
	private String emp_name;
	private String rank;
	private String depart_name;
	private int state;
	private String loc_addr;
	private String htime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getDepart_name() {
		return depart_name;
	}
	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getLoc_addr() {
		return loc_addr;
	}
	public void setLoc_addr(String loc_addr) {
		this.loc_addr = loc_addr;
	}
	public String getHtime() {
		return htime;
	}
	public void setHtime(String htime) {
		this.htime = htime;
	}

	
	
}
