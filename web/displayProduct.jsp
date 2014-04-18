
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product View</title>
    </head>
    <body>
        <form action="SelectedItem" method="post">
            <table border="1">
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Purchase</th>

                <tr>
                    <td>${product.productId}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.quantity}</td>
                    <td><input type="submit" name="selectedItem" value="${product.productId}"</td>
                </tr>
            </table>
        </form>
        <br/><br/>

        Add Comment
        <form action="AddComment" method="post">
            <input name="productId" type="submit" value="${product.productId}"/>
        </form>

        <br/><br/>
        <table border="1">
            <th>Username</th>
            <th>Comment</th>
                <c:forEach items="${comments}" var="comment">
                <tr>
                    <td>${comment.username}</td>
                    <td>${comment.comment}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
