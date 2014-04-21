/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.user;

import entity.Administrators;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.AbstractFacade;
import session.AbstractFacade;

/**
 *
 * @author Admin
 */
@Stateless
public class AdministratorsFacade extends AbstractFacade<Administrators> implements AdministratorsFacadeLocal {
    @PersistenceContext(unitName = "ShopWebApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministratorsFacade() {
        super(Administrators.class);
    }
    
    @Override
    public Administrators getAdminByUsername(String username){
        return em.find(Administrators.class, username);
    }
    
    @Override
    public List <Administrators> getAllAdmins(){
        return em.createNamedQuery("Administrators.findAll").getResultList();
    }
}
