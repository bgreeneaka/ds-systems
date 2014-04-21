/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.shop;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

            if (null != shoppingCart) {
                productIds = shoppingCart.getItems();

                if (null != productIds) {
                    for (Integer productId : productIds) {
                        Product product = productFacade.getProductById(productId);
                        product.setQuantity(product.getQuantity() - 1);
                        productFacade.editProduct(product);
                    }

                    messageSender.sendMessage("User bought products, quantity: " + shoppingCart.getItems().size());
                    shoppingCart.removeAllItems();

                    out.println("Bought items");
                    out.println("</body>");
                    out.println("</html>");
                }
            } else {
                out.println("Nothing to buy");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
