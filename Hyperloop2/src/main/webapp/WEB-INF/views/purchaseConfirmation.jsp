<%--@elvariable id="cseArr" type="ru.knize.hyperloop.entities.CapsulesScheduleEntity"--%>
<%--@elvariable id="schedule" type="ru.knize.hyperloop.entities.CapsulesScheduleEntity"--%>
<%--@elvariable id="arrStation" type="ru.knize.hyperloop.entities.StationEntity"--%>
<%--@elvariable id="depStation" type="ru.knize.hyperloop.entities.StationEntity"--%>
<%--@elvariable id="capsule" type="ru.knize.hyperloop.entities.CapsuleEntity"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <%@include file="/WEB-INF/templates/head.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/templates/scripts.jsp" %>
<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <div class="container">
            <title>Ticket purchase</title>
            <h2>You going to purchase this ticket</h2>
            <table>
                <thead>
                <tr>
                    <th>From</th>
                    <th>To</th>
                    <th>Departure time(local)</th>
                    <th>Arrival time(local)</th>
                    <th>Name</th>
                    <th>Birthdate</th>
                    <th>Car slot</th>
                    <th>Children</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>${depStation.name}</th>
                    <th>${arrStation.name}</th>
                    <th>${schedule.departureTime}</th>
                    <th>${cseArr.arrivalTime}</th>
                    <th>${name}</th>
                    <th>${birthdate}</th>
                    <th>${car}</th>
                    <th>${children}</th>
                    <th>${price}</th>
                </tr>
                </tbody>
            </table>
            <form action="/purchaseSuccess" method="post">
                <input value="${depStation.id}" name="fromStation" type="text" hidden>
                <input value="${arrStation.id}" name="toStation" type="text" hidden>
                <input value="${schedule.departureTime}" name="departureTime" type="text" hidden>
                <input value="${cseArr.arrivalTime}" name="arrivalTime" type="text" hidden>
                <input value="${name}" name="name" type="text" hidden>
                <input value="${birthdate}" name="birthdate" type="text" hidden>
                <input value="${car}" name="car" type="text" hidden>
                <input value="${children}" name="children" type="text" hidden>
                <input value="${price}" name="price" type="text" hidden>
                <input value="${schedule.capsule.id}" name="capsule" type="text" hidden>
                <input value="${schedule.id}" name="schedule" type="text" hidden>
                <input type="submit" class="btn">
            </form>
        </div>
    </main>
</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
</html>