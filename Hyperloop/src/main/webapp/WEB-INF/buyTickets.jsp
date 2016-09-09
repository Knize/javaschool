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
        <h2>Buy tickets</h2>
        <form action="/schedule" method="get">
            <div class="input-field">
                <select name="arrive_station">
                    <%--@elvariable id="selectedStationID" type="java.lang.Integer"--%>
                    <%--@elvariable id="stationsList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                    <c:forEach items="${stationsList}" var="station">
                        <option
                                <c:if test="${selectedStationID==station.stationId}">selected</c:if>
                                value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                    <c:if test="${selectedStationID == null}">
                        <option value="" selected disabled>Choose station</option>
                    </c:if>
                </select>
                <label>Arrive Station</label>
            </div>
            <div class="input-field">
                <select name="departure_station">
                    <%--@elvariable id="selectedStationID" type="java.lang.Integer"--%>
                    <%--@elvariable id="stationsList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                    <c:forEach items="${stationsList}" var="station">
                        <option
                                <c:if test="${selectedStationID==station.stationId}">selected</c:if>
                                value="${station.stationId}">${station.stationName}</option>
                    </c:forEach>
                    <c:if test="${selectedStationID == null}">
                        <option value="" selected disabled>Choose station</option>
                    </c:if>
                </select>
                <label>Departure Station</label>
            </div>
            <div>
                <input name="arrival_time" type="date" class="datepicker">
            </div>
            <div>
                <input name="departure_time" type="date" class="datepicker">
            </div>
            <input class="btn" type="submit">
        </form>

        <table>
            <thead>
            <tr>
                <th>Capsule ID</th>
                <th>From -
                <c:out value="${fromStation}"/> </th>
                <th>To - <c:out value="${toStation}"/></th>
            </tr>
            </thead>
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


    </main>
</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
</html>
