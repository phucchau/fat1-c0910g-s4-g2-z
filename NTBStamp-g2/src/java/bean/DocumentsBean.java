/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import eb.ChargeType;
import eb.ChargeTypeFacade;
import eb.Documents;
import eb.DocumentsFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author DuMaster
 */
@ManagedBean
@SessionScoped
public class DocumentsBean {

    @EJB
    private ChargeTypeFacade chargeTypeFacade;
    @EJB
    private DocumentsFacade documentsFacade;
    public Documents doc;
    public Integer docID;
    public String LandsName;
    public String title;
    public String image;
    public String description;
    public Date permitDate;
    public String signature;
    public Integer chargeId;

    public String getLandsName() {
        return LandsName;
    }

    public void setLandsName(String LandsName) {
        this.LandsName = LandsName;
    }

    public String Image;
    private Documents d;

    public Documents getD() {
        return d;
    }

    public void setD(Documents d) {
        this.d = d;
    }
    
    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDocID() {
        return docID;
    }

    public void setDocID(Integer docID) {
        this.docID = docID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPermitDate() {
        return permitDate;
    }

    public void setPermitDate(Date permitDate) {
        this.permitDate = permitDate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** Creates a new instance of DocumentsBean */
    public DocumentsBean() {
    }

    public DocumentsBean(Integer docID, String title, String description, Date permitDate, String signature, Integer chargeId, String Image) {
        this.docID = docID;
        this.title = title;
        this.description = description;
        this.signature = signature;
        this.chargeId = chargeId;
        this.image = image;
        this.permitDate = permitDate;
    }

    // Load all documents
    @PostConstruct
    public List<Documents> getListDocument() {
        return documentsFacade.findAll();
    }

    // create documents
    @PostConstruct
    public void createDocument() {
        try {

            doc = new Documents();
            doc.setDocID(docID);
            doc.setTitle(title);
            doc.setDescription(description);
            doc.setSignature(signature);
            doc.setChargeId(chargeTypeFacade.find(chargeId));

            doc.setStatus(true);

            Calendar cal = Calendar.getInstance();
            doc.setPermitDate(cal.getTime());

            doc.setImage(image);
            documentsFacade.create(doc);

            FacesContext.getCurrentInstance().getExternalContext().redirect("lands.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update chargetType
    @PostConstruct
    public void updateDocument() {
        try {

            doc = new Documents();
            doc.setTitle(title);
            doc.setDescription(description);
            doc.setSignature(signature);
            doc.setChargeId(chargeTypeFacade.find(chargeId));
            doc.setDocID(docID);
            documentsFacade.edit(doc);
            this.setTitle("");
            this.setDescription("");
            this.setSignature("");
            FacesContext.getCurrentInstance().getExternalContext().redirect("lands.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void findDocument(int id) {
        try {

            Documents doc = documentsFacade.find(id);
            this.docID = doc.getDocID();
            this.title = doc.getTitle();
            this.description = doc.getDescription();
            this.signature = doc.getSignature();
            this.chargeId = doc.getChargeId().getChargeId();


            FacesContext.getCurrentInstance().getExternalContext().redirect("editdocument.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void permitDocument(int id, String landName) {
        try {

            if (documentsFacade.find(id) != null) {

                Documents doc = documentsFacade.find(id);
                this.docID = doc.getDocID();
                this.title = doc.getTitle();
                this.description = doc.getDescription();
                this.signature = doc.getSignature();
                this.chargeId = doc.getChargeId().getChargeId();
                this.setLandsName(landName);
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("editdocument.xhtml");
                
            } else {
                this.setDocID(id);
                this.setLandsName(landName);
                this.title = "";
                this.description = "";
                this.signature = "";
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("insertdocument.xhtml");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete document
    @PostConstruct
    public void deleteDocument(int idc) {
        try {
            documentsFacade.remove(documentsFacade.find(idc));
            FacesContext.getCurrentInstance().getExternalContext().redirect("lands.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DocumentsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PostConstruct
    public List<SelectItem> getDocumentItem() {

        List<SelectItem> rs = new LinkedList<SelectItem>();
        List<ChargeType> lst = chargeTypeFacade.findAll();
        for (ChargeType c : lst) {
            rs.add(new SelectItem(c.getChargeId(), c.getCharge()));
        }
        return rs;
    }

    public void handleFileUpload(FileUploadEvent event) {

        try {

            FileOutputStream fos = null;

            UploadedFile file = event.getFile();

            byte[] buff = file.getContents();

            String realpath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
            String patch = realpath.substring(0, realpath.lastIndexOf("build")) + "web\\ImageUser\\";

            File f = new File(patch + file.getFileName());

            if (!f.exists()) {
                f.createNewFile();
            }

            fos = new FileOutputStream(f);

            fos.write(buff);

            fos.close();

            this.setImage(file.getFileName());
        } catch (IOException ex) {
            Logger.getLogger(LandsBean.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
     public void goBuilding() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        setD(documentsFacade.find(Integer.parseInt(params.get("idDocument"))));
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        if (url.endsWith("index.xhtml")) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("Home/document.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("document.xhtml");
        }
    }
}
