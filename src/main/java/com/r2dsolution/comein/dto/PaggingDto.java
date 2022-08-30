package com.r2dsolution.comein.dto;

public class PaggingDto {


	private int pageNumber;
	private int pageSize;
	private long total;
	
	public PaggingDto() {
	}
	
	public PaggingDto(int pageNumber, int pageSize, long total) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.total = total;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
}
