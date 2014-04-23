/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */
package Controller.shop;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.logging.MessagingBeanLocal;
import session.shop.ProductFacadeLocal;
import session.shop.ShoppingCartLocal;

/**
 *
 * @author chromodynamics
 */
public class CheckOut extends HttpServlet {

    @EJB
    ProductFacadeLocal productFacade;

    @EJB
    MessagingBeanLocal messageSender;

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

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Checkout Items</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Checkout Items</h1>");

            ShoppingCartLocal shoppingCart = (ShoppingCartLocal) request.getSession().getAttribute("shoppingCart");
            List<Integer> productIds = null;

            if (null != shoppingCart && !shoppingCart.getItems().isEmpty()) {
                productIds = shoppingCart.getItems();

                for (Integer productId : productIds) {
                    Product product = productFacade.getProductById(productId);
                    product.setQuantity(product.getQuantity() - 1);
                    productFacade.editProduct(product);
                }

                String user = "";

                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("safeName")) {
                        user = cookie.getValue();
                    }
                }

                messageSender.sendMessage("User " + user + " bought products, quantity: " + shoppingCart.getItems().size());
                shoppingCart.removeAllItems();

                out.println("Bought items");
                out.println("</body>");
                out.println("</html>");

            } else {
                out.println("Nothing to buy");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
