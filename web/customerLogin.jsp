<%-- 
    Document   : customerLogin
    Created on : 18-Apr-2014, 14:23:55
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Login</title>
    </head>
    <body>
        <h1>Customer Login</h1>
        <form action="loginServlet" method="get">  
            <table>
                <tr>
                    <td>User Name</td>
                    <td><input type="text" name="username" value="${customer.username}" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value="${custromer.password}" /></td>
                <input type="hidden" name="action" id="operId2" value="customer" />
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Submit" />
                    </td>                
                </tr>            
            </table>
        </form>
    </body>
</html>
