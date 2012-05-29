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
public class ChargeTypeFacade extends AbstractFacade<ChargeType> {
    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ChargeTypeFacade() {
        super(ChargeType.class);
    }
    
    public List<ChargeType> getAllChargeTypeDESC() {
        return  em.createQuery("SELECT c FROM ChargeType c ORDER BY c.chargeId DESC").getResultList();
    }
    
    
    
}
