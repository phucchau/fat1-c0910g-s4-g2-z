/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eb;

import entity.Removetag;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TRUONG
 */
@Entity
@Table(name = "Lands")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lands.findAll", query = "SELECT l FROM Lands l"),
    @NamedQuery(name = "Lands.findByLandId", query = "SELECT l FROM Lands l WHERE l.landId = :landId"),
    @NamedQuery(name = "Lands.findByLandName", query = "SELECT l FROM Lands l WHERE l.landName = :landName"),
    @NamedQuery(name = "Lands.findByAddress", query = "SELECT l FROM Lands l WHERE l.address = :address"),
    @NamedQuery(name = "Lands.findByNearBy", query = "SELECT l FROM Lands l WHERE l.nearBy = :nearBy"),
    @NamedQuery(name = "Lands.findByImage", query = "SELECT l FROM Lands l WHERE l.image = :image"),
    @NamedQuery(name = "Lands.findByDate", query = "SELECT l FROM Lands l WHERE l.date = :date"),
    @NamedQuery(name = "Lands.findByPrice", query = "SELECT l FROM Lands l WHERE l.price = :price"),
    @NamedQuery(name = "Lands.findByDescription", query = "SELECT l FROM Lands l WHERE l.description = :description"),
    @NamedQuery(name = "Lands.findByStatus", query = "SELECT l FROM Lands l WHERE l.status = :status")})
public class Lands implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
   // @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "LandId")
    private Integer landId;
    @Size(max = 200)
    @Column(name = "LandName")
    private String landName;
    @Size(max = 500)
    @Column(name = "Address")
    private String address;
    @Size(max = 500)
    @Column(name = "NearBy")
    private String nearBy;
    @Size(max = 1000)
    @Column(name = "Image")
    private String image;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
    @Size(max = 1073741823)
    @Column(name = "Description")
    private String description;
    @Column(name = "Status")
    private Boolean status;
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "lands")
    private Documents documents;
    @OneToMany(mappedBy = "landId")
    private Collection<Buildings> buildingsCollection;
    @JoinColumn(name = "LandTypeID", referencedColumnName = "LandTypeID")
    @ManyToOne
    private LandTypes landTypeID;

    public Lands() {
    }

    public Lands(Integer landId) {
        this.landId = landId;
    }

    public Integer getLandId() {
        return landId;
    }

    public void setLandId(Integer landId) {
        this.landId = landId;
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNearBy() {
        return nearBy;
    }

    public void setNearBy(String nearBy) {
        this.nearBy = nearBy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public String getSubDescription(){
        Removetag rm = new Removetag();
        String subdes = rm.StripAllTags(description);
        if (subdes.length() >= 200) {
            return subdes.substring(0,200)+" ...";
        }else{
            return subdes;
        }
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Documents getDocuments() {
        return documents;
    }

    public void setDocuments(Documents documents) {
        this.documents = documents;
    }

    @XmlTransient
    public Collection<Buildings> getBuildingsCollection() {
        return buildingsCollection;
    }

    public void setBuildingsCollection(Collection<Buildings> buildingsCollection) {
        this.buildingsCollection = buildingsCollection;
    }

    public LandTypes getLandTypeID() {
        return landTypeID;
    }

    public void setLandTypeID(LandTypes landTypeID) {
        this.landTypeID = landTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (landId != null ? landId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lands)) {
            return false;
        }
        Lands other = (Lands) object;
        if ((this.landId == null && other.landId != null) || (this.landId != null && !this.landId.equals(other.landId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eb.Lands[ landId=" + landId + " ]";
    }
    
}
