/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Accounts;
import eb.AccountsFacade;
import entity.SendMail;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRUONG
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    @EJB
    private AccountsFacade accountsFacade;
    private String username = "Enter keywords...";
    private String password = "Enter keywords...";
    private String tomail;

    public String getTomail() {
        return tomail;
    }

    public void setTomail(String tomail) {
        this.tomail = tomail;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /** Creates a new instance of LoginBean */
    public LoginBean() {
    }

    public void checkLogin() throws IOException {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (username.trim().length() == 0 || password.trim().length() == 0) {
            if (url.endsWith("login.xhtml")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
            }
        } else {
            if (accountsFacade.checkAccount(username, password)) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("username", username);
                Accounts ac = accountsFacade.getAccountbyUser(username);
                session.setAttribute("authenticate", ac.getAccountrole());
                if (url.endsWith("login.xhtml")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect(url);
                }
            } else {
                if (url.endsWith("login.xhtml")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
                }
            }
        }
    }

    public void logout() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("username", null);
        setUsername("Enter keywords...");
        setPassword("Enter keywords...");
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.endsWith("admin.xhtml")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
        }

    }

    // send mail 
    public void sendPass() {
        try {
            String from = "dudv_a04525@fpt.aptech.ac.vn";
            String to = tomail;
            String subject = "you or someone has asked to retrieve your password";
            Accounts ac = accountsFacade.getPassWord(tomail);
            
            String message = "UserName :" + ac.getUsername() + ", Password:"+ ac.getPassword();

            SendMail sendMail = new SendMail(from, to, subject, message);
            sendMail.send();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Send sucessed, Please check Mail"));

        } catch (Exception e) {
            //e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error , email does not exist Or  Error System"));
        }
    }
}
