<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.StationEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <%@include file="../../templates/head.jsp" %>
    <%@include file="../../templates/CSRF.jsp" %>
</head>
<body>
<%@include file="../../templates/scripts.jsp" %>

<%@include file="../../templates/cmsHeader.jsp" %>
<div class="page-flexbox-wrapper">

    <main>
        <div class="container">
            <h4>Station adding</h4>
            <div id="map" style="height: 500px">
            </div>
            <button disabled id="saveButton" class="btn-floating btn-large waves-effect waves-light red"><i
                    class="material-icons">save</i></button>
            <form action="/cms/addStation" method="get">
                <div class="input-field col s12">
                    <select id="branch" name="branch">
                        <%--@elvariable id="branchList" type="java.util.List<ru.knize.hyperloop.entities.BranchEntity>"--%>
                        <%--@elvariable id="selectedBranch" type="ru.knize.hyperloop.entities.BranchEntity"--%>
                        <c:forEach items="${branchList}" var="branch">
                            <option value="${branch.id}" ${branch.id == selectedBranch.id ? 'selected="selected"' : ''}>${branch.name}</option>
                        </c:forEach>
                    </select>
                    <label>Branch Select</label>
                </div>
            </form>
            <button id="add_station" type="submit" class="btn">Add station</button>
            <script>
                $(document).ready(function () {
                    $('select').material_select();
                });
            </script>
            <script type="text/javascript" src="../../../js/mapAPI.js"></script>
            <div id="stationEditModal" class="modal">
                <div class="modal-content">
                    <label for="name">Station Name</label>
                    <input id="name" type="text">
                    <label for="prevStation">Previous Station</label>
                    <select name="prevStation" id="prevStation">
                        <option value="" disabled selected>Choose station</option>
                        <%--@elvariable id="stationList" type="java.util.List<ru.knize.hyperloop.entities.StationEntity>"--%>
                        <c:forEach items="${stationList}" var="station">
                            <option value="${station.id}" >${station.name}</option>
                        </c:forEach>
                    </select>
                    <label for="rangeKm">Range, km</label>
                    <input type="number" id="rangeKm">
                    <p id="timezone"></p>
                    <p id="coordinates"></p>
                </div>
                <div class="modal-footer">
                    <a id="submitStation" class="waves-effect waves-green btn-flat">OK</a>
                    <a id="deleteStation" class="waves-effect waves-green btn-flat red">DELETE STATION</a>
                </div>
            </div>
        </div>
    </main>
</div>
<%@include file="../../templates/footer.jsp" %>
<%@include file="../../templates/googleMaps.jsp" %>
</body>

</html>