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

@Entity(name="topup_rate_default")
public class TopupRateDefault implements Serializable {

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
    @Column(name="min_period", nullable=false, precision=131089)
    private BigDecimal minPeriod;
    @Column(name="max_period", nullable=false, precision=131089)
    private BigDecimal maxPeriod;
    @Column(name="topup_rate", nullable=false, precision=131089)
    private BigDecimal topupRate;
    @Column(name="comein_rate", nullable=false, precision=131089)
    private BigDecimal comeinRate;
    @Column(name="hotel_rate", nullable=false, precision=131089)
    private BigDecimal hotelRate;
    @Column(name="updated_date")
    private LocalDateTime updatedDate;
    
    /** Default constructor. */
    public TopupRateDefault() {
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
     * Access method for minPeriod.
     *
     * @return the current value of minPeriod
     */
    public BigDecimal getMinPeriod() {
        return minPeriod;
    }

    /**
     * Setter method for minPeriod.
     *
     * @param aMinPeriod the new value for minPeriod
     */
    public void setMinPeriod(BigDecimal aMinPeriod) {
        minPeriod = aMinPeriod;
    }

    /**
     * Access method for maxPeriod.
     *
     * @return the current value of maxPeriod
     */
    public BigDecimal getMaxPeriod() {
        return maxPeriod;
    }

    /**
     * Setter method for maxPeriod.
     *
     * @param aMaxPeriod the new value for maxPeriod
     */
    public void setMaxPeriod(BigDecimal aMaxPeriod) {
        maxPeriod = aMaxPeriod;
    }

    /**
     * Access method for topupRate.
     *
     * @return the current value of topupRate
     */
    public BigDecimal getTopupRate() {
        return topupRate;
    }

    /**
     * Setter method for topupRate.
     *
     * @param aTopupRate the new value for topupRate
     */
    public void setTopupRate(BigDecimal aTopupRate) {
        topupRate = aTopupRate;
    }

    /**
     * Access method for comeinRate.
     *
     * @return the current value of comeinRate
     */
    public BigDecimal getComeinRate() {
        return comeinRate;
    }

    /**
     * Setter method for comeinRate.
     *
     * @param aComeinRate the new value for comeinRate
     */
    public void setComeinRate(BigDecimal aComeinRate) {
        comeinRate = aComeinRate;
    }

    /**
     * Access method for hotelRate.
     *
     * @return the current value of hotelRate
     */
    public BigDecimal getHotelRate() {
        return hotelRate;
    }

    /**
     * Setter method for hotelRate.
     *
     * @param aHotelRate the new value for hotelRate
     */
    public void setHotelRate(BigDecimal aHotelRate) {
        hotelRate = aHotelRate;
    }

    /**
     * Compares the key for this instance with another TopupRateDefault.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TopupRateDefault and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TopupRateDefault)) {
            return false;
        }
        TopupRateDefault that = (TopupRateDefault) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TopupRateDefault.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TopupRateDefault)) return false;
        return this.equalKeys(other) && ((TopupRateDefault)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[TopupRateDefault |");
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

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
