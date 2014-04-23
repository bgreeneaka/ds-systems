/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */
package session.logging;

import javax.ejb.Local;

/**
 *
 * @author chromodynamics
 */
@Local
public interface MessagingBeanLocal {

    void sendMessage(Object msg);
    
}
