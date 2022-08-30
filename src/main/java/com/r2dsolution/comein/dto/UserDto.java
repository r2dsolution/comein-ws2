package com.r2dsolution.comein.dto;

import java.time.LocalDateTime;

public class UserDto {
    private String refNo;
    private String email;
    private String role;
    private LocalDateTime invitedDate;
    private LocalDateTime registeredDate;
    private String userToken;
    private String status;
    private String name;
    private String hotelName;
    private Long hotelId;
    private Long tourId;
    
    private String firstName;
    private String lastName;
    
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public LocalDateTime getInvitedDate() {
		return invitedDate;
	}
	public void setInvitedDate(LocalDateTime invitedDate) {
		this.invitedDate = invitedDate;
	}
	public LocalDateTime getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(LocalDateTime registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public Long getTourId() {
		return tourId;
	}
	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    
}
