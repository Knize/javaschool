<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div id="map" style="height: 500px">
            </div>
            <button disabled id="saveButton" class="btn-floating btn-large waves-effect waves-light red"><i
                    class="material-icons">save</i></button>
            <form action="/cms/addStation" method="get">
                <div class="input-field col s12">
                    <select name="branch">
                        <option value="" disabled selected>Choose branch</option>
                        <%--@elvariable id="branchList" type="java.util.List<ru.knize.hyperloop.entities.BranchEntity>"--%>
                        <c:forEach items="${branchList}" var="branch">
                            <option value="${branch.id}">${branch.name}</option>
                        </c:forEach>
                    </select>
                    <label>Branch Select</label>
                    <input type="submit" class="btn" value="Add station">
                </div>
            </form>
            <script>
                $(document).ready(function () {
                    $('select').material_select();
                });
            </script>
            <script type="text/javascript">
                var map;
                var stationData = [];
                var changed = false;
                function setChanged(v) {
                    changed = v;
                    $('#saveButton').prop("disabled", !changed);
                }
                function changeListener() {
                    setChanged(true);
                }
                function stationEditPopup(station) {
                    var modal = $('#stationEditModal');
                    modal.find('#stationName').val(station.name);
                    modal.find('#stationIndex').val(station.index);
                    modal.find('#rangeKm').val(station.range);
                    modal.openModal();
                }

                $('#saveButton').click(function () {
                    $.ajax({
                        type: "POST",
                        url: '/api/stations/update',
                        dataType: 'json',
                        data: JSON.stringify(stationData.map(function (station) {
                            var markerPos = station.marker.position;
                            return {id: station.id, lat: markerPos.lat(), lng: markerPos.lng(), name: station.name, timezone: station.timezone, branch: station.branch}
                        }))
                    }).done(function () {
                        setChanged(false);
                        Materialize.toast("Saved!");
                    })
                });

                $(window).bind('beforeunload', function () {
                    return changed;
                });

                function initMap() {
                    map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 1,
                        center: {lat: 0, lng: 0}
                    });
                    console.log("Map loaded");


                    $.getJSON("/api/stations/list", function (stations) {
                        stationData = stations;
                        console.log(stations);
                        stations.forEach(function (station) {
                            station.marker = new google.maps.Marker({
                                position: {lat: station.lat, lng: station.lng},
                                map: map,
                                title: station.name,
                                draggable: true
                            });
                            station.marker.addListener('dragend', changeListener);
                            station.marker.addListener('dragend', function () {
                                console.log("ddd");
                            });
                            station.marker.addListener('click', function () {
                                stationEditPopup(station);
                            })
                        })
                    })
                }
            </script>
            <div id="stationEditModal" class="modal">
                <div class="modal-content">
                    <input id="stationName" type="text">
                    <label for="stationName">Station Name</label>
                    <input type="number" id="stationIndex">
                    <label for="stationIndex">Station Index</label>
                    <input type="number" id="rangeKm">
                    <label for="rangeKm">Range, km</label>
                    <p id="timezone"></p>
                    <p id="coordinates"></p>
                </div>
                <div class="modal-footer">
                    <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">OK</a>
                </div>
            </div>
        </div>
    </main>
</div>
<%@include file="../templates/footer.jsp" %>
<%@include file="../templates/googleMaps.jsp" %>
</body>

</html>