(function(i, s, o, g, r, a , m) {
    i['GoogleAnalyticsObject'] = r;
    i[r] = i[r] || function() {
        (i[r].q = i[r].q || []).push(arguments)
    };
    i[r].l = 1 * new Date();
    a = s.createElement(o);
    m = s.getElementsByTagName(o)[0];
    a.async = 1;
    a.src = g;
})

(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
ga('create', 'UA-91376272-1', 'auto');
ga('send', 'pageview');

$(function() {
    var currentLocation = [0, 0];
    if (currentLocation[0] === 0 && currentLocation[1] === 0) {
        currentLocation = [35.699072, 51.402186];
    }
    var map = L.map('mapLocation').setView(currentLocation, 10);
    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png').addTo(map);
    map.attributionControl.setPrefix(false);
    var marker = new L.marker(currentLocation, {
        draggable: 'true'
    });
    marker.on('dragend', function() {
        var position = marker.getLatLng();
        marker.setLatLng(position, {
            draggable: 'true'
        }).bindPopup(position).update();
        $("#latitude").val(position.lat);
        $("#longitude").val(position.lng).keyup();
    });
    $("#latitude, #longitude").change(function() {
        var position = [parseInt($("#latitude").val()), parseInt($("#longitude").val())];
        marker.setLatLng(position, {
            draggable: 'true'
        }).bindPopup(position).update();
        map.panTo(position);
    });
    map.addLayer(marker);
})
