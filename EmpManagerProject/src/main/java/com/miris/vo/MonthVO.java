package com.miris.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonthVO {
	
	private String id;
	private int section;	// DB상에는 1: 내부 2: 외부
	private String emp_name;
	private String rank;
	private String business_name;
	private int baseYear;
	private int baseMonth;
	private int state; // DB상에는 1: C 2: P
	private String month_remarks;
	

	
	
}
	