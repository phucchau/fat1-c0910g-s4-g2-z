/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.ChargeType;
import eb.RoomTypes;
import eb.RoomTypesFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author VietDuc
 */
@ManagedBean
@RequestScoped
public class RoomTypeBean {
    @EJB
    private RoomTypesFacade roomTypesFacade;
    List<RoomTypes> listRoomType ;

    /** Creates a new instance of RoomTypeBean */
    private int typeID;
    private String typeName;
    private BigDecimal price; 
    public RoomTypeBean() {
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<RoomTypes> getListRoomType() {
        List<RoomTypes> listRT = new ArrayList<RoomTypes>();
        List<RoomTypes> list  = roomTypesFacade.findAll();
        for (RoomTypes roomTypes : list) {
            listRT.add(roomTypes);
        }
        return listRT;
    }

    public void setListRoomType(List<RoomTypes> listRoomType) {
        this.listRoomType = listRoomType;
    }
    public String createRoomType(){
            RoomTypes rt = new RoomTypes();

            rt.setTypeId(1);
            rt.setTypeName(typeName);
            rt.setPrice(price);

            roomTypesFacade.create(rt);

            return "RoomType"; 
    }
    public String updateRoomType(){
        int id = typeID;
        RoomTypes rt = roomTypesFacade.find(id);
        rt.setTypeName(typeName);
        rt.setPrice(price);
       
        roomTypesFacade.edit(rt);
//        System.out.println("id"+typeID);
        
        return "RoomType";
    }
    public String deleteRoomType(){
       RoomTypes rt = roomTypesFacade.find(getTypeID());
        roomTypesFacade.remove(rt);
        return "RoomType.xhtml";
    }
    
    public void findRoomType(int id) {
        try {
            RoomTypes rt = roomTypesFacade.find(id);
            this.typeID = rt.getTypeId();
            this.typeName = rt.getTypeName();
            this.price = rt.getPrice();

            FacesContext.getCurrentInstance().getExternalContext().redirect("EditRoomType.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
