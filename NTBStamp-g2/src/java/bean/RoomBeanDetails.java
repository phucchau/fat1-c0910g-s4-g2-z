/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Payments;
import eb.Rooms;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import entity.ERooms;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author VietDuc
 */
@ManagedBean
@RequestScoped
public class RoomBeanDetails {
//    @EJB
//    private PaymentsFacadeLocal paymentsFacade;
//    @EJB
//    private DocumentFacadeLocal documentInfoFacade;
//   
//    @EJB
//    private ChargeTypeFacadeLocal chargeTypeFacade;
//    @EJB
//    private RoomsFacadeLocal roomsFacade;
//    
//    
//    
//    ERooms er = new ERooms();
//    private double Stamcharge;
//    private BigDecimal totalRemain;
//    private List<ERooms> listRoomDetails;
//    
//    /** Creates a new instance of RoomBeanDetails */
//      HttpSession httpSession;
//    public RoomBeanDetails() {
//         FacesContext context = FacesContext.getCurrentInstance();   
//         HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();   
//         httpSession = request.getSession(false);   
//    }
//
//    public double getStamcharge() {
//       
//        return Stamcharge;
//    }
//
//    public void setStamcharge(double Stamcharge) {
//        this.Stamcharge = Stamcharge;
//    }
//
//    public ERooms getEr() {
//        return er;
//    }
//
//    public void setEr(ERooms er) {
//        this.er = er;
//    }
//
////    public double getTotalRemain() {
////        int room = Integer.parseInt(httpSession.getAttribute("rooms").toString());
////        
////         Collection<Payments> lr = roomsFacade.find(room).getPaymentsList();
////         Rooms r = roomsFacade.find(room);
////         
////         BigDecimal total = r.getTotalPrice();
////         double remain =  0.0;
////         for (Payments payments : lr) {
////             if (payments.getStatus() == 1) {
////                
////                     double payment = payments.getPayment();
////                     remain  +=  total - payment; 
////                 
////             }
////         }
////        return remain;
////    }
//
//    public void setTotalRemain(BigDecimal totalRemain) {
//        this.totalRemain = totalRemain;
//    }
//
////    public List<ERooms> getListRoomDetails() {
////        listRoomDetails = new ArrayList<ERooms>();
////        
////        int room = Integer.parseInt(httpSession.getAttribute("rooms").toString());
////        int building = Integer.parseInt(httpSession.getAttribute("building").toString());    
////        Stamcharge =   documentInfoFacade.find(building).getChargeId().getCharge();
////     
////        List<Payments> lr =paymentsFacade.getPaymentByRoom(room);//roomsFacade.find(room).getPaymentsList();
////        Rooms r = roomsFacade.find(room);
////        er.setRoomID(room);
////        er.setRoomNo(r.getRoomNo());
////        er.setBuildingName("Building");
////        er.setTotalSquare(r.getTotalSquare());
////        er.setTotalPrice(r.getTotalPrice());
////        er.setTypeRoom(String.valueOf(Stamcharge));
////        
////         BigDecimal total = r.getTotalPrice();
////         BigDecimal remain = "";
////         boolean flag = true;
////         for (Payments payments : lr) {
////             if (payments.getStatus() == 1) {
////                     flag = false;
////                     BigDecimal payment = payments.getPayment();
////                     remain  =  total - payment; 
////                    
////             }
////            
////                  
////         }
////         if(flag == true){
////             remain = total;
////         }
////         if(roomsFacade.find(room).getStatus() == true){
////              remain  = 250.0; 
////         }
////        er.setTotalremain(remain);
////         String status = "";
////            int stt = r.getStatus();
////            if (stt == 0) {
////                status = "On Sale";
////            } else if (stt == 1) {
////                status = "Payment Not Received";
////            }
////                    
////             else if (stt == 2) {
////                status = "Yet to be Registered";
////            }
////             else if (stt == 3) {
////                status = "Registration and Stamp Duty is Done";
////            }
////        er.setStatus(status);
////         listRoomDetails.add(er);
////         
////        return listRoomDetails;
////    }
//
//    public void setListRoomDetails(List<ERooms> listRoomDetails) {
//        
//        this.listRoomDetails = listRoomDetails;
//    }
//     public String registerNowButton(){
//         int room = Integer.parseInt(httpSession.getAttribute("rooms").toString());
//         Rooms r = roomsFacade.find(room);    
//         if (r.getStatus() == true) {
//             r.setStatus(false);
//             roomsFacade.edit(r);       
//         }       
//         return "roomDetailPage";
//     }
}
