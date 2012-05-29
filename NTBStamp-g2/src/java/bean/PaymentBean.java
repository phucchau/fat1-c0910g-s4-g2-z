/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import entity.EPayments;
import java.util.Collection;

/**
 *
 * @author VietDuc
 */
@ManagedBean
@RequestScoped
public class PaymentBean {

//    @EJB
//    private PaymentsFacadeLocal paymentsFacade;
//    @EJB
//    private CustomerFacadeLocal customerFacade;
//    @EJB
//    private RoomsFacadeLocal roomsFacade;
//    /** Creates a new instance of PaymentBean */
//    private int roomID;
//    List<EPayments> listPayments;
//
//    public PaymentBean() {
//    }
//    HttpSession httpSession;
//    public int getRoom() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        httpSession = request.getSession(false);
//        int room = Integer.parseInt(httpSession.getAttribute("rooms").toString());
//        return room;
//    }
//
//    public List<EPayments> getListPayments() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        List<EPayments> list = new ArrayList<EPayments>();
//        Collection<Payments> listP = paymentsFacade.getPaymentByRoom(getRoom());
//        for (Payments payments : listP) {
//            EPayments ep = new EPayments();
//            ep.setPaymentID(payments.getPaymentID());
//            ep.setRoomID(payments.getRoomID().getRoomId());
//            ep.setCustomerID(payments.getCustomerID().getCustomerName());
//            ep.setPayment(payments.getPayment());
//            ep.setPaydate(dateFormat.format(payments.getPaydate()));
//            ep.setPayCharge(payments.getPayChargeID().getChargename());
//            String status = "";
//            int stt = payments.getStatus();
//            if (stt == 0) {
//                status = "Not Payment";
//            } else if (stt == 1) {
//                status = "Payment";
//            }
//            ep.setStatus(status);
//
//            list.add(ep);
//        }
//        return list;
//    }
//
//    public void setListPayments(List<EPayments> listPayments) {
//        this.listPayments = listPayments;
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
//    public void pay(int paymentId) {
//        Payments p = paymentsFacade.find(paymentId);
//        if (p.getStatus() == 0) {
//            p.setPaydate(new Date());
//            p.setStatus(1);
//            paymentsFacade.edit(p);
//        }
//        int room = Integer.parseInt(httpSession.getAttribute("rooms").toString());
//         int flag = 1;
//         Collection<Payments> lr = roomsFacade.find(room).getPaymentsList();
//         Rooms r = roomsFacade.find(room);
//         for (Payments payments : lr) {
//             if (payments.getStatus() == 0 ) {
//                 flag = 0;
//                 break;
//             }
//             flag = 1;
//            
//         }
//         if (flag == 1) {
//             r.setStatus(true);
//             roomsFacade.edit(r);
//         }
//
//    }
}
