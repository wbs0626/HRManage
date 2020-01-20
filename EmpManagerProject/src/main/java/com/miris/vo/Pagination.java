package com.miris.vo;

public class Pagination {
	public final int PAGE_SCALE = 10;	// 페이지당 데이터 개수
	public final int BLOCK = 5;
	
	private int curPage;
	private int totalPage;
	
	private int start;
	private int end;
	
	// 총 데이터 갯수, 현재 페이지 번호
	public Pagination(int totalCnt, int curPage) {
		if(curPage == 0) {
			curPage = 1;
		}
		this.curPage = curPage;
		
		setTotalPage(totalCnt);
		if(this.curPage > totalPage ) {
			this.curPage = totalPage;
		}
		setPageRange();
	}

	// 출력 데이터 범위
	public void setPageRange() {
		start = (curPage - 1) * PAGE_SCALE + 1;
		end = start + PAGE_SCALE - 1;
	}

	public void setTotalPage(int totalCnt) {
		totalPage = (int) Math.ceil(totalCnt * 1.0 / PAGE_SCALE);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	
	public int getCurPage() {
		return curPage;
	}
}
