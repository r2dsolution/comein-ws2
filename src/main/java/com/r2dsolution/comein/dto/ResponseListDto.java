package com.r2dsolution.comein.dto;

import java.util.List;

public class ResponseListDto<T> {

	private List<T> datas;
	private PaggingDto pagging;
	
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public PaggingDto getPagging() {
		return pagging;
	}
	public void setPagging(PaggingDto pagging) {
		this.pagging = pagging;
	}
}
