// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="company_booking_summary")
public class CompanyBookingSummary implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary key. */
    protected static final String PK = "id";

    @Id
    @Column(unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="company_id", nullable=false)
    private long companyId;
    @Column(name="company_name", nullable=false)
    private String companyName;
    @Column(name="booking_code", nullable=false)
    private String bookingCode;
    @Column(name="reference_name")
    private String referenceName;
    @Column(name="tour_date", nullable=false)
    private LocalDate tourDate;
    @Column(name="tour_name", nullable=false)
    private String tourName;
    private String status;
    @Column(name="total_sell_value")
    private BigDecimal totalSellValue;
    
    
    /** Default constructor. */
    public CompanyBookingSummary() {
        super();
    }


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public BigDecimal getTotalSellValue() {
		return totalSellValue;
	}


	public void setTotalSellValue(BigDecimal totalSellValue) {
		this.totalSellValue = totalSellValue;
	}


}
