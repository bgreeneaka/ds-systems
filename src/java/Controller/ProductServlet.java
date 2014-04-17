package Controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.ProductFacadeLocal;

public class ProductServlet extends HttpServlet {

    @EJB
    private ProductFacadeLocal productFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("Add".equalsIgnoreCase(action)) {
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                Product product = new Product(productId, name, description, quantity);
                productFacade.addProduct(product);
            } catch (NumberFormatException e) {
            }

        } else if ("Edit".equalsIgnoreCase(action)) {

        } else if ("Delete".equalsIgnoreCase(action)) {

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

        request.getRequestDispatcher("productInfo.jsp").forward(request, response);
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
