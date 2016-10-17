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
            <h2 class="header">Sign up</h2>
            <form action="/registration" method="post">
                <div class="input-field">
                    <input name="username" id="username" type="text" class="validate">
                    <label for="username">Username</label>
                </div>
                <div class="input-field">
                    <input name="email" id="email" type="email" class="validate">
                    <label for="email">Email</label>
                </div>
                <div class="input-field">
                    <input name="password" id="password" type="password" class="validate">
                    <label for="password">Password</label>
                </div>
                <div class="input-field">
                    <input name="passwordTwo" id="passwordTwo" type="password" class="validate">
                    <label for="passwordTwo">Repeat Password</label>
                </div>
                <input type="submit" class="btn">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
        </div>
    </main>
</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
