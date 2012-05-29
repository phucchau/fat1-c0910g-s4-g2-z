/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TRUONG
 */
@Entity
@Table(name = "PaymentCharge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentCharge.findAll", query = "SELECT p FROM PaymentCharge p"),
    @NamedQuery(name = "PaymentCharge.findByPayChargeID", query = "SELECT p FROM PaymentCharge p WHERE p.payChargeID = :payChargeID"),
    @NamedQuery(name = "PaymentCharge.findByChargename", query = "SELECT p FROM PaymentCharge p WHERE p.chargename = :chargename"),
    @NamedQuery(name = "PaymentCharge.findByInterest", query = "SELECT p FROM PaymentCharge p WHERE p.interest = :interest"),
    @NamedQuery(name = "PaymentCharge.findByPayChargeTime", query = "SELECT p FROM PaymentCharge p WHERE p.payChargeTime = :payChargeTime")})
public class PaymentCharge implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PayChargeID")
    private Integer payChargeID;
    @Size(max = 200)
    @Column(name = "Chargename")
    private String chargename;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Interest")
    private Double interest;
    @Column(name = "PayChargeTime")
    private Integer payChargeTime;
    @OneToMany(mappedBy = "payChargeID")
    private Collection<Payments> paymentsCollection;

    public PaymentCharge() {
    }

    public PaymentCharge(Integer payChargeID) {
        this.payChargeID = payChargeID;
    }

    public Integer getPayChargeID() {
        return payChargeID;
    }

    public void setPayChargeID(Integer payChargeID) {
        this.payChargeID = payChargeID;
    }

    public String getChargename() {
        return chargename;
    }

    public void setChargename(String chargename) {
        this.chargename = chargename;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Integer getPayChargeTime() {
        return payChargeTime;
    }

    public void setPayChargeTime(Integer payChargeTime) {
        this.payChargeTime = payChargeTime;
    }

    @XmlTransient
    public Collection<Payments> getPaymentsCollection() {
        return paymentsCollection;
    }

    public void setPaymentsCollection(Collection<Payments> paymentsCollection) {
        this.paymentsCollection = paymentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payChargeID != null ? payChargeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentCharge)) {
            return false;
        }
        PaymentCharge other = (PaymentCharge) object;
        if ((this.payChargeID == null && other.payChargeID != null) || (this.payChargeID != null && !this.payChargeID.equals(other.payChargeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eb.PaymentCharge[ payChargeID=" + payChargeID + " ]";
    }
    
}
