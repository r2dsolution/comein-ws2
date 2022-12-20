// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="v_ticket")
public class VTicket implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private VTicketPK id;
	
    @Column(nullable=false, precision=19)
    private long total;
    @Column(name="adult_rate", nullable=false, precision=131089)
    private BigDecimal adultRate;
    @Column(name="child_rate", precision=131089)
    private BigDecimal childRate;
    @Column(length=1)
    private String cancelable;
    @Column(name="cancel_before", precision=10)
    private int cancelBefore;
    @Column(name="ticket_status")
    private String ticketStatus;
    
	public VTicketPK getId() {
		return id;
	}
	public void setId(VTicketPK id) {
		this.id = id;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public BigDecimal getAdultRate() {
		return adultRate;
	}
	public void setAdultRate(BigDecimal adultRate) {
		this.adultRate = adultRate;
	}
	public BigDecimal getChildRate() {
		return childRate;
	}
	public void setChildRate(BigDecimal childRate) {
		this.childRate = childRate;
	}
	public String getCancelable() {
		return cancelable;
	}
	public void setCancelable(String cancelable) {
		this.cancelable = cancelable;
	}
	public int getCancelBefore() {
		return cancelBefore;
	}
	public void setCancelBefore(int cancelBefore) {
		this.cancelBefore = cancelBefore;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}


}
