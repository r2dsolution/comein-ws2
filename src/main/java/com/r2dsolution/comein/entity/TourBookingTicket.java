// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tour_booking_ticket")
public class TourBookingTicket implements Serializable {

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
    @Column(name="booking_id", nullable=false, precision=19)
    private long bookingId;
    @Column(name="ticket_id", nullable=false, precision=19)
    private long ticketId;
    @Column(name="booking_type", length=5)
    private String bookingType;
    @Column(name="sell_value", precision=131089)
    private BigDecimal sellValue;
    @Column(name="created_date", nullable=false)
    private LocalDateTime createdDate;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="updated_date")
    private LocalDateTime updatedDate;
    @Column(name="updated_by")
    private String updatedBy;

    /** Default constructor. */
    public TourBookingTicket() {
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
     * Access method for ticketId.
     *
     * @return the current value of ticketId
     */
    public long getTicketId() {
        return ticketId;
    }

    /**
     * Setter method for ticketId.
     *
     * @param aTicketId the new value for ticketId
     */
    public void setTicketId(long aTicketId) {
        ticketId = aTicketId;
    }

    /**
     * Access method for bookingType.
     *
     * @return the current value of bookingType
     */
    public String getBookingType() {
        return bookingType;
    }

    /**
     * Setter method for bookingType.
     *
     * @param aBookingType the new value for bookingType
     */
    public void setBookingType(String aBookingType) {
        bookingType = aBookingType;
    }

    /**
     * Access method for sellValue.
     *
     * @return the current value of sellValue
     */
    public BigDecimal getSellValue() {
        return sellValue;
    }

    /**
     * Setter method for sellValue.
     *
     * @param aSellValue the new value for sellValue
     */
    public void setSellValue(BigDecimal aSellValue) {
        sellValue = aSellValue;
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
     * Compares the key for this instance with another TourBookingTicket.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TourBookingTicket and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TourBookingTicket)) {
            return false;
        }
        TourBookingTicket that = (TourBookingTicket) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TourBookingTicket.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TourBookingTicket)) return false;
        return this.equalKeys(other) && ((TourBookingTicket)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[TourBookingTicket |");
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
