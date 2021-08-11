<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        ${user}
        ${user.userName}
        <form method="post" action="login">
            <label>User Name</label><br>
            <input type="text" name="userName"/><br>
            <label>Password</label><br>
            <input type="password" name="password"/><br>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
