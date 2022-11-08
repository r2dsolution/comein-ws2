package com.r2dsolution.comein.dto;

public class PaymentConditionDto {
	private Integer payableTourDate;
	private Integer payableDate;
	private Long companyId;
	private boolean useDefault;
	
	public Integer getPayableTourDate() {
		return payableTourDate;
	}
	public void setPayableTourDate(Integer payableTourDate) {
		this.payableTourDate = payableTourDate;
	}
	public Integer getPayableDate() {
		return payableDate;
	}
	public void setPayableDate(Integer payableDate) {
		this.payableDate = payableDate;
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
