<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<header>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Forum</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="<%=request.getContextPath()%>/topic?getTopic">Topics</a></li>
                    <c:if test="${status!=1&&ulogovan}">
                    <li><a href="user?allUser">Users</a></li>
                    </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${ulogovan}">
                    <li><a href="logout">Logout</a>    </li>
                    <li style="color: white;">
                        <a style="color:white">
                            Welcome ${userName}
                        </a>
                    </li>    
                </c:if>
                <li><a href="<%=request.getContextPath()%>/registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="<%=request.getContextPath()%>/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </div>
    </nav>
</header>