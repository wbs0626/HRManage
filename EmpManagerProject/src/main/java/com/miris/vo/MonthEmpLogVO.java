package com.miris.vo;

import lombok.Getter;
import lombok.Setter;

/*
 * 	월별 투입이력
 */

@Getter
@Setter
public class MonthEmpLogVO {
	private String baseDate;
	private String business_name;
	private int exclusion_state;
	private String month_remarks;
	private int baseYear;
	private int baseMonth;
	private int state;
	private String site_name;
}
