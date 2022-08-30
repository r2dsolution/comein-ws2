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
@Table(name="user_info")
public class UserInfo implements Serializable {

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
    @Column(name="ref_no", nullable=false)
    private String refNo;
    @Column(nullable=false)
    private String role;
    @Column(name="invited_date", nullable=false)
    private LocalDateTime invitedDate;
    @Column(name="registered_date")
    private LocalDateTime registeredDate;
    @Column(name="user_token")
    private String userToken;
    private String status;
    
    @Column(name="owner_id")
    private String ownerId;

    /** Default constructor. */
    public UserInfo() {
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
     * Access method for refNo.
     *
     * @return the current value of refNo
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     * Setter method for refNo.
     *
     * @param aRefNo the new value for refNo
     */
    public void setRefNo(String aRefNo) {
        refNo = aRefNo;
    }

    /**
     * Access method for role.
     *
     * @return the current value of role
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter method for role.
     *
     * @param aRole the new value for role
     */
    public void setRole(String aRole) {
        role = aRole;
    }

    /**
     * Access method for invitedDate.
     *
     * @return the current value of invitedDate
     */
    public LocalDateTime getInvitedDate() {
        return invitedDate;
    }

    /**
     * Setter method for invitedDate.
     *
     * @param aInvitedDate the new value for invitedDate
     */
    public void setInvitedDate(LocalDateTime aInvitedDate) {
        invitedDate = aInvitedDate;
    }

    /**
     * Access method for registeredDate.
     *
     * @return the current value of registeredDate
     */
    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    /**
     * Setter method for registeredDate.
     *
     * @param aRegisteredDate the new value for registeredDate
     */
    public void setRegisteredDate(LocalDateTime aRegisteredDate) {
        registeredDate = aRegisteredDate;
    }

    /**
     * Access method for userToken.
     *
     * @return the current value of userToken
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * Setter method for userToken.
     *
     * @param aUserToken the new value for userToken
     */
    public void setUserToken(String aUserToken) {
        userToken = aUserToken;
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
     * Compares the key for this instance with another UserInfo.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class UserInfo and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof UserInfo)) {
            return false;
        }
        UserInfo that = (UserInfo) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another UserInfo.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UserInfo)) return false;
        return this.equalKeys(other) && ((UserInfo)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[UserInfo |");
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

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}
