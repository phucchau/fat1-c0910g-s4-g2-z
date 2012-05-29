/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Accounts;
import eb.AccountsFacade;
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
public class AccountsBean {
    @EJB
    private AccountsFacade accountsFacade;

    public Accounts acc ;
    public Integer accountId;
    public String accountName;
    public String username;
    public String password;
    public String email;
    public String sex = "Male";
    public String Phone;
    public String address;
    public String accountrole;
    public boolean  status;

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountrole() {
        return accountrole;
    }

    public void setAccountrole(String accountrole) {
        this.accountrole = accountrole;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    /** Creates a new instance of AccountsBean */
    public AccountsBean() {
    }
    
    public AccountsBean(Integer accountId,String accountName,String username,String password,String email,String Phone,String address,String accountrole,boolean  status){
         this.accountId = accountId;
         this.accountName = accountName;
         this.username = username;
         this.password = password;
         this.email = email;
         this.sex = sex;
         this.Phone = Phone;
         this.address = address;
         this.accountrole = accountrole;
         this.status = status;
                 
    }
    
    
    
    // Load all account
    
    public List<Accounts> getListAccountType(){
        return  accountsFacade.getAllAccountDESC();
    }
    
    // create account
    
    public void createAccountType() {
        try {
            acc = new Accounts();
            
            acc.setAccountName(accountName);
            acc.setUsername(username);
            acc.setAccountrole(accountrole);
            acc.setAddress(address);
            acc.setEmail(email);
            acc.setPassword(password);
            acc.setPhone(Phone);
            acc.setStatus(true);
            
            acc.setSex(sex);
            
            accountsFacade.create(acc);
                       
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("accounts.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // update account
    public void updateAccount() {
        try {

            acc = new Accounts();
            acc.setAccountId(accountId);
            acc.setAccountName(accountName);
            acc.setAccountrole(accountrole);
            acc.setAddress(address);
            acc.setEmail(email);
            acc.setPassword(password);
            acc.setPhone(Phone);
            acc.setStatus(true);
            acc.setUsername(username);
            acc.setSex(sex);
            
            accountsFacade.edit(acc);
            FacesContext.getCurrentInstance().getExternalContext().redirect("accounts.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void findAccount(int id) {
        try {
            Accounts acc = accountsFacade.find(id);
            this.Phone =acc.getPhone();
            this.password = acc.getPassword();
            this.accountName = acc.getAccountName();
            this.address = acc.getAddress();
            this.sex = acc.getSex();
            this.username = acc.getUsername();
            this.email = acc.getEmail();
            this.accountrole = acc.getAccountrole();

            FacesContext.getCurrentInstance().getExternalContext().redirect("editaccount.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // xóa category
    public void deleteAccount(int idc) {
        try {
            Accounts acc = accountsFacade.find(idc);
            acc.setStatus(false);
            accountsFacade.edit(acc);
            FacesContext.getCurrentInstance().getExternalContext().redirect("accounts.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AccountsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }
    
    
}
