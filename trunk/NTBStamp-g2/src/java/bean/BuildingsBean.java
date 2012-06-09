/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Buildings;
import eb.BuildingsFacade;
import eb.LandsFacade;
import eb.RoomTypes;
import eb.Rooms;
import eb.RoomsFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author DuMaster
 */
@ManagedBean
@SessionScoped
public class BuildingsBean {

    @EJB
    private RoomsFacade roomsFacade;
    @EJB
    private LandsFacade landsFacade;
    @EJB
    private BuildingsFacade buildingsFacade;

    
    
    public BuildingsBean() {
    }
    public Integer BuildingId;
    public String BuildingName;
    public String BuildingType;
    public Integer TotalFloors;
    public Integer TotalRooms;
    public String Image;
    public Date Date;
    public BigDecimal Price;
    public Integer LandId;
    public String Description;
    public boolean Status;
    private Buildings b;
    public String LandName;
    public BuildingsBean buil = null;
    public ArrayList<BuildingsBean> alb;
    
    public Integer getBuildingId() {
        return BuildingId;
    }

    public Buildings getB() {
        return b;
    }

    public void setB(Buildings b) {
        this.b = b;
    }

    public void setBuildingId(Integer BuildingId) {
        this.BuildingId = BuildingId;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public void setBuildingName(String BuildingName) {
        this.BuildingName = BuildingName;
    }

    public String getBuildingType() {
        return BuildingType;
    }

    public void setBuildingType(String BuildingType) {
        this.BuildingType = BuildingType;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
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

    public Integer getLandId() {
        return LandId;
    }

    public void setLandId(Integer LandId) {
        this.LandId = LandId;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal Price) {
        this.Price = Price;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public Integer getTotalFloors() {
        return TotalFloors;
    }

    public void setTotalFloors(Integer TotalFloors) {
        this.TotalFloors = TotalFloors;
    }

    public Integer getTotalRooms() {
        return TotalRooms;
    }

    public void setTotalRooms(Integer TotalRooms) {
        this.TotalRooms = TotalRooms;
    }

    public String getLandName() {
        return LandName;
    }

    public void setLandName(String LandName) {
        this.LandName = LandName;
    }

    public BuildingsBean(Integer BuildingId, String BuildingName, String BuildingType, Integer TotalFloors, Integer TotalRooms, String Image, Date Date, BigDecimal Price, Integer LandId, String Description, boolean Status, String LandName) {
        this.BuildingId = BuildingId;
        this.BuildingName = BuildingName;
        this.BuildingType = BuildingType;
        this.TotalFloors = TotalFloors;
        this.TotalRooms = TotalRooms;
        this.Image = Image;
        this.Date = Date;
        this.Price = Price;
        this.LandId = LandId;
        this.Description = Description;
        this.Status = Status;
        this.LandName = LandName;
    }

    
    // Load all Lands
    public ArrayList<BuildingsBean> getListBuildingsALLs() {

        List<Buildings> l = buildingsFacade.getAllBuildingsDESC();

        alb = new ArrayList<BuildingsBean>();

        for (int i = 0; i < l.size(); i++) {

            buil = new BuildingsBean();
            buil.setBuildingId(l.get(i).getBuildingId());
            buil.setBuildingName(l.get(i).getBuildingName());
            buil.setTotalFloors(l.get(i).getTotalFloors());
            buil.setTotalRooms(l.get(i).getTotalRooms());
            buil.setImage(l.get(i).getImage());
            buil.setPrice(l.get(i).getPrice());
            if (landsFacade.find(l.get(i).getLandId().getLandId()) != null) {
                buil.setLandName(landsFacade.find(l.get(i).getLandId().getLandId()).getLandName());
                buil.setLandId(landsFacade.find(l.get(i).getLandId().getLandId()).getLandId());
            }

            alb.add(buil);
        }

        return alb;
    }

    @PostConstruct
    public List<Buildings> getAllBuildingsDESC() {
        return buildingsFacade.getAllBuildingsDESC();
    }

    @PostConstruct
    public List<Rooms> getRoombyBuildingID() {
        try {
            return roomsFacade.getAllRoomsDESCbyIDBuilding(b.getBuildingId());
        } catch (Exception e) {
            return null;
        }
    }

    public void goBuilding() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        setB(buildingsFacade.find(Integer.parseInt(params.get("idBuilding"))));
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.endsWith("index.xhtml")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Home/building.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("building.xhtml");
        }
    }

    // create Buildings
    public void createBuildings() {
        try {
            b = new Buildings();
            b.setBuildingName(BuildingName);
            b.setBuildingType(BuildingType);
            b.setTotalFloors(TotalFloors);
            b.setTotalRooms(TotalRooms);
            b.setImage(Image);
            b.setPrice(Price);
            b.setLandId(landsFacade.find(LandId));
            b.setDescription(Description);
            b.setStatus(true);
            Calendar cal = Calendar.getInstance();
            b.setDate(cal.getTime());

            buildingsFacade.create(b);

            this.BuildingName = "";
            this.BuildingType = "";
            this.TotalFloors = 0;
            this.TotalRooms = 0;
            this.Description = "";


            FacesContext.getCurrentInstance().getExternalContext().redirect("Buildings.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update Buildings
    public void updateBuildings() {
        try {

            b = new Buildings();
            b.setBuildingId(BuildingId);
            b.setBuildingName(BuildingName);
            b.setBuildingType(BuildingType);
            b.setTotalFloors(TotalFloors);
            b.setTotalRooms(TotalRooms);
            b.setPrice(Price);
            b.setLandId(landsFacade.find(LandId));
            b.setDescription(Description);
            b.setStatus(true);

            Calendar cal = Calendar.getInstance();
            b.setDate(cal.getTime());


            if (!Image.equals("")) {
                b.setImage(Image);
            }

            buildingsFacade.edit(b);

            this.BuildingName = "";
            this.BuildingType = "";
            this.TotalFloors = 0;
            this.TotalRooms = 0;
            this.Description = "";

            FacesContext.getCurrentInstance().getExternalContext().redirect("Buildings.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
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
            Logger.getLogger(BuildingsBean.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void findBuildings(int id) {
        try {
            Buildings build = buildingsFacade.find(id);
            this.BuildingId = build.getBuildingId();
            this.BuildingName = build.getBuildingName();
            this.BuildingType = build.getBuildingType();
            this.Image = build.getImage();
            this.Price = build.getPrice();
            this.TotalFloors = build.getTotalFloors();
            this.TotalRooms = build.getTotalRooms();
            this.Description = build.getDescription();
            this.LandId = build.getLandId().getLandId();


            FacesContext.getCurrentInstance().getExternalContext().redirect("editbuildings.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // delete Buildings
    public void deleteBuildings(int idc) {
        try {
            Buildings build = buildingsFacade.find(idc);
            build.setStatus(false);
            buildingsFacade.edit(build);

            this.BuildingName = "";
            this.BuildingType = "";
            this.TotalFloors = 0;
            this.TotalRooms = 0;
            this.Description = "";


            FacesContext.getCurrentInstance().getExternalContext().redirect("Buildings.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(BuildingsBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public List<SelectItem> getBuildingItem() {
        List<SelectItem> rs = new LinkedList<SelectItem>();
        List<Buildings> lst = buildingsFacade.getAllBuildingsDESC();
        for (Buildings l : lst) {
            rs.add(new SelectItem(l.getBuildingId(),l.getBuildingName()));
        }
        return rs;
    }
}
