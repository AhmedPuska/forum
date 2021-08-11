<%@page import="java.util.List"%>
<%@page import="model.Topic"%>
<%@page import="java.util.ArrayList"%>
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
        <div style="margin:10px">
            <h1> ${topic.title}</h1>
            <span> ${topic.description}</span>
            <hr/>
            <h2>Posts</h2>
            <br/>
            <div style="border:2px dotted red; padding: 10px;margin:15px;  ">
                <c:forEach var="p" items="${topic.posts}">
                    <div style="border:2px solid red; padding: 20px;margin: 5px;  ">
                        <div style="background-color: tomato; color: white;padding: 2px; margin-bottom: 2px">
                            <h3>${p.text}</h3><span>${p.creationDate}</span>
                            <c:if test="${status!=1}">
                                <a href="post?edit&id=${p.id}&topic=${topic.id}" style="float:right; margin-left:20px;margin-right: 4px">Edit</a>
                                <a href="post?del&id=${p.id}&topic=${topic.id}" style="float:right; margin-left:20px">Del</a>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${status!=1}">
                <a href="post?post=${topic.id}" style=" margin-left:20px"><h3>Add new post</h3></a>
                <hr>
                <div style="padding: 10px;margin: 10px;border: dotted 5px greenyellow">

                    <h3>Add post:</h3> 
                    <c:if test="${post==null}">
                        <form method="post" action="post?create&id=${topic.getId()}">
                        </c:if>
                        <c:if test="${post!=null}">
                            <form method="post" action="post?update&id=${post.id}">
                            </c:if>
                            <input  type="hidden" name="topic" value="${topic.id}" />
                            <input  type="hidden" name="id" value="${post.id}" />
                            <label>Comment:</label><input  name="text" value="${post.text}" /><br/>

                            <c:if test="${post==null}">
                                <input type="submit" value="Send post">
                            </c:if>
                            <c:if test="${post!=null}">
                                <input type="submit" value="Update">
                            </c:if>

                        </form>
                </div>
            </c:if>
        </div>
    </body>
</html>
