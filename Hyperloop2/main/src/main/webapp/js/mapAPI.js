/**
 * Created by knize on 08.10.16.
 */

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

class Station {
    constructor(name, rangeKm, timezone, latitude, longitude) {
        this.name = name;
        this.rangeKm = rangeKm;
        this.timezone = timezone;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

var map;
var stationData = [];
var newStations = [];
var deletedStations = [];
var updatedStations = [];
var edgesData = [];
var newEdges = [];
var deletedEdges = [];
var updatedEdges = [];
var color;
var changed = false;
var drawOn = false;
var drawAnchor;
var currentEdge = {
    fromStation: undefined,
    toStation: undefined,
    branch: undefined,
    rangeKm: 0
};
var selectedBranch;


// COLORS
var colors = ['#FF0000', '#FFD634', '#00FF00', '#0000FF', '#BB34FD'];


function setChanged(v) {
    changed = v;
    $('#saveButton').prop("disabled", !changed);
}

function setChangedTrue() {
    setChanged(true);
}

function stationEditPopup(station) {
    var modal = $('#stationEditModal');
    modal.find('#name').val(station.name);
    modal.find('#timezone').val(station.timezone);
    var position = station.marker.getPosition();
    modal.find('#coordinates').text("Latitude: " + position.lat() + ". Longitude: " + position.lng() + ".");
    modal.data('station', station);
    modal.find('#isEnd').val((station.endForBranch >= 0) ? 1 : 0);
    $("#isEnd").material_select("update");
    modal.openModal();
}

function edgeEditPopup(edge) {
    console.log(edge);
    var modal = $('#edgeEditModal');
    modal.find('#fromStation').val(edge.fromStationId);
    $("#fromStation").material_select("update");
    modal.find('#toStation').val(edge.toStationId);
    $("#toStation").material_select("update");
    modal.find('#edgeBranch').val(edge.branchId);
    $("#edgeBranch").material_select("update");
    modal.find('#rangeKm').val(edge.rangeKm);
    modal.data('edge', edge);
    modal.openModal();
}

function createEdge(edgeProto, path) {
    edgeProto.drawLine = new google.maps.Polyline({
        path: path,
        geodesic: false,
        strokeColor: colors[edgeProto.branchId],
        strokeOpacity: 0.7,
        strokeWeight: 6,
        map: map
    });

    edgeProto.drawLine.addListener('click', function () {
        edgeEditPopup(edgeProto);
    });
    edgeProto.drawLine.addListener('rightclick', function () {
        edgesData.remove(edgeProto);
        deletedEdges.push(edgeProto);
        edgeProto.drawLine.setMap(null);
        setChanged(true);
    });
}

function createStation(stationProto) {
    stationProto.marker = new google.maps.Marker({
        position: {lat: stationProto.latitude, lng: stationProto.longitude},
        map: map,
        title: stationProto.name,
        draggable: true
    });

    stationProto.marker.addListener('dragend', function () {
        if (!_.includes(newStations, stationProto) && !_.includes(updatedStations, stationProto)) {
            updatedStations.push(stationProto);
        }


        setChangedTrue();
    });
    function updateEdges(_edges, i) {
        var edgesToUpdate = _edges[stationProto.id];
        if (edgesToUpdate != undefined)
            edgesToUpdate.forEach(function (it) {
                it.drawLine.getPath().setAt(i, stationProto.marker.getPosition());
            });
    }

    stationProto.marker.addListener('drag', function () {


        updateEdges(_.groupBy(edgesData, "toStation.id"), 1);
        updateEdges(_.groupBy(edgesData, "fromStation.id"), 0);
    });


    stationProto.marker.addListener('click', function () {
        stationEditPopup(stationProto);
    });

    stationProto.marker.addListener('rightclick', function () {
        var self = stationProto.marker;
        if (!drawOn) {
            currentEdge = {};
            currentEdge.branchId = $('#branch').prop('selectedIndex');
            console.log(currentEdge.branchId);
            currentEdge.rangeKm = 0;
            currentEdge.fromStation = stationProto;
            currentEdge.drawLine = new google.maps.Polyline({
                path: [self.getPosition(), self.getPosition()],
                geodesic: false,
                strokeColor: colors[currentEdge.branchId],
                strokeOpacity: 0.7,
                strokeWeight: 6,
                map: map
            });
        } else {
            currentEdge.toStation = stationProto;
            currentEdge.drawLine.getPath().setAt(1, stationProto.marker.getPosition());
            currentEdge.drawLine.addListener('click', function () {
                edgeEditPopup(currentEdge);

            });
            currentEdge.drawLine.addListener('rightclick', function () {
                edgesData.remove(currentEdge);
                deletedEdges.push(currentEdge);
                currentEdge.drawLine.setMap(null);
                setChanged(true);
            });

            console.log("Listener: ");
            console.log(currentEdge);
            newEdges.push(currentEdge);
            edgesData.push(currentEdge);
            setChangedTrue();
        }

        drawOn = !drawOn;


    });
    return stationProto;
}

$(document).ready(function () {
    $('#submitStation').click(function () {
        var modal = $('#stationEditModal');
        var station = modal.data('station');
        station.name = $(modal.find('#name')).val();
        station.timezone = $(modal.find('#timezone')).val();
        updatedStations.push(station);
        modal.closeModal();
        setChanged(true);
    });
});

$(document).ready(function () {
    $('#submitEdge').click(function () {
        var modal = $('#edgeEditModal');
        var edge = modal.data('edge');
        var fromStationId = parseInt($(modal.find('#fromStation')).val());
        var toStationId = parseInt($(modal.find('#toStation')).val());
        var branchId = parseInt($(modal.find('#branch')).val());
        var rangeKm = parseInt($(modal.find('#rangeKm')).val());
        var stationById = _.keyBy(stationData, "id");
        edge.fromStation = stationById[fromStationId];
        edge.toStation = stationById[toStationId];
        edge.branchId = branchId;
        edge.rangeKm = rangeKm;
        updatedEdges.push(edge);
        modal.closeModal();
        setChanged(true);
    });
});

$(document).ready(function () {
    $('#deleteStation').click(function () {

        var modal = $('#stationEditModal');
        var station = modal.data('station');
        station.marker.setMap(null);
        modal.closeModal();
        stationData.remove(station);
        newStations.remove(station);
        updatedStations.remove(station);
        deletedStations.push(station);
        setChanged(true);
    });
});


$('#add_station').click(function () {

    var center = map.getCenter();
    var station = {
        name: "New Station",
        timezone: "UTC+0",
        latitude: center.lat(),
        longitude: center.lng(),
        endForBranch: false
    };
    createStation(station);
    newStations.push(station);
    stationData.push(station);
    setChanged(true);
});

$('#saveButton').click(function () {
    console.log(stationData);
    function clientStationToStationDTO(station) {
        var markerPos = station.marker.position;
        return {
            id: station.id,
            name: station.name,
            timezone: station.timezone,
            latitude: markerPos.lat(),
            longitude: markerPos.lng(),
            endForBranch: station.endForBranch
        }
    }

    function clientEdgesToEdgesDTO(edge) {
        console.log("From station to DTO:");
        console.log(edge.fromStationId);
        console.log("From station to DTO:");
        console.log(edge.toStationId);
        return {
            fromStationId: edge.fromStation.id,
            toStationId: edge.toStation.id,
            branchId: edge.branchId,
            rangeKm: edge.rangeKm

        }
    }


    var requestData = {
        stationsCreate: _.map(newStations, clientStationToStationDTO),
        stationsUpdate: _.map(updatedStations, clientStationToStationDTO),
        stationsDelete: _.map(deletedStations, "id"),
        edgesCreate: _.map(newEdges, clientEdgesToEdgesDTO),
        edgesUpdate: _.map(updatedEdges, clientEdgesToEdgesDTO),
        edgesDelete: _.map(deletedEdges, "id")
    };
    console.log(requestData);
    var JSONstring = JSON.stringify(requestData);
    console.log(JSONstring);
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: '/api/stations/update',
        dataType: 'json',
        data: JSONstring
    }).done(function () {
        setChanged(false);
        Materialize.toast("Saved!", 2000);
    })

    newStations = [];
    updatedStations = [];
    deletedStations = [];
    newEdges = [];
    updatedEdges = [];
    deletedEdges = [];
});


function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 2,
        center: {lat: 60, lng: 60},
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });


    map.addListener("mousemove", function (e) {
        if (drawOn) {
            currentEdge.drawLine.getPath().setAt(1, e.latLng);
        }

    });


    $.getJSON("/api/stations/list", function (wrapper) {
        console.log(wrapper);
        var stations = wrapper.stations;
        stationData = stations;
        stations.forEach(function (station) {
            createStation(station);
        });


        var stationById = _.keyBy(stationData, "id");
        var edges = wrapper.edges;
        edgesData = edges;
        edges.forEach(function (edge) {
            edge.toStation = stationById[edge.toStationId];
            edge.fromStation = stationById[edge.fromStationId];
            var path = [
                edge.fromStation.marker.getPosition(),
                edge.toStation.marker.getPosition()
            ];
            createEdge(edge, path);
        });

    })
}