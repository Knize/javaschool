<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.CapsuleEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <%@include file="../templates/head.jsp" %>
    <script src="/js/sc-date-time.js"></script>
    <link rel="stylesheet" href="/css/sc-date-time.css">
</head>
<body>
<%@include file="../templates/scripts.jsp" %>

<%@include file="../templates/cmsHeader.jsp" %>
<h4>Capsules list</h4>
<%
    List<CapsuleEntity> capsuleEntities =
            (List<CapsuleEntity>) request.getAttribute("capsulesList");
    for (CapsuleEntity ce : capsuleEntities
            ) {
    }
%>
<form action="/cms/watchCapsules" method="get">
    <div class="input-field col s12">
        <select name="capsule">
            <option value="" disabled selected>Choose capsule</option>
            <c:forEach items="${capsulesList}" var="capsule">
                <option value="${capsule.capsuleId}">${capsule.capsuleId}</option>
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
<table class="responsive-table centered">
    <thead>
    <tr>
        <th data-field="station">Station</th>
        <th data-field="arrival_time">Arrival Time</th>
        <th data-field="departure_time">Departure Time</th>
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
    <tr>
        <form action="/cms/watchCapsules" method="get">
            <td>
                <div class="input-field col s12">
                    <select name="station">
                        <option value="" disabled selected>Choose station</option>
                        <%--@elvariable id="stationList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                        <c:forEach items="${stationList}" var="station">
                            <option value="${station.stationId}">${station.stationName}</option>
                        </c:forEach>
                    </select>
                    <label>Station</label>
                </div>
            </td>
            <td><input type="datetime"></td>
            <td></td>
            <td>
                <button type="submit" class="btn-floating btn-large waves-effect waves-light red"><i
                        class="material-icons">add</i></button>
            </td>
        </form>
    </tr>
    </tbody>
</table>
<%@include file="../templates/footer.jsp" %>
</body>