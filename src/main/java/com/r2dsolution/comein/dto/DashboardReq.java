package com.r2dsolution.comein.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DashboardReq {
	private long dashboard_id;
    private long tour_company_id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate date_from;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate date_to;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate tour_date;
    
	public long getDashboard_id() {
		return dashboard_id;
	}
	public void setDashboard_id(long dashboard_id) {
		this.dashboard_id = dashboard_id;
	}
	public long getTour_company_id() {
		return tour_company_id;
	}
	public void setTour_company_id(long tour_company_id) {
		this.tour_company_id = tour_company_id;
	}
	public LocalDate getDate_from() {
		return date_from;
	}
	public void setDate_from(LocalDate date_from) {
		this.date_from = date_from;
	}
	public LocalDate getDate_to() {
		return date_to;
	}
	public void setDate_to(LocalDate date_to) {
		this.date_to = date_to;
	}
	public LocalDate getTour_date() {
		return tour_date;
	}
	public void setTour_date(LocalDate tour_date) {
		this.tour_date = tour_date;
	}

}
