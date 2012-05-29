/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Customers;
import eb.CustomersFacade;
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
public class CustomersBean {
    @EJB
    private CustomersFacade customersFacade;

    public Customers cus ;
    public Integer customerId;
    public String customerName;
    public String sex = "Male";
    public String address;
    public String phone;
    public String email;
    public boolean status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    /** Creates a new instance of CustomersBean */
    public CustomersBean() {
    }
    
    // Load all customer
    
    public List<Customers> getListCustomer(){
        return  customersFacade.getAllCustomerDESC();
    }
    
    // create customer
    
    public void createCustomer() {
        try {
            cus = new Customers();
            cus.setAddress(address);
            cus.setCustomerName(customerName);
            cus.setEmail(email);
            cus.setPhone(phone);
            cus.setSex(sex);
            cus.setAddress(address);
            cus.setStatus(true);
            
            customersFacade.create(cus);
            this.address = "";
            this.customerName = "";
            this.email = "";
            this.phone = "";
            
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("customers.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // update account
    public void updateCustomer() {
        try {

            cus = new Customers();
            cus.setCustomerId(customerId);
            cus.setAddress(address);
            cus.setCustomerName(customerName);
            cus.setEmail(email);
            cus.setPhone(phone);
            cus.setSex(sex);
            cus.setStatus(true);
            customersFacade.edit(cus);
            
            this.customerName = "";
            this.address = "";
            this.email = "";
            this.phone = "";
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("customers.xhtml");
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void findCustomer(int id) {
        try {
            Customers cus = customersFacade.find(id);
            
            this.address = cus.getAddress();
            this.customerId = cus.getCustomerId();
            this.email = cus.getEmail();
            this.phone = cus.getPhone();
            this.customerName = cus.getCustomerName();
            this.sex = cus.getSex();

            FacesContext.getCurrentInstance().getExternalContext().redirect("editcustomer.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xóa Customer
    public void deleteCustomer(int idc) {
        try {
            Customers cus = customersFacade.find(idc);
            cus.setStatus(false);
            customersFacade.edit(cus);
            FacesContext.getCurrentInstance().getExternalContext().redirect("customers.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CustomersBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        

    }
    
}
