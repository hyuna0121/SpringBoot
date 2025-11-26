package com.example.app.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
	
	private Long page; // primitive type이 아니므로 초기값 : null
	private Long perPage; // 한 페이지 당 보여줄 글의 개수
	private Long perBlock; // 한 블럭 당 출력할 번호의 개수
	private Long startNum;
	private Long begin;
	private Long end;
	
	public Long getPage() {
		if (this.page == null || this.page < 1) this.page = 1L;
		
		return this.page;
	}
	
	public Long getPerPage() {
		if (this.perPage == null ||
			this.perPage < 1 ||
			this.perPage % 5 != 0) {
			this.perPage = 10L;
		}
		
		return this.perPage;
	}
	
	public Long getPerBlock() {
		if (this.perBlock == null ||
			this.perBlock < 1 ||
			this.perBlock % 5 != 0) {
			this.perBlock = 5L;
		}
		
		return this.perBlock;
	}
	
	// Paging 계산
	public void paging(Long totalCount) throws Exception {
		// 총 글의 갯수로 총 페이지 구하기
		Long totalPage = totalCount / this.getPerPage();
		if (totalCount % this.perPage != 0) totalPage++;
		
		// page가 totalPage보다 큰 경우
		if (this.getPage() > totalPage) this.page = totalPage;
		
		// 총 페이지 수로 총 블럭 수 구하기
		Long totalBlock = totalPage / this.getPerBlock();
		if (totalPage % this.perBlock != 0) totalBlock++;
		
		// 페이지 번호로 현재 블럭 번호 구하기
		Long curBlock = this.page / this.perBlock;
		if (this.page % this.perBlock != 0) curBlock++;
		
		// 현재 블록 번호로 시작 번호와 끝 번호 구하기
		this.begin = (curBlock - 1) * this.perBlock + 1;
		this.end = curBlock * this.perBlock;
		
		// 마지막 블록이라면 끝 번호를 총 페이지 수로 대입
		if (curBlock >= totalBlock) {
			this.begin = (totalBlock - 1) * this.perBlock + 1;
			this.end = totalPage;
		}
		
		this.makeStrNum();
	}
	
	// DB에서 일정한 갯수만큼 조회
	private void makeStrNum() throws Exception {
		this.startNum = (this.page - 1) * this.perPage;
	}
}
