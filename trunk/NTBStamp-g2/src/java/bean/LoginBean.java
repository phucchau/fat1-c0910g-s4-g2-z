/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.Accounts;
import eb.AccountsFacade;
import java.io.IOException;
import javax.ejb.EJB;
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
}
