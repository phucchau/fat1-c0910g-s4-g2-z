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
@Table(name = "LandTypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LandTypes.findAll", query = "SELECT l FROM LandTypes l"),
    @NamedQuery(name = "LandTypes.findByLandTypeID", query = "SELECT l FROM LandTypes l WHERE l.landTypeID = :landTypeID"),
    @NamedQuery(name = "LandTypes.findByLandName", query = "SELECT l FROM LandTypes l WHERE l.landName = :landName")})
public class LandTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
   // @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "LandTypeID")
    private Integer landTypeID;
    @Size(max = 200)
    @Column(name = "LandName")
    private String landName;
    @OneToMany(mappedBy = "landTypeID")
    private Collection<Lands> landsCollection;

    public LandTypes() {
    }

    public LandTypes(Integer landTypeID) {
        this.landTypeID = landTypeID;
    }

    public Integer getLandTypeID() {
        return landTypeID;
    }

    public void setLandTypeID(Integer landTypeID) {
        this.landTypeID = landTypeID;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    @XmlTransient
    public Collection<Lands> getLandsCollection() {
        return landsCollection;
    }

    public void setLandsCollection(Collection<Lands> landsCollection) {
        this.landsCollection = landsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (landTypeID != null ? landTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LandTypes)) {
            return false;
        }
        LandTypes other = (LandTypes) object;
        if ((this.landTypeID == null && other.landTypeID != null) || (this.landTypeID != null && !this.landTypeID.equals(other.landTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eb.LandTypes[ landTypeID=" + landTypeID + " ]";
    }
    
}
