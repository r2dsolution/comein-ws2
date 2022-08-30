// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="states")
public class States implements Serializable {


	private static final long serialVersionUID = 1L;
	
    /** Primary key. */
    protected static final String PK = "id";


    @Id
    @Column(unique=true, nullable=false, precision=19)
    private long id;
    @Column(nullable=false, length=255)
    private String name;
    @Column(name="country_code", nullable=false, length=2)
    private String countryCode;
    @Column(name="fips_code", length=255)
    private String fipsCode;
    @Column(length=255)
    private String iso2;
    @Column(length=191)
    private String type;
    @Column(precision=10, scale=8)
    private BigDecimal latitude;
    @Column(precision=11, scale=8)
    private BigDecimal longitude;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;
    @Column(nullable=false, precision=5)
    private short flag;
    @Column(length=255)
    private String wikidataid;
    @ManyToOne(optional=false)
    @JoinColumn(name="country_id", nullable=false)
    private Countries countries;

    /** Default constructor. */
    public States() {
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
     * Access method for name.
     *
     * @return the current value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param aName the new value for name
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Access method for countryCode.
     *
     * @return the current value of countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Setter method for countryCode.
     *
     * @param aCountryCode the new value for countryCode
     */
    public void setCountryCode(String aCountryCode) {
        countryCode = aCountryCode;
    }

    /**
     * Access method for fipsCode.
     *
     * @return the current value of fipsCode
     */
    public String getFipsCode() {
        return fipsCode;
    }

    /**
     * Setter method for fipsCode.
     *
     * @param aFipsCode the new value for fipsCode
     */
    public void setFipsCode(String aFipsCode) {
        fipsCode = aFipsCode;
    }

    /**
     * Access method for iso2.
     *
     * @return the current value of iso2
     */
    public String getIso2() {
        return iso2;
    }

    /**
     * Setter method for iso2.
     *
     * @param aIso2 the new value for iso2
     */
    public void setIso2(String aIso2) {
        iso2 = aIso2;
    }

    /**
     * Access method for type.
     *
     * @return the current value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for type.
     *
     * @param aType the new value for type
     */
    public void setType(String aType) {
        type = aType;
    }

    /**
     * Access method for latitude.
     *
     * @return the current value of latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * Setter method for latitude.
     *
     * @param aLatitude the new value for latitude
     */
    public void setLatitude(BigDecimal aLatitude) {
        latitude = aLatitude;
    }

    /**
     * Access method for longitude.
     *
     * @return the current value of longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * Setter method for longitude.
     *
     * @param aLongitude the new value for longitude
     */
    public void setLongitude(BigDecimal aLongitude) {
        longitude = aLongitude;
    }

    /**
     * Access method for createdAt.
     *
     * @return the current value of createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter method for createdAt.
     *
     * @param aCreatedAt the new value for createdAt
     */
    public void setCreatedAt(LocalDateTime aCreatedAt) {
        createdAt = aCreatedAt;
    }

    /**
     * Access method for updatedAt.
     *
     * @return the current value of updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Setter method for updatedAt.
     *
     * @param aUpdatedAt the new value for updatedAt
     */
    public void setUpdatedAt(LocalDateTime aUpdatedAt) {
        updatedAt = aUpdatedAt;
    }

    /**
     * Access method for flag.
     *
     * @return the current value of flag
     */
    public short getFlag() {
        return flag;
    }

    /**
     * Setter method for flag.
     *
     * @param aFlag the new value for flag
     */
    public void setFlag(short aFlag) {
        flag = aFlag;
    }

    /**
     * Access method for wikidataid.
     *
     * @return the current value of wikidataid
     */
    public String getWikidataid() {
        return wikidataid;
    }

    /**
     * Setter method for wikidataid.
     *
     * @param aWikidataid the new value for wikidataid
     */
    public void setWikidataid(String aWikidataid) {
        wikidataid = aWikidataid;
    }

    /**
     * Access method for countries.
     *
     * @return the current value of countries
     */
    public Countries getCountries() {
        return countries;
    }

    /**
     * Setter method for countries.
     *
     * @param aCountries the new value for countries
     */
    public void setCountries(Countries aCountries) {
        countries = aCountries;
    }

    /**
     * Compares the key for this instance with another States.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class States and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof States)) {
            return false;
        }
        States that = (States) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another States.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof States)) return false;
        return this.equalKeys(other) && ((States)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[States |");
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
