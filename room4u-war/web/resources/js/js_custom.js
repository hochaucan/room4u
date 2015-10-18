$(function () {
    SetActiveMenu();
   // alert(window.location.href.replace("Admin", ""));
});
//Google Map API
// In the following example, markers appear when the user clicks on the map.
// Each marker is labeled with a single alphabetical character.

// Set Active Menu
function SetActiveMenu() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);

    $(".nav a").each(function () {
        $(this).click(function () {
            if (this.href == window.location.href.replace("Admin","")) {
                // $(this).closest("li").removeClass()("active");
                $(this).closest("li").addClass("active");
            }
        });

    });

}

var map;
var markers = [];
var panorama;
var long = -122.254811, lat = 37.869260;
var curLong, curLat;
function initMap() {

    // Initialize Map
    var divMap = document.getElementById('map');
    map = new google.maps.Map(divMap, {
        center: {lat: -34.397, lng: 150.644},
        zoom: 15
    });

    // Get current position
    var infoWindow = new google.maps.InfoWindow({map: map});
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            curLong = position.coords.longitude;
            curLat = position.coords.latitude;

            infoWindow.setPosition(pos);
            infoWindow.setContent('Your current location.');
            map.setCenter(pos);
        }, function () {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }


    // This event listener will call addMarker() when the map is clicked.
    map.addListener('click', function (event) {
         deleteMarkers();
        addMarker(event.latLng);
        // alert("Latitude: " + event.latLng.lat() + " " + ", longitude: " + event.latLng.lng());
        long = event.latLng.lng();
        lat = event.latLng.lat();
        //alert(long+" " +lat)
        panorama = new google.maps.StreetViewPanorama(
                document.getElementById('street-view'),
                {
                    position: {lat: lat, lng: long},
                    pov: {heading: 165, pitch: 0},
                    zoom: 1
                });
    });

//Street View
    panorama = new google.maps.StreetViewPanorama(
            document.getElementById('street-view'),
            {
                position: {lat: lat, lng: long},
                pov: {heading: 165, pitch: 0},
                zoom: 1
            });

    $("#btnSearch").click(function () {
        var radius = $("#sltRadius").val();
        distanceService(radius);

    });
}


function distanceService(radius) {
    deleteMarkers();
    var bounds = new google.maps.LatLngBounds;
    //var origin1 = '546A, Hung Phu, Phuong 9, Quan 8, TP Ho Chi Minh, Vietnam'//{lat: 55.93, lng: -3.118};
    var yourLocation = {lat: curLat, lng: curLong};
    //var origin2 = 'Greenwich, England';
    var destinationA = 'Khu dan cu EHOME3, TP Ho Chi Minh'//'Stockholm, Sweden';
    var destinationB = 'duong D1, quan Binh Thanh, TP Ho Chi Minh, Vietnam';
    var destinationC = '5B Ton Duc Thang, Quan 1, TP Ho Chi Minh, Vietnam';
    var destinationD = '546A, Hung Phu, phuong 9, quan 8, TP Ho Chi Minh, Vietnam';
    var destinationIcon = 'https://chart.googleapis.com/chart?' +
            'chst=d_map_pin_letter&chld=D|FF0000|000000';
    var originIcon = 'https://chart.googleapis.com/chart?' +
            'chst=d_map_pin_letter&chld=O|FFFF00|000000';
    var geocoder = new google.maps.Geocoder;
    var service = new google.maps.DistanceMatrixService;
    service.getDistanceMatrix({
        origins: [yourLocation],
        destinations: [destinationA, destinationB, destinationC, destinationD],
        travelMode: google.maps.TravelMode.DRIVING,
        unitSystem: google.maps.UnitSystem.METRIC,
        avoidHighways: false,
        avoidTolls: false
    }, function (response, status) {
        if (status !== google.maps.DistanceMatrixStatus.OK) {
            alert('Error was: ' + status);
        } else {
            var originList = response.originAddresses;
            var destinationList = response.destinationAddresses;
            var outputDiv = document.getElementById('output');
            outputDiv.innerHTML = '';
            //deleteMarkers(markersArray);

            var showGeocodedAddressOnMap = function (asDestination) {
                var icon = asDestination ? destinationIcon : originIcon;
                return function (results, status) {
                    if (status === google.maps.GeocoderStatus.OK) {
                        map.fitBounds(bounds.extend(results[0].geometry.location));
                        markers.push(new google.maps.Marker({
                            map: map,
                            position: results[0].geometry.location,
                            icon: icon
                        }));
                    } else {
                        alert('Geocode was not successful due to: ' + status);
                    }
                };
            };

            for (var i = 0; i < originList.length; i++) {
                var results = response.rows[i].elements;
                geocoder.geocode({'address': originList[i]},
                showGeocodedAddressOnMap(false));
                for (var j = 0; j < results.length; j++) {

                    // Just get room around current location with radius
                    if (parseFloat(results[j].distance.text).toFixed(2) <= parseInt(radius)) {
                        geocoder.geocode({'address': destinationList[j]},
                        showGeocodedAddressOnMap(true));
                        outputDiv.innerHTML += originList[i] + ' to ' + destinationList[j] +
                                ': ' + results[j].distance.text + ' in ' +
                                results[j].duration.text + '<br>';
                    }
                }
            }
        }
    });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
}

// Adds a marker to the map.
// Adds a marker to the map and push to the array.
function addMarker(location) {
    var marker = new google.maps.Marker({
        position: location,
        map: map
    });
    markers.push(marker);
}
// Deletes all markers in the array by removing references to them.
function deleteMarkers() {
    clearMarkers();
    markers = [];
}

// Sets the map on all markers in the array.
function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

// Removes the markers from the map, but keeps them in the array.
function clearMarkers() {
    setMapOnAll(null);
}
