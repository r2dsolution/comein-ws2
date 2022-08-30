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

@Entity(name="tour_image")
public class TourImage implements Serializable {

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
    @Column(name="img_type", nullable=false)
    private String imgType;
    @Column(nullable=false, precision=19)
    private long seq;
    @Column(name="tour_img", nullable=false)
    private String tourImg;
    private String status;

    /** Default constructor. */
    public TourImage() {
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
     * Access method for imgType.
     *
     * @return the current value of imgType
     */
    public String getImgType() {
        return imgType;
    }

    /**
     * Setter method for imgType.
     *
     * @param aImgType the new value for imgType
     */
    public void setImgType(String aImgType) {
        imgType = aImgType;
    }

    /**
     * Access method for seq.
     *
     * @return the current value of seq
     */
    public long getSeq() {
        return seq;
    }

    /**
     * Setter method for seq.
     *
     * @param aSeq the new value for seq
     */
    public void setSeq(long aSeq) {
        seq = aSeq;
    }

    /**
     * Access method for tourImg.
     *
     * @return the current value of tourImg
     */
    public String getTourImg() {
        return tourImg;
    }

    /**
     * Setter method for tourImg.
     *
     * @param aTourImg the new value for tourImg
     */
    public void setTourImg(String aTourImg) {
        tourImg = aTourImg;
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
     * Compares the key for this instance with another TourImage.
     *
     * @param other The object to compare to
     * @return True if other object is instance of class TourImage and the key objects are equal
     */
    private boolean equalKeys(Object other) {
        if (this==other) {
            return true;
        }
        if (!(other instanceof TourImage)) {
            return false;
        }
        TourImage that = (TourImage) other;
        if (this.getId() != that.getId()) {
            return false;
        }
        return true;
    }

    /**
     * Compares this instance with another TourImage.
     *
     * @param other The object to compare to
     * @return True if the objects are the same
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TourImage)) return false;
        return this.equalKeys(other) && ((TourImage)other).equalKeys(this);
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
        StringBuffer sb = new StringBuffer("[TourImage |");
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
