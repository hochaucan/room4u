
//Google Map API
function initMap() {
    var divMap = document.getElementById('map');
    //var divMap = $("#map");
    var map = new google.maps.Map(divMap, {
        center: {lat: -34.397, lng: 150.644},
        zoom: 15
    });
    var infoWindow = new google.maps.InfoWindow({map: map});

    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('Location found.');
            map.setCenter(pos);
        }, function () {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
}

function initialize() {
    var fenway = {lat: 42.345573, lng: -71.098326};
    var map = new google.maps.Map(document.getElementById('map'), {
        center: fenway,
        zoom: 14
    });
    var panorama = new google.maps.StreetViewPanorama(
            document.getElementById('pano'), {
        position: fenway,
        pov: {
            heading: 34,
            pitch: 10
        }
    });
    map.setStreetView(panorama);
}
