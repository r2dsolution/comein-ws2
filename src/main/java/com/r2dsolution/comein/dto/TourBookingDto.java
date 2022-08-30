package com.r2dsolution.comein.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TourBookingDto {
	
	private Long id;
	private Long ticketId;
	private String bookingCode;
	private String referenceName;
	private String locationPickup;
	private LocalDate bookingDate;
	private int totalAdult;
	private int totalChild;
	private BigDecimal totalRate;
	private String remark;
	private String paymentMethod;
	private String tourName;
	private LocalDate tourDate;
	private String cancelFlag;
	private String cancelable;
	private int cancelBefore;
	
    private TourInfoDto tourData;
    private TourInventoryDto tourInventoryData;
    private PersonalDto personalData;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBookingCode() {
		return bookingCode;
	}
	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}
	public String getReferenceName() {
		return referenceName;
	}
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	public String getLocationPickup() {
		return locationPickup;
	}
	public void setLocationPickup(String locationPickup) {
		this.locationPickup = locationPickup;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public int getTotalAdult() {
		return totalAdult;
	}
	public void setTotalAdult(int totalAdult) {
		this.totalAdult = totalAdult;
	}
	public int getTotalChild() {
		return totalChild;
	}
	public void setTotalChild(int totalChild) {
		this.totalChild = totalChild;
	}
	public BigDecimal getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(BigDecimal totalRate) {
		this.totalRate = totalRate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public TourInfoDto getTourData() {
		return tourData;
	}
	public void setTourData(TourInfoDto tourData) {
		this.tourData = tourData;
	}
	public TourInventoryDto getTourInventoryData() {
		return tourInventoryData;
	}
	public void setTourInventoryData(TourInventoryDto tourInventoryData) {
		this.tourInventoryData = tourInventoryData;
	}
	public PersonalDto getPersonalData() {
		return personalData;
	}
	public void setPersonalData(PersonalDto personalData) {
		this.personalData = personalData;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public LocalDate getTourDate() {
		return tourDate;
	}
	public void setTourDate(LocalDate tourDate) {
		this.tourDate = tourDate;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getCancelable() {
		return cancelable;
	}
	public void setCancelable(String cancelable) {
		this.cancelable = cancelable;
	}
	public int getCancelBefore() {
		return cancelBefore;
	}
	public void setCancelBefore(int cancelBefore) {
		this.cancelBefore = cancelBefore;
	}

    
}
