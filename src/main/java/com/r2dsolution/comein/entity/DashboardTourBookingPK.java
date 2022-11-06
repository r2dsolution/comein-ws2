// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DashboardTourBookingPK implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="tour_company_id", nullable=false, precision=10)
    private long tourCompanyId;
    @Column(name="tour_date", nullable=false)
    private LocalDate tourDate;
    @Column(name="tour_name", nullable=false)
    private String tourName;
    
    public DashboardTourBookingPK() {
		super();
	}
    
	public DashboardTourBookingPK(int tourCompanyId, LocalDate tourDate, String tourName) {
		super();
		this.tourCompanyId = tourCompanyId;
		this.tourDate = tourDate;
		this.tourName = tourName;
	}

	public long getTourCompanyId() {
		return tourCompanyId;
	}

	public void setTourCompanyId(long tourCompanyId) {
		this.tourCompanyId = tourCompanyId;
	}

	public LocalDate getTourDate() {
		return tourDate;
	}

	public void setTourDate(LocalDate tourDate) {
		this.tourDate = tourDate;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

}
