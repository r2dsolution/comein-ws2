package com.r2dsolution.comein.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TourInventoryDto {

	private Long id;
	private Long tourId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    
    private LocalDate tourDate;
    private long total;
    private BigDecimal adultRate;
    private BigDecimal childRate;
    private String cancelable;
    private int cancelBefore;
    
	public Long getTourId() {
		return tourId;
	}
	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getCancelable() {
		return cancelable;
	}
	public void setCancelable(String cancelable) {
		this.cancelable = cancelable;
	}
	public int getCancelBefore() {
		return cancelBefore;
	}
	public void setCancelBefore(int cancelBefore) {
		this.cancelBefore = cancelBefore;
	}
	public LocalDate getTourDate() {
		return tourDate;
	}
	public void setTourDate(LocalDate tourDate) {
		this.tourDate = tourDate;
	}
	public BigDecimal getAdultRate() {
		return adultRate;
	}
	public void setAdultRate(BigDecimal adultRate) {
		this.adultRate = adultRate;
	}
	public BigDecimal getChildRate() {
		return childRate;
	}
	public void setChildRate(BigDecimal childRate) {
		this.childRate = childRate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
}
