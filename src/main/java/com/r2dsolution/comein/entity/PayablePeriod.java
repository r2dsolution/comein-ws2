// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="payable_period")
public class PayablePeriod implements Serializable {

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
    @Column(name="period_type", nullable=false, length=20)
    private String periodType;
    @Column(name="date_from", nullable=false)
    private LocalDate dateFrom;
    @Column(name="date_to", nullable=false)
    private LocalDate dateTo;
    @Column(name="period_desc", nullable=false, length=100)
    private String periodDesc;
    @Column(name="pay_id", nullable=false)
    private long payId;
    private String status;
    private String note;

    /** Default constructor. */
    public PayablePeriod() {
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
     * Access method for periodType.
     *
     * @return the current value of periodType
     */
    public String getPeriodType() {
        return periodType;
    }

    /**
     * Setter method for periodType.
     *
     * @param aPeriodType the new value for periodType
     */
    public void setPeriodType(String aPeriodType) {
        periodType = aPeriodType;
    }

    /**
     * Access method for dateFrom.
     *
     * @return the current value of dateFrom
     */
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    /**
     * Setter method for dateFrom.
     *
     * @param aDateFrom the new value for dateFrom
     */
    public void setDateFrom(LocalDate aDateFrom) {
        dateFrom = aDateFrom;
    }

    /**
     * Access method for dateTo.
     *
     * @return the current value of dateTo
     */
    public LocalDate getDateTo() {
        return dateTo;
    }

    /**
     * Setter method for dateTo.
     *
     * @param aDateTo the new value for dateTo
     */
    public void setDateTo(LocalDate aDateTo) {
        dateTo = aDateTo;
    }

    /**
     * Access method for periodDesc.
     *
     * @return the current value of periodDesc
     */
    public String getPeriodDesc() {
        return periodDesc;
    }

    /**
     * Setter method for periodDesc.
     *
     * @param aPeriodDesc the new value for periodDesc
     */
    public void setPeriodDesc(String aPeriodDesc) {
        periodDesc = aPeriodDesc;
    }

    /**
     * Compares the key for this instance with another PayablePeriod.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class PayablePeriod and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof PayablePeriod)) {
            return false;
        }
        PayablePeriod that = (PayablePeriod) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another PayablePeriod.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PayablePeriod)) return false;
        return this.equalKeys(other) && ((PayablePeriod)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[PayablePeriod |");
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

	public long getPayId() {
		return payId;
	}

	public void setPayId(long payId) {
		this.payId = payId;
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

}
