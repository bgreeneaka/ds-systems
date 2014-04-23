/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */
package session.user;

import entity.Customer;
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
public class CustomerFacade extends AbstractFacade<Customer> implements CustomerFacadeLocal {

    @PersistenceContext(unitName = "ShopWebApplicationPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }

    @Override
    public void addCustomer(Customer student) {
        em.persist(student);
    }

    @Override
    public void editCustomer(Customer customer) {
        em.merge(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        em.remove(getCustomer(customerId));
    }

    @Override
    public Customer getCustomer(int customerId) {
        return em.find(Customer.class, customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createNamedQuery("Customer.findAll").getResultList();
    }
    
    @Override
    public Customer getCustomerByUsername(String username) {
        return em.find(Customer.class, username);
    }
}
