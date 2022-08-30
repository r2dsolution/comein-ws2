// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="countries")
public class Countries implements Serializable {


	private static final long serialVersionUID = 1L;
	
    /** Primary key. */
    protected static final String PK = "id";


    @Id
    @Column(unique=true, nullable=false, precision=19)
    private long id;
    @Column(nullable=false, length=100)
    private String name;
    @Column(length=3)
    private String iso3;
    @Column(name="numeric_code", length=3)
    private String numericCode;
    @Column(length=2)
    private String iso2;
    @Column(length=255)
    private String phonecode;
    @Column(length=255)
    private String capital;
    @Column(length=255)
    private String currency;
    @Column(name="currency_name", length=255)
    private String currencyName;
    @Column(name="currency_symbol", length=255)
    private String currencySymbol;
    @Column(length=255)
    private String tld;
    @Column(name="native", length=255)
    private String native_;
    @Column(length=255)
    private String region;
    @Column(length=255)
    private String subregion;
    private String timezones;
    private String translations;
    @Column(precision=10, scale=8)
    private BigDecimal latitude;
    @Column(precision=11, scale=8)
    private BigDecimal longitude;
    @Column(length=191)
    private String emoji;
    @Column(length=191)
    private String emojiu;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;
    @Column(nullable=false, precision=5)
    private short flag;
    @Column(length=255)
    private String wikidataid;
    @OneToMany(mappedBy="countries")
    private Set<States> states;

    /** Default constructor. */
    public Countries() {
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
     * Access method for iso3.
     *
     * @return the current value of iso3
     */
    public String getIso3() {
        return iso3;
    }

    /**
     * Setter method for iso3.
     *
     * @param aIso3 the new value for iso3
     */
    public void setIso3(String aIso3) {
        iso3 = aIso3;
    }

    /**
     * Access method for numericCode.
     *
     * @return the current value of numericCode
     */
    public String getNumericCode() {
        return numericCode;
    }

    /**
     * Setter method for numericCode.
     *
     * @param aNumericCode the new value for numericCode
     */
    public void setNumericCode(String aNumericCode) {
        numericCode = aNumericCode;
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
     * Access method for phonecode.
     *
     * @return the current value of phonecode
     */
    public String getPhonecode() {
        return phonecode;
    }

    /**
     * Setter method for phonecode.
     *
     * @param aPhonecode the new value for phonecode
     */
    public void setPhonecode(String aPhonecode) {
        phonecode = aPhonecode;
    }

    /**
     * Access method for capital.
     *
     * @return the current value of capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * Setter method for capital.
     *
     * @param aCapital the new value for capital
     */
    public void setCapital(String aCapital) {
        capital = aCapital;
    }

    /**
     * Access method for currency.
     *
     * @return the current value of currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Setter method for currency.
     *
     * @param aCurrency the new value for currency
     */
    public void setCurrency(String aCurrency) {
        currency = aCurrency;
    }

    /**
     * Access method for currencyName.
     *
     * @return the current value of currencyName
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Setter method for currencyName.
     *
     * @param aCurrencyName the new value for currencyName
     */
    public void setCurrencyName(String aCurrencyName) {
        currencyName = aCurrencyName;
    }

    /**
     * Access method for currencySymbol.
     *
     * @return the current value of currencySymbol
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * Setter method for currencySymbol.
     *
     * @param aCurrencySymbol the new value for currencySymbol
     */
    public void setCurrencySymbol(String aCurrencySymbol) {
        currencySymbol = aCurrencySymbol;
    }

    /**
     * Access method for tld.
     *
     * @return the current value of tld
     */
    public String getTld() {
        return tld;
    }

    /**
     * Setter method for tld.
     *
     * @param aTld the new value for tld
     */
    public void setTld(String aTld) {
        tld = aTld;
    }

    /**
     * Access method for native_.
     *
     * @return the current value of native_
     */
    public String getNative_() {
        return native_;
    }

    /**
     * Setter method for native_.
     *
     * @param aNative_ the new value for native_
     */
    public void setNative_(String aNative_) {
        native_ = aNative_;
    }

    /**
     * Access method for region.
     *
     * @return the current value of region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Setter method for region.
     *
     * @param aRegion the new value for region
     */
    public void setRegion(String aRegion) {
        region = aRegion;
    }

    /**
     * Access method for subregion.
     *
     * @return the current value of subregion
     */
    public String getSubregion() {
        return subregion;
    }

    /**
     * Setter method for subregion.
     *
     * @param aSubregion the new value for subregion
     */
    public void setSubregion(String aSubregion) {
        subregion = aSubregion;
    }

    /**
     * Access method for timezones.
     *
     * @return the current value of timezones
     */
    public String getTimezones() {
        return timezones;
    }

    /**
     * Setter method for timezones.
     *
     * @param aTimezones the new value for timezones
     */
    public void setTimezones(String aTimezones) {
        timezones = aTimezones;
    }

    /**
     * Access method for translations.
     *
     * @return the current value of translations
     */
    public String getTranslations() {
        return translations;
    }

    /**
     * Setter method for translations.
     *
     * @param aTranslations the new value for translations
     */
    public void setTranslations(String aTranslations) {
        translations = aTranslations;
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
     * Access method for emoji.
     *
     * @return the current value of emoji
     */
    public String getEmoji() {
        return emoji;
    }

    /**
     * Setter method for emoji.
     *
     * @param aEmoji the new value for emoji
     */
    public void setEmoji(String aEmoji) {
        emoji = aEmoji;
    }

    /**
     * Access method for emojiu.
     *
     * @return the current value of emojiu
     */
    public String getEmojiu() {
        return emojiu;
    }

    /**
     * Setter method for emojiu.
     *
     * @param aEmojiu the new value for emojiu
     */
    public void setEmojiu(String aEmojiu) {
        emojiu = aEmojiu;
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
     * Access method for states.
     *
     * @return the current value of states
     */
    public Set<States> getStates() {
        return states;
    }

    /**
     * Setter method for states.
     *
     * @param aStates the new value for states
     */
    public void setStates(Set<States> aStates) {
        states = aStates;
    }

    /**
     * Compares the key for this instance with another Countries.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class Countries and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof Countries)) {
            return false;
        }
        Countries that = (Countries) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another Countries.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Countries)) return false;
        return this.equalKeys(other) && ((Countries)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[Countries |");
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
