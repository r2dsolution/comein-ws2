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
import javax.persistence.Version;

@Entity(name="ota_booking_feed")
public class OtaBookingFeed implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Version
    @Column(name="LOCK_FLAG")
    private Integer lockFlag;

    /**
     * Access method for the lockFlag property.
     *
     * @return the current value of the lockFlag property
     */
    public Integer getLockFlag() {
        return lockFlag;
    }

    /**
     * Sets the value of the lockFlag property.
     *
     * @param aLockFlag the new value of the lockFlag property
     */
    public void setLockFlag(Integer aLockFlag) {
        lockFlag = aLockFlag;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=19)
    private long id;
    @Column(name="feed_date", nullable=false)
    private LocalDate feedDate;
    @Column(name="booking_no", nullable=false)
    private String bookingNo;
    @Column(name="booking_date", nullable=false)
    private LocalDate bookingDate;
    @Column(name="ota_name", nullable=false)
    private String otaName;
    @Column(name="created_date", nullable=false)
    private LocalDateTime createdDate;
    @Column(name="created_by")
    private String createdBy;

    /** Default constructor. */
    public OtaBookingFeed() {
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
     * Access method for feedDate.
     *
     * @return the current value of feedDate
     */
    public LocalDate getFeedDate() {
        return feedDate;
    }

    /**
     * Setter method for feedDate.
     *
     * @param aFeedDate the new value for feedDate
     */
    public void setFeedDate(LocalDate aFeedDate) {
        feedDate = aFeedDate;
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
     * Access method for otaName.
     *
     * @return the current value of otaName
     */
    public String getOtaName() {
        return otaName;
    }

    /**
     * Setter method for otaName.
     *
     * @param aOtaName the new value for otaName
     */
    public void setOtaName(String aOtaName) {
        otaName = aOtaName;
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
     * Compares the key for this instance with another OtaBookingFeed.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class OtaBookingFeed and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof OtaBookingFeed)) {
            return false;
        }
        OtaBookingFeed that = (OtaBookingFeed) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another OtaBookingFeed.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OtaBookingFeed)) return false;
        return this.equalKeys(other) && ((OtaBookingFeed)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[OtaBookingFeed |");
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

}
