$(function () {
    SetActiveMenu();
    //alert("Can")
    createRoomAddressArray();
//    alert($(".AccomAddress").text())
//    getLongAddressBaseOnLngLat($(".AccomAddress").text(), 0);

    
});

// Set Active Menu
function SetActiveMenu() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);

    $(".nav a").each(function () {
        $(this).click(function () {
            if (this.href == window.location.href.replace("Admin", "")) {
                // $(this).closest("li").removeClass()("active");
                $(this).closest("li").addClass("active");
            }
        });

    });
}
var roomAddressArr = new Array();
var selectedRoomAddressArr = new Array();

function createRoomAddressArray() {
    $(".txtRoomAddress").each(function () {
        roomAddressArr.push($(this).text());
    });
}

var map;
var markers = [];
var panorama;
var long = -122.254811, lat = 37.869260;
var curLong, curLat;
var image = 'http://www.google.com/intl/en_us/mapfiles/ms/micons/blue-dot.png';
function initMap() {

    // Initialize Map
    var divMap = document.getElementById('map');
    map = new google.maps.Map(divMap, {
        center: {lat: -34.397, lng: 150.644},
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP
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

            //getLongAddressBaseOnLngLat("(" + position.coords.latitude + "," + position.coords.longitude + ")");

            infoWindow.setPosition(pos);
            infoWindow.setContent('Vị trí của bạn');
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
        long = curLong = event.latLng.lng();
        lat = curLat = event.latLng.lat();
        //alert(long+" " +lat)

        // Click to see Street View
        panorama = new google.maps.StreetViewPanorama(
                document.getElementById('street-view'),
                {
                    position: {lat: lat, lng: long},
                    pov: {heading: 165, pitch: 0},
                    zoom: 1
                });

        getLongAddressBaseOnLngLat(event.latLng, $("#sltRadius").val());
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



    // SEARCH BOX
    // Create the search box and link it to the UI element.
    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function () {
        searchBox.setBounds(map.getBounds());
    });

    // [START region_getplaces]
    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function () {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Clear out the old markers.
        markers.forEach(function (marker) {
            marker.setMap(null);
        });
        markers = [];

        // For each place, get the icon, name and location.
        var bounds = new google.maps.LatLngBounds();
        places.forEach(function (place) {
            var icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25)
            };

            // Create a marker for each place.
            markers.push(new google.maps.Marker({
                map: map,
                icon: icon,
                title: place.name,
                position: place.geometry.location
            }));

            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
    // [END region_getplaces]
    
    assignLongAddressForRoom($(".AccomAddress").text());
}
;

function registerRoomChangeRadius() {
    $(".registerRoomRadius").html($("#sltRadius").val());
    $("#frmRegisterRoom\\:hdrad").val($("#sltRadius").val());
}

function getLongAddressBaseOnLngLat(lngLat, radius) {

    // Get address base on Lat and Lng
    var geocoder = new google.maps.Geocoder();
    //alert("can")
    geocoder.geocode({
        "latLng": lngLat
    }, function (results, status) {
        console.log(results, status);
        if (status == google.maps.GeocoderStatus.OK) {
            console.log(results);
            var lat = results[0].geometry.location.lat(),
                    lng = results[0].geometry.location.lng(),
                    placeName = results[0].address_components[0].long_name,
                    latlng = new google.maps.LatLng(lat, lng);

            // var radius = $("#sltRadius").val();
            $("#homepage_registerroom_info").html("Bạn muốn đăng ký phòng trong vòng bán kính <strong class='registerRoomRadius'>" + radius + "</strong> Km từ vị trí</br><strong>"
                    + results[0].formatted_address + "</strong>?");

            $("#frmRegisterRoom\\:hdlatt").val(lat);
            $("#frmRegisterRoom\\:hdlong").val(lng);
            $("#frmRegisterRoom\\:hdrad").val(radius);
            $("#frmRegisterRoom\\:hdaddress").val(results[0].formatted_address);
            $(".AccomAddress").text("results[0].formatted_address");
//
//            $("#frmRegisterRoom\\:hdlatt").val(lat);
//            $("#frmRegisterRoom\\:hdlong").val(lng);
//            $("#frmRegisterRoom\\:hdrad").val(radius);
//            $("#frmRegisterRoom\\:hdaddress").val(results[0].formatted_address);
        }
    });
}

function assignLongAddressForRoom(lngLat) {
alert("Can")
    // Get address base on Lat and Lng
    var geocoder = new google.maps.Geocoder();
    //alert("can")
    geocoder.geocode({
        "latLng": lngLat
    }, function (results, status) {
        console.log(results, status);
        if (status == google.maps.GeocoderStatus.OK) {
            console.log(results);
            var lat = results[0].geometry.location.lat(),
                    lng = results[0].geometry.location.lng(),
                    placeName = results[0].address_components[0].long_name,
                    latlng = new google.maps.LatLng(lat, lng);

            // var radius = $("#sltRadius").val();
//            $("#homepage_registerroom_info").html("Bạn muốn đăng ký phòng trong vòng bán kính <strong class='registerRoomRadius'>" + radius + "</strong> Km từ vị trí</br><strong>"
//                    + results[0].formatted_address + "</strong>?");

            $(".AccomAddress").text(results[0].formatted_address);

        }
    });
}

function getLngLatBaseOnAddress() {
    // Get address base on Lat and Lng
    var geocoder = new google.maps.Geocoder();
    var address = $("#frmPostRoom\\:hourseNumber").val() + " " + $("#frmPostRoom\\:roomStreet").val() + " ,phường "
            + $("#frmPostRoom\\:roomWard").val() + " ,quận "
            + $("#frmPostRoom\\:roomDistrict").val() + " "
            + $("#frmPostRoom\\:roomCity").val();
    //alert(address)
    geocoder.geocode({
        "address": address
    }, function (results, status) {
        console.log(results, status);
        if (status == google.maps.GeocoderStatus.OK) {
            console.log(results);
            $("#frmPostRoom\\:txtRoomFullAddress").val(results[0].geometry.location);
            $("#frmPostRoom\\:btnSubmitPostRoom").click();
            $("#modal_post_room").modal("toggle");
        }
    });
}

function distanceService(radius) {
    deleteMarkers();
    var bounds = new google.maps.LatLngBounds;
    //var origin1 = '546A, Hung Phu, Phuong 9, Quan 8, TP Ho Chi Minh, Vietnam'//{lat: 55.93, lng: -3.118};
    var yourLocation = {lat: curLat, lng: curLong};
    var origin2 = 'Greenwich, England';
    var destinationA = 'Khu dan cu EHOME3, TP Ho Chi Minh'//'Stockholm, Sweden';
    var destinationB = 'duong D1, quan Binh Thanh, TP Ho Chi Minh, Vietnam';
    var destinationC = '5B Ton Duc Thang, Quan 1, TP Ho Chi Minh, Vietnam';
    var destinationD = '546A, Hung Phu, phuong 9, quan 8, TP Ho Chi Minh, Vietnam';
//    var desArr = new Array();
//    desArr.push(destinationA);
//    desArr.push(destinationB);
//    desArr.push(destinationC);
//    desArr.push(destinationD);
    // alert(desArr)
    var destinationIcon = 'https://chart.googleapis.com/chart?' +
            'chst=d_map_pin_letter&chld=D|FF0000|000000';
    var originIcon = 'https://chart.googleapis.com/chart?' +
            'chst=d_map_pin_letter&chld=O|FFFF00|000000';
    var geocoder = new google.maps.Geocoder;
    var service = new google.maps.DistanceMatrixService;
    service.getDistanceMatrix({
        origins: [yourLocation],
        destinations: roomAddressArr, //[destinationA, destinationB, destinationC, destinationD],
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
//                    if (parseFloat(results[j].distance.text).toFixed(2) <= parseInt(radius)) {
                    if (parseInt(results[j].distance.text.replace(".", "")) <= parseInt(radius)) {
                        geocoder.geocode({'address': destinationList[j]},
                        showGeocodedAddressOnMap(true));
                        outputDiv.innerHTML += '<div class="row"><div class="col-md-12"><span class="glyphicon glyphicon-home" style="color: #0c84e4;"></span> Từ ' + originList[i] + ' <span class="glyphicon glyphicon-arrow-right" style="color: #0c84e4;"></span>  ' + destinationList[j] +
                                ': ' + results[j].distance.text + ' in ' +
                                results[j].duration.text + '</div></div>';


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
        map: map,
        icon: image
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
