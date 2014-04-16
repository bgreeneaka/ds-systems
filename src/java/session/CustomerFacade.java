/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void editStudent(Customer customer) {
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
}
