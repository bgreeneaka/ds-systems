<%-- 
    Document   : shoppingCartInfo
    Created on : 17-Apr-2014, 16:29:07
    Author     : chromodynamics
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        <h1>Shopping Cart Details</h1>
        <form action="RemoveShoppingCartItem" method="post">
            <table border="1">
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Remove</th>
                    <c:forEach items="${allProducts}" var="prod">
                    <tr>
                        <td>${prod.productId}</td>
                        <td>${prod.name}</td>
                        <td>${prod.description}</td>
                        <td><input type="submit" name="removedItem" value="${prod.productId}"
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>