<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="../templates/head.jsp" %>
</head>
<body>
<%@include file="../templates/scripts.jsp" %>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/materialize.clockpicker.js"></script>

<%@include file="../templates/cmsHeader.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <div class="container">
            <h4>Capsule adding</h4>
            <table class="responsive-table centered">
                <thead>
                <tr>
                    <th data-field="capsule_id">Capsule ID</th>
                    <th data-field="branch_index">Branch</th>
                    <th data-field="car_slots">Car Slots</th>
                    <th data-field="seats_number">Seats Number</th>
                    <th data-field="arrival_time">Departure Time</th>
                    <th data-field="buttons">Delete/add</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>1</td>
                    <td>3</td>
                    <td>28</td>
                    <td>12:00</td>
                    <td><a class="btn-floating btn-large waves-effect waves-light red"><i
                            class="material-icons">clear</i></a>
                    </td>
                </tr>
                <tr>
                    <form action="">
                        <td><input type="number"></td>
                        <td><input type="number"></td>
                        <td><input type="text"></td>
                        <td><input type="text"></td>
                        <td>
                            <div class="row">
                                <div class="input-field col s12">
                                    <label for="timepicker_ampm_dark">Time am/pm</label>
                                    <input id="timepicker_ampm_dark" class="timepicker" type="time">
                                </div>
                            </div>
                        </td>
                        <td>
                            <button type="submit" class="btn-floating btn-large waves-effect waves-light red"><i
                                    class="material-icons">add</i></button>
                        </td>
                    </form>
                </tr>
                </tbody>
            </table>
            <script>
                $('.timepicker').pickatime({
                    default: 'now',
                    twelvehour: false, // change to 12 hour AM/PM clock from 24 hour
                    donetext: 'OK',
                    autoclose: false,
                    vibrate: true // vibrate the device when dragging clock hand
                });
            </script>

        </div>
    </main>
</div>
<%@include file="../templates/footer.jsp" %>
</body>
</html>