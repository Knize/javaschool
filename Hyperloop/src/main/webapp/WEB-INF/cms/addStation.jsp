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
                            <option value="${branch.id}" ${branch.id == 0 ? 'selected="selected"' : ''}>${branch.name}</option>
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
                    modal.find('#rangeKm').val(station.rangeKm);
                    modal.find('#timezone').text("Timezone: " + station.timezone + ".");
                    modal.find('#coordinates').text("Latitude: " + station.lat.toString() + ". Longitude: " + station.lng.toString() + ".");
                    modal.data('station', station);
                    modal.openModal();
                }

                function createStation(stationProto) {
                    stationProto.marker = new google.maps.Marker({
                        position: {lat: stationProto.lat, lng: stationProto.lng},
                        map: map,
                        title: stationProto.name,
                        draggable: true
                    });
                    stationProto.marker.addListener('dragend', changeListener);
                    stationProto.marker.addListener('click', function () {
                        stationEditPopup(stationProto);
                    });
                    return stationProto;
                }

                $(document).ready(function () {
                    $('#submitStation').click(function () {

                        var modal = $('#stationEditModal');
                        var station = modal.data('station');

                        station.name = $(modal.find('#stationName')).val();
                        station.index = parseInt($(modal.find('#stationIndex')).val());
                        station.rangeKm = parseInt($(modal.find('#rangeKm')).val());

                        modal.closeModal();
                        console.log(station);
                        setChanged(true)
                    });
                });


                $('#add_station').click(function () {
                    var station = {
                        name: "New Station",
                        timezone: "UTC+0",
                        rangeKm: 0,
                        branch: 0,
                        stationIndex: 0,
                        lat: 0.322,
                        lng: 0.228
                    };
                    createStation(station);
                    stationData.push(station);
                    setChanged(true);
                });

                $('#saveButton').click(function () {
                    console.log(stationData);
                    $.ajax({
                        type: "POST",
                        url: '/api/stations/update',
                        dataType: 'json',
                        data: JSON.stringify(stationData.map(function (station) {
                            var markerPos = station.marker.position;
                            return {
                                id: station.id, lat: markerPos.lat(), lng: markerPos.lng(),
                                name: station.name, timezone: station.timezone, branch: station.branch,
                                index: station.index, rangeKm: station.rangeKm
                            }
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
                        center: {lat: 0, lng: 0},
                        mapTypeId: google.maps.MapTypeId.TERRAIN
                    });
                    console.log("Map loaded");


                    $.getJSON("/api/stations/list", function (stations) {
                        stationData = stations;
                        console.log(stations);
                        stations.forEach(function (station) {
                            createStation(station);
                        });

                        var branchCoordinates = [];
                        stations.forEach(function (stationInFor) {
                            branchCoordinates.push({
                                lat: stationInFor.marker.position.lat(),
                                lng: stationInFor.marker.position.lng()
                            });
                        });
                        var branch = new google.maps.Polyline({
                            path: branchCoordinates,
                            geodesic: true,
                            strokeColor: '#FF0000',
                            strokeOpacity: 1.0,
                            strokeWeight: 2
                        });
                        branch.setMap(map);

                    })

                }
            </script>
            <div id="stationEditModal" class="modal">
                <div class="modal-content">
                    <label for="stationName">Station Name</label>
                    <input id="stationName" type="text">
                    <label for="stationIndex">Station Index</label>
                    <input type="number" id="stationIndex">
                    <label for="rangeKm">Range, km</label>
                    <input type="number" id="rangeKm">
                    <p id="timezone"></p>
                    <p id="coordinates" ></p>
                </div>
                <div class="modal-footer">
                    <a id="submitStation" class="waves-effect waves-green btn-flat">OK</a>
                </div>
            </div>
        </div>
    </main>
</div>
<%@include file="../templates/footer.jsp" %>
<%@include file="../templates/googleMaps.jsp" %>
</body>

</html>