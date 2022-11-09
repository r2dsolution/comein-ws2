package com.r2dsolution.comein.dto;

public class PaymentConditionDto {
	private Integer payableTourDay;
	private Integer payableDay;
	private Long companyId;
	private boolean useDefault;
	
	public Integer getPayableTourDay() {
		return payableTourDay;
	}
	public void setPayableTourDay(Integer payableTourDay) {
		this.payableTourDay = payableTourDay;
	}
	public Integer getPayableDay() {
		return payableDay;
	}
	public void setPayableDay(Integer payableDay) {
		this.payableDay = payableDay;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public boolean isUseDefault() {
		return useDefault;
	}
	public void setUseDefault(boolean useDefault) {
		this.useDefault = useDefault;
	}
	
}
