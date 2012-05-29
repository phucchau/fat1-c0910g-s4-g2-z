/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.LandTypes;
import eb.LandTypesFacade;
import java.io.IOException;
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
public class LandtypeBean {

    @EJB
    private LandTypesFacade landTypesFacade;
    public LandTypes landt;
    public Integer landTypeID;
    public String landName;

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

    /** Creates a new instance of LandtypeBean */
    public LandtypeBean() {
    }

    public LandtypeBean(Integer landTypeID, String landName) {
        this.landTypeID = landTypeID;
        this.landName = landName;
    }

    // Load all LandTypes
    public List<LandTypes> getListLandTypes() {
        return landTypesFacade.getAllLandTypesDESC();
    }

    // create LandTypes
    public void createLandTypes() {
        try {
            landt = new LandTypes();
            landt.setLandName(landName);
            landTypesFacade.create(landt);
            
            
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("landType.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update LandTypes
    public void updateLandTypes() {
        try {

            landt = new LandTypes();
            landt.setLandName(landName);
            landt.setLandTypeID(landTypeID);

            landTypesFacade.edit(landt);

            FacesContext.getCurrentInstance().getExternalContext().redirect("landType.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void findLandTypes(int id) {
        try {

            LandTypes lt = landTypesFacade.find(id);
            this.landName = lt.getLandName();
            this.landTypeID = lt.getLandTypeID();

            FacesContext.getCurrentInstance().getExternalContext().redirect("editlandtype.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xóa LandTypes
    public void deleteLandTypes(int idc) {
        try {
            landTypesFacade.remove(landTypesFacade.find(idc));
            FacesContext.getCurrentInstance().getExternalContext().redirect("landType.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LandtypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<SelectItem> getLandTypesItem() {
        List<SelectItem> rs = new LinkedList<SelectItem>();
        List<LandTypes> lst = landTypesFacade.getAllLandTypesDESC();
        for (LandTypes l : lst) {
            rs.add(new SelectItem(l.getLandTypeID(),l.getLandName()));
        }
        return rs;
    }
}
