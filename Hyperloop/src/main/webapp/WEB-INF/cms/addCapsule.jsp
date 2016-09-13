<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="../templates/head.jsp" %>
</head>
<body>
<%@include file="../templates/scripts.jsp" %>


<%@include file="../templates/cmsHeader.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <div class="container">
            <h4>Capsule adding</h4>
            <table class="responsive-table centered">
                <thead>
                <tr>
                    <th data-field="capsule_id">Capsule ID</th>
                    <th data-field="car_slots">Car Slots</th>
                    <th data-field="seats_number">Seats Number</th>
                    <th data-field="buttons">Delete/add</th>
                </tr>
                </thead>
                <tbody>

                <%--@elvariable id="capsuleList" type="java.util.List<ru.knize.hyperloop.entities.CapsuleEntity>"--%>
                <c:forEach items="${capsuleList}" var="capsule">
                    <tr>
                        <td>${capsule.capsuleId}</td>
                        <td>${capsule.carSlots}</td>
                        <td>${capsule.seatsNumber}</td>
                        <td>
                            <form method="get" action="/cms/addCapsule">
                                <button type="submit" id="delete"
                                        class="btn-floating btn-large waves-effect waves-light red"><i
                                        class="material-icons">clear</i></button>
                                <input name="deletedCapsuleNumber" type="text" value="${capsule.capsuleId}" hidden>
                            </form>

                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <form action="/cms/addCapsule" method="post">
                        <td></td>
                        <td><input name="carSlots" type="number"></td>
                        <td><input name="seatsNumber" type="number"></td>
                        <td>
                            <button type="submit" class="btn-floating btn-large waves-effect waves-light red"><i
                                    class="material-icons">add</i></button>
                        </td>
                    </form>
                </tr>
                </tbody>
            </table>

        </div>
    </main>
</div>
<%@include file="../templates/footer.jsp" %>
</body>
</html>