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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TRUONG
 */
@Entity
@Table(name = "Documents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documents.findAll", query = "SELECT d FROM Documents d"),
    @NamedQuery(name = "Documents.findByDocID", query = "SELECT d FROM Documents d WHERE d.docID = :docID"),
    @NamedQuery(name = "Documents.findByTitle", query = "SELECT d FROM Documents d WHERE d.title = :title"),
    @NamedQuery(name = "Documents.findByImage", query = "SELECT d FROM Documents d WHERE d.image = :image"),
    @NamedQuery(name = "Documents.findByDescription", query = "SELECT d FROM Documents d WHERE d.description = :description"),
    @NamedQuery(name = "Documents.findByPermitDate", query = "SELECT d FROM Documents d WHERE d.permitDate = :permitDate"),
    @NamedQuery(name = "Documents.findBySignature", query = "SELECT d FROM Documents d WHERE d.signature = :signature"),
    @NamedQuery(name = "Documents.findByStatus", query = "SELECT d FROM Documents d WHERE d.status = :status")})
public class Documents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DocID")
    private Integer docID;
    @Size(max = 200)
    @Column(name = "Title")
    private String title;
    @Size(max = 1000)
    @Column(name = "Image")
    private String image;
    @Size(max = 500)
    @Column(name = "Description")
    private String description;
    @Column(name = "PermitDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date permitDate;
    @Size(max = 1073741823)
    @Column(name = "Signature")
    private String signature;
    @Column(name = "Status")
    private Boolean status;
    @JoinColumn(name = "DocID", referencedColumnName = "LandId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Lands lands;
    @JoinColumn(name = "ChargeId", referencedColumnName = "ChargeId")
    @ManyToOne
    private ChargeType chargeId;

    public Documents() {
    }

    public Documents(Integer docID) {
        this.docID = docID;
    }

    public Integer getDocID() {
        return docID;
    }

    public void setDocID(Integer docID) {
        this.docID = docID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPermitDate() {
        return permitDate;
    }

    public void setPermitDate(Date permitDate) {
        this.permitDate = permitDate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Lands getLands() {
        return lands;
    }

    public void setLands(Lands lands) {
        this.lands = lands;
    }

    public ChargeType getChargeId() {
        return chargeId;
    }

    public void setChargeId(ChargeType chargeId) {
        this.chargeId = chargeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docID != null ? docID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documents)) {
            return false;
        }
        Documents other = (Documents) object;
        if ((this.docID == null && other.docID != null) || (this.docID != null && !this.docID.equals(other.docID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eb.Documents[ docID=" + docID + " ]";
    }
    
}
