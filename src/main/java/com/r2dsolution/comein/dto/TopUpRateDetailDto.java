package com.r2dsolution.comein.dto;

import java.math.BigDecimal;

public class TopUpRateDetailDto {
	private BigDecimal minPeriod;
	private BigDecimal maxPeriod;
	private BigDecimal topUpRate;
	private BigDecimal comeinRate;
	private BigDecimal hotelRate;
	
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
	
}
