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
@Table(name="payable_tour_view")
public class PayableTourView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary key. */
    protected static final String PK = "id";

    @Id
    @Column(unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="date_from", nullable=false)
    private LocalDate dateFrom;
    @Column(name="date_to", nullable=false)
    private LocalDate dateTo;
    @Column(name="company_id", nullable=false)
    private long companyId;
    @Column(name="booking_code", nullable=false)
    private String bookingCode;
    @Column(name="tour_date", nullable=false)
    private LocalDate tourDate;
    @Column(name="tour_name")
    private String tourName;
    private String status;
    private String note;
    @Column(name="net_value")
    private BigDecimal netValue;
    @Column(name="period_id", nullable=false)
    private long periodId;
    @Column(name="reference_name")
    private String referenceName;
    @Column(name="tour_id")
    private Long tourId;
    
    /** Default constructor. */
    public PayableTourView() {
        super();
    }


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public LocalDate getDateFrom() {
		return dateFrom;
	}


	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}


	public LocalDate getDateTo() {
		return dateTo;
	}


	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}


	public long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}


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


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public BigDecimal getNetValue() {
		return netValue;
	}


	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}


	public long getPeriodId() {
		return periodId;
	}


	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}


	public String getReferenceName() {
		return referenceName;
	}


	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}


	public Long getTourId() {
		return tourId;
	}


	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}


}
