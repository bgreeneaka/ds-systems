/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */
package Controller.comment;

import entity.Comment;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.comment.CommentFacadeLocal;
import org.owasp.esapi.ESAPI;

/**
 *
 * @author chromodynamics
 */
public class SaveComment extends HttpServlet {

    @EJB
    CommentFacadeLocal commentFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //this method checks for a valid session ID
        String sessionid = null;

        Cookie[] cookies = request.getCookies();    //retrieves cookies
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    sessionid = cookie.getValue();         //retrieves session ID cookie
                }
            }
        }
        if (sessionid == null || !sessionid.equals(request.getSession().getAttribute("sessionId"))) {
            request.getRequestDispatcher("sessionTimeOut.jsp").forward(request, response);
        }

        String user = "";
        cookies = request.getCookies();    //retrieves cookies
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    user = cookie.getValue();
                }
            }
        }

        String commentText = ESAPI.encoder().encodeForHTMLAttribute(
                request.getParameter("comment"));

        int productId = Integer.parseInt(request.getParameter("productId"));

        Comment comment = new Comment(user, productId, commentText);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Posting Comment</title>");
            out.println("</head>");
            out.println("<body>");

            try {
                commentFacade.addComment(comment);
                out.println("Comment Added");
                out.println("</body>");
                out.println("</html>");
            } catch (EJBException e) {
                out.println("Error in processing comment");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
