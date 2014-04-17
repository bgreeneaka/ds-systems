/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chromodynamics
 */
@Local
public interface ShoppingCartLocal {

    String addItem(final Integer productId);

    List<Integer> getItems();

    public void removeAllItems();
    
}
