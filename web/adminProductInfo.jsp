<%-- 
    Document   : productInfo
    Created on : 16-Apr-2014, 21:28:15
    Author     : chromodynamics
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Information</title>
    </head>
    <body>
         <%
            //this method checks for a valid session ID
            String id = null;
            String user= null;
            Cookie[] cookies = request.getCookies();    //retrieves cookies
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("id")) {
                        id = cookie.getValue();         //retrieves session ID cookie
                    }
                    if(cookie.getName().equals("user")){
                        user = cookie.getValue();
                    }
                }
            }
            if (id == null) {
                response.sendRedirect("sessionTimeOut.jsp");        //if session ID cookie is null redirect to session timeout page
            }
        %>
        <h1>Product Information</h1>
        <form action="AdminProductServlet" method="post">  
            <table>
                <tr>
                    <td>Product ID</td>
                    <td><input type="text" name="productId" /></td>
                </tr>
                <tr>
                    <td>Product Name</td>
                    <td><input type="text" name="name" /></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><input type="text" name="description" /></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add" />
                        <input type="submit" name="action" value="Edit Quantity By Id" />
                        <input type="submit" name="action" value="Delete By Id" />
                        <input type="submit" name="action" value="Search By Id" />
                        <input type="submit" name="action" value="Search By Name" />
                        <input type="submit" name="action" value="View All Items" />
                    </td>                
                </tr>            
            </table>
        </form>

        <form action="ViewShoppingCart" method="post">
            <input type="submit" value="View Shopping Cart">
        </form>
        
        <form action="CancelOrder" method="post">
            <input type="submit" value="Cancel Order">
        </form>

        <br>
        <form action="ViewProduct" method="post">
            <table border="1">
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>View</th>
                    <c:forEach items="${allProducts}" var="prod">
                    <tr>
                        <td>${prod.productId}</td>
                        <td>${prod.name}</td>
                        <td>${prod.description}</td>
                        <td>${prod.quantity}</td>
                        <td><input type="submit" name="selectedProduct" value="${prod.productId}"</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
