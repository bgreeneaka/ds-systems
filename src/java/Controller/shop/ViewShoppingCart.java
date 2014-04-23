/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package Controller.shop;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.shop.ProductFacadeLocal;
import session.shop.ShoppingCartLocal;

public class ViewShoppingCart extends HttpServlet {

    @EJB
    ProductFacadeLocal productFacade;

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

        ShoppingCartLocal shoppingCart = (ShoppingCartLocal) request.getSession().getAttribute("shoppingCart");

        List<Integer> productIds = null;
        List<Product> products = null;

        if (null != shoppingCart) {
            productIds = shoppingCart.getItems();
            products = new ArrayList<>();
        }

        if (null != products) {
            for (Integer productId : productIds) {
                Product product = productFacade.getProductById(productId);
                products.add(product);
            }
        }

        request.setAttribute("allProducts", products);
        request.getRequestDispatcher("WEB-INF/shoppingCartInfo.jsp").forward(request, response);
    }
}