<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.CapsuleEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <%@include file="../templates/head.jsp" %>
</head>
<body>
<%@include file="../templates/scripts.jsp" %>

<%@include file="../templates/cmsHeader.jsp" %>
<main>
    <div class="container">
        <h4>Capsules list</h4>
        <form action="/cms/watchCapsules" method="get">
            <div class="input-field col s12">
                <select id="capsule" name="capsule">
                    <option value="" disabled selected>Choose capsule</option>
                    <c:forEach items="${capsulesList}" var="capsule">
                        <option value="${capsule.capsuleId}"  ${capsule.capsuleId == selectedCapsule.capsuleId ? 'selected="selected"' : ''}>${capsule.capsuleId}</option>
                    </c:forEach>
                </select>
                <label>Materialize Select</label>
                <input class="btn" type="submit">
            </div>
        </form>
        <script>
            $(document).ready(function () {
                $('select').material_select();
            });
        </script>

        <h4>This capsule</h4>
        <table>
            <thead>
            <tr>
                <th data-field="capsule_id">Capsule ID</th>
                <th data-field="car_slots">Car Slots</th>
                <th data-field="seats_number">Seats Number</th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="selectedCapsule" type="ru.knize.hyperloop.entities.CapsuleEntity"--%>
            <tr>
                <td><c:out value="${selectedCapsule.capsuleId}"/></td>
                <td><c:out value="${selectedCapsule.carSlots}"/></td>
                <td><c:out value="${selectedCapsule.seatsNumber}"/></td>
            </tr>
            </tbody>
        </table>
        <h3>Schedule</h3>
        <table class="responsive-table centered">
            <thead>
            <tr>
                <th data-field="station">Station</th>
                <th data-field="arrival_time">Arrival Time UTC+0</th>
                <th data-field="departure_time">Departure Time UTC+0</th>
                <th>Delete/Add</th>
            </tr>
            </thead>
            <tbody>
            <%--@elvariable id="scheduleList" type="java.util.List<ru.knize.hyperloop.entities.CapsulesScheduleEntity>"--%>
            <c:forEach var="row" items="${scheduleList}" varStatus="loop">
                <tr>
                    <td><c:out value="${row.stationByStationId.stationName}"/></td>
                    <td><c:out value="${row.arrivalTime}"/></td>
                    <td><c:out value="${row.departureTime}"/></td>
                    <td>
                        <button id="<c:out value='${loop.count}'/>" type="submit"
                                class="btn-floating btn-large waves-effect waves-light red"><i
                                class="material-icons">clear</i></button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <h4>Add schedule</h4>
        <form action="/cms/watchCapsules" method="post">
            <div class="input-field col s12">

                <select name="station" required>
                    <option value="" disabled selected>Choose station</option>
                    <%--@elvariable id="stationList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                    <c:forEach items="${stationList}" var="station">
                        <option value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                </select>
                <label>Start station</label>
            </div>
            <label for="date">Date</label>
            <input id="date" name="date" type="date" required>
            <label for="time">Time</label>
            <input id="time" name="time" type="time" required>
            <div class="input-field col s12">
                <select name="direction" required>
                    <option value="" disabled selected>Choose direction</option>
                    <%--@elvariable id="stationList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                    <option value="0">To Lisboa</option>
                    <option value="1">To Tokyo</option>
                </select>
                <label>Direction</label>
            </div>
            <input name="capsuleIdHidden" id="capsuleIdHidden" type="text" value="" hidden>
            <input type="submit" class="btn">
        </form>
        <script>
            $('#capsuleIdHidden').val($('#capsule').find(':selected').val());
        </script>
    </div>
</main>
<%@include file="../templates/footer.jsp" %>
</body>