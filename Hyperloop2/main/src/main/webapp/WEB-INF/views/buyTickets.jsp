<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.CapsuleEntity" %>
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
            <h2>Buy tickets</h2>
            <form action="/buyTickets" method="get">
                <div class="input-field">
                    <select name="from_station">
                        <%--@elvariable id="stationList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                        <%--@elvariable id="station" type="ru.knize.hyperloop.entities.StationEntity"--%>
                        <%--@elvariable id="fromStationId" type="java.lang.Integer"--%>
                        <c:forEach items="${stationList}" var="station">--%>
                            <option value="${station.id}" ${fromStationId == station.id ? 'selected="selected"' : ''}>
                                    ${station.name}</option>
                        </c:forEach>
                    </select>
                    <label>Arrive Station</label>
                </div>
                <div class="input-field">
                    <select name="to_station">
                        <%--@elvariable id="stationList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                        <%--@elvariable id="station" type="ru.knize.hyperloop.entities.StationEntity"--%>
                        <%--@elvariable id="fromStationId" type="java.lang.Integer"--%>
                        <c:forEach items="${stationList}" var="station">--%>
                            <option value="${station.id}" ${toStationId == station.id ? 'selected="selected"' : ''}>
                                    ${station.name}</option>
                        </c:forEach>
                    </select>
                    <label>Departure Station</label>
                </div>
                <%--@elvariable id="arrDate" type="java.lang.String"--%>
                <div>
                    <input id="from_time" name="from_time" type="date" class="datepicker"
                           value="${arrDate.equals(null)?"":arrDate}">
                    <label for="from_time">From date</label>
                </div>
                <div>
                    <input id="to_time" name="to_time" type="date" class="datepicker"
                           value="${depDate.equals(null)?"":depDate}">
                    <label for="to_time">To date</label>
                </div>
                <div class="input-field">
                    <input id="number" type="number" class="validate" required>
                    <label for="number">Number</label>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input class="btn" type="submit">
            </form>
            <table>
                <thead>
                <tr>
                    <th>Capsule ID</th>
                    <th>Station</th>
                    <th>Departure Time</th>
                    <th>Arrival Time</th>
                    <th>Buy Ticket</th>
                </tr>
                </thead>
                <tbody>
                <%--@elvariable id="routesList" type="java.util.List<java.util.List<org.springframework.data.util.Pair<ru.knize.hyperloop.entities.CapsulesScheduleEntity,ru.knize.hyperloop.entities.CapsulesScheduleEntity>>>"--%>
                <c:forEach items="${routesList}" var="route">
                    <c:forEach items="${route}" var="subRoute">
                        <tr>
                            <td>${subRoute.first.capsule.id}</td>
                            <td>${subRoute.first.fromStation.id}</td>
                            <td>${subRoute.first.departureTime}</td>
                            <td>${subRoute.second.toStation.id}</td>
                            <td>${subRoute.second.arrivalTime}</td>
                            <%--<td>--%>
                                <%--<form method="post" action="/purchaseTicket">--%>
                                    <%--<button type="submit"--%>
                                            <%--class="btn-floating btn-large waves-effect waves-light red"><i--%>
                                            <%--class="material-icons">attach_money</i></button>--%>
                                    <%--<input name="capsule" value="${cse.capsule.id}" hidden>--%>
                                    <%--<input name="scheduleEntry" value="${cse.id}" hidden>--%>
                                    <%--<input name="trip_id" type="text" value="${cse.tripID}" hidden>--%>
                                <%--</form>--%>
                            <%--</td>--%>
                        </tr>
                    </c:forEach>

                </c:forEach>
                </tbody>
            </table>
            <script>
                $(document).ready(function () {
                    $('select').material_select();
                });
            </script>
            <script>
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
