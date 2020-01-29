package com.miris.vo;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 	페이징 구현을 위한 데이터를 묶어서 보낸다.
 * 
 */

@Getter
@Setter
@ToString
public class MonWrapperVO {
	private List<MonthVO> list;	// 데이터 리스트
	private int totalCnt;		// 총 데이터 개수
}
