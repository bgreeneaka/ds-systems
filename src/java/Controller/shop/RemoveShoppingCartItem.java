/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */
package Controller.shop;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.shop.ProductFacadeLocal;
import session.shop.ShoppingCartLocal;

public class RemoveShoppingCartItem extends HttpServlet {

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

        int removedItem = Integer.parseInt(request.getParameter("removedItem"));

        ShoppingCartLocal shoppingCart = (ShoppingCartLocal) request.getSession().getAttribute("shoppingCart");

        shoppingCart.removeItemById(removedItem);

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/ViewShoppingCart");
        dispatcher.forward(request, response);
    }
}
