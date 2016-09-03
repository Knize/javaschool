<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.StationEntity" %>
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
            <h4>Station adding</h4>
            <table class="responsive-table centered">
                <thead>
                <tr>
                    <th data-field="branch_index">Branch</th>
                    <th data-field="station_index">Station Index</th>
                    <th data-field="station_name">Station Name</th>
                    <th data-field="timezone">Time zone</th>
                    <th data-field="range">Range to next</th>
                    <th data-field="buttons">Delete/add</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (StationEntity se : (List<StationEntity>) request.getAttribute("StationList")) {
                %>
                <tr>
                    <td><%= se.getBranchIndex()%>
                    </td>
                    <td><%= se.getStationIndex()%>
                    </td>
                    <td><%= se.getStationName()%>
                    </td>
                    <td><%= se.getTimezone()%>
                    </td>
                    <td><%= se.getRangeKm()%>
                    </td>
                    <td><a class="btn-floating btn-large waves-effect waves-light red"><i
                            class="material-icons">clear</i></a></td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <form action="">
                        <td><input type="text"></td>
                        <td><input type="text"></td>
                        <td><input type="text"></td>
                        <td><input type="text"></td>
                        <td><input type="text"></td>
                        <td><a class="btn-floating btn-large waves-effect waves-light red"><i
                                class="material-icons">add</i></a>
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