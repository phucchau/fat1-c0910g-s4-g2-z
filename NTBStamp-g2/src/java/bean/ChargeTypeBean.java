/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.ChargeType;
import eb.ChargeTypeFacade;
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
public class ChargeTypeBean {
    @EJB
    private ChargeTypeFacade chargeTypeFacade;
    public ChargeType c = null;
    
    
    public Integer ChargeId;
    public String Charge;

    public String getCharge() {
        return Charge;
    }

    public void setCharge(String Charge) {
        this.Charge = Charge;
    }

    public Integer getChargeId() {
        return ChargeId;
    }

    public void setChargeId(Integer ChargeId) {
        this.ChargeId = ChargeId;
    }
    
    
    /** Creates a new instance of ChargeTypeBean */
    public ChargeTypeBean() {
    }
    
    public ChargeTypeBean(Integer ChargeId,String Charge){
        this.ChargeId = ChargeId;
        this.Charge = Charge;
    }
    
    
    // Load all chargetType
    
    public List<ChargeType> getListChargetType(){
        return  chargeTypeFacade.getAllChargeTypeDESC();
    }
    
    // create chargetType
    
    public void createChargetType() {
        try {
            c = new ChargeType();
            c.setCharge(Charge);
            chargeTypeFacade.create(c);
            this.Charge="";
            FacesContext.getCurrentInstance().getExternalContext().redirect("chargetype.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // update chargetType
    public void updateChargetType() {
        try {

            c = new ChargeType();
            c.setCharge(Charge);
            c.setChargeId(ChargeId);

            chargeTypeFacade.edit(c);
            this.setCharge("");
            FacesContext.getCurrentInstance().getExternalContext().redirect("chargetype.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void findChargetType(int id) {
        try {
            ChargeType c = chargeTypeFacade.find(id);
            this.ChargeId = c.getChargeId();
            this.Charge = c.getCharge();

            FacesContext.getCurrentInstance().getExternalContext().redirect("editchargetype.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xóa category
    public void deleteChargetType(int idc) {
        try {
            chargeTypeFacade.remove(chargeTypeFacade.find(idc));
            FacesContext.getCurrentInstance().getExternalContext().redirect("chargetype.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ChargeTypeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    
}
