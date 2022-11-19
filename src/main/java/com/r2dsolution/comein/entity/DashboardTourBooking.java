// Generated with g9.

package com.r2dsolution.comein.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name="dashboard_tour_booking")
public class DashboardTourBooking implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private DashboardTourBookingPK id;
	

    @Column(name="total_visitor", precision=19)
    private long totalVisitor;
    @Column(name="total_booking", precision=19)
    private long totalBooking;
    @Column(name="total_confirm", precision=19)
    private long totalConfirm;
    @Column(name="total_cancel", precision=19)
    private long totalCancel;
    @Column(name="total_available", precision=19)
    private long totalAvailable;

    /** Default constructor. */
    public DashboardTourBooking() {
        super();
    }

    /**
     * Access method for totalVisitor.
     *
     * @return the current value of totalVisitor
     */
    public long getTotalVisitor() {
        return totalVisitor;
    }

    /**
     * Setter method for totalVisitor.
     *
     * @param aTotalVisitor the new value for totalVisitor
     */
    public void setTotalVisitor(long aTotalVisitor) {
        totalVisitor = aTotalVisitor;
    }

    /**
     * Access method for totalBooking.
     *
     * @return the current value of totalBooking
     */
    public long getTotalBooking() {
        return totalBooking;
    }

    /**
     * Setter method for totalBooking.
     *
     * @param aTotalBooking the new value for totalBooking
     */
    public void setTotalBooking(long aTotalBooking) {
        totalBooking = aTotalBooking;
    }

    /**
     * Access method for totalConfirm.
     *
     * @return the current value of totalConfirm
     */
    public long getTotalConfirm() {
        return totalConfirm;
    }

    /**
     * Setter method for totalConfirm.
     *
     * @param aTotalConfirm the new value for totalConfirm
     */
    public void setTotalConfirm(long aTotalConfirm) {
        totalConfirm = aTotalConfirm;
    }

    /**
     * Access method for totalCancel.
     *
     * @return the current value of totalCancel
     */
    public long getTotalCancel() {
        return totalCancel;
    }

    /**
     * Setter method for totalCancel.
     *
     * @param aTotalCancel the new value for totalCancel
     */
    public void setTotalCancel(long aTotalCancel) {
        totalCancel = aTotalCancel;
    }

    /**
     * Access method for totalAvailable.
     *
     * @return the current value of totalAvailable
     */
    public long getTotalAvailable() {
        return totalAvailable;
    }

    /**
     * Setter method for totalAvailable.
     *
     * @param aTotalAvailable the new value for totalAvailable
     */
    public void setTotalAvailable(long aTotalAvailable) {
        totalAvailable = aTotalAvailable;
    }

	public DashboardTourBookingPK getId() {
		return id;
	}

	public void setId(DashboardTourBookingPK id) {
		this.id = id;
	}

}
