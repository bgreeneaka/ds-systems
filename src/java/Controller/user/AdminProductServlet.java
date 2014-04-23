package Controller.user;

import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.logging.MessagingBeanLocal;
import session.shop.ProductFacadeLocal;

public class AdminProductServlet extends HttpServlet {

    @EJB
    private ProductFacadeLocal productFacade;

    @EJB
    private MessagingBeanLocal messageSender;

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

        if ("Add".equalsIgnoreCase(action)) {
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                Product product = new Product(productId, name, description, quantity);
                productFacade.addProduct(product);

                messageSender.sendMessage("Added product: " + product.toString());
            } catch (NumberFormatException e) {
            }

        } else if ("Edit Quantity By Id".equalsIgnoreCase(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("productId"));
                Product product = productFacade.getProductById(id);

                int quantity = Integer.parseInt(request.getParameter("quantity"));
                product.setQuantity(quantity);

                productFacade.editProduct(product);
            } catch (NumberFormatException e) {
            }

        } else if ("Delete By Id".equalsIgnoreCase(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("productId"));
                Product product = productFacade.getProductById(id);

                messageSender.sendMessage("Removed product: " + product.toString());

                productFacade.deleteProduct(id);
            } catch (NumberFormatException e) {
            }

        } else if ("Search By Id".equalsIgnoreCase(action)) {
            try {
                List<Product> products = new ArrayList<>();

                int productId = Integer.parseInt(request.getParameter("productId"));
                products.add(productFacade.getProductById(productId));

                request.setAttribute("allProducts", products);
            } catch (NumberFormatException e) {
            }

        } else if ("Search By Name".equalsIgnoreCase(action)) {
            try {
                List<Product> products = new ArrayList<>();

                String name = request.getParameter("name");
                products.add(productFacade.getProductByName(name));

                request.setAttribute("allProducts", products);
            } catch (NumberFormatException e) {
            }

        } else if ("View All Items".equalsIgnoreCase(action)) {
            request.setAttribute("allProducts", productFacade.getAllProducts());
        }

        request.getRequestDispatcher("WEB-INF/adminProductInfo.jsp").forward(request, response);
    }
}
