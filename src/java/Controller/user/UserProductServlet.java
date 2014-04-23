/**
 * Brian Greene - 11042141 Eoghan Griffin - 10091157 Bartosz Kaminiecki -
 * 11060204
 */

package Controller.user;

import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.shop.ProductFacadeLocal;

public class UserProductServlet extends HttpServlet {

    @EJB
    private ProductFacadeLocal productFacade;

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

        if ("Search By Id".equalsIgnoreCase(action)) {
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
            } catch (NumberFormatException | EJBException e) {
            }

        } else if ("View All Items".equalsIgnoreCase(action)) {
            request.setAttribute("allProducts", productFacade.getAllProducts());
        }

        request.getRequestDispatcher("WEB-INF/userProductInfo.jsp").forward(request, response);
    }
}
