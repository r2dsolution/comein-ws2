// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;

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
    private String province;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

}
