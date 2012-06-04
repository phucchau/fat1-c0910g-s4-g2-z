/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.CustomersFacade;
import eb.PaymentChargeFacade;
import eb.Payments;
import eb.PaymentsFacade;
import eb.Rooms;
import eb.RoomsFacade;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DuMaster
 */
@ManagedBean
@SessionScoped
public class PaymentBean {
    @EJB
    private PaymentChargeFacade paymentChargeFacade;
    @EJB
    private RoomsFacade roomsFacade;
    @EJB
    private CustomersFacade customersFacade;
    @EJB
    private PaymentsFacade paymentsFacade;
    
    public Integer PaymentID;
    public Integer RoomID;
    public Integer CustomerID;
    public Double Payment;
    public Date Paydate;
    public Integer PayChargeID;
    public Integer Status;
    
    public Rooms selectRooms;

    public Rooms getSelectRooms() {
        return selectRooms;
    }

    public void setSelectRooms(Rooms selectRooms) {
        this.selectRooms = selectRooms;
    }
    
    public Integer CodeCustomer;
    
    public Payments pm ;

    public Integer getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(Integer CustomerID) {
        this.CustomerID = CustomerID;
    }

    public Integer getPayChargeID() {
        return PayChargeID;
    }

    public void setPayChargeID(Integer PayChargeID) {
        this.PayChargeID = PayChargeID;
    }

    public Date getPaydate() {
        return Paydate;
    }

    public void setPaydate(Date Paydate) {
        this.Paydate = Paydate;
    }

    public Double getPayment() {
        return Payment;
    }

    public void setPayment(Double Payment) {
        this.Payment = Payment;
    }

    public Integer getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(Integer PaymentID) {
        this.PaymentID = PaymentID;
    }

    public Integer getRoomID() {
        return RoomID;
    }

    public void setRoomID(Integer RoomID) {
        this.RoomID = RoomID;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    public PaymentBean(Integer PaymentID, Integer RoomID, Integer CustomerID, Double Payment, Date Paydate, Integer PayChargeID, Integer Status) {
        this.PaymentID = PaymentID;
        this.RoomID = RoomID;
        this.CustomerID = CustomerID;
        this.Payment = Payment;
        this.Paydate = Paydate;
        this.PayChargeID = PayChargeID;
        this.Status = Status;
    }
    
    
    /** Creates a new instance of PaymentBean */
    public PaymentBean() {
    }
    
     // Load all Payments
   
    
    public List<Payments> getListPaymentsType(){
        return  paymentsFacade.getAllPaymentsDESC(CodeCustomer);
    }
    
    
    // update Payments
    public void updatePayments(Integer id,Double payment) {
        try {
            
            Payments pm = paymentsFacade.find(id);
            pm.setPayment(payment);
            paymentsFacade.edit(pm);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("Payments.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void findPayments(int id) {
        try 
        {
            Payments pm = paymentsFacade.find(id);
            this.PaymentID = pm.getPaymentID();
            this.CustomerID = pm.getCustomerID().getCustomerId();
            this.PayChargeID = pm.getPayChargeID().getPayChargeID();
            this.Paydate = pm.getPaydate();
            this.RoomID = pm.getRoomID().getRoomId();
            this.Status = pm.getStatus();
            this.Payment = pm.getPayment();
            

            FacesContext.getCurrentInstance().getExternalContext().redirect("editPayments.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public void inforPayment(Integer id) {
        try 
        {
            this.CodeCustomer = id;
            System.out.print("kq:"+id);
            FacesContext.getCurrentInstance().getExternalContext().redirect("Payments.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xóa Payments
    public void deletePayments(int idc) {
        try {
            Payments pm = paymentsFacade.find(idc);
            pm.setStatus(1);
            paymentsFacade.edit(pm);
            FacesContext.getCurrentInstance().getExternalContext().redirect("Payments.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PaymentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
