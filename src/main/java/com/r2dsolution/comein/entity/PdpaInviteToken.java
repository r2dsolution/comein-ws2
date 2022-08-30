// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
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
@Table(name="pdpa_invite_token")
public class PdpaInviteToken implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, precision=10)
    private int id;
    @Column(name="comein_id", length=100)
    private String comeinId;
    @Column(name="expire_date")
    private LocalDateTime expireDate;
    @Column(name="max_used", precision=10)
    private int maxUsed;
    @Column(name="secret_code", length=100)
    private String secretCode;
    @Column(length=10)
    private String status;
    @Column(length=100)
    private String token;

    /** Default constructor. */
    public PdpaInviteToken() {
        super();
    }

    /**
     * Access method for id.
     *
     * @return the current value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id.
     *
     * @param aId the new value for id
     */
    public void setId(int aId) {
        id = aId;
    }

    /**
     * Access method for comeinId.
     *
     * @return the current value of comeinId
     */
    public String getComeinId() {
        return comeinId;
    }

    /**
     * Setter method for comeinId.
     *
     * @param aComeinId the new value for comeinId
     */
    public void setComeinId(String aComeinId) {
        comeinId = aComeinId;
    }

    /**
     * Access method for expireDate.
     *
     * @return the current value of expireDate
     */
    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    /**
     * Setter method for expireDate.
     *
     * @param aExpireDate the new value for expireDate
     */
    public void setExpireDate(LocalDateTime aExpireDate) {
        expireDate = aExpireDate;
    }

    /**
     * Access method for maxUsed.
     *
     * @return the current value of maxUsed
     */
    public int getMaxUsed() {
        return maxUsed;
    }

    /**
     * Setter method for maxUsed.
     *
     * @param aMaxUsed the new value for maxUsed
     */
    public void setMaxUsed(int aMaxUsed) {
        maxUsed = aMaxUsed;
    }

    /**
     * Access method for secretCode.
     *
     * @return the current value of secretCode
     */
    public String getSecretCode() {
        return secretCode;
    }

    /**
     * Setter method for secretCode.
     *
     * @param aSecretCode the new value for secretCode
     */
    public void setSecretCode(String aSecretCode) {
        secretCode = aSecretCode;
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
     * Access method for token.
     *
     * @return the current value of token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter method for token.
     *
     * @param aToken the new value for token
     */
    public void setToken(String aToken) {
        token = aToken;
    }

    /**
     * Compares the key for this instance with another PdpaInviteToken.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class PdpaInviteToken and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof PdpaInviteToken)) {
            return false;
        }
        PdpaInviteToken that = (PdpaInviteToken) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another PdpaInviteToken.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PdpaInviteToken)) return false;
        return this.equalKeys(other) && ((PdpaInviteToken)other).equalKeys(this);
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
        i = getId();
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
        StringBuffer sb = new StringBuffer("[PdpaInviteToken |");
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
        ret.put("id", Integer.valueOf(getId()));
        return ret;
    }

}
