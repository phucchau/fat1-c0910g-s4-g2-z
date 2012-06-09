/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Buildings;
import eb.BuildingsFacade;
import eb.Lands;
import eb.LandsFacade;
import eb.Rooms;
import eb.RoomsFacade;
import entity.OSearch;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TRUONG
 */
@ManagedBean
@SessionScoped
public class SearchBean {

    @EJB
    private RoomsFacade roomsFacade;
    @EJB
    private BuildingsFacade buildingsFacade;
    @EJB
    private LandsFacade landsFacade;
    private ArrayList<OSearch> list;
    private String choosetype = "Lands";
    private String choosemoney = "none";
    private String choosesquare = "none";
    private String ipname;

    public String getChoosesquare() {
        return choosesquare;
    }

    public void setChoosesquare(String choosesquare) {
        this.choosesquare = choosesquare;
    }

    public ArrayList<OSearch> getList() {
        return list;
    }

    public void setList(ArrayList<OSearch> list) {
        this.list = list;
    }

    public String getChoosemoney() {
        return choosemoney;
    }

    public void setChoosemoney(String choosemoney) {
        this.choosemoney = choosemoney;
    }

    public String getChoosetype() {
        return choosetype;
    }

    public void setChoosetype(String choosetype) {
        this.choosetype = choosetype;
    }

    public String getIpname() {
        return ipname;
    }

    public void setIpname(String ipname) throws UnsupportedEncodingException {
        this.ipname = new String(ipname.getBytes("ISO-8859-1"), "UTF-8");
    }

    /** Creates a new instance of SearchBean */
    public SearchBean() {
    }

