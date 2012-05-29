/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TRUONG
 */
@Stateless
public class DocumentsFacade extends AbstractFacade<Documents> {
    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentsFacade() {
        super(Documents.class);
    }
    
    public Documents getDocumentbyLandid(int id){
        return (Documents) em.createQuery("SELECT d FROM Documents d WHERE d.status = true AND d.lands.landId = :id ").setParameter("id", id).getSingleResult();
    }
    
}
