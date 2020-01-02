package com.miris.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/*
 * 	사원 현황 데이터 저장용 
 * 	-- history_time 이 date 형 변환이 정상적으로 이루어지지않아서 String 형으로 변경 (숫자값으로 들어옴)
 */

@Getter
@Setter
public class EmpDataTableDTO {
	private String id;
	private String emp_name;
	private String rank;
	private String depart_name;
	private int state;
	private String loc_addr;
	private String htime;
	
	
	
	
}
