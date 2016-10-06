<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.CapsuleEntity" %>
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
            <h2>Buy tickets</h2>
            <form action="/buyTickets" method="get">
                <div class="input-field">
                    <select name="from_station">
                        <%--@elvariable id="selectedStationID" type="java.lang.Integer"--%>
                        <%--@elvariable id="stationList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                        <c:if test="${selectedStationID == null}">
                            <option value="" selected disabled>Choose stationId</option>
                        </c:if>
                        <%--@elvariable id="fromStationId" type="ru.knize.hyperloop.entities.StationEntity"--%>
                        <c:forEach items="${stationList}" var="stationId">
                            <option value="${stationId.id}" ${fromStationId == stationId.id ? 'selected="selected"' : ''}>
                                    ${stationId.name}</option>
                        </c:forEach>
                    </select>
                    <label>Arrive Station</label>
                </div>
                <div class="input-field">
                    <select name="to_station">
                        <%--@elvariable id="selectedStationID" type="java.lang.Integer"--%>
                        <%--@elvariable id="stationsList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                        <c:if test="${selectedStationID == null}">
                            <option value="" selected disabled>Choose stationId</option>
                        </c:if>
                        <%--@elvariable id="toStationId" type="ru.knize.hyperloop.entities.StationEntity"--%>
                        <c:forEach items="${stationList}" var="stationId">
                            <option value="${stationId.id}" ${toStationId==stationId.id ? 'selected="selected"' : ''}>
                                    ${stationId.name}</option>
                        </c:forEach>
                    </select>
                    <label>Departure Station</label>
                </div>
                <%--@elvariable id="arrDate" type="java.lang.String"--%>
                <div>
                    <input name="from_time" type="date" class="datepicker" value="${arrDate.equals(null)?"":arrDate}">
                </div>
                <div>
                    <input name="to_time" type="date" class="datepicker" value="${depDate.equals(null)?"":depDate}">
                </div>
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
                <%--@elvariable id="cseList" type="java.util.List<ru.knize.hyperloop.entities.CapsulesScheduleEntity>"--%>
                <c:forEach items="${cseList}" var="cse">
                    <tr>
                        <td>${cse.capsule.id}</td>
                        <td>${cse.station.name}</td>
                        <td>${cse.departureTime}</td>
                        <td>${cse.arrivalTime}</td>
                        <td>
                            <form method="post" action="/purchaseTicket">
                                <button type="submit"
                                        class="btn-floating btn-large waves-effect waves-light red"><i
                                        class="material-icons">attach_money</i></button>
                                <input name="capsule" value="${cse.capsule.id}" hidden>
                                <input name="scheduleEntry" value="${cse.id}" hidden>
                                <input name="trip_id" type="text" value="${cse.tripID}" hidden>
                            </form>
                        </td>
                    </tr>

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
