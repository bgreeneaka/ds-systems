/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */
package session.user;

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
