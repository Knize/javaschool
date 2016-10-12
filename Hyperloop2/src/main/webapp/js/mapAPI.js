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
var edges = [];
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
var colors = ['#FF0000', '#00FF00', '#0000FF', '#FFD634', '#BB34FD'];


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
    modal.find('#timezone').text("Timezone: " + station.timezone + ".");
    modal.find('#coordinates').text("Latitude: " + station.latitude.toString() + ". Longitude: " + station.longitude.toString() + ".");
    modal.data('station', station);
    modal.openModal();
}

function createStation(stationProto) {
    stationProto.marker = new google.maps.Marker({
        position: {lat: stationProto.latitude, lng: stationProto.longitude},
        map: map,
        title: stationProto.name,
        draggable: true
    });

    stationProto.marker.addListener('dragend', function () {
        if (!_.includes(updatedStations, stationProto)) {
            updatedStations.push(stationProto);
        }

        function updateEdges(_edges, i){
            var edgesToUpdate = _edges[stationProto.id];
            if(edgesToUpdate != undefined)
                edgesToUpdate.forEach(function (it) {
                    it.drawLine.getPath().setAt(i, stationProto.marker.getPosition());
                });
        }

        updateEdges(_.groupBy(edges, "toStation.id"),1);
        updateEdges(_.groupBy(edges, "fromStation.id"),0);

        setChangedTrue();
    });

    stationProto.marker.addListener('click', function () {
        stationEditPopup(stationProto);
    });

    stationProto.marker.addListener('rightclick', function () {
        var self = stationProto.marker;
        if (!drawOn) {
            currentEdge = {};
            currentEdge.branch = $('#branch').prop('selectedIndex');
            currentEdge.fromStation = stationProto;
            currentEdge.drawLine = new google.maps.Polyline({
                path: [self.getPosition(), self.getPosition()],
                geodesic: false,
                strokeColor: colors[currentEdge.branch],
                strokeOpacity: 1.0,
                strokeWeight: 2,
                map: map
            });
        } else {
            currentEdge.toStation = stationProto;
            currentEdge.drawLine.getPath().setAt(1, stationProto.marker.getPosition());
            edges.push(currentEdge);
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
        var prevStation = parseInt($(modal.find('#prevStation')).val());
        selectedBranch = $("#branch").prop('selectedIndex');
        station.name = $(modal.find('#name')).val();
        station.rangeKm = parseInt($(modal.find('#rangeKm')).val());
        modal.closeModal();
        setChanged(true);
    });
});

$(document).ready(function () {
    $('#deleteStation').click(function () {

        var modal = $('#stationEditModal');
        var station = modal.data('station');

        modal.closeModal();

        stationData.remove(station);
        deletedStations.push(station);
        setChanged(true);
    });
});


$('#add_station').click(function () {


    var station = {
        name: "New Station",
        timezone: "UTC+0",
        rangeKm: 0,
        latitude: 0.322,
        longitude: 0.228
    };
    createStation(station);
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
            rangeKm: station.rangeKm,
            latitude: markerPos.lat(),
            longitude: markerPos.lng()
        }
    }

    function clientEdgesToEdgesDTO(edge) {
        console.log("From station to DTO:");
        console.log(edge.fromStation);
        console.log("From station to DTO:");
        console.log(edge.toStation);
        return {
            fromStation: edge.fromStation,
            toStation: edge.toStation,
            rangeKm: edge.rangeKm,
            branch: edge.branch
        }
    }


    var requestData = {
        create: _.map(newStations, clientStationToStationDTO),
        update: _.map(updatedStations, clientStationToStationDTO),
        delete: _.map(deletedStations, "id"),
        edges: _.map(edges, clientEdgesToEdgesDTO)
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


    $.getJSON("/api/stations/list", function (stations) {
        stationData = stations;
        console.log(stations);
        stations.forEach(function (station) {
            createStation(station);
        });
    })
}