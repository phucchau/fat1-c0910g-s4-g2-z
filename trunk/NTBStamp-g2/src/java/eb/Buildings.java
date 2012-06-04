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
import javax.persistence.OneToMany;
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
@Table(name = "Buildings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buildings.findAll", query = "SELECT b FROM Buildings b"),
    @NamedQuery(name = "Buildings.findByBuildingId", query = "SELECT b FROM Buildings b WHERE b.buildingId = :buildingId"),
    @NamedQuery(name = "Buildings.findByBuildingName", query = "SELECT b FROM Buildings b WHERE b.buildingName = :buildingName"),
    @NamedQuery(name = "Buildings.findByBuildingType", query = "SELECT b FROM Buildings b WHERE b.buildingType = :buildingType"),
    @NamedQuery(name = "Buildings.findByTotalFloors", query = "SELECT b FROM Buildings b WHERE b.totalFloors = :totalFloors"),
    @NamedQuery(name = "Buildings.findByTotalRooms", query = "SELECT b FROM Buildings b WHERE b.totalRooms = :totalRooms"),
    @NamedQuery(name = "Buildings.findByImage", query = "SELECT b FROM Buildings b WHERE b.image = :image"),
    @NamedQuery(name = "Buildings.findByDate", query = "SELECT b FROM Buildings b WHERE b.date = :date"),
    @NamedQuery(name = "Buildings.findByPrice", query = "SELECT b FROM Buildings b WHERE b.price = :price"),
    @NamedQuery(name = "Buildings.findByDescription", query = "SELECT b FROM Buildings b WHERE b.description = :description"),
    @NamedQuery(name = "Buildings.findByStatus", query = "SELECT b FROM Buildings b WHERE b.status = :status")})
public class Buildings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "BuildingId")
    private Integer buildingId;
    @Size(max = 200)
    @Column(name = "BuildingName")
    private String buildingName;
    @Size(max = 200)
    @Column(name = "BuildingType")
    private String buildingType;
    @Column(name = "TotalFloors")
    private Integer totalFloors;
    @Column(name = "TotalRooms")
    private Integer totalRooms;
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
    @OneToMany(mappedBy = "buildingId")
    private Collection<Rooms> roomsCollection;
    @JoinColumn(name = "LandId", referencedColumnName = "LandId")
    @ManyToOne
    private Lands landId;

    public Buildings() {
    }

    public Buildings(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public Integer getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(Integer totalFloors) {
        this.totalFloors = totalFloors;
    }

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
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

    public String getSubDescription() {
        Removetag rm = new Removetag();
        String subdes = rm.StripAllTags(description);
        if (subdes.length() >= 200) {
            return subdes.substring(0, 200) + " ...";
        } else {
            return subdes;
        }
    }

    public String getDescription() {
        return description;
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

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    public Lands getLandId() {
        return landId;
    }

    public void setLandId(Lands landId) {
        this.landId = landId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buildingId != null ? buildingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buildings)) {
            return false;
        }
        Buildings other = (Buildings) object;
        if ((this.buildingId == null && other.buildingId != null) || (this.buildingId != null && !this.buildingId.equals(other.buildingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eb.Buildings[ buildingId=" + buildingId + " ]";
    }
}
