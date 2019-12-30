package com.miris.vo;

import lombok.Getter;
import lombok.Setter;

/*
 * 	월별 상태값
 */

@Getter
@Setter
public class MonthEmpStateVO {
	private String id;
	private int baseYear;
	private int[] state;
}
