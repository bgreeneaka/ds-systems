<%-- 
    Brian Greene - 11042141 
    Eoghan Griffin - 10091157 
    Bartosz Kaminiecki - 11060204
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
        <h1>Hello world</h1>
        <div><a href="customerInfo.jsp">Customers</a></div>
        <div><a href="adminProductInfo.jsp">Products</a></div> 
        <form action="logoutServlet" method="post">
            <input type="submit" value="Logout" >
        </form>
    </body>
</html>
