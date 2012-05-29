/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eb;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "RoomTypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomTypes.findAll", query = "SELECT r FROM RoomTypes r"),
    @NamedQuery(name = "RoomTypes.findByTypeId", query = "SELECT r FROM RoomTypes r WHERE r.typeId = :typeId"),
    @NamedQuery(name = "RoomTypes.findByTypeName", query = "SELECT r FROM RoomTypes r WHERE r.typeName = :typeName"),
    @NamedQuery(name = "RoomTypes.findByPrice", query = "SELECT r FROM RoomTypes r WHERE r.price = :price")})
public class RoomTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TypeId")
    private Integer typeId;
    @Size(max = 500)
    @Column(name = "TypeName")
    private String typeName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
    @OneToMany(mappedBy = "typeId")
    private Collection<Rooms> roomsCollection;

    public RoomTypes() {
    }

    public RoomTypes(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeId != null ? typeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomTypes)) {
            return false;
        }
        RoomTypes other = (RoomTypes) object;
        if ((this.typeId == null && other.typeId != null) || (this.typeId != null && !this.typeId.equals(other.typeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eb.RoomTypes[ typeId=" + typeId + " ]";
    }
    
}
