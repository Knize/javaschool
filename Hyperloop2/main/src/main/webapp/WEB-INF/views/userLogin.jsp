<%--
  Created by IntelliJ IDEA.
  User: knize
  Date: 25.08.16
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="/WEB-INF/templates/head.jsp" %>
    <%@include file="../templates/CSRF.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/templates/scripts.jsp" %>

<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <div class="container">
            <h2 class="header">Sign in</h2>
            <form action="/userLogin" method="post">
                <div class="input-field">
                    <input id="username" type="text" class="validate">
                    <label for="username">Username</label>
                </div>
                <div class="input-field">
                    <input id="password" type="password" class="validate">
                    <label for="password">Password</label>
                </div>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
            <p>or</p>
            <a href="/registration">sign up</a>
        </div>
    </main>
</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
