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
public class PaymentsFacade extends AbstractFacade<Payments> {
    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentsFacade() {
        super(Payments.class);
    }
    
    public boolean checkRoomNo(int roomNo){
        try {
            Payments payments =
                    (Payments) em.createQuery("SELECT p FROM Payments p WHERE p.roomID.roomId = :room").setParameter("room", roomNo).getSingleResult();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Payments> getAllPaymentsDESC(Integer id) {
        return em.createQuery("SELECT p FROM Payments p WHERE p.customerID.customerId = :customerID and p.status = 0 ORDER BY p.paymentID DESC")
                .setParameter("customerID", id).getResultList();
    } 
    
    public List<Payments> getAllPaymentsDESC() {
        return em.createQuery("SELECT p FROM Payments p ORDER BY p.paymentID DESC").getResultList();
    } 
}
