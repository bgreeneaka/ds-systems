/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
