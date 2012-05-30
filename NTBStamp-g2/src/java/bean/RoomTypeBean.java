/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.RoomTypes;
import eb.RoomTypesFacade;
import eb.Rooms;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author DuMaster
 */
@ManagedBean
@SessionScoped
public class RoomTypeBean {

    @EJB
    private RoomTypesFacade roomTypesFacade;
    public Integer TypeId;
    public String TypeName;
    public BigDecimal Price;
    public RoomTypes rt;

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer TypeId) {
        this.TypeId = TypeId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public RoomTypeBean(Integer TypeId, String TypeName, BigDecimal Price) {
        this.TypeId = TypeId;
        this.TypeName = TypeName;
        this.Price = Price;
    }

    /** Creates a new instance of RoomTypeBean */
    public RoomTypeBean() {
    }

    // Load all RoomType
    public List<RoomTypes> getListAllRoomType() {
        return roomTypesFacade.getAllRoomTypeDESC();
    }

    // create Roomtype
    public void createAllRoomType() {
        try {

            rt = new RoomTypes();
            rt.setTypeName(TypeName);
            rt.setPrice(Price);

            roomTypesFacade.create(rt);
            this.TypeName = "";

            FacesContext.getCurrentInstance().getExternalContext().redirect("RoomType.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update RoomType
    public void updateRoomType() {
        try {

            rt = new RoomTypes();
            rt.setTypeId(TypeId);
            rt.setTypeName(TypeName);
            rt.setPrice(Price);

            roomTypesFacade.edit(rt);
            this.TypeName = "";

            FacesContext.getCurrentInstance().getExternalContext().redirect("RoomType.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void findRoomType(int id) {
        try {

            RoomTypes rt = roomTypesFacade.find(id);
            this.TypeId = rt.getTypeId();
            this.TypeName = rt.getTypeName();
            this.Price = rt.getPrice();

            FacesContext.getCurrentInstance().getExternalContext().redirect("editroomtype.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xóa RoomType
    public void deleteRoomType(int idc) {
        try {
            RoomTypes rt = roomTypesFacade.find(idc);
            roomTypesFacade.remove(rt);

            FacesContext.getCurrentInstance().getExternalContext().redirect("RoomType.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RoomTypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SelectItem> getRoomTypeItem() {
        List<SelectItem> rs = new LinkedList<SelectItem>();
        List<RoomTypes> lst = roomTypesFacade.getAllRoomTypeDESC();
        for (RoomTypes l : lst) {
            rs.add(new SelectItem(l.getTypeId(),l.getTypeName()));
        }
        return rs;
    }
}
