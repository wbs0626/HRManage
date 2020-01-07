package com.miris.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 	직원의 월별 정보
 */

@Getter
@Setter
@ToString
public class MonthVO {
	
	private String id;
	private int section;	// DB상에는 1: 내부 2: 외부
	private String emp_name;
	private String rank;
	private String business_name;
	private int exclusion_state;
	private String site_id;
	private String site_name;
	private int baseYear;
	private int baseMonth;
	private int state; // DB상에는 1: C 2: P
	private String month_remarks;
	private String depart_name;
	// ============================
	private int m1;//jan;
	private int m2;//feb;
	private int m3;//mar;
	private int m4;//apr;
	private int m5;//may;
	private int m6;//jun;
	private int m7;//jul;
	private int m8;//aug;
	private int m9;//sep;
	private int m10;//oct;
	private int m11;//nov;
	private int m12;//dec;
}
	