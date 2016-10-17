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
            <title>Purchase success</title>
            <h2>${block == 1? "You can't buy this ticket. Check if you already bought a ticket on this capsule for this person." : "Thank you for purchase!"}</h2>
        </div>
    </main>
</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
</html>