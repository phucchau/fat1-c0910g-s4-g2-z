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
public class PaymentChargeFacade extends AbstractFacade<PaymentCharge> {
    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentChargeFacade() {
        super(PaymentCharge.class);
    }
    
    public List<PaymentCharge> getAllPaymentChargerDESC() {
        return  em.createQuery("SELECT p FROM PaymentCharge p ORDER BY p.payChargeID DESC").getResultList();
    }
    
}
