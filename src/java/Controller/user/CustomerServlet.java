package Controller.user;

import entity.Customer;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.user.CustomerFacadeLocal;

public class CustomerServlet extends HttpServlet {
    @EJB
    private CustomerFacadeLocal customerFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        
        Customer customer = new Customer(customerId, firstname, lastname, address, password,username);

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
        request.getRequestDispatcher("customerInfo.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
