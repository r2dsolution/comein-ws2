package com.r2dsolution.comein.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayablePeriodDto {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate from;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate to;
    
	public LocalDate getFrom() {
		return from;
	}
	public void setFrom(LocalDate from) {
		this.from = from;
	}
	public LocalDate getTo() {
		return to;
	}
	public void setTo(LocalDate to) {
		this.to = to;
	}
    
}
