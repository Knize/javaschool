<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="../../templates/head.jsp" %>
</head>
<body>
<%@include file="../../templates/scripts.jsp" %>


<%@include file="../../templates/cmsHeader.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <div class="container">
            <h4>Login to cms</h4>
            <c:url value="/cms/login" var="loginUrl"/>
            <form action="${loginUrl}" method="post">
                <c:if test="${param.error != null}">
                    <p>
                        Invalid username and password.
                    </p>
                </c:if>
                <c:if test="${param.logout != null}">
                    <p>
                        You have been logged out.
                    </p>
                </c:if>
                <p>
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username"/>	4
                </p>
                <p>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password"/>	5
                </p>
                <input type="hidden"                        6
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button type="submit" class="btn">Log in</button>
            </form>
        </div>
    </main>
</div>
<%@include file="../../templates/footer.jsp" %>
</body>
</html>