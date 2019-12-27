package com.miris.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonthlyRateVO {
	private int mTotal;	// 총인원
	private int mExcept;	// 제외인원
	private int mPossible;	// 가동가능인원
	private int mInput1;	// 투입인원1
	private int mInput2;	// 투입인원2
	private int mWait;		// 대기인원
	private double rate;		// 가동율
	
}
