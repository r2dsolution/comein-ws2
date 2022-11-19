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

@Entity(name="tour_province")
public class TourProvince implements Serializable {

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
    @Column(nullable=false, length=50)
    private String province;

    /** Default constructor. */
    public TourProvince() {
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
     * Access method for province.
     *
     * @return the current value of province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Setter method for province.
     *
     * @param aProvince the new value for province
     */
    public void setProvince(String aProvince) {
        province = aProvince;
    }

    /**
     * Compares the key for this instance with another TourProvince.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TourProvince and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TourProvince)) {
            return false;
        }
        TourProvince that = (TourProvince) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TourProvince.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TourProvince)) return false;
        return this.equalKeys(other) && ((TourProvince)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[TourProvince |");
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
