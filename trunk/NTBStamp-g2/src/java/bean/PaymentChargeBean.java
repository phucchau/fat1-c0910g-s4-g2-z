/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.PaymentCharge;
import eb.PaymentChargeFacade;
import eb.RoomTypes;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author VietDuc
 */
@ManagedBean
@RequestScoped
public class PaymentChargeBean {
    @EJB
    private PaymentChargeFacade paymentChargeFacade;    
    private int payChargeID;
    private String chargeName;
    private double interest;
    private int month;
    private List<PaymentCharge> listPaymentCharge;
    /** Creates a new instance of PaymentChargeBean */
    public PaymentChargeBean() {
        
    }

    public int getPayChargeID() {
        return payChargeID;
    }

    public void setPayChargeID(int payChargeID) {
        this.payChargeID = payChargeID;
    }
    
    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    public List<PaymentCharge> getListPaymentCharge() {
        List<PaymentCharge> list  = new ArrayList<PaymentCharge>();
        List<PaymentCharge> listPC = paymentChargeFacade.findAll();
        for (PaymentCharge paymentCharge : listPC) {
            list.add(paymentCharge);
        }
        return list;
    }

    public void setListPaymentCharge(List<PaymentCharge> listPaymentCharge) {
        this.listPaymentCharge = listPaymentCharge;
    }
    public String createPaymentCharge(){
        PaymentCharge pc = new PaymentCharge();
        pc.setPayChargeID(1);
        pc.setChargename(chargeName);
        pc.setInterest(interest);
        pc.setPayChargeTime(month);
        paymentChargeFacade.create(pc);                
        return "paymentChargePage";
    }
    public String updatePaymentCharge(){
       PaymentCharge pc = paymentChargeFacade.find(payChargeID);
       
        pc.setChargename(chargeName);
        pc.setInterest(interest);
        pc.setPayChargeTime(month);
        paymentChargeFacade.edit(pc);     
        return "paymentChargePage";
    }
     public String deletePaymentCharge(){
       PaymentCharge pc = paymentChargeFacade.find(payChargeID);
       
        
        paymentChargeFacade.remove(pc);     
        return "paymentChargePage";
    }
     
     public void findPaymentCharge(int id) {
        try {
            PaymentCharge pc = paymentChargeFacade.find(id);
            this.payChargeID = pc.getPayChargeID();
            this.chargeName = pc.getChargename();
            this.interest = pc.getInterest();
            this.month = pc.getPayChargeTime();

            FacesContext.getCurrentInstance().getExternalContext().redirect("EditPaymentCharge.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
