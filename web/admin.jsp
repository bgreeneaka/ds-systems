<%-- 
    Document   : admin
    Created on : 18-Apr-2014, 15:37:39
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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
                response.sendRedirect("sessioTimeOut.jsp");    //if session ID cookie is null redirect to session timeout page
            }
        %>
        <h1>Hello world</h1>
        <div><a href="customerInfo.jsp">Customers</a></div>
        <div><a href="adminProductInfo.jsp">Products</a></div> 
    </body>
</html>
