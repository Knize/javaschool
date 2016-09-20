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
<%@include file="/WEB-INF/templates/cmsHeader.jsp" %>
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
                            <option value="" selected disabled>Choose station</option>
                        </c:if>
                        <%--@elvariable id="fromStationId" type="ru.knize.hyperloop.entities.StationEntity"--%>
                        <c:forEach items="${stationList}" var="station">
                            <option value="${station.stationId}" ${fromStationId==station.stationId ? 'selected="selected"' : ''}>
                                    ${station.stationName}</option>
                        </c:forEach>
                    </select>
                    <label>Arrive Station</label>
                </div>
                <div class="input-field">
                    <select name="to_station">
                        <%--@elvariable id="selectedStationID" type="java.lang.Integer"--%>
                        <%--@elvariable id="stationsList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                        <c:if test="${selectedStationID == null}">
                            <option value="" selected disabled>Choose station</option>
                        </c:if>
                        <%--@elvariable id="toStationId" type="ru.knize.hyperloop.entities.StationEntity"--%>
                        <c:forEach items="${stationList}" var="station">
                            <option value="${station.stationId}" ${toStationId==station.stationId ? 'selected="selected"' : ''}>
                                    ${station.stationName}</option>
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
                        <td>${cse.capsuleByCapsuleId.capsuleId}</td>
                        <td>${cse.stationByStationId.stationName}</td>
                        <td>${cse.departureTime}</td>
                        <td>${cse.arrivalTime}</td>
                        <td><form method="post" action="/purchaseTicket">
                            <button type="submit"
                                    class="btn-floating btn-large waves-effect waves-light red"><i
                                    class="material-icons">attach_money</i></button>
                            <input name="capsule" value="${cse.capsuleByCapsuleId.capsuleId}" hidden>
                            <input name="scheduleEntry" value="${cse.capsuleScheduleId}" hidden>
                            <input name="trip_id" type="text" value="${cse.trip_ID}" hidden>
                        </form></td>
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
