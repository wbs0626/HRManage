package com.miris.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/*
 *	직원 정보
 */

@Getter
@Setter
@ToString
public class EmpVO {
	private String id;
	private String pwd;
	private String emp_name;
	private int depart_id;
	private Date start_date;
	private Date login_date;
	private String emp_remarks;
	private String rank;
	private String section;
	
}
