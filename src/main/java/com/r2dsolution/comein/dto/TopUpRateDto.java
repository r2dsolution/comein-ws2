package com.r2dsolution.comein.dto;

import java.util.List;

public class TopUpRateDto {
	private Long companyId;
	private boolean useDefault;
	private List<TopUpRateDetailDto> detail;
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public List<TopUpRateDetailDto> getDetail() {
		return detail;
	}
	public void setDetail(List<TopUpRateDetailDto> detail) {
		this.detail = detail;
	}
	public boolean isUseDefault() {
		return useDefault;
	}
	public void setUseDefault(boolean useDefault) {
		this.useDefault = useDefault;
	}
	}
