/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.BuildingsFacade;
import eb.RoomTypesFacade;
import eb.Rooms;
import eb.RoomsFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author DuMaster
 */
@ManagedBean
@SessionScoped
public class RoomBean {

    @EJB
    private RoomsFacade roomsFacade;
    @EJB
    private RoomTypesFacade roomTypesFacade;
    @EJB
    private BuildingsFacade buildingsFacade;
    public Integer RoomId;
    public Integer RoomNo;
    public Integer TotalSquare;
    public BigDecimal TotalPrice;
    public String Image;
    public Integer TypeId;
    public Integer BuildingId;
    public String Description;
    public boolean Status;
    public Rooms r;

    public Integer getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(Integer BuildingId) {
        this.BuildingId = BuildingId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public Integer getRoomId() {
        return RoomId;
    }

    public void setRoomId(Integer RoomId) {
        this.RoomId = RoomId;
    }

    public Integer getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(Integer RoomNo) {
        this.RoomNo = RoomNo;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public BigDecimal getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(BigDecimal TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public Integer getTotalSquare() {
        return TotalSquare;
    }

    public void setTotalSquare(Integer TotalSquare) {
        this.TotalSquare = TotalSquare;
    }

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer TypeId) {
        this.TypeId = TypeId;
    }

    /** Creates a new instance of RoomBean */
    public RoomBean() {
    }

    public RoomBean(Integer RoomId, Integer RoomNo, Integer TotalSquare, BigDecimal TotalPrice, String Image, Integer TypeId, Integer BuildingId, String Description, boolean Status) {
        this.RoomId = RoomId;
        this.RoomNo = RoomNo;
        this.TotalSquare = TotalSquare;
        this.TotalPrice = TotalPrice;
        this.Image = Image;
        this.TypeId = TypeId;
        this.BuildingId = BuildingId;
        this.Description = Description;
        this.Status = Status;
    }

    // Load all RoomType
    public List<Rooms> getListAllRoomType() {
        return roomsFacade.getAllRoomsDESC();
    }

    // create Room
    public void createAllRoom() {
        try {

            r = new Rooms();
            r.setRoomNo(RoomNo);
            r.setTotalSquare(TotalSquare);
            r.setTotalPrice(TotalPrice);
            r.setImage(Image);
            r.setTypeId(roomTypesFacade.find(TypeId));
            r.setBuildingId(buildingsFacade.find(BuildingId));
            r.setDescription(Description);
            r.setStatus(true);

            roomsFacade.create(r);

            this.RoomNo = 0;
            this.Image = "";
            this.Description = "";


            FacesContext.getCurrentInstance().getExternalContext().redirect("rooms.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update Room
    public void updateRoom() {
        try {

            r = new Rooms();
            r.setRoomId(RoomId);
            r.setRoomNo(RoomNo);
            r.setTotalSquare(TotalSquare);
            r.setTotalPrice(TotalPrice);
            r.setImage(Image);
            r.setTypeId(roomTypesFacade.find(TypeId));
            r.setBuildingId(buildingsFacade.find(BuildingId));
            r.setDescription(Description);
            r.setStatus(Status);
            roomsFacade.edit(r);

            this.RoomNo = 0;
            this.Image = "";
            this.Description = "";

            FacesContext.getCurrentInstance().getExternalContext().redirect("rooms.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void findRoomType(int id) {
        try {

            Rooms r = roomsFacade.find(id);
            this.RoomId = r.getRoomId();
            this.RoomNo = r.getRoomNo();
            this.TotalSquare = r.getTotalSquare();
            this.TotalPrice = r.getTotalPrice();
            this.Image = r.getImage();
            this.Status = r.getStatus();
            this.TypeId = r.getTypeId().getTypeId();
            this.BuildingId = r.getBuildingId().getBuildingId();
            this.Description = r.getDescription();

            FacesContext.getCurrentInstance().getExternalContext().redirect("editroom.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xóa Room
    public void deleteRoom(int idc) {
        try {

            Rooms r = roomsFacade.find(idc);
            r.setStatus(false);
            roomsFacade.edit(r);

            FacesContext.getCurrentInstance().getExternalContext().redirect("rooms.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RoomBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        try {
            FileOutputStream fos = null;

            UploadedFile file = event.getFile();

            byte[] buff = file.getContents();

            String realpath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            String patch = realpath.substring(0, realpath.lastIndexOf("build")) + "web\\ImageUser\\";

            File f = new File(patch + file.getFileName());

            if (!f.exists()) {
                f.createNewFile();
            }

            fos = new FileOutputStream(f);

            fos.write(buff);

            fos.close();
            this.setImage(file.getFileName());
        } catch (IOException ex) {
            Logger.getLogger(RoomBean.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
}
