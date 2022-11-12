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
@Table(name="payable_booking_view")
public class PayableBookingView implements Serializable {

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
    @Column(name="booking_code", nullable=false)
    private String bookingCode;
    @Column(name="tour_date", nullable=false)
    private LocalDate tourDate;
    private String status;
    @Column(name="payment_date", nullable=false)
    private LocalDate paymentDate;
    @Column(name="payment_method", nullable=false)
    private String paymentMethod;
    @Column(name="total_sell_value")
    private BigDecimal totalSellValue;
    
    /** Default constructor. */
    public PayableBookingView() {
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

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public BigDecimal getTotalSellValue() {
		return totalSellValue;
	}

	public void setTotalSellValue(BigDecimal totalSellValue) {
		this.totalSellValue = totalSellValue;
	}


}
