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
public class BuildingsFacade extends AbstractFacade<Buildings> {
    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public BuildingsFacade() {
        super(Buildings.class);
    }
    
    public List<Buildings> getAllBuildingsDESC() {
        return  em.createQuery("SELECT b FROM Buildings b WHERE b.status = true ORDER BY b.buildingId DESC").getResultList();
    }
    
    public List<Buildings> getAllBuildingsDESCbyIDland(int id){
        return  em.createQuery("SELECT b FROM Buildings b WHERE b.landId.landId = :id AND b.status = true ORDER BY b.buildingId DESC").setParameter("id", id).getResultList();
    }
    
     public List<Buildings> getAllBuildingsLikebyName(String name){
        return  em.createQuery("SELECT b FROM Buildings b WHERE b.buildingName = :name AND b.status = true ORDER BY b.buildingId DESC").setParameter("name", "'%"+name+"%'").getResultList();
    }
}
