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
        <h2 class="header">Schedule</h2>
        <%
            List<StationEntity> stationList =
                    (List<StationEntity>) request.getAttribute("stationsList");
            for (StationEntity se : stationList
                    ) {
                out.println(se.getStationName());
            }
        %>
        <div>
            <h4>Choose station</h4>
            <form action="/schedule" method="post">
                <c:out value="${stationslist.get(1)}"/>
                <div class="input-field">
                    <select>
                        <option value="" disabled selected>Choose station</option>
                        <%--@elvariable id="stationslist" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>

                        <c:forEach var="station" items="${stationslist}" varStatus="loopCount">
                            <option value="<c:out value='${loopCount.count}'/>">
                                <c:out value="${station.stationName}"/></option>
                        </c:forEach>
                    </select>
                    <label>Station</label>
                </div>
            </form>
        </div>
        <div>
            <table class="responsive-table centered">
                <thead>
                <tr>
                    <th data-field="capsule_id">Capsule ID</th>
                    <th data-field="branch_index">Branch</th>
                    <th data-field="car_slots">Car Slots</th>
                    <th data-field="seats_number">Seats Number</th>
                    <th data-field="station">Station</th>
                    <th data-field="arrival_time">Arrival Time</th>
                    <th data-field="departure_time">Departure Time</th>
                </tr>
                </thead>
                <tbody>
                <%--@elvariable id="capsulesList" type="java.util.List<ru.knize.hyperloop.entities.CapsuleEntity>"--%>
                <c:forEach var="capsule" items="${capsulesList}">
                    <tr>
                        <td><c:out value="${capsule.capsuleId}"/></td>
                        <td><c:out value="${capsule.branch.name}"/></td>
                        <td><c:out value="${capsule.carSlots}"/></td>
                        <td><c:out value="${capsule.seatsNumber}"/></td>
                        <td>
                            <table>
                                <tbody>
                                <c:forEach var="row" items="${capsule.capsulesSchedulesByCapsuleId}">
                                    <tr>
                                        <td><c:out value="${row.capsuleScheduleId}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </td>
                        <td>
                            <table>
                                <tbody>
                                <c:forEach var="row" items="${capsule.capsulesSchedulesByCapsuleId}">
                                    <tr>
                                        <td><c:out value="${row.arrivalTime}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </td>
                        <td>
                            <table>
                                <tbody>
                                <c:forEach var="row" items="${capsule.capsulesSchedulesByCapsuleId}">
                                    <tr>
                                        <td><c:out value="${row.departureTime}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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
