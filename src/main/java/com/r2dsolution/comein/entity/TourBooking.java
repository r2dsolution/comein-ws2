// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tour_booking")
public class TourBooking implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="ticket_id", nullable=false, precision=19)
    private long ticketId;
    @Column(name="booking_code", nullable=false)
    private String bookingCode;
    @Column(name="owner_id", nullable=false)
    private String ownerId;
    @Column(name="reference_name")
    private String referenceName;
    @Column(name="location_pickup")
    private String locationPickup;
    @Column(name="booking_date", nullable=false)
    private LocalDate bookingDate;
    @Column(name="booking_type")
    private String bookingType;
    @Column(name="payment_method", nullable=false)
    private String paymentMethod;
    private String remark;
    private String status;
    @Column(name="created_date", nullable=false)
    private LocalDateTime createdDate;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="updated_date")
    private LocalDateTime updatedDate;
    @Column(name="updated_by")
    private String updatedBy;
    @Column(name="tour_name")
    private String tourName;
    @Column(name="tour_date", nullable=false)
    private LocalDate tourDate;
    
    /** Default constructor. */
    public TourBooking() {
        super();
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
     * Access method for bookingCode.
     *
     * @return the current value of bookingCode
     */
    public String getBookingCode() {
        return bookingCode;
    }

    /**
     * Setter method for bookingCode.
     *
     * @param aBookingCode the new value for bookingCode
     */
    public void setBookingCode(String aBookingCode) {
        bookingCode = aBookingCode;
    }

    /**
     * Access method for ownerId.
     *
     * @return the current value of ownerId
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Setter method for ownerId.
     *
     * @param aOwnerId the new value for ownerId
     */
    public void setOwnerId(String aOwnerId) {
        ownerId = aOwnerId;
    }

    /**
     * Access method for referenceName.
     *
     * @return the current value of referenceName
     */
    public String getReferenceName() {
        return referenceName;
    }

    /**
     * Setter method for referenceName.
     *
     * @param aReferenceName the new value for referenceName
     */
    public void setReferenceName(String aReferenceName) {
        referenceName = aReferenceName;
    }

    /**
     * Access method for locationPickup.
     *
     * @return the current value of locationPickup
     */
    public String getLocationPickup() {
        return locationPickup;
    }

    /**
     * Setter method for locationPickup.
     *
     * @param aLocationPickup the new value for locationPickup
     */
    public void setLocationPickup(String aLocationPickup) {
        locationPickup = aLocationPickup;
    }

    /**
     * Access method for bookingDate.
     *
     * @return the current value of bookingDate
     */
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    /**
     * Setter method for bookingDate.
     *
     * @param aBookingDate the new value for bookingDate
     */
    public void setBookingDate(LocalDate aBookingDate) {
        bookingDate = aBookingDate;
    }

    /**
     * Access method for paymentMethod.
     *
     * @return the current value of paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Setter method for paymentMethod.
     *
     * @param aPaymentMethod the new value for paymentMethod
     */
    public void setPaymentMethod(String aPaymentMethod) {
        paymentMethod = aPaymentMethod;
    }

    /**
     * Access method for remark.
     *
     * @return the current value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter method for remark.
     *
     * @param aRemark the new value for remark
     */
    public void setRemark(String aRemark) {
        remark = aRemark;
    }

    /**
     * Access method for status.
     *
     * @return the current value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for status.
     *
     * @param aStatus the new value for status
     */
    public void setStatus(String aStatus) {
        status = aStatus;
    }

    /**
     * Access method for createdDate.
     *
     * @return the current value of createdDate
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Setter method for createdDate.
     *
     * @param aCreatedDate the new value for createdDate
     */
    public void setCreatedDate(LocalDateTime aCreatedDate) {
        createdDate = aCreatedDate;
    }

    /**
     * Access method for createdBy.
     *
     * @return the current value of createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter method for createdBy.
     *
     * @param aCreatedBy the new value for createdBy
     */
    public void setCreatedBy(String aCreatedBy) {
        createdBy = aCreatedBy;
    }

    /**
     * Access method for updatedDate.
     *
     * @return the current value of updatedDate
     */
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Setter method for updatedDate.
     *
     * @param aUpdatedDate the new value for updatedDate
     */
    public void setUpdatedDate(LocalDateTime aUpdatedDate) {
        updatedDate = aUpdatedDate;
    }

    /**
     * Access method for updatedBy.
     *
     * @return the current value of updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Setter method for updatedBy.
     *
     * @param aUpdatedBy the new value for updatedBy
     */
    public void setUpdatedBy(String aUpdatedBy) {
        updatedBy = aUpdatedBy;
    }

    /**
     * Compares the key for this instance with another TourBooking.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TourBooking and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TourBooking)) {
            return false;
        }
        TourBooking that = (TourBooking) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TourBooking.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TourBooking)) return false;
        return this.equalKeys(other) && ((TourBooking)other).equalKeys(this);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        int i;
        int result = 17;
        i = (int)(getId() ^ (getId()>>>32));
        result = 37*result + i;
        return result;
    }

    /**
     * Returns a debug-friendly String representation of this instance.
     *
     * @return String representation of this instance
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[TourBooking |");
        sb.append(" id=").append(getId());
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return all elements of the primary key.
     *
     * @return Map of key names to values
     */
    public Map<String, Object> getPrimaryKey() {
        Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
        ret.put("id", Long.valueOf(getId()));
        return ret;
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

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

}
