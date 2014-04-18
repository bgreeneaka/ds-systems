package Controller;

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
import session.AdministratorsFacadeLocal;
import session.CustomerFacadeLocal;

/**
 *
 * @author Admin
 */
public class loginServlet extends HttpServlet {

    @EJB
    private AdministratorsFacadeLocal adminFacade;
    @EJB
    private CustomerFacadeLocal customerFacade;
    private SessionId sessionId = new SessionId();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet loginServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        String sessionID = sessionId.newSessionId(userName);   // gets username parameter and encrypts it and uses it as a session ID

        if (action.equals("admin")) {

            List<Administrators> adminList = adminFacade.getAllAdmins();
            for (Administrators admin : adminList) {
                if (admin.getUsername().equals(userName) && admin.getPassword().equals(password)) {

                    Cookie userNameCookie = new Cookie("user", userName);
                    Cookie sessionIDCookie = new Cookie("id", sessionID);
                    userNameCookie.setMaxAge(60 * 10);
                    sessionIDCookie.setMaxAge(60 * 10);
                    response.addCookie(userNameCookie);
                    response.addCookie(sessionIDCookie);
                    response.sendRedirect("admin.jsp");
                }

            }
        } else if (action.equals("customer")) {
            List<Customer> customerList = customerFacade.getAllCustomers();
            for (Customer customer : customerList) {
                if (customer.getUsername().equals(userName) && customer.getPassword().equals(password)) {
                    Cookie userNameCookie = new Cookie("user", userName);
                    Cookie sessionIDCookie = new Cookie("id", sessionID);
                    userNameCookie.setMaxAge(60 * 10);
                    sessionIDCookie.setMaxAge(60 * 10);
                    response.addCookie(userNameCookie);
                    response.addCookie(sessionIDCookie);
                    response.sendRedirect("shop.jsp");

                }

            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
