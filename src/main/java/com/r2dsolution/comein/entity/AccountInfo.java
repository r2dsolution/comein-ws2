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

@Entity(name="account_info")
public class AccountInfo implements Serializable {

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
    @Column(name="ref_code", nullable=false, length=50)
    private String refCode;
    @Column(name="account_code", nullable=false, length=50)
    private String accountCode;
    @Column(name="account_name", length=200)
    private String accountName;
    @Column(name="account_type", nullable=false, length=20)
    private String accountType;
    @Column(name="book_no", length=100)
    private String bookNo;

    /** Default constructor. */
    public AccountInfo() {
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
     * Access method for refCode.
     *
     * @return the current value of refCode
     */
    public String getRefCode() {
        return refCode;
    }

    /**
     * Setter method for refCode.
     *
     * @param aRefCode the new value for refCode
     */
    public void setRefCode(String aRefCode) {
        refCode = aRefCode;
    }

    /**
     * Access method for accountCode.
     *
     * @return the current value of accountCode
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * Setter method for accountCode.
     *
     * @param aAccountCode the new value for accountCode
     */
    public void setAccountCode(String aAccountCode) {
        accountCode = aAccountCode;
    }

    /**
     * Access method for accountName.
     *
     * @return the current value of accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Setter method for accountName.
     *
     * @param aAccountName the new value for accountName
     */
    public void setAccountName(String aAccountName) {
        accountName = aAccountName;
    }

    /**
     * Access method for accountType.
     *
     * @return the current value of accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Setter method for accountType.
     *
     * @param aAccountType the new value for accountType
     */
    public void setAccountType(String aAccountType) {
        accountType = aAccountType;
    }

    /**
     * Access method for bookNo.
     *
     * @return the current value of bookNo
     */
    public String getBookNo() {
        return bookNo;
    }

    /**
     * Setter method for bookNo.
     *
     * @param aBookNo the new value for bookNo
     */
    public void setBookNo(String aBookNo) {
        bookNo = aBookNo;
    }

    /**
     * Compares the key for this instance with another AccountInfo.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class AccountInfo and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof AccountInfo)) {
            return false;
        }
        AccountInfo that = (AccountInfo) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another AccountInfo.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AccountInfo)) return false;
        return this.equalKeys(other) && ((AccountInfo)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[AccountInfo |");
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
