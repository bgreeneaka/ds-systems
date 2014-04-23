/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package Controller.shop;

import entity.Comment;
import entity.Product;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.comment.CommentFacadeLocal;
import session.shop.ProductFacadeLocal;

public class ViewProduct extends HttpServlet {

    @EJB
    ProductFacadeLocal productFacade;

    @EJB
    CommentFacadeLocal commentFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //this method checks for a valid session ID
        String id = null;

        Cookie[] cookies = request.getCookies();    //retrieves cookies
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    id = cookie.getValue();         //retrieves session ID cookie
                }
            }
        }
        if (id == null || !id.equals(request.getSession().getAttribute("sessionId"))) {
            request.getRequestDispatcher("sessionTimeOut.jsp").forward(request, response);
        }

        int selectedProduct = Integer.parseInt(request.getParameter("selectedProduct"));

        Product product = productFacade.getProductById(selectedProduct);

        List<Comment> comments = commentFacade.getCommentsByProductId(selectedProduct);

        request.setAttribute("product", product);
        request.setAttribute("comments", comments);

        request.getRequestDispatcher("WEB-INF/displayProduct.jsp").forward(request, response);
    }
}
