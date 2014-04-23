/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package session.shop;

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

    public void removeItemById(int id);
    
}
