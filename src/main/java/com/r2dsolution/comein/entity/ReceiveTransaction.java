// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="receive_transaction")
public class ReceiveTransaction implements Serializable {

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
    @Column(length=200)
    private String detail;
    @Column(name="transaction_date", nullable=false)
    private LocalDate transactionDate;
    @Column(nullable=false, precision=131089)
    private BigDecimal value;
    @Column(name="account_ref", nullable=false, length=50)
    private String accountRef;
    @Column(name="accounting_id", nullable=false)
    private long accountingId;
    private String note;
    
    /** Default constructor. */
    public ReceiveTransaction() {
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
     * Access method for detail.
     *
     * @return the current value of detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Setter method for detail.
     *
     * @param aDetail the new value for detail
     */
    public void setDetail(String aDetail) {
        detail = aDetail;
    }

    /**
     * Access method for transactionDate.
     *
     * @return the current value of transactionDate
     */
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    /**
     * Setter method for transactionDate.
     *
     * @param aTransactionDate the new value for transactionDate
     */
    public void setTransactionDate(LocalDate aTransactionDate) {
        transactionDate = aTransactionDate;
    }

    /**
     * Access method for value.
     *
     * @return the current value of value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Setter method for value.
     *
     * @param aValue the new value for value
     */
    public void setValue(BigDecimal aValue) {
        value = aValue;
    }

    /**
     * Access method for accountRef.
     *
     * @return the current value of accountRef
     */
    public String getAccountRef() {
        return accountRef;
    }

    /**
     * Setter method for accountRef.
     *
     * @param aAccountRef the new value for accountRef
     */
    public void setAccountRef(String aAccountRef) {
        accountRef = aAccountRef;
    }

    /**
     * Compares the key for this instance with another ReceiveTransaction.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class ReceiveTransaction and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof ReceiveTransaction)) {
            return false;
        }
        ReceiveTransaction that = (ReceiveTransaction) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another ReceiveTransaction.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ReceiveTransaction)) return false;
        return this.equalKeys(other) && ((ReceiveTransaction)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[ReceiveTransaction |");
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getAccountingId() {
		return accountingId;
	}

	public void setAccountingId(long accountingId) {
		this.accountingId = accountingId;
	}

}
