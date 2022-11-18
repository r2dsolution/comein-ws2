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

@Entity(name="accounting_transaction")
public class AccountingTransaction implements Serializable {

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
    @Column(name="company_id", nullable=false, precision=19)
    private long companyId;
    @Column(name="booking_code", nullable=false, length=50)
    private String bookingCode;
    @Column(name="tour_date", nullable=false)
    private LocalDate tourDate;
    @Column(name="tour_name")
    private String tourName;
    @Column(name="payment_date", nullable=false)
    private LocalDate paymentDate;
    @Column(name="payment_method", nullable=false, length=50)
    private String paymentMethod;
    @Column(name="sell_value", precision=131089)
    private BigDecimal sellValue;
    @Column(name="tour_id", precision=19)
    private long tourId;
    @Column(name="reference_name")
    private String referenceName;

    /** Default constructor. */
    public AccountingTransaction() {
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
     * Access method for companyId.
     *
     * @return the current value of companyId
     */
    public long getCompanyId() {
        return companyId;
    }

    /**
     * Setter method for companyId.
     *
     * @param aCompanyId the new value for companyId
     */
    public void setCompanyId(long aCompanyId) {
        companyId = aCompanyId;
    }

    /**
     * Access method for bookingCode.
     *
     * @return the current value of bookingCode
     */
    public String getBookingCode() {
        return bookingCode;
    }

    /**
     * Setter method for bookingCode.
     *
     * @param aBookingCode the new value for bookingCode
     */
    public void setBookingCode(String aBookingCode) {
        bookingCode = aBookingCode;
    }

    /**
     * Access method for tourDate.
     *
     * @return the current value of tourDate
     */
    public LocalDate getTourDate() {
        return tourDate;
    }

    /**
     * Setter method for tourDate.
     *
     * @param aTourDate the new value for tourDate
     */
    public void setTourDate(LocalDate aTourDate) {
        tourDate = aTourDate;
    }

    /**
     * Access method for paymentDate.
     *
     * @return the current value of paymentDate
     */
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Setter method for paymentDate.
     *
     * @param aPaymentDate the new value for paymentDate
     */
    public void setPaymentDate(LocalDate aPaymentDate) {
        paymentDate = aPaymentDate;
    }

    /**
     * Access method for paymentMethod.
     *
     * @return the current value of paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Setter method for paymentMethod.
     *
     * @param aPaymentMethod the new value for paymentMethod
     */
    public void setPaymentMethod(String aPaymentMethod) {
        paymentMethod = aPaymentMethod;
    }

    /**
     * Access method for sellValue.
     *
     * @return the current value of sellValue
     */
    public BigDecimal getSellValue() {
        return sellValue;
    }

    /**
     * Setter method for sellValue.
     *
     * @param aSellValue the new value for sellValue
     */
    public void setSellValue(BigDecimal aSellValue) {
        sellValue = aSellValue;
    }

    /**
     * Compares the key for this instance with another AccountingTransaction.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class AccountingTransaction and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof AccountingTransaction)) {
            return false;
        }
        AccountingTransaction that = (AccountingTransaction) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another AccountingTransaction.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AccountingTransaction)) return false;
        return this.equalKeys(other) && ((AccountingTransaction)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[AccountingTransaction |");
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

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public long getTourId() {
		return tourId;
	}

	public void setTourId(long tourId) {
		this.tourId = tourId;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

}
