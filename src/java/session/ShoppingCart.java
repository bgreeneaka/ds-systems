/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author chromodynamics
 */
@Stateful
public class ShoppingCart implements ShoppingCartLocal {

    @EJB
    ProductFacadeLocal productFacade;

    List<Integer> productIds = new ArrayList<>();

    @Override
    public String addItem(final Integer productId) {
        int freq = Collections.frequency(productIds, productId);
        int quantity = productFacade.getProductById(productId).getQuantity();

        if (freq >= quantity) {
            return "Not enough items in stock";
        } else {
            productIds.add(productId);
            return "Added Item";
        }
    }

    @Override
    public List<Integer> getItems() {
        return productIds;
    }
}
