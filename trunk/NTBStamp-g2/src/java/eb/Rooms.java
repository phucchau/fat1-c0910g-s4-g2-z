/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eb;

import entity.Removetag;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TRUONG
 */
@Entity
@Table(name = "Rooms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByRoomId", query = "SELECT r FROM Rooms r WHERE r.roomId = :roomId"),
    @NamedQuery(name = "Rooms.findByRoomNo", query = "SELECT r FROM Rooms r WHERE r.roomNo = :roomNo"),
    @NamedQuery(name = "Rooms.findByTotalSquare", query = "SELECT r FROM Rooms r WHERE r.totalSquare = :totalSquare"),
    @NamedQuery(name = "Rooms.findByTotalPrice", query = "SELECT r FROM Rooms r WHERE r.totalPrice = :totalPrice"),
    @NamedQuery(name = "Rooms.findByImage", query = "SELECT r FROM Rooms r WHERE r.image = :image"),
    @NamedQuery(name = "Rooms.findByDescription", query = "SELECT r FROM Rooms r WHERE r.description = :description"),
    @NamedQuery(name = "Rooms.findByStatus", query = "SELECT r FROM Rooms r WHERE r.status = :status")})
public class Rooms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
   // @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "RoomId")
    private Integer roomId;
    @Column(name = "RoomNo")
    private Integer roomNo;
    @Column(name = "TotalSquare")
    private Integer totalSquare;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TotalPrice")
    private BigDecimal totalPrice;
    @Size(max = 1000)
    @Column(name = "Image")
    private String image;
    @Size(max = 1073741823)
    @Column(name = "Description")
    private String description;
    @Column(name = "Status")
    private Boolean status;
    @JoinColumn(name = "TypeId", referencedColumnName = "TypeId")
    @ManyToOne
    private RoomTypes typeId;
    @JoinColumn(name = "BuildingId", referencedColumnName = "BuildingId")
    @ManyToOne
    private Buildings buildingId;
    @OneToMany(mappedBy = "roomID")
    private Collection<Payments> paymentsCollection;

    public Rooms() {
    }

    public Rooms(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getTotalSquare() {
        return totalSquare;
    }

    public void setTotalSquare(Integer totalSquare) {
        this.totalSquare = totalSquare;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public RoomTypes getTypeId() {
        return typeId;
    }

    public void setTypeId(RoomTypes typeId) {
        this.typeId = typeId;
    }

    public Buildings getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Buildings buildingId) {
        this.buildingId = buildingId;
    }
    
    @XmlTransient
    public Collection<Payments> getPaymentsList() {
        return paymentsCollection;
    }

    public void setPaymentsList(Collection<Payments> paymentsCollection) {
        this.paymentsCollection = paymentsCollection;
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
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eb.Rooms[ roomId=" + roomId + " ]";
    }
    
}
