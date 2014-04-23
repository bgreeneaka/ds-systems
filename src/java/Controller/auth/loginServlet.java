/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package Controller.auth;

import entity.Administrators;
import entity.Customer;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.user.AdministratorsFacadeLocal;
import session.user.CustomerFacadeLocal;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import org.owasp.esapi.ESAPI;

/**
 *
 * @author Admin
 */
public class loginServlet extends HttpServlet {

    @EJB
    private AdministratorsFacadeLocal adminFacade;
    @EJB
    private CustomerFacadeLocal customerFacade;
    private final SessionId sessionId = new SessionId();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sessionID = sessionId.newSessionId(request.getParameter("username"));   // gets username parameter and encrypts it and uses it as a session ID
        String userName = ESAPI.encoder().encodeForHTMLAttribute(request.getParameter("username"));
        String safeUserName = ESAPI.encoder().encodeForHTMLAttribute(request.getParameter("username"));

        String password = request.getParameter("password");
        String action = request.getParameter("action");

        if (action.equals("admin")) {

            List<Administrators> adminList = adminFacade.getAllAdmins();
            for (Administrators admin : adminList) {
                if (admin.getUsername().equals(ESAPI.encoder().decodeForHTML(userName)) && admin.getPassword().equals(password)) {

                    Cookie userNameCookie = new Cookie("user", userName);
                    Cookie sessionIDCookie = new Cookie("id", sessionID);

                    Cookie encodedName = new Cookie("safeName", safeUserName);
                    encodedName.setMaxAge(60 * 10);
                    encodedName.setSecure(true);
                    response.addCookie(encodedName);

                    userNameCookie.setMaxAge(60 * 10);
                    sessionIDCookie.setSecure(true);
                    userNameCookie.setSecure(true);

                    response.addCookie(userNameCookie);
                    response.addCookie(sessionIDCookie);
                    
                    request.getSession().setAttribute("sessionId", sessionID);
                    request.getRequestDispatcher("WEB-INF/adminProductInfo.jsp").forward(request, response);
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                    PrintWriter out = response.getWriter();
                    out.println("<font color=red>Either user name or password is wrong.</font>");
                    rd.include(request, response);
                }

            }
        } else if (action.equals("customer")) {
            List<Customer> customerList = customerFacade.getAllCustomers();
            for (Customer customer : customerList) {
                if (customer.getUsername().equals(ESAPI.encoder().decodeForHTML(userName)) && customer.getPassword().equals(password)) {
                    Cookie userNameCookie = new Cookie("user", userName);
                    Cookie sessionIDCookie = new Cookie("id", sessionID);

                    Cookie encodedName = new Cookie("safeName", safeUserName);
                    encodedName.setMaxAge(60 * 10);
                    encodedName.setSecure(true);
                    response.addCookie(encodedName);

                    userNameCookie.setMaxAge(60 * 10);
                    sessionIDCookie.setMaxAge(60 * 10);

                    response.addCookie(userNameCookie);
                    response.addCookie(sessionIDCookie);
                    request.getRequestDispatcher("WEB-INF/userProductInfo.jsp").forward(request, response);

                    sessionIDCookie.setSecure(true);
                    userNameCookie.setSecure(true);

                    request.getSession().setAttribute("sessionId", sessionID);
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
                    PrintWriter out = response.getWriter();
                    out.println("<font color=red>Either user name or password is wrong.</font>");
                    rd.include(request, response);
                }

            }
        }
    }
}
