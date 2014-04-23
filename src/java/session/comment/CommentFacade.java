/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package session.comment;

import entity.Comment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import session.AbstractFacade;

/**
 *
 * @author chromodynamics
 */
@Stateless
public class CommentFacade extends AbstractFacade<Comment> implements CommentFacadeLocal {
    
    @PersistenceContext(unitName = "ShopWebApplicationPU")
    private EntityManager em;

    public CommentFacade() {
        super(Comment.class);
    }

    @Override
    protected EntityManager getEntityManager() {
       return em;
    }
    
    @Override
    public void addComment(Comment comment) {
        em.persist(comment);
    }
    
    @Override
    public List<Comment> getCommentsByProductId(int productId) {
        return em.createNamedQuery("Comment.findByProductId").setParameter("productId", productId).getResultList();
    }
}
