/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Customers;
import eb.CustomersFacade;
import eb.Payments;
import eb.PaymentsFacade;
import eb.Rooms;
import eb.RoomsFacade;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
public class RoomsBean {

    @EJB
    private CustomersFacade customersFacade;
    @EJB
    private PaymentsFacade paymentsFacade;
    @EJB
    private RoomsFacade roomsFacade;
    private Rooms r;
    private String Cusname;
    private String sex = "Male";
    private String phone;
    private String address;
    private String email;

    public String getCusname() {
        return Cusname;
    }

    public void setCusname(String Cusname) {
        this.Cusname = Cusname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Rooms getR() {
        return r;
    }

    public void setR(Rooms r) {
        this.r = r;
    }

    /** Creates a new instance of RoomsBean */
    public RoomsBean() {
    }

    @PostConstruct
    public List<Rooms> getAllRoomsDESC() {
        return roomsFacade.getAllRoomsDESC();
    }

    public void goRoom() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        setR(roomsFacade.find(Integer.parseInt(params.get("idRoom"))));
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.endsWith("index.xhtml")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Home/room.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("room.xhtml");
        }
    }

    public void registerCus() {
        try {
            if (paymentsFacade.checkRoomNo(r.getRoomId())) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Room have been set", " Room have been set !");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_YEAR,3);
                Date date = cal.getTime(); 
                
                Customers cus = new Customers();
                cus.setCustomerName(Cusname);
                cus.setSex(sex);
                cus.setPhone(phone);
                cus.setAddress(address);
                cus.setEmail(email);
                cus.setStatus(true);
                customersFacade.create(cus);
                Payments pay = new Payments();
                pay.setPaydate(date);
                pay.setStatus(0);
                pay.setCustomerID(cus);
                pay.setRoomID(r);
                paymentsFacade.create(pay);
                setCusname("");
                setSex("Male");
                setPhone("");
                setAddress("");
                setEmail("");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Register sucessed"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", " Have error !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
