<%-- 
    Document   : enterComment
    Created on : 18-Apr-2014, 00:27:12
    Author     : chromodynamics
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <form action="SaveComment" method="post">
            Enter comment:<br/>
            <input type="text" name="comment"/>
            <br/>
            Submit:
            <input type="submit" name="productId" value="${productId}"/>
        </form>
    </body>
</html>
