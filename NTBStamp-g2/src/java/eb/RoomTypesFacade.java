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
public class RoomTypesFacade extends AbstractFacade<RoomTypes> {
    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RoomTypesFacade() {
        super(RoomTypes.class);
    }
    
    public List<RoomTypes> getAllRoomTypeDESC() {
        return  em.createQuery("SELECT r FROM RoomTypes r ORDER BY r.typeId DESC").getResultList();
    }
    
    
}
