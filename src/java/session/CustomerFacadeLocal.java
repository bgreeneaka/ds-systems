/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface CustomerFacadeLocal {

    void create(Customer customer);

    void edit(Customer customer);

    void remove(Customer customer);

    void addCustomer(Customer customer);

    void deleteCustomer(int customerId);

    Customer getCustomer(int customerId);
    
    void editCustomer(Customer customer);
    
    List<Customer> getAllCustomers();

    Customer find(Object id);

    List<Customer> findAll();

    List<Customer> findRange(int[] range);

    public Customer getCustomerByUsername(String username);

        int count
    

();
    
}
