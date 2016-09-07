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
<h4>Capsules list</h4>
<%
    List<CapsuleEntity> capsuleEntities =
            (List<CapsuleEntity>) request.getAttribute("capsulesList");
    for (CapsuleEntity ce : capsuleEntities
            ) {
    }
%>


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
    <tr>
        <form action="">
            <td><input type="number"></td>
            <td><input type="number"></td>
            <td><input type="text"></td>
            <td><input type="text"></td>
            <td>

            </td>
            <td>
                <pre id="output"></pre>
                <a class="c-btn c-datepicker-btn">
                    <span class="material-icon">Click me</span>
                </a>
            </td>
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