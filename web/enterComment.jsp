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
        <form action="SaveComment" method="post">
            Enter comment:<br/>
            <input type="text" name="comment"/>
            <br/>
            Submit:
            <input type="submit" name="productId" value="${productId}"/>
        </form>
    </body>
</html>
