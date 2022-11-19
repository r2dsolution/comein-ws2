// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="payment_condition_default")
public class PaymentConditionDefault implements Serializable {

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
    @Column(name="payable_day", nullable=false, precision=10)
    private int payableDay;
    @Column(name="payable_tour_day", nullable=false, precision=10)
    private int payableTourDay;

    /** Default constructor. */
    public PaymentConditionDefault() {
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
     * Access method for payableDay.
     *
     * @return the current value of payableDay
     */
    public int getPayableDay() {
        return payableDay;
    }

    /**
     * Setter method for payableDay.
     *
     * @param aPayableDay the new value for payableDay
     */
    public void setPayableDay(int aPayableDay) {
        payableDay = aPayableDay;
    }

    /**
     * Access method for payableTourDay.
     *
     * @return the current value of payableTourDay
     */
    public int getPayableTourDay() {
        return payableTourDay;
    }

    /**
     * Setter method for payableTourDay.
     *
     * @param aPayableTourDay the new value for payableTourDay
     */
    public void setPayableTourDay(int aPayableTourDay) {
        payableTourDay = aPayableTourDay;
    }

    /**
     * Compares the key for this instance with another PaymentConditionDefault.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class PaymentConditionDefault and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof PaymentConditionDefault)) {
            return false;
        }
        PaymentConditionDefault that = (PaymentConditionDefault) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another PaymentConditionDefault.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PaymentConditionDefault)) return false;
        return this.equalKeys(other) && ((PaymentConditionDefault)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[PaymentConditionDefault |");
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
