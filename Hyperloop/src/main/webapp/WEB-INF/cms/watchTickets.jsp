<%--@elvariable id="capsuleID" type="int"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <%@include file="../templates/head.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/templates/scripts.jsp" %>
<%@include file="/WEB-INF/templates/cmsHeader.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <div class="container">
            <h2>Watch tickets</h2>
            <form action="/cms/watchTickets" method="get">
                <div class="row">
                    <div class="input-field col s6">
                        <input name="capsule" placeholder="Enter capsule number" id="capsule_number" type="number"
                               class="validate" value="${capsuleID >= 0? capsuleID: ''}">
                        <label for="capsule_number">Capsule Number</label>
                    </div>
                    <input name="date" type="date">
                    <input class="btn" type="submit">
                </div>
            </form>
            <script>
                $(document).ready(function () {
                    $('select').material_select();
                });
            </script>
            <table>
                <thead>
                <tr>
                    <th>Ticket ID</th>
                    <th>Passenger</th>
                    <th>Car slot</th>
                    <th>Children</th>
                    <th>Departure Station</th>
                    <th>Arrival Station</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <%--@elvariable id="ticketList" type="java.util.List<ru.knize.hyperloop.entities.TicketEntity>"--%>
                <c:forEach var="ticket" items="${ticketList}">
                    <tr>
                        <td><c:out value="${ticket.ticketId}"/></td>
                        <td><c:out value="${ticket.personByPersonId.name}"/></td>
                        <!-- Car slot -->
                        <c:if test="${ticket.carSlot == 1}">
                            <td>Yes</td>
                        </c:if>
                        <c:if test="${ticket.carSlot == 0}">
                            <td>No</td>
                        </c:if>
                        <!-- Children -->
                        <c:if test="${ticket.children > 0 && ticket.children < 3}">
                            <td><c:out value="${ticket.children}"/></td>
                        </c:if>
                        <c:if test="${ticket.children == 0}">
                            <td>No</td>
                        </c:if>
                        <td><c:out value="${ticket.stationByDepartureStationId.stationName}"/></td>
                        <td><c:out value="${ticket.stationByArrivalStationId.stationName}"/></td>
                        <td><c:out value="${ticket.price}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
</html>
