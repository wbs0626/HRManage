package com.miris.dto;

import java.sql.Date;

/*
 * 	일별 직원 근무 상태
 * 	총인원, 근무, 휴가, 출장, 기타
 */

public class DailyEmpCountDTO {
	private int total;
	private int work;
	private int vacation;
	private int business_trip;
	private int others;

	
	public int getTotal() {
		return total;
	}
	public int getWork() {
		return work;
	}
	public int getVacation() {
		return vacation;
	}
	public int getBusiness_trip() {
		return business_trip;
	}
	public int getOthers() {
		return others;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setWork(int work) {
		this.work = work;
	}
	public void setVacation(int vacation) {
		this.vacation = vacation;
	}
	public void setBusiness_trip(int business_trip) {
		this.business_trip = business_trip;
	}
	public void setOthers(int others) {
		this.others = others;
	}

	
	
}
