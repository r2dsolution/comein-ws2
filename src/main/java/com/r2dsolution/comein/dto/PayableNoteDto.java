package com.r2dsolution.comein.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayableNoteDto {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate transactionDate;
    private BigDecimal hotelRate;
    private BigDecimal total;
	private String note;
	private List<PayablePeriodDto> periods;
	
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public BigDecimal getHotelRate() {
		return hotelRate;
	}
	public void setHotelRate(BigDecimal hotelRate) {
		this.hotelRate = hotelRate;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<PayablePeriodDto> getPeriods() {
		return periods;
	}
	public void setPeriods(List<PayablePeriodDto> periods) {
		this.periods = periods;
	}
    
}
