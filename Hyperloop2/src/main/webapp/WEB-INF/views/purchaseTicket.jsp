<%--@elvariable id="tripId" type="long"--%>
<%--@elvariable id="scheduleEntry" type="ru.knize.hyperloop.entities.CapsulesScheduleEntity"--%>
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
            <h2>You going to purchase a ticket</h2>
            <h4>Capsule info</h4>
            <h5>Capsule number: ${capsule.id}</h5>
            <h5>Seats number: ${capsule.seatsNumber}</h5>
            <h5>Car slots: ${capsule.carSlots}</h5>
            <h4>Tell us about your trip</h4>
            <form action="/purchaseConfirmation" method="post">
                <h5>Your start stationId is ${scheduleEntry.station.name}</h5>
                <div class="input-field">
                    <select name="arrStation">
                        <%--@elvariable id="selectedStationID" type="java.lang.Integer"--%>
                        <%--@elvariable id="stationsList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                        <c:forEach items="${stationList}" var="stationId">
                            <option
                                    <c:if test="${selectedStationID==stationId.stationId}">selected</c:if>
                                    value="${stationId.stationId}">${stationId.stationName}</option>
                        </c:forEach>
                        <c:if test="${selectedStationID == null}">
                            <option value="" selected disabled>Choose destination stationId</option>
                        </c:if>
                    </select>
                    <label>Destination stationId</label>
                </div>
                <div class="input-field col s6">
                    <i class="material-icons prefix">account_circle</i>
                    <input name="full_name" id="full_name" type="text" class="validate">
                    <label for="full_name">Your Full Name</label>
                </div>
                <div class="input-field">
                    <input id="birthdate" name="birthdate" type="date" class="datepicker">
                    <label for="birthdate">Your birthdate</label>
                </div>
                <div class="input-field col s12">
                    <select name="children">
                        <option value="0" selected>No children with me</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                    </select>
                    <label>Children</label>
                </div>
                <div class="input-field col s12">
                    <select name="car">
                        <option value="0" selected>No car</option>
                        <option value="1">I will take a car</option>
                    </select>
                    <label>Children</label>
                </div>
                <input name="capsule" type="text" value="${capsule.id}" hidden>
                <input name="schedule" type="text" value="${scheduleEntry.id}" hidden>
                <input name="depStation" type="text" value="${scheduleEntry.station.id}" hidden>
                <input name="trip_id" type="text" value="${tripID}" hidden>
                <input type="submit" class="btn">
            </form>
            <script>
                $(document).ready(function () {
                    $('select').material_select();
                });

                $('.datepicker').pickadate({
                    selectMonths: true, // Creates a dropdown to control month
                    selectYears: 15 // Creates a dropdown of 15 years to control year
                });
            </script>
        </div>
    </main>

</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
</html>