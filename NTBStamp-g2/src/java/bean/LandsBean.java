/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Buildings;
import eb.BuildingsFacade;
import eb.Documents;
import eb.DocumentsFacade;
import eb.LandTypesFacade;
import eb.Lands;
import eb.LandsFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

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
public class LandsBean {
    @EJB
    private DocumentsFacade documentsFacade;
    @EJB
    private BuildingsFacade buildingsFacade;
    @EJB
    private LandsFacade landsFacade;
    
    @EJB
    private LandTypesFacade landTypesFacade;
    public Lands land = null;
    public Integer landId;
    public String landName;
    public Integer landTypeID;
    public String address;
    public String image;
    public String nearBy;
    public Date date;
    public BigDecimal price;
    public String description;
    public Boolean status;
    public Integer permit;
    public Lands l;
    public LandsBean lb = null;
    public ArrayList<LandsBean> alb;

    public Integer getPermit() {
        return permit;
    }

    public void setPermit(Integer permit) {
        this.permit = permit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Lands getLand() {
        return land;
    }

    public void setLand(Lands land) {
        this.land = land;
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

    public Integer getLandTypeID() {
        return landTypeID;
    }

    public void setLandTypeID(Integer landTypeID) {
        this.landTypeID = landTypeID;
    }

    public String getNearBy() {
        return nearBy;
    }

    public void setNearBy(String nearBy) {
        this.nearBy = nearBy;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Lands getL() {
        return l;
    }

    public void setL(Lands l) {
        this.l = l;
    }

    /** Creates a new instance of LandsBean */
    public LandsBean() {
    }

    public LandsBean(Integer landId, String landName, Integer landTypeID, String address, String image, Date date, BigDecimal price, String description, Boolean status) {
        this.landId = landId;
        this.landName = landName;
        this.landTypeID = landTypeID;
        this.address = address;
        this.image = image;
        this.date = date;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    // Load all Lands
    public ArrayList<LandsBean> getListLandsALLs() {

        List<Lands> l = landsFacade.getAllLandsDESC();

        alb = new ArrayList<LandsBean>();

        for (int i = 0; i < l.size(); i++) {

            lb = new LandsBean();
            lb.setLandId(l.get(i).getLandId());
            lb.setLandName(l.get(i).getLandName());
            lb.setPrice(l.get(i).getPrice());
            lb.setImage(l.get(i).getImage());
            if (documentsFacade.find(l.get(i).getLandId())!=null) {
                lb.setPermit(documentsFacade.find(l.get(i).getLandId()).getDocID());
            } else {
                setPermit(null);
            }
            alb.add(lb);
        }
        return alb;
    }
    
    @PostConstruct
    public List<Lands> getListLands() {
        return landsFacade.getAllLandsDESC();
    }

    @PostConstruct
    public List<Buildings> getListBuildingbyIdland() {
        try {
            return buildingsFacade.getAllBuildingsDESCbyIDland(l.getLandId());
        } catch (Exception e) {
            return null;
        }
    }
    
    @PostConstruct
    public Documents getDocumentsbyLandID(){
        try {
            return documentsFacade.getDocumentbyLandid(l.getLandId());
        } catch (Exception e) {
            return null;
        }
    }
    // create Lands
    public void createLands() {
        try {
            land = new Lands();
            land.setLandName(landName);
            land.setAddress(address);
            land.setNearBy(nearBy);
            land.setLandTypeID(landTypesFacade.find(landTypeID));
            Calendar cal = Calendar.getInstance();
            land.setDate(cal.getTime());
            land.setPrice(price);
            land.setImage(image);
            land.setDescription(description);
            land.setStatus(true);

            landsFacade.create(land);

            this.setAddress("");
            this.setNearBy("");
            this.setLandName("");

            FacesContext.getCurrentInstance().getExternalContext().redirect("lands.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update Lands
    public void updateLands() {
        try {

            land = new Lands();
            land.setLandName(landName);
            land.setAddress(address);
            land.setNearBy(nearBy);
            land.setDate(date);
            land.setPrice(price);
            land.setDescription(description);
            land.setStatus(true);
            land.setLandTypeID(landTypesFacade.find(landTypeID));
            Calendar cal = Calendar.getInstance();
            land.setDate(cal.getTime());

            land.setLandId(landId);


            if (!image.equals("")) {
                land.setImage(image);
            }

            landsFacade.edit(land);

            this.setAddress("");
            this.setNearBy("");
            this.setLandName("");

            FacesContext.getCurrentInstance().getExternalContext().redirect("lands.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void findLands(int id) {
        try {

            Lands l = landsFacade.find(id);
            this.landName = l.getLandName();
            this.landId = l.getLandId();
            this.landTypeID = l.getLandTypeID().getLandTypeID();
            this.description = l.getDescription();
            this.nearBy = l.getNearBy();
            this.address = l.getAddress();
            this.price = l.getPrice();
            this.image = l.getImage();

            FacesContext.getCurrentInstance().getExternalContext().redirect("editlands.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // delete lands
    public void deleteLands(int idc) {
        try {
            //landsFacade.remove(landsFacade.find(idc));
            Lands l = landsFacade.find(idc);
            l.setStatus(false);
            landsFacade.edit(l);

            this.setAddress("");
            this.setNearBy("");
            this.setLandName("");
            this.setAddress("");
            this.setPermit(0);

            FacesContext.getCurrentInstance().getExternalContext().redirect("lands.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LandsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Lands> getAllLandsDESC() {
        return landsFacade.getAllLandsDESC();
    }

    public void goLand() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        setL(landsFacade.find(Integer.parseInt(params.get("idLand"))));
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.endsWith("index.xhtml")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Home/land.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("land.xhtml");
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
            Logger.getLogger(LandsBean.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
    
    public List<SelectItem> getLandsItem() {
        List<SelectItem> rs = new LinkedList<SelectItem>();
        List<Lands> lst = landsFacade.getAllLandsDESC();
        for (Lands l : lst) {
            rs.add(new SelectItem(l.getLandId(),l.getLandName()));
        }
        return rs;
    }
}
