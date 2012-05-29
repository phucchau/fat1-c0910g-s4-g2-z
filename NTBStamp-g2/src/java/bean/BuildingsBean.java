/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Buildings;
import eb.BuildingsFacade;
import eb.Lands;
import eb.Rooms;
import eb.RoomsFacade;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
    private BuildingsFacade buildingsFacade;
    private Buildings b;
   
    public Buildings getB() {
        return b;
    }

    public void setB(Buildings b) {
        this.b = b;
    }

    /** Creates a new instance of BuildingsBean */
    public BuildingsBean() {
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
}
