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
public class AccountsFacade extends AbstractFacade<Accounts> {

    @PersistenceContext(unitName = "NTBStamp-g2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountsFacade() {
        super(Accounts.class);
    }

    public List<Accounts> getAllAccountDESC() {
        return em.createQuery("SELECT a FROM Accounts a WHERE a.status = true ORDER BY a.accountId DESC").getResultList();
    }

    public boolean checkAccount(String user, String pass) {
        try {
            Accounts accounts =
                    (Accounts) em.createQuery("SELECT a FROM Accounts a WHERE a.username = :user AND a.password = :pass AND a.status = true").setParameter("user", user).setParameter("pass", pass).getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Accounts getAccountbyUser(String user) 
    {
        
            Accounts accounts =
                (Accounts) 
                em.createQuery("SELECT a FROM Accounts a WHERE a.username = :username AND a.status = true")
                .setParameter("username", user).getSingleResult();
            
            return accounts;
    }
    
    public boolean getAccountbyUser1(String user) 
    {
        try
        {
            Accounts accounts =(Accounts) em.createQuery("SELECT a FROM Accounts a WHERE a.username = :username AND a.status = true")
                .setParameter("username", user).getSingleResult();
              return true;
        } catch (Exception e) {
             return false;
        }
    }
    
    public Accounts getPassWord(String email) {
        Accounts accounts =
                (Accounts) em.createQuery("SELECT a FROM Accounts a WHERE a.email = :email AND a.status = true").setParameter("email", email).getSingleResult();
        return accounts;
    }
    
    public boolean getEmail(String email) {
        try
        {
        Accounts accounts =
                (Accounts) em.createQuery("SELECT a FROM Accounts a WHERE a.email = :email AND a.status = true").setParameter("email", email).getSingleResult();
                return true;
        } catch (Exception e) {
             return false;
        }
    }
    
    public Accounts getPassWordbyID(Integer id) {
        Accounts accounts =
                (Accounts) em.createQuery("SELECT a FROM Accounts a WHERE a.accountId = :accountId AND a.status = true").setParameter("accountId", id).getSingleResult();
        return accounts;
    }
}
