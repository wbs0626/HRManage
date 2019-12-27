package com.miris.vo;

import lombok.Getter;
import lombok.Setter;

/*
 * 	일별 근무 이력
 */
@Getter
@Setter

public class HistoryVO {
	//private String id;
	private String history_time;	// 등록 시간
	private int state;
	private String loc_name;	
	
}
