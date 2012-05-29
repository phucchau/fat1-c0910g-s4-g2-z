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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    private String choosetype = "none";
    private String choosemoney = "none";
    private String ipname;

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

    public void setIpname(String ipname) {
        this.ipname = ipname;
    }

    /** Creates a new instance of SearchBean */
    public SearchBean() {
    }

    public void processSearch() {
        list = new ArrayList<OSearch>();
        if (choosetype.equals("none")) {
        } else {
            if (choosetype.equals("Lands") && choosemoney.equals("none") && ipname.isEmpty()) {
                process1();
            }
            if (choosetype.equals("Buildings") && choosemoney.equals("none") && ipname.isEmpty()) {
                process2();
            }
            if (choosetype.equals("Rooms") && choosemoney.equals("none") && ipname.isEmpty()) {
                process3();
            }
            if (choosetype.equals("Lands") && choosemoney.equals("none") && ipname.trim().length() > 1) {
                process4();
            }
            if (choosetype.equals("Buildings") && choosemoney.equals("none") && ipname.trim().length() > 1) {
                process5();
            }
            if (choosetype.equals("Rooms") && choosemoney.equals("none") && ipname.trim().length() > 1) {
                process6();
            }
            if (choosetype.equals("Lands") && choosemoney.equalsIgnoreCase("none") && ipname.isEmpty()) {
                process7();
            }
            if (choosetype.equals("Buildings") && choosemoney.equalsIgnoreCase("none") && ipname.isEmpty()) {
                process8();
            }
            if (choosetype.equals("Rooms") && choosemoney.equalsIgnoreCase("none") && ipname.isEmpty()) {
                process9();
            }
            if (choosetype.equals("Lands") && choosemoney.equalsIgnoreCase("none") && ipname.trim().length() > 1) {
                process10();
            }
            if (choosetype.equals("Buildings") && choosemoney.equalsIgnoreCase("none") && ipname.trim().length() > 1) {
                process11();
            }
            if (choosetype.equals("Rooms") && choosemoney.equalsIgnoreCase("none") && ipname.trim().length() > 1) {
                process12();
            }
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
    }

    public void process8() {
    }

    public void process9() {
    }

    public void process10() {
    }

    public void process11() {
    }

    public void process12() {
    }
}
