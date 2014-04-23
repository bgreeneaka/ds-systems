/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.shop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.logging.MessagingBeanLocal;
import session.shop.ShoppingCartLocal;

public class CancelOrder extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cancel Order</title>");
            out.println("</head>");
            out.println("<body>");

            ShoppingCartLocal shoppingCart = (ShoppingCartLocal) request.getSession().getAttribute("shoppingCart");

            if (null != shoppingCart && !shoppingCart.getItems().isEmpty()) {
                int items = shoppingCart.getItems().size();
                shoppingCart.removeAllItems();
                out.println("Removed shopping cart items");
                out.println("</body>");
                out.println("</html>");

                String user = "";

                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("safeName")) {
                        user = cookie.getValue();
                    }
                }

                messageSender.sendMessage("user: " + user
                        + " cancelled order for: " + items + " items");

            } else {
                out.println("No items to remove");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
