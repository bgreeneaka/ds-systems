/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author chromodynamics
 */
@Stateful
public class ShoppingCart implements ShoppingCartLocal {

    List<Integer> productIds = new ArrayList<>();
    
    @Override
    public void addItem(final int productId) {
        productIds.add(productId);
    }
    
    @Override
    public List<Integer> getItems() {
        return productIds;
    }
}
