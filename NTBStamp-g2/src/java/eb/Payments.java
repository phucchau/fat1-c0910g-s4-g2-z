/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TRUONG
 */
@Entity
@Table(name = "Payments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p"),
    @NamedQuery(name = "Payments.findByPaymentID", query = "SELECT p FROM Payments p WHERE p.paymentID = :paymentID"),
    @NamedQuery(name = "Payments.findByPayment", query = "SELECT p FROM Payments p WHERE p.payment = :payment"),
    @NamedQuery(name = "Payments.findByPaydate", query = "SELECT p FROM Payments p WHERE p.paydate = :paydate"),
    @NamedQuery(name = "Payments.findByStatus", query = "SELECT p FROM Payments p WHERE p.status = :status")})
public class Payments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Integer paymentID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Payment")
    private Double payment;
    @Column(name = "Paydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paydate;
    @Column(name = "Status")
    private Integer status;
    @JoinColumn(name = "RoomID", referencedColumnName = "RoomId")
    @ManyToOne
    private Rooms roomID;
    @JoinColumn(name = "PayChargeID", referencedColumnName = "PayChargeID")
    @ManyToOne
    private PaymentCharge payChargeID;
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerId")
    @ManyToOne
    private Customers customerID;

    public Payments() {
    }

    public Payments(Integer paymentID) {
        this.paymentID = paymentID;
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Integer paymentID) {
        this.paymentID = paymentID;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Rooms getRoomID() {
        return roomID;
    }

    public void setRoomID(Rooms roomID) {
        this.roomID = roomID;
    }

    public PaymentCharge getPayChargeID() {
        return payChargeID;
    }

    public void setPayChargeID(PaymentCharge payChargeID) {
        this.payChargeID = payChargeID;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentID != null ? paymentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payments)) {
            return false;
        }
        Payments other = (Payments) object;
        if ((this.paymentID == null && other.paymentID != null) || (this.paymentID != null && !this.paymentID.equals(other.paymentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eb.Payments[ paymentID=" + paymentID + " ]";
    }
    
}
