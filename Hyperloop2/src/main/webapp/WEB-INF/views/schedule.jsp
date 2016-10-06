<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.CapsuleEntity" %>
<%@ page import="ru.knize.hyperloop.entities.StationEntity" %><%--
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
</head>
<body>
<%@include file="/WEB-INF/templates/scripts.jsp" %>

<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <div class="container">
            <h2 class="header">Schedule</h2>
            <h4>Choose stationId</h4>
            <form action="/schedule" method="get">
                <div class="input-field">
                    <select name="stationId">
                        <%--@elvariable id="selectedStationID" type="java.lang.Integer"--%>
                        <%--@elvariable id="stationList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                        <c:forEach items="${stationList}" var="stationId">
                            <option
                                    <c:if test="${selectedStationID==stationId.stationId}">selected</c:if>
                                    value="${stationId.stationId}">${stationId.stationName}</option>
                        </c:forEach>
                        <c:if test="${selectedStationID == null}">
                            <option value="" selected disabled>Choose station</option>
                        </c:if>
                    </select>
                    <label>Station</label>
                    <input class="btn" type="submit">
                </div>
            </form>
            <script>
                $(document).ready(function () {
                    $('select').material_select();
                });
            </script>
            <table class="responsive-table centered">
                <thead>
                <tr>
                    <th data-field="capsule_id">Capsule ID</th>
                    <th data-field="arrival_time">Arrival Time</th>
                    <th data-field="departure_time">Departure Time</th>
                    <th>Buy ticket</th>
                </tr>
                </thead>
                <tbody>
                <%--@elvariable id="scheduleList" type="java.util.List<ru.knize.hyperloop.entities.CapsulesScheduleEntity>"--%>
                <c:forEach var="schedule" items="${scheduleList}">
                    <tr>
                        <td><c:out value="${schedule.capsuleByCapsuleId.capsuleId}"/></td>
                        <td><c:out value="${schedule.arrivalTime}"/></td>
                        <td><c:out value="${schedule.departureTime}"/></td>
                        <td>
                            <form method="post" action="/purchaseTicket">
                                <button type="submit"
                                        class="btn-floating btn-large waves-effect waves-light red"><i
                                        class="material-icons">attach_money</i></button>
                                <input name="capsule" value="${schedule.capsuleByCapsuleId.capsuleId}" hidden>
                                <input name="scheduleEntry" value="${schedule.capsuleScheduleId}" hidden>
                                <input name="trip_id" type="text" value="${schedule.trip_ID}" hidden>
                            </form>
                        </td>
                    </tr>
                    <div class="divider"></div>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
