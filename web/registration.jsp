<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <h1>This is Registration Page!</h1>
        <div style="border:solid 5px aqua; width: 30%; padding:  10px; margin: 10px;">
            <form action="user?register" method="post">
                <label>User Name:</label><br/>
                <input name="userName" /><br/>
                <label>User email:</label><br/>
                <input type="email" name="email" /><br/>
                <label>User passowrd:</label><br/>
                <input type="password" name="password" /><br/>
                <input type="hidden" name="status" value="1"/><br>
                <input type="submit" value="Submit" />
            </form>
        </div>
    </body>
</html>
