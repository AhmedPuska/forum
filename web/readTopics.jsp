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
        <h2>
            <c:if test="${status!=1}">
                <a href="topic.jsp">Add topic</a>
            </c:if>
        </h2>
        <c:forEach var="t" items="${allTopics}">
            <div style="border:1px solid red;padding:4px;margin:4px;">
                <a href="post?post=${t.id}"> 
                    <h3>${t.title}</h3>
                </a>
                <h4>${t.description}</h4>
                <c:if test="${status!=1}">
                    <a href="topic?edit&id=${t.id}" style="float:right; margin-left:20px">Edit</a>
                </c:if>
                <p>Created on: ${t.creationDate}</p> 
            </div>
        </c:forEach>
    </body>
</html>