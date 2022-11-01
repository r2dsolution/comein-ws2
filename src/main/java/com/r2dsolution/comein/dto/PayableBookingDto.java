package com.r2dsolution.comein.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayableBookingDto {
    private String bookingCode;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate tourDate;
    private String status;
	private String paymentMethod;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate paymentDate;
	private BigDecimal sellValue;
	
	public String getBookingCode() {
		return bookingCode;
	}
	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}
	public LocalDate getTourDate() {
		return tourDate;
	}
	public void setTourDate(LocalDate tourDate) {
		this.tourDate = tourDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public BigDecimal getSellValue() {
		return sellValue;
	}
	public void setSellValue(BigDecimal sellValue) {
		this.sellValue = sellValue;
	}
    
	
    
}
