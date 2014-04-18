<%-- 
    Document   : customerInfo
    Created on : 14-Apr-2014, 22:06:23
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Information</title>
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
        <h1>Customer Information</h1>
        <form action="CustomerServlet" method="post">  
            <table>
                <tr>
                <td>User-Name</td>
                <td><input type="text" name="username" value="${customer.username}" /></td>
                </tr>
                <tr>
                    <td>Customer ID</td>
                    <td><input type="text" name="id" value="${customer.id}" /></td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstname" value="${customer.firstname}" /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastname" value="${customer.lastname}" /></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="address" value="${customer.address}" /></td>
                </tr>

                <td>Password</td>
                <td><input type="text" name="password" value="${customer.password}" /></td>
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
            <th>User Name</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Password</th>
                <c:forEach items="${allCustomers}" var="cust">
                <tr>
                    <td>${cust.id}</td>
                    <td>${cust.username}</td>
                    <td>${cust.firstname}</td>
                    <td>${cust.lastname}</td>
                    <td>${cust.address}</td>
                    <td>${cust.password}</td>
                </tr>
            </c:forEach>
        </table>  
    </body>
</html>
