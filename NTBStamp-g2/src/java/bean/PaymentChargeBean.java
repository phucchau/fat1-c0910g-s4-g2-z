/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.PaymentCharge;
import eb.PaymentChargeFacade;
import java.io.IOException;
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
public class PaymentChargeBean {
    @EJB
    private PaymentChargeFacade paymentChargeFacade;

    public Integer PayChargeID;
    public String Chargename;
    public Double Interest;
    public Integer PayChargeTime;
    public PaymentCharge pc;
    public String getChargename() {
        return Chargename;
    }

    public void setChargename(String Chargename) {
        this.Chargename = Chargename;
    }

    public Double getInterest() {
        return Interest;
    }

    public void setInterest(Double Interest) {
        this.Interest = Interest;
    }

    public Integer getPayChargeID() {
        return PayChargeID;
    }

    public void setPayChargeID(Integer PayChargeID) {
        this.PayChargeID = PayChargeID;
    }

    public Integer getPayChargeTime() {
        return PayChargeTime;
    }

    public void setPayChargeTime(Integer PayChargeTime) {
        this.PayChargeTime = PayChargeTime;
    }
    
    /** Creates a new instance of PaymentChargeBean */
    public PaymentChargeBean() {
    }

    public PaymentChargeBean(Integer PayChargeID, String Chargename, Double Interest, Integer PayChargeTime) {
        this.PayChargeID = PayChargeID;
        this.Chargename = Chargename;
        this.Interest = Interest;
        this.PayChargeTime = PayChargeTime;
    }
    
    // Load all PaymentCharge
    public List<PaymentCharge> getListAllPaymentCharge() {
        return paymentChargeFacade.getAllPaymentChargerDESC();
    }

    // create PaymentCharge
    public void createAllPaymentCharge() {
        try {

            pc = new PaymentCharge();
            pc.setChargename(Chargename);
            pc.setInterest(Interest);
            pc.setPayChargeTime(PayChargeTime);
           
            paymentChargeFacade.create(pc);
            this.Chargename = "";
            this.Interest = 0.0;
            this.PayChargeTime = 0;
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("PaymentCharge.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update PaymentCharge
    public void updatePaymentCharge() {
        try {

            pc = new PaymentCharge();
            pc.setPayChargeID(PayChargeID);
            pc.setChargename(Chargename);
            pc.setInterest(Interest);
            pc.setPayChargeTime(PayChargeTime);

            paymentChargeFacade.edit(pc);
            this.Chargename = "";
            this.Interest = 0.0;
            this.PayChargeTime = 0;
            FacesContext.getCurrentInstance().getExternalContext().redirect("PaymentCharge.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void findPaymentCharge(int id) {
        try {

            PaymentCharge p = paymentChargeFacade.find(id);
            this.setPayChargeID(p.getPayChargeID());
            this.setChargename(p.getChargename());
            this.setInterest(p.getInterest());
            this.setPayChargeTime(p.getPayChargeTime());

            FacesContext.getCurrentInstance().getExternalContext().redirect("EditPaymentCharge.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xóa PaymentCharge
    public void deletePaymentCharge(int idc) {
        try {
            PaymentCharge p = paymentChargeFacade.find(idc);
            paymentChargeFacade.remove(p);

            FacesContext.getCurrentInstance().getExternalContext().redirect("PaymentCharge.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PaymentChargeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
