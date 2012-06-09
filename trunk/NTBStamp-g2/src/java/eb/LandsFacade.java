/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TRUONG
 */
@Stateless
public class LandsFacade extends AbstractFacade<Lands> {

    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public LandsFacade() {
        super(Lands.class);
    }

    public List<Lands> getAllLandsDESC() {
        return  em.createQuery("SELECT l FROM Lands l Where l.status = true ORDER BY l.landId DESC").getResultList();
    }
    
    public List<Lands> getAllLandsLikebyName(String name) {
        return  em.createQuery("SELECT l FROM Lands l Where l.landName LIKE '%"+name+"%' AND l.status = true ORDER BY l.landId DESC").getResultList();
    }
    
    public List<Lands> getAllLandsbyPrice(String price){
        return  em.createQuery("SELECT l FROM Lands l Where l.price "+price+" AND l.status = true ORDER BY l.landId DESC").getResultList();
    }
    
     public List<Lands> getAllLandsbyNameandPrice(String name, String price){
        return  em.createQuery("SELECT l FROM Lands l Where l.landName LIKE '%"+name+"%' AND l.price "+price+" AND l.status = true ORDER BY l.landId DESC").getResultList();
    }
}
