// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VTicketPK implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Column(name="tour_id", nullable=false, precision=19)
    private long tourId;
    @Column(name="inventory_id", nullable=false, precision=19)
    private long inventoryId;
    @Column(name="tour_date", nullable=false)
    private LocalDate tourDate;
    
	public long getTourId() {
		return tourId;
	}
	public void setTourId(long tourId) {
		this.tourId = tourId;
	}
	public long getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}
	public LocalDate getTourDate() {
		return tourDate;
	}
	public void setTourDate(LocalDate tourDate) {
		this.tourDate = tourDate;
	}
    
  

}
