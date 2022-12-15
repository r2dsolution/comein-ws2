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
import javax.persistence.Table;

@Entity
@Table(name="ota_booking")
public class OtaBooking implements Serializable {

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
    @Column(name="is_booking", nullable=false)
    private boolean isBooking;
    @Column(name="is_cancel", nullable=false)
    private boolean isCancel;
    @Column(name="template_logic", nullable=false, precision=19)
    private long templateLogic;
    @Column(name="first_name", length=100)
    private String firstName;
    @Column(name="last_name", length=100)
    private String lastName;
    @Column(name="checkin_date", nullable=false)
    private LocalDate checkinDate;
    @Column(name="checkout_date", nullable=false)
    private LocalDate checkoutDate;
    @Column(nullable=false, length=100)
    private String email;
    @Column(name="contact_no", length=100)
    private String contactNo;
    @Column(length=50)
    private String nationality;
    @Column(name="room_night", precision=19)
    private long roomNight;
    @Column(name="room_type", length=100)
    private String roomType;
    @Column(name="booking_number", length=100)
    private String bookingNumber;
    @Column(precision=131089)
    private BigDecimal price;
    @Column(nullable=false, precision=19)
    private long adult;
    @Column(precision=19)
    private Long child;
    @Column(name="hotel_name", nullable=false, length=100)
    private String hotelName;
    @Column(name="date_receive", nullable=false)
    private LocalDateTime dateReceive;
    @Column(name="created_date", nullable=false)
    private LocalDateTime createdDate;
    @Column(name="feed_date", nullable=false)
    private LocalDate feedDate;
    private String status;
    private String remark;

    /** Default constructor. */
    public OtaBooking() {
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
     * Access method for isBooking.
     *
     * @return true if and only if isBooking is currently true
     */
    public boolean getIsBooking() {
        return isBooking;
    }

    /**
     * Setter method for isBooking.
     *
     * @param aIsBooking the new value for isBooking
     */
    public void setIsBooking(boolean aIsBooking) {
        isBooking = aIsBooking;
    }

    /**
     * Access method for isCancel.
     *
     * @return true if and only if isCancel is currently true
     */
    public boolean getIsCancel() {
        return isCancel;
    }

    /**
     * Setter method for isCancel.
     *
     * @param aIsCancel the new value for isCancel
     */
    public void setIsCancel(boolean aIsCancel) {
        isCancel = aIsCancel;
    }

    /**
     * Access method for templateLogic.
     *
     * @return the current value of templateLogic
     */
    public long getTemplateLogic() {
        return templateLogic;
    }

    /**
     * Setter method for templateLogic.
     *
     * @param aTemplateLogic the new value for templateLogic
     */
    public void setTemplateLogic(long aTemplateLogic) {
        templateLogic = aTemplateLogic;
    }

    /**
     * Access method for firstName.
     *
     * @return the current value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter method for firstName.
     *
     * @param aFirstName the new value for firstName
     */
    public void setFirstName(String aFirstName) {
        firstName = aFirstName;
    }

    /**
     * Access method for lastName.
     *
     * @return the current value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter method for lastName.
     *
     * @param aLastName the new value for lastName
     */
    public void setLastName(String aLastName) {
        lastName = aLastName;
    }

    /**
     * Access method for checkinDate.
     *
     * @return the current value of checkinDate
     */
    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    /**
     * Setter method for checkinDate.
     *
     * @param aCheckinDate the new value for checkinDate
     */
    public void setCheckinDate(LocalDate aCheckinDate) {
        checkinDate = aCheckinDate;
    }

    /**
     * Access method for checkoutDate.
     *
     * @return the current value of checkoutDate
     */
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * Setter method for checkoutDate.
     *
     * @param aCheckoutDate the new value for checkoutDate
     */
    public void setCheckoutDate(LocalDate aCheckoutDate) {
        checkoutDate = aCheckoutDate;
    }

    /**
     * Access method for email.
     *
     * @return the current value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for email.
     *
     * @param aEmail the new value for email
     */
    public void setEmail(String aEmail) {
        email = aEmail;
    }

    /**
     * Access method for contactNo.
     *
     * @return the current value of contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * Setter method for contactNo.
     *
     * @param aContactNo the new value for contactNo
     */
    public void setContactNo(String aContactNo) {
        contactNo = aContactNo;
    }

    /**
     * Access method for nationality.
     *
     * @return the current value of nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Setter method for nationality.
     *
     * @param aNationality the new value for nationality
     */
    public void setNationality(String aNationality) {
        nationality = aNationality;
    }

    /**
     * Access method for roomNight.
     *
     * @return the current value of roomNight
     */
    public long getRoomNight() {
        return roomNight;
    }

    /**
     * Setter method for roomNight.
     *
     * @param aRoomNight the new value for roomNight
     */
    public void setRoomNight(long aRoomNight) {
        roomNight = aRoomNight;
    }

    /**
     * Access method for roomType.
     *
     * @return the current value of roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Setter method for roomType.
     *
     * @param aRoomType the new value for roomType
     */
    public void setRoomType(String aRoomType) {
        roomType = aRoomType;
    }

    /**
     * Access method for bookingNumber.
     *
     * @return the current value of bookingNumber
     */
    public String getBookingNumber() {
        return bookingNumber;
    }

    /**
     * Setter method for bookingNumber.
     *
     * @param aBookingNumber the new value for bookingNumber
     */
    public void setBookingNumber(String aBookingNumber) {
        bookingNumber = aBookingNumber;
    }

    /**
     * Access method for price.
     *
     * @return the current value of price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Setter method for price.
     *
     * @param aPrice the new value for price
     */
    public void setPrice(BigDecimal aPrice) {
        price = aPrice;
    }

    /**
     * Access method for adult.
     *
     * @return the current value of adult
     */
    public long getAdult() {
        return adult;
    }

    /**
     * Setter method for adult.
     *
     * @param aAdult the new value for adult
     */
    public void setAdult(long aAdult) {
        adult = aAdult;
    }

    /**
     * Access method for child.
     *
     * @return the current value of child
     */
    public Long getChild() {
        return child;
    }

    /**
     * Setter method for child.
     *
     * @param aChild the new value for child
     */
    public void setChild(Long aChild) {
        child = aChild;
    }

    /**
     * Access method for hotelName.
     *
     * @return the current value of hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Setter method for hotelName.
     *
     * @param aHotelName the new value for hotelName
     */
    public void setHotelName(String aHotelName) {
        hotelName = aHotelName;
    }

    /**
     * Access method for dateReceive.
     *
     * @return the current value of dateReceive
     */
    public LocalDateTime getDateReceive() {
        return dateReceive;
    }

    /**
     * Setter method for dateReceive.
     *
     * @param aDateReceive the new value for dateReceive
     */
    public void setDateReceive(LocalDateTime aDateReceive) {
        dateReceive = aDateReceive;
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
     * Compares the key for this instance with another OtaBooking.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class OtaBooking and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof OtaBooking)) {
            return false;
        }
        OtaBooking that = (OtaBooking) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another OtaBooking.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OtaBooking)) return false;
        return this.equalKeys(other) && ((OtaBooking)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[OtaBooking |");
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public LocalDate getFeedDate() {
		return feedDate;
	}

	public void setFeedDate(LocalDate feedDate) {
		this.feedDate = feedDate;
	}

}
