package com.miris.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 	투입 인력의 현황 조회
 */

@Getter
@Setter
@ToString
public class EmpDetailVO {
	private String id;
	private int section;
	private String depart_name;
	private String emp_name;
	private String rank;
	private String emp_remarks;
	private int baseYear;
	private int baseMonth;
	private String business_name;
	private String site_name;
	private int state;
	private String month_remarks;
}