    public void processSearch() throws IOException {
        list = new ArrayList<OSearch>();
        if (choosetype.equals("Lands") && choosemoney.equals("none") && ipname.isEmpty()) {
            process1();
        }
        if (choosetype.equals("Buildings") && choosemoney.equals("none") && ipname.isEmpty()) {
            process2();
        }
        if (choosetype.equals("Rooms") && choosemoney.equals("none") && ipname.isEmpty() && choosesquare.equals("none")) {
            process3();
        }
        if (choosetype.equals("Lands") && choosemoney.equals("none") && ipname.trim().length() >= 1) {
            process4();
        }
        if (choosetype.equals("Buildings") && choosemoney.equals("none") && ipname.trim().length() >= 1) {
            process5();
        }
        if (choosetype.equals("Rooms") && choosemoney.equals("none") && ipname.trim().length() >= 1 && choosesquare.equals("none")) {
            process6();
        }
        if (choosetype.equals("Lands") && !"none".equals(choosemoney) && ipname.isEmpty()) {
            process7();
        }
        if (choosetype.equals("Buildings") && !"none".equals(choosemoney) && ipname.isEmpty()) {
            process8();
        }
        if (choosetype.equals("Rooms") && !"none".equals(choosemoney) && ipname.isEmpty() && choosesquare.equals("none")) {
            process9();
        }
        if (choosetype.equals("Lands") && !"none".equals(choosemoney) && ipname.trim().length() >= 1) {
            process10();
        }
        if (choosetype.equals("Buildings") && !"none".equals(choosemoney) && ipname.trim().length() >= 1) {
            process11();
        }
        if (choosetype.equals("Rooms") && !"none".equals(choosemoney) && ipname.trim().length() >= 1 && choosesquare.equals("none")) {
            process12();
        }
        if (choosetype.equals("Rooms") && choosemoney.equals("none") && ipname.isEmpty() && !"none".equals(choosesquare)) {
            process13();
        }
        if (choosetype.equals("Rooms") && !"none".equals(choosemoney) && ipname.isEmpty() && !"none".equals(choosesquare)) {
            process14();
        }
        if (choosetype.equals("Rooms") && choosemoney.equals("none") && ipname.trim().length() >= 1 && !"none".equals(choosesquare)) {
            process15();
        }
        if (choosetype.equals("Rooms") && !"none".equals(choosemoney) && ipname.trim().length() >= 1 && !"none".equals(choosesquare)) {
            process16();
        }
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.endsWith("index.xhtml")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Home/search.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("search.xhtml");
        }
    }

    public void process1() {
        List<Lands> listland = landsFacade.getAllLandsDESC();
        for (Lands lands : listland) {
            OSearch os = new OSearch();
            os.setId(lands.getLandId());
            os.setTitle(lands.getLandName());
            os.setImage(lands.getImage());
            os.setDescription(lands.getDescription());
            os.setType("lands");
            list.add(os);
        }
    }

    public void process2() {
        List<Buildings> listbuild = buildingsFacade.getAllBuildingsDESC();
        for (Buildings build : listbuild) {
            OSearch os = new OSearch();
            os.setId(build.getBuildingId());
            os.setTitle(build.getBuildingName());
            os.setImage(build.getImage());
            os.setDescription(build.getDescription());
            os.setType("building");
            list.add(os);
        }
    }

    public void process3() {
        List<Rooms> listroom = roomsFacade.getAllRoomsDESC();
        for (Rooms room : listroom) {
            OSearch os = new OSearch();
            os.setId(room.getRoomId());
            os.setTitle(String.valueOf(room.getRoomNo()));
            os.setImage(room.getImage());
            os.setDescription(room.getDescription());
            os.setType("room");
            list.add(os);
        }
    }

    public void process4() {
        System.out.print(ipname);
        List<Lands> listland = landsFacade.getAllLandsLikebyName(ipname);
        for (Lands lands : listland) {
            OSearch os = new OSearch();
            os.setId(lands.getLandId());
            os.setTitle(lands.getLandName());
            os.setImage(lands.getImage());
            os.setDescription(lands.getDescription());
            os.setType("lands");
            list.add(os);
        }
    }

    public void process5() {
        List<Buildings> listbuild = buildingsFacade.getAllBuildingsLikebyName(ipname);
        for (Buildings build : listbuild) {
            OSearch os = new OSearch();
            os.setId(build.getBuildingId());
            os.setTitle(build.getBuildingName());
            os.setImage(build.getImage());
            os.setDescription(build.getDescription());
            os.setType("building");
            list.add(os);
        }
    }

    public void process6() {
        List<Rooms> listroom = roomsFacade.getAllRoomLikebyName(ipname);
        for (Rooms room : listroom) {
            OSearch os = new OSearch();
            os.setId(room.getRoomId());
            os.setTitle(String.valueOf(room.getRoomNo()));
            os.setImage(room.getImage());
            os.setDescription(room.getDescription());
            os.setType("room");
            list.add(os);
        }
    }

    public void process7() {
        List<Lands> listland = landsFacade.getAllLandsbyPrice(choosemoney);
        for (Lands lands : listland) {
            OSearch os = new OSearch();
            os.setId(lands.getLandId());
            os.setTitle(lands.getLandName());
            os.setImage(lands.getImage());
            os.setDescription(lands.getDescription());
            os.setType("lands");
            list.add(os);
        }
    }

    public void process8() {
        List<Buildings> listbuild = buildingsFacade.getAllBuildingsbyPrice(choosemoney);
        for (Buildings build : listbuild) {
            OSearch os = new OSearch();
            os.setId(build.getBuildingId());
            os.setTitle(build.getBuildingName());
            os.setImage(build.getImage());
            os.setDescription(build.getDescription());
            os.setType("building");
            list.add(os);
        }
    }

    public void process9() {
        List<Rooms> listroom = roomsFacade.getAllRoomsbyPrice(choosemoney);
        for (Rooms room : listroom) {
            OSearch os = new OSearch();
            os.setId(room.getRoomId());
            os.setTitle(String.valueOf(room.getRoomNo()));
            os.setImage(room.getImage());
            os.setDescription(room.getDescription());
            os.setType("room");
            list.add(os);
        }
    }

    public void process10() {
        List<Lands> listland = landsFacade.getAllLandsbyNameandPrice(ipname, choosemoney);
        for (Lands lands : listland) {
            OSearch os = new OSearch();
            os.setId(lands.getLandId());
            os.setTitle(lands.getLandName());
            os.setImage(lands.getImage());
            os.setDescription(lands.getDescription());
            os.setType("lands");
            list.add(os);
        }
    }

    public void process11() {
        List<Buildings> listbuild = buildingsFacade.getAllBuildingsbyNameandPrice(ipname, choosemoney);
        for (Buildings build : listbuild) {
            OSearch os = new OSearch();
            os.setId(build.getBuildingId());
            os.setTitle(build.getBuildingName());
            os.setImage(build.getImage());
            os.setDescription(build.getDescription());
            os.setType("building");
            list.add(os);
        }
    }

    public void process12() {
        List<Rooms> listroom = roomsFacade.getAllRoomsbyNameandPrice(ipname, choosemoney);
        for (Rooms room : listroom) {
            OSearch os = new OSearch();
            os.setId(room.getRoomId());
            os.setTitle(String.valueOf(room.getRoomNo()));
            os.setImage(room.getImage());
            os.setDescription(room.getDescription());
            os.setType("room");
            list.add(os);
        }
    }

    public void process13() {
        List<Rooms> listroom = roomsFacade.getAllRoomsbySquare(choosesquare);
        for (Rooms room : listroom) {
            OSearch os = new OSearch();
            os.setId(room.getRoomId());
            os.setTitle(String.valueOf(room.getRoomNo()));
            os.setImage(room.getImage());
            os.setDescription(room.getDescription());
            os.setType("room");
            list.add(os);
        }
    }

    public void process14() {
        List<Rooms> listroom = roomsFacade.getAllRoomsbySquareandPrice(choosesquare, choosemoney);
        for (Rooms room : listroom) {
            OSearch os = new OSearch();
            os.setId(room.getRoomId());
            os.setTitle(String.valueOf(room.getRoomNo()));
            os.setImage(room.getImage());
            os.setDescription(room.getDescription());
            os.setType("room");
            list.add(os);
        }
    }

    public void process15() {
        List<Rooms> listroom = roomsFacade.getAllRoomsbyNameandSquare(ipname, choosesquare);
        for (Rooms room : listroom) {
            OSearch os = new OSearch();
            os.setId(room.getRoomId());
            os.setTitle(String.valueOf(room.getRoomNo()));
            os.setImage(room.getImage());
            os.setDescription(room.getDescription());
            os.setType("room");
            list.add(os);
        }
    }

    public void process16() {
        List<Rooms> listroom = roomsFacade.getAllRoomsbyNameandSquareandPrice(ipname, choosesquare, choosemoney);
        for (Rooms room : listroom) {
            OSearch os = new OSearch();
            os.setId(room.getRoomId());
            os.setTitle(String.valueOf(room.getRoomNo()));
            os.setImage(room.getImage());
            os.setDescription(room.getDescription());
            os.setType("room");
            list.add(os);
        }
    }
}
