<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp"%>

    <c:if test="${!error}">
        <h1>You have Successfully registred!</h1>
    </c:if>
    ${error}
    <h1>This name is already taken!</h1>
    <p>Go to login page <a href="#">Login</a></p>
    <p>Go to all users page <a href="user?allUser">Login</a></p>
</body>
</html>
