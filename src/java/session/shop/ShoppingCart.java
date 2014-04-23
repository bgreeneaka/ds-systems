/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */
package session.shop;

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
    
    @Override
    public void removeAllItems() {
        productIds.clear();
    }
    
    @Override
    public void removeItemById(int id) {
        for (int i = 0; i < productIds.size(); i++) {
            if (id == productIds.get(i).intValue()) {
                productIds.remove(i);
                break;
            }
        }
    }
}
