package com.r2dsolution.comein.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReceivableNoteDto {

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate transactionDate;
    private String paymentMethod;
    private BigDecimal receive;
    private BigDecimal total;
	private String note;
	
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public BigDecimal getReceive() {
		return receive;
	}
	public void setReceive(BigDecimal receive) {
		this.receive = receive;
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
}
