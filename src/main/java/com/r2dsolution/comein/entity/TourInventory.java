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
@Table(name="tour_inventory")
public class TourInventory implements Serializable {

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
    @Column(name="tour_id", nullable=false, precision=19)
    private long tourId;
    @Column(name="tour_date", nullable=false)
    private LocalDate tourDate;
    @Column(nullable=false, precision=19)
    private long total;
    @Column(name="adult_rate", nullable=false, precision=131089)
    private BigDecimal adultRate;
    @Column(name="child_rate", precision=131089)
    private BigDecimal childRate;
    @Column(length=1)
    private String cancelable;
    @Column(name="cancel_before", precision=10)
    private int cancelBefore;
    private String status;
    @Column(name="created_date", nullable=false)
    private LocalDateTime createdDate;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="updated_date")
    private LocalDateTime updatedDate;
    @Column(name="updated_by")
    private String updatedBy;

    /** Default constructor. */
    public TourInventory() {
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
     * Access method for tourId.
     *
     * @return the current value of tourId
     */
    public long getTourId() {
        return tourId;
    }

    /**
     * Setter method for tourId.
     *
     * @param aTourId the new value for tourId
     */
    public void setTourId(long aTourId) {
        tourId = aTourId;
    }

    /**
     * Access method for tourDate.
     *
     * @return the current value of tourDate
     */
    public LocalDate getTourDate() {
        return tourDate;
    }

    /**
     * Setter method for tourDate.
     *
     * @param aTourDate the new value for tourDate
     */
    public void setTourDate(LocalDate aTourDate) {
        tourDate = aTourDate;
    }

    /**
     * Access method for total.
     *
     * @return the current value of total
     */
    public long getTotal() {
        return total;
    }

    /**
     * Setter method for total.
     *
     * @param aTotal the new value for total
     */
    public void setTotal(long aTotal) {
        total = aTotal;
    }

    /**
     * Access method for adultRate.
     *
     * @return the current value of adultRate
     */
    public BigDecimal getAdultRate() {
        return adultRate;
    }

    /**
     * Setter method for adultRate.
     *
     * @param aAdultRate the new value for adultRate
     */
    public void setAdultRate(BigDecimal aAdultRate) {
        adultRate = aAdultRate;
    }

    /**
     * Access method for childRate.
     *
     * @return the current value of childRate
     */
    public BigDecimal getChildRate() {
        return childRate;
    }

    /**
     * Setter method for childRate.
     *
     * @param aChildRate the new value for childRate
     */
    public void setChildRate(BigDecimal aChildRate) {
        childRate = aChildRate;
    }

    /**
     * Access method for cancelable.
     *
     * @return the current value of cancelable
     */
    public String getCancelable() {
        return cancelable;
    }

    /**
     * Setter method for cancelable.
     *
     * @param aCancelable the new value for cancelable
     */
    public void setCancelable(String aCancelable) {
        cancelable = aCancelable;
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
     * Compares the key for this instance with another TourInventory.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TourInventory and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TourInventory)) {
            return false;
        }
        TourInventory that = (TourInventory) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TourInventory.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TourInventory)) return false;
        return this.equalKeys(other) && ((TourInventory)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[TourInventory |");
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

	public int getCancelBefore() {
		return cancelBefore;
	}

	public void setCancelBefore(int cancelBefore) {
		this.cancelBefore = cancelBefore;
	}

}
