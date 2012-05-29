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
public class RoomsFacade extends AbstractFacade<Rooms> {
    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public RoomsFacade() {
        super(Rooms.class);
    }
    
    public List<Rooms> getAllRoomsDESC() {
        return em.createQuery("SELECT r FROM Rooms r WHERE r.status = true ORDER BY r.roomId DESC").getResultList();
    }
    
    public List<Rooms> getAllRoomsDESCbyIDBuilding(int id){
        return  em.createQuery("SELECT r FROM Rooms r WHERE r.buildingId.buildingId = :id AND r.status = true ORDER BY r.roomId DESC").setParameter("id", id).getResultList();
    }
    
    public List<Rooms> getAllRoomLikebyName(String name){
        return em.createQuery("SELECT r FROM Rooms r WHERE r.RoomNo LIKE :name AND r.status = true ORDER BY r.roomId DESC").setParameter("name", "'%" +name+"%'").getResultList();
    }
}
