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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ChargeType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChargeType.findAll", query = "SELECT c FROM ChargeType c"),
    @NamedQuery(name = "ChargeType.findByChargeId", query = "SELECT c FROM ChargeType c WHERE c.chargeId = :chargeId"),
    @NamedQuery(name = "ChargeType.findByCharge", query = "SELECT c FROM ChargeType c WHERE c.charge = :charge")})
public class ChargeType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ChargeId")
    private Integer chargeId;
    @Size(max = 100)
    @Column(name = "Charge")
    private String charge;
    @OneToMany(mappedBy = "chargeId")
    private Collection<Documents> documentsCollection;

    public ChargeType() {
    }

    public ChargeType(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    @XmlTransient
    public Collection<Documents> getDocumentsCollection() {
        return documentsCollection;
    }

    public void setDocumentsCollection(Collection<Documents> documentsCollection) {
        this.documentsCollection = documentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chargeId != null ? chargeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChargeType)) {
            return false;
        }
        ChargeType other = (ChargeType) object;
        if ((this.chargeId == null && other.chargeId != null) || (this.chargeId != null && !this.chargeId.equals(other.chargeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eb.ChargeType[ chargeId=" + chargeId + " ]";
    }
    
}
