// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="booking_info")
public class BookingInfo implements Serializable {

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
    @Column(name="hotel_id")
    private Long hotelId;
    @Column(name="booking_no", nullable=false)
    private String bookingNo;
    @Column(name="room_name", nullable=false)
    private String roomName;
    @Column(name="room_desc")
    private String roomDesc;
    @Column(nullable=false)
    private LocalDate checkin;
    private LocalDate checkout;
    @Column(name="visitor_adult", nullable=false, precision=19)
    private long visitorAdult;
    @Column(name="visitor_child", precision=19)
    private Long visitorChild;
    private String status;
    @Column(name="created_date", nullable=false)
    private LocalDateTime createdDate;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="updated_date")
    private LocalDateTime updatedDate;
    @Column(name="updated_by")
    private String updatedBy;
    @Column(name="owner_id")
    private String ownerId;
    @Column(name="ref_name")
    private String refName;
    @Column(name="booking_date")
    private LocalDate bookingDate;
    
    @Column(name="ota_ref_email")
	private String otaRefEmail;
    @Column(name="ota_ref_contact")
	private String otaRefContact;
    @Column(name="ota_booking_id")
	private Long otaBookingId;
    @Column(name="ota_cancel_id")
	private Long otaCancelId;
	private BigDecimal price;
    
    /** Default constructor. */
    public BookingInfo() {
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
     * Access method for bookingNo.
     *
     * @return the current value of bookingNo
     */
    public String getBookingNo() {
        return bookingNo;
    }

    /**
     * Setter method for bookingNo.
     *
     * @param aBookingNo the new value for bookingNo
     */
    public void setBookingNo(String aBookingNo) {
        bookingNo = aBookingNo;
    }

    /**
     * Access method for roomName.
     *
     * @return the current value of roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Setter method for roomName.
     *
     * @param aRoomName the new value for roomName
     */
    public void setRoomName(String aRoomName) {
        roomName = aRoomName;
    }

    /**
     * Access method for roomDesc.
     *
     * @return the current value of roomDesc
     */
    public String getRoomDesc() {
        return roomDesc;
    }

    /**
     * Setter method for roomDesc.
     *
     * @param aRoomDesc the new value for roomDesc
     */
    public void setRoomDesc(String aRoomDesc) {
        roomDesc = aRoomDesc;
    }

    /**
     * Access method for checkin.
     *
     * @return the current value of checkin
     */
    public LocalDate getCheckin() {
        return checkin;
    }

    /**
     * Setter method for checkin.
     *
     * @param aCheckin the new value for checkin
     */
    public void setCheckin(LocalDate aCheckin) {
        checkin = aCheckin;
    }

    /**
     * Access method for checkout.
     *
     * @return the current value of checkout
     */
    public LocalDate getCheckout() {
        return checkout;
    }

    /**
     * Setter method for checkout.
     *
     * @param aCheckout the new value for checkout
     */
    public void setCheckout(LocalDate aCheckout) {
        checkout = aCheckout;
    }

    /**
     * Access method for visitorAdult.
     *
     * @return the current value of visitorAdult
     */
    public long getVisitorAdult() {
        return visitorAdult;
    }

    /**
     * Setter method for visitorAdult.
     *
     * @param aVisitorAdult the new value for visitorAdult
     */
    public void setVisitorAdult(long aVisitorAdult) {
        visitorAdult = aVisitorAdult;
    }

    /**
     * Access method for visitorChild.
     *
     * @return the current value of visitorChild
     */
    public Long getVisitorChild() {
        return visitorChild;
    }

    /**
     * Setter method for visitorChild.
     *
     * @param aVisitorChild the new value for visitorChild
     */
    public void setVisitorChild(Long aVisitorChild) {
        visitorChild = aVisitorChild;
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
     * Compares the key for this instance with another BookingInfo.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class BookingInfo and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof BookingInfo)) {
            return false;
        }
        BookingInfo that = (BookingInfo) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another BookingInfo.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BookingInfo)) return false;
        return this.equalKeys(other) && ((BookingInfo)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[BookingInfo |");
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

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getOtaRefEmail() {
		return otaRefEmail;
	}

	public void setOtaRefEmail(String otaRefEmail) {
		this.otaRefEmail = otaRefEmail;
	}

	public String getOtaRefContact() {
		return otaRefContact;
	}

	public void setOtaRefContact(String otaRefContact) {
		this.otaRefContact = otaRefContact;
	}

	public Long getOtaBookingId() {
		return otaBookingId;
	}

	public void setOtaBookingId(Long otaBookingId) {
		this.otaBookingId = otaBookingId;
	}

	public Long getOtaCancelId() {
		return otaCancelId;
	}

	public void setOtaCancelId(Long otaCancelId) {
		this.otaCancelId = otaCancelId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
