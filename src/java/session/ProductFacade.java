/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chromodynamics
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> implements ProductFacadeLocal {
    
    @PersistenceContext(unitName = "ShopWebApplicationPU")
    private EntityManager em;
    
    public ProductFacade() {
        super(Product.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void addProduct(Product product) {
        em.persist(product);
    }
    
    @Override
    public void editProduct(Product product) {
        em.merge(product);
    }
    
    @Override
    public void deleteProduct(int productId) {
        em.remove(getProductById(productId));
    }
    
    @Override
    public List<Product> getAllProducts() {
        return em.createNamedQuery("Product.findAll").getResultList();
    }
    
    @Override
    public Product getProductById(int productId) {
        return em.find(Product.class, productId);
    }
    
    @Override
    public Product getProductByName(String name) {
        return (Product) em.createNamedQuery("Product.findByName").setParameter("name", name).getSingleResult();
    }
}
