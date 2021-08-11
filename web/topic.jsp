<%-- 
    Document   : topic
    Created on : Aug 1, 2021, 5:05:42 PM
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
    <body style="padding: 10px;">
        <%@include file="header.jsp"%>
        <h1>Welcome to topic page</h1>
        ${user}
        ispis
        ${topic}
        ${topic.id}
        <c:if test="${topic!=null}">
            <form method="post" action="topic?update">
            </c:if>
            <c:if test="${topic==null}">
                <form method="post" action="topic?create">
                </c:if>
                <!--treba implementirati dobivanja id od usersa preko sessije-->
                <c:if test="${topic!=null}">
                    <input type="hidden" name="id" value="<c:out value='${topic.id}'/>"/>
                </c:if>
                <label>Title:</label><br>
                <input type="text" name="title" value="<c:out value='${topic.title}'/>"/> <br>
                <label>Description:</label><br>
                <input name="description" value="<c:out value='${topic.description}'/>"/>
                <!--<input type="text" name="description"/><br>-->
                <input type="submit" value="Submit"/>
            </form>
    </body>
</html>
