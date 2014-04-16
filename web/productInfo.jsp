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
        <h1>Product Information</h1>
        <form action="ProductServlet" method="post">  
            <table>
                <tr>
                    <td>Product ID</td>
                    <td><input type="text" name="productId" value="${product.productId}"/></td>
                </tr>
                <tr>
                    <td>Product Name</td>
                    <td><input type="text" name="name" value="${product.name}"/></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><input type="text" name="description" value="${product.description}"/></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity" value="${product.quantity}"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add" />
                        <input type="submit" name="action" value="Edit" />
                        <input type="submit" name="action" value="Delete" />
                        <input type="submit" name="action" value="Search" />
                    </td>                
                </tr>            
            </table>
        </form>        
        <br>
        <table border="1">
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Quantity</th>
                <c:forEach items="${allProducts}" var="prod">
                <tr>
                    <td>${prod.productId}</td>
                    <td>${prod.name}</td>
                    <td>${prod.description}</td>
                    <td>${prod.quantity}</td>
                </tr>
            </c:forEach>
        </table>  
    </body>
</html>
