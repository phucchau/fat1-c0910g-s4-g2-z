/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eb;

import com.sun.org.apache.bcel.internal.generic.LAND;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author TRUONG
 */
@Stateless
public class LandTypesFacade extends AbstractFacade<LandTypes> {
    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public LandTypesFacade() {
        super(LandTypes.class);
    }
    
    public List<LandTypes> getAllLandTypesDESC() {
        return  em.createQuery("SELECT l FROM LandTypes l ORDER BY l.landTypeID DESC").getResultList();
    }
    
}
