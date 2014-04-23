<%-- 
    Brian Greene - 11042141 
    Eoghan Griffin - 10091157 
    Bartosz Kaminiecki - 11060204
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
         <%
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
                response.sendRedirect("sessionTimeOut.jsp");    //if session ID cookie is null redirect to session timeout page
            }
        %>
        
        <h1>Shopping Cart Details</h1>

        ${isItemAdded}
        <br/>
        <br/>

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