// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="booking_kyc")
public class BookingKyc implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private long id;
    
    @Column(name="ref_id", length=100)
    private String refId;
    @Column(name="ref_type", length=10)
    private String refType;
    @Column(name="booking_id", precision=19)
    private long bookingId;
    @Column(name="user_kyc_id", precision=19)
    private long userKycId;
    
    private String consent;

    /** Default constructor. */
    public BookingKyc() {
        super();
    }

    /**
     * Access method for refId.
     *
     * @return the current value of refId
     */
    public String getRefId() {
        return refId;
    }

    /**
     * Setter method for refId.
     *
     * @param aRefId the new value for refId
     */
    public void setRefId(String aRefId) {
        refId = aRefId;
    }

    /**
     * Access method for refType.
     *
     * @return the current value of refType
     */
    public String getRefType() {
        return refType;
    }

    /**
     * Setter method for refType.
     *
     * @param aRefType the new value for refType
     */
    public void setRefType(String aRefType) {
        refType = aRefType;
    }

    /**
     * Access method for bookingId.
     *
     * @return the current value of bookingId
     */
    public long getBookingId() {
        return bookingId;
    }

    /**
     * Setter method for bookingId.
     *
     * @param aBookingId the new value for bookingId
     */
    public void setBookingId(long aBookingId) {
        bookingId = aBookingId;
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(long aId) {
        id = aId;
    }

    /**
     * Access method for userKycId.
     *
     * @return the current value of userKycId
     */
    public long getUserKycId() {
        return userKycId;
    }

    /**
     * Setter method for userKycId.
     *
     * @param aUserKycId the new value for userKycId
     */
    public void setUserKycId(long aUserKycId) {
        userKycId = aUserKycId;
    }

	public String getConsent() {
		return consent;
	}

	public void setConsent(String consent) {
		this.consent = consent;
	}

}
