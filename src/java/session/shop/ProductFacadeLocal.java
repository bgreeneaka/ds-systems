/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package session.shop;

import entity.Product;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chromodynamics
 */
@Local
public interface ProductFacadeLocal {
    
    public void create(Product product);
    
    public void edit(Product product);
    
    public void remove(Product product);
    
    public void addProduct(Product product);
    
    public List<Product> getAllProducts();
    
    public List<Product> findAll();

    public Product getProductById(int productId);

    public Product getProductByName(String name);

    public void editProduct(Product product);

    public void deleteProduct(int productId);
}
