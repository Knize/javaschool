<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.StationEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <%@include file="../../templates/head.jsp" %>
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
                    <select name="branch">
                        <option value="" disabled selected>Choose branch</option>
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
            <script type="text/javascript">
                Array.prototype.remove = function () {
                    var what, a = arguments, L = a.length, ax;
                    while (L && this.length) {
                        what = a[--L];
                        while ((ax = this.indexOf(what)) !== -1) {
                            this.splice(ax, 1);
                        }
                    }
                    return this;
                };

                var map;
                var stationData = [];
                var changed = false;
                function setChanged(v) {
                    changed = v;
                    $('#saveButton').prop("disabled", !changed);
                }
                function setChangedTrue() {
                    setChanged(true);
                }

                function stationEditPopup(stationId) {
                    var modal = $('#stationEditModal');
                    modal.find('#stationName').val(stationId.name);
                    modal.find('#stationIndex').val(stationId.index);
                    modal.find('#rangeKm').val(stationId.rangeKm);
                    modal.find('#timezone').text("Timezone: " + stationId.timezone + ".");
                    modal.find('#coordinates').text("Latitude: " + stationId.lat.toString() + ". Longitude: " + stationId.lng.toString() + ".");
                    modal.data('stationId', stationId);
                    modal.openModal();
                }

                function createStation(stationProto) {
                    stationProto.marker = new google.maps.Marker({
                        position: {lat: stationProto.lat, lng: stationProto.lng},
                        map: map,
                        title: stationProto.name,
                        draggable: true
                    });
                    stationProto.marker.addListener('dragend', setChangedTrue);
                    stationProto.marker.addListener('click', function () {
                        stationEditPopup(stationProto);
                    });
                    return stationProto;
                }

                $(document).ready(function () {
                    $('#submitStation').click(function () {

                        var modal = $('#stationEditModal');
                        var stationId = modal.data('stationId');

                        stationId.name = $(modal.find('#stationName')).val();
                        stationId.index = parseInt($(modal.find('#stationIndex')).val());
                        stationId.rangeKm = parseInt($(modal.find('#rangeKm')).val());

                        modal.closeModal();
                        setChanged(true)
                    });
                });

                $(document).ready(function () {
                    $('#deleteStation').click(function () {

                        var modal = $('#stationEditModal');
                        var stationId = modal.data('stationId');

                        stationData.remove(stationId);
                        modal.closeModal();
                        setChanged(true)
                    });
                });


                $('#add_station').click(function () {
                    var selectedBranch = $( "#branch").find("option:selected" ).index();

                    var stationId = {
                        name: "New Station",
                        timezone: "UTC+0",
                        rangeKm: 0,
                        branch: 0,
                        stationIndex: selectedBranch,
                        lat: 0.322,
                        lng: 0.228
                    };
                    createStation(stationId);
                    stationData.push(stationId);
                    setChanged(true);
                });

                $('#saveButton').click(function () {
                    console.log(stationData);
                    $.ajax({
                        type: "POST",
                        url: '/api/stations/update',
                        dataType: 'json',
                        data: JSON.stringify(stationData.map(function (stationId) {
                            var markerPos = stationId.marker.position;
                            return {
                                id: stationId.id, lat: markerPos.lat(), lng: markerPos.lng(),
                                name: stationId.name, timezone: stationId.timezone, branch: stationId.branch,
                                index: stationId.index, rangeKm: stationId.rangeKm
                            }
                        }))
                    }).done(function () {
                        setChanged(false);
                        Materialize.toast("Saved!", 2000);
                    })
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
                        stations.forEach(function (stationId) {
                            createStation(stationId);
                        });

                        var branchCoordinates = [];
                        stations.sort(function (a,b) {
                            var indexA = a.index;
                            var indexB = b.index;
                            if(indexA < indexB) return -1;
                            if(indexA > indexB) return 1;
                            return 0

                        });
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