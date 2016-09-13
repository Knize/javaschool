<%--@elvariable id="capsule" type="ru.knize.hyperloop.entities.CapsuleEntity"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <%@include file="/WEB-INF/templates/head.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/templates/scripts.jsp" %>
<%@include file="/WEB-INF/templates/cmsHeader.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <div class="container">
            <title>Ticket purchase</title>
            <h2>You going to purchase this ticket</h2>
            <h4>Capsule info</h4>
            <h5>Capsule number: ${capsule.capsuleId}</h5>
            <h5>Seats number: ${capsule.seatsNumber}</h5>
            <h5>Car slots: ${capsule.carSlots}</h5>
            <form action="/purchaseConfirmation" method="get">

            </form>
        </div>
    </main>
</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
</html>