/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Buildings;
import eb.RoomTypes;
import eb.Rooms;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import entity.ERooms;
import java.math.BigDecimal;

/**
 *
 * @author VietDuc
 */
@ManagedBean
@RequestScoped
public class RoomBean {
//    @EJB
//    private PaymentsFacadeLocal paymentsFacade;
//    
//
//    @EJB
//    private RoomTypesFacadeLocal roomTypesFacade;
//    @EJB
//    private BuildingsFacadeLocal buildingsFacade;
//    @EJB
//    private RoomsFacadeLocal roomsFacade;
//    
//    
//    
//    
//    private int selectRoomType;
//    private int roomID;
//    
//    private BigDecimal square;
//    private int remainRoom;
//    private int totalRoom;
//    private int numberRoom;
//    private int roomNo;
//    private String Description;
//
//    
//    private String search;
//    
//    
//    private List<ERooms> listRoom;
//    private List<ERooms> listRoomSearch;
//    private List<SelectItem> listItemRoomType;
//    private List<SelectItem> listItRoomType;
//
//    /** Creates a new instance of RoomBean */
//    HttpSession httpSession;
//    public RoomBean() {
//         FacesContext context = FacesContext.getCurrentInstance();   
//         HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();   
//          httpSession = request.getSession(false);   
//    }
//    public int getBuildID(){
////        FacesContext context = FacesContext.getCurrentInstance();   
////         HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();   
////         HttpSession httpSession = request.getSession(false);   
//         int buildID = Integer.parseInt(httpSession.getAttribute("building").toString());
//         return buildID;
//    }
////    public List<ERooms> displayRoom(List<Rooms> listR, List<ERooms> list) {
////        for (Rooms rooms : listR) {
////            ERooms erooms = new ERooms();
////            erooms.setRoomID(rooms.getRoomId());
////            erooms.setBuildingName(rooms.getBuildingId().getBuildingName());
////            erooms.setRoomNo(rooms.getRoomNo());
////            erooms.setTotalSquare(rooms.getTotalSquare());
////            erooms.setTotalPrice(rooms.getTotalPrice());
////            erooms.setTypeRoom(rooms.getTypeId().getTypeName());
////            erooms.setDescription(rooms.getDescription());
////            String status = "";
////            int stt = rooms.getStatus();
////            if (stt == 0) {
////                status = "On Sale";
////            } else if (stt == 1) {
////                status = "Payment Not Received";
////            }
////             else if (stt == 2) {
////                status = "Yet to be Registered";
////            }
////             else if (stt == 3) {
////                status = "Registration and Stamp Duty is Done";
////            }
////            erooms.setStatus(status);
////            list.add(erooms);
////        }
////        return list;
////    }
//
//    public String getSearch() {
//        return search;
//    }
//
//    public void setSearch(String search) {
//        this.search = search;
//    }
//
//    public int getRoomNo() {
//        return roomNo;
//    }
//
//    public void setRoomNo(int roomNo) {
//        this.roomNo = roomNo;
//    }
//    
//    public int getNumberRoom() {
//        return numberRoom;
//    }
//
//    public void setNumberRoom(int numberRoom) {
//        this.numberRoom = numberRoom;
//    }
//
//    public int getRemainRoom() {
//        Buildings b = buildingsFacade.find(getBuildID());
//        remainRoom =totalRoom - b.getRoomsCollection().size();
//        return remainRoom; 
//    }
//
//    public void setRemainRoom(int remainRoom) {
//        this.remainRoom = remainRoom;
//    }
//    
//    public void setDescription(String Description) {
//        this.Description = Description;
//    }
//
//    public String getDescription() {
//        return Description;
//    }
//
////    public int getSquare() {
////        square = 100;
////        return square;
////    }
////
////    public void setSquare(int square) {
////        this.square = square;
////    }
//
//    public int getTotalRoom() {
//        Buildings b = buildingsFacade.find(getBuildID());
//        totalRoom = b.getTotalRooms();
//        return totalRoom;
//    }
//
//    public void setTotalRoom(int totalRoom) {
//        this.totalRoom = totalRoom;
//    }
//
// 
//
////    public List<ERooms> getListRoom() {
////       
////        List<ERooms> list = new ArrayList<ERooms>();
////        List<Rooms> listR;
////        int IDbuilding = getBuildID();
////        if (selectRoomType == 0) {
////            listR = roomsFacade.getAllRooms(IDbuilding);
////        }
////        else if(!search.isEmpty()){
////            listR = roomsFacade.searchByRoomNo(IDbuilding, selectRoomType, search);
////        }
////        else{
////             listR = roomsFacade.getRoomListByType(IDbuilding,selectRoomType) ;
////        }
////       return displayRoom(listR, list);
////    }
//
//    public void setListRoom(List<ERooms> listRoom) {
//        this.listRoom = listRoom;
//    }
//
//    public List<ERooms> getListRoomSearch() {
//      return listRoomSearch;
//    }
//
//    public void setListRoomSearch(List<ERooms> listRoomSearch) {
//        this.listRoomSearch = listRoomSearch;
//    }
//
//    public int getRoomID() {
//        return roomID;
//    }
//
//    public void setRoomID(int roomID) {
//        this.roomID = roomID;
//    }
//
//    public List<SelectItem> getListItemRoomType() {
//        List<SelectItem> listItem = new ArrayList<SelectItem>();
//        List<RoomTypes> listRT = roomTypesFacade.findAll();
//        for (RoomTypes roomTypes : listRT) {
//            SelectItem item = new SelectItem();
//            item.setValue(roomTypes.getTypeId());
//            item.setLabel(roomTypes.getTypeName());
//            listItem.add(item);
//        }
//        return listItem;
//    }
//
//    public void setListItemRoomType(List<SelectItem> listItemRoomType) {
//        this.listItemRoomType = listItemRoomType;
//    }
//
//    public List<SelectItem> getListItRoomType() {
//        List<SelectItem> listItem = new ArrayList<SelectItem>();
//        SelectItem i = new SelectItem(0,"All");
//        boolean add = listItem.add(i);
//        List<RoomTypes> listRT = roomTypesFacade.findAll();
//        for (RoomTypes roomTypes : listRT) {
//            SelectItem item = new SelectItem();
//            item.setValue(roomTypes.getTypeId());
//            item.setLabel(roomTypes.getTypeName());
//            listItem.add(item);
//        }
//        return listItem;
//    }
//
//    public void setListItRoomType(List<SelectItem> listItRoomType) {
//        this.listItRoomType = listItRoomType;
//    }
//
//    public int getSelectRoomType() {
//        return selectRoomType;
//    }
//
//    public void setSelectRoomType(int selectRoomType) {
//        this.selectRoomType = selectRoomType;
//    }
//
////    public String createNewRoom() {  
////
////        RoomTypes rt = roomTypesFacade.find(selectRoomType);
////        BigDecimal roomTypePrice = rt.getPrice();
////        BigDecimal totalPrice = square * roomTypePrice;
////
////        Buildings bd = buildingsFacade.find(getBuildID());       
////        int tRoom = bd.getTotalRooms();
////        int createdRoom = bd.getRoomsCollection().size();
////        int roomN = tRoom - createdRoom;
////        
////        for (int i = 0; i < numberRoom; i++) {
////            Rooms r = new Rooms();
////            if (roomN > 0) {
////                r.setRoomId(1);
////                r.setBuildingId(bd);
////                r.setRoomNo(roomN);
////                r.setTotalSquare(square);
////                r.setTotalPrice(totalPrice);
////                r.setTypeId(rt);
////                r.setStatus(false);
////                roomsFacade.create(r);
////                roomN--;
////        
////            }
////        }
////     
////            return "successRoom";
////    }
//    
////    public String updateRoom(){
////         RoomTypes rt = roomTypesFacade.find(selectRoomType);
////         BigDecimal roomTypePrice = rt.getPrice();
////         BigDecimal totalPrice = square * roomTypePrice;
////         Rooms r = roomsFacade.find(roomID);
////         r.setTotalSquare(square);
////         r.setTotalPrice(totalPrice);
////         r.setTypeId(rt);
////         
////         roomsFacade.edit(r);
////         return "successRoom";
////    }
//    
//    public String deleteRoom(){
//         Rooms r = roomsFacade.find(roomID);
//         roomsFacade.remove(r);
//         return "successRoom";
//    }
//     public String searchRoom(){           
//        return "SearchRoom";
//    }
//    public String addRooms(){           
//        return "CreateRoom";
//    }
//    public String mapRooms(){           
//        return "Room";
//    }
//    public String mappingPayment(int room){
//       
//         httpSession.setAttribute("rooms",room);
//       
//         return "Payments";
//    }
//     public String mappingRoomDetail(int room){
//       
//         httpSession.setAttribute("rooms",room);
//         return "roomDetailPage";
//    }
//    
}
