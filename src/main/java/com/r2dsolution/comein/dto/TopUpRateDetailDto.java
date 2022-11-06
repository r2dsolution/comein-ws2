package com.r2dsolution.comein.dto;

import java.math.BigDecimal;

public class TopUpRateDetailDto {
	private BigDecimal min;
	private BigDecimal max;
	private BigDecimal topUpRate;
	private BigDecimal comeinRate;
	private BigDecimal hotelRate;
	
	public BigDecimal getMin() {
		return min;
	}
	public void setMin(BigDecimal min) {
		this.min = min;
	}
	public BigDecimal getMax() {
		return max;
	}
	public void setMax(BigDecimal max) {
		this.max = max;
	}
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
	
}
