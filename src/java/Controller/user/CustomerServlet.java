/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package Controller.user;

import entity.Customer;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.user.CustomerFacadeLocal;

public class CustomerServlet extends HttpServlet {

    @EJB
    private CustomerFacadeLocal customerFacade;

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

        String action = request.getParameter("action");
        String customerIdStr = request.getParameter("id");
        int customerId = 0;
        if (customerIdStr != null && !customerIdStr.equals("")) {
            customerId = Integer.parseInt(customerIdStr);
        }
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String username = request.getParameter("username");

        Customer customer = new Customer(customerId, firstname, lastname, address, password, username);

        if ("Add".equalsIgnoreCase(action)) {
            customerFacade.addCustomer(customer);
        } else if ("Edit".equalsIgnoreCase(action)) {
            customerFacade.editCustomer(customer);
        } else if ("Delete".equalsIgnoreCase(action)) {
            customerFacade.deleteCustomer(customerId);
        } else if ("Search".equalsIgnoreCase(action)) {
            customer = customerFacade.getCustomer(customerId);
        }
        request.setAttribute("customer", customer);
        request.setAttribute("allCustomers", customerFacade.getAllCustomers());
        request.getRequestDispatcher("WEB-INF/customerInfo.jsp").forward(request, response);
    }
}
