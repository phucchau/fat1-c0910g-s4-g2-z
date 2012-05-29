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
public class PaymentsFacade extends AbstractFacade<Payments> {
    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentsFacade() {
        super(Payments.class);
    }
    
}
