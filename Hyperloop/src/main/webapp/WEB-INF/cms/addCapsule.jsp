
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="../templates/head.jsp" %>
</head>
<body>
<%@include file="../templates/scripts.jsp" %>
<%@include file="../templates/cmsHeader.jsp" %>
    <div class="container">
        <h4>Capsule adding</h4>
        <table class="responsive-table centered">
            <thead>
            <tr>
                <th data-field="capsule_id">Capsule ID</th>
                <th data-field="branch_index">Branch</th>
                <th data-field="car_slots">Car Slots</th>
                <th data-field="seats_number">Seats Number</th>
                <th data-field="arrival_time">Arrival Time</th>
                <th data-field="buttons">Delete/add</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>1</td>
                <td>Berlin</td>
                <td>CET - UTC +2</td>
                <td>0</td>
                <td><a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">clear</i></a></td>
            </tr>
            <tr>
                <form action="">
                    <td><input type="number"></td>
                    <td><input type="number"></td>
                    <td><input type="text"></td>
                    <td><input type="text"></td>
                    <td><input type="text"></td>
                    <td></td>
                </form>
            </tr>
            </tbody>
        </table>
    </div>
<%@include file="../templates/footer.jsp" %>
</body>
</html>