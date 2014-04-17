/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

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
