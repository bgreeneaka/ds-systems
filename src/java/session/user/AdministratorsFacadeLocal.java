/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package session.user;

import entity.Administrators;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Admin
 */
@Local
public interface AdministratorsFacadeLocal {

    void create(Administrators administrators);

    void edit(Administrators administrators);

    void remove(Administrators administrators);

    Administrators find(Object id);

    List<Administrators> findAll();

    List<Administrators> findRange(int[] range);
    
    public Administrators getAdminByUsername(String username);
    
    public List <Administrators> getAllAdmins();

    int count();
    
}
