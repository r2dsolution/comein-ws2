package com.r2dsolution.comein.dto;

import java.math.BigDecimal;
import java.util.List;

public class PayableCompanySummaryDto {
	private Long periodId;
    private String periodDesc;
    private String status;
	private BigDecimal total;
	
	private List<PayableCompanyDto> details;

	public Long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Long periodId) {
		this.periodId = periodId;
	}

	public String getPeriodDesc() {
		return periodDesc;
	}

	public void setPeriodDesc(String periodDesc) {
		this.periodDesc = periodDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<PayableCompanyDto> getDetails() {
		return details;
	}

	public void setDetails(List<PayableCompanyDto> details) {
		this.details = details;
	}
	
	
	    
}
