package com.r2dsolution.comein.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TopUpRateDetailDto {
	private BigDecimal minPeriod;
	private BigDecimal maxPeriod;
	private BigDecimal topUpRate;
	private BigDecimal comeinRate;
	private BigDecimal hotelRate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDate;
	
	public BigDecimal getTopUpRate() {
		return topUpRate;
	}
	public void setTopUpRate(BigDecimal topUpRate) {
		this.topUpRate = topUpRate;
	}
	public BigDecimal getComeinRate() {
		return comeinRate;
	}
	public void setComeinRate(BigDecimal comeinRate) {
		this.comeinRate = comeinRate;
	}
	public BigDecimal getHotelRate() {
		return hotelRate;
	}
	public void setHotelRate(BigDecimal hotelRate) {
		this.hotelRate = hotelRate;
	}
	public BigDecimal getMinPeriod() {
		return minPeriod;
	}
	public void setMinPeriod(BigDecimal minPeriod) {
		this.minPeriod = minPeriod;
	}
	public BigDecimal getMaxPeriod() {
		return maxPeriod;
	}
	public void setMaxPeriod(BigDecimal maxPeriod) {
		this.maxPeriod = maxPeriod;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
