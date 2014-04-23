/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */
package Controller.comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chromodynamics
 */
public class AddComment extends HttpServlet {

    @Override
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

        request.setAttribute("productId", request.getParameter("productId"));
        request.getRequestDispatcher("WEB-INF/enterComment.jsp").forward(request, response);
    }
}
