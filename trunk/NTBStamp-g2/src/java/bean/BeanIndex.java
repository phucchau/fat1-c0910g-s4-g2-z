/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TRUONG
 */
@ManagedBean
@SessionScoped
public class BeanIndex {

    /** Creates a new instance of BeanIndex */
    public BeanIndex() {
    }

    public void goIndex() throws IOException {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.endsWith("index.xhtml")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        }
    }
    public void goContact() throws IOException{
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.endsWith("index.xhtml")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Home/contact.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contact.xhtml");
        }
    }
    
}
