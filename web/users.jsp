<%-- 
    Document   : users
    Created on : Aug 8, 2021, 7:06:09 PM
    Author     : KUCA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <a href="registration.jsp">Sign up for new user</a>
        <div style="border: dotted 4px aqua; padding: 10px; width: 40%;">
            <c:forEach var="u" items="${allUsers}">
                <a href="user?edit&id=${u.id}">
                    ${u.userName}
                    <span>
                        <b>
                            <a href="user?del&id=${u.id}">X</a><br/>
                        </b>
                    </span>
                </a><br/>
            </c:forEach>
        </div>
        <c:out value="${user.userName}"></c:out>
        <c:if test="${user!=null}">
            <c:out value="${user}"></c:out><br/>
            ${user.id}
            <div style="border:solid 5px aqua; width: 30%; padding:  10px; width: 40%">
                <form action="user?update" method="post">
                    <input type="hidden" name="id" value="${user.id}"/><br>
                    <label>User Name:</label><br/>
                    <input name="userName" value="${user.userName}" /><br/>
                    <label>User email:</label><br/>
                    <input type="email" name="email" value="${user.email}" /><br/>
                    <label>User passowrd:</label><br/>
                    <input type="password" name="password" value="${user.password}" /><br/>
                    <input type="hidden" name="status" value="1"/><br>
                    <input type="submit" value="Submit" />
                </form>
            </div>
        </c:if>
    </body>
</html>
