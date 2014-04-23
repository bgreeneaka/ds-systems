/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package session.comment;

import entity.Comment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chromodynamics
 */
@Local
public interface CommentFacadeLocal {

    public void addComment(Comment comment);

    public List<Comment> getCommentsByProductId(int productId);
    
}
