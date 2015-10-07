
//Google Map API
// In the following example, markers appear when the user clicks on the map.
// Each marker is labeled with a single alphabetical character.


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
       // deleteMarkers();
        //addMarker(event.latLng);
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
    
  

//Distance Service

//    var bounds = new google.maps.LatLngBounds;
//    var origin1 = {lat: 55.93, lng: -3.118};
//    var origin2 = 'Greenwich, England';
//    var destinationA = 'Stockholm, Sweden';
//    var destinationB = {lat: 50.087, lng: 14.421};
//    var destinationIcon = 'https://chart.googleapis.com/chart?' +
//            'chst=d_map_pin_letter&chld=D|FF0000|000000';
//    var originIcon = 'https://chart.googleapis.com/chart?' +
//            'chst=d_map_pin_letter&chld=O|FFFF00|000000';
//    var geocoder = new google.maps.Geocoder;
//    var service = new google.maps.DistanceMatrixService;
//    service.getDistanceMatrix({
//        origins: [origin1],
//        destinations: [destinationA],
//        travelMode: google.maps.TravelMode.DRIVING,
//        unitSystem: google.maps.UnitSystem.METRIC,
//        avoidHighways: false,
//        avoidTolls: false
//    }, function (response, status) {
//        if (status !== google.maps.DistanceMatrixStatus.OK) {
//            alert('Error was: ' + status);
//        } else {
//            var originList = response.originAddresses;
//            var destinationList = response.destinationAddresses;
//            var outputDiv = document.getElementById('output');
//            outputDiv.innerHTML = '';
//            //deleteMarkers(markersArray);
//
//            var showGeocodedAddressOnMap = function (asDestination) {
//                var icon = asDestination ? destinationIcon : originIcon;
//                return function (results, status) {
//                    if (status === google.maps.GeocoderStatus.OK) {
//                        map.fitBounds(bounds.extend(results[0].geometry.location));
//                        markers.push(new google.maps.Marker({
//                            map: map,
//                            position: results[0].geometry.location,
//                            icon: icon
//                        }));
//                    } else {
//                        alert('Geocode was not successful due to: ' + status);
//                    }
//                };
//            };
//
//            for (var i = 0; i < originList.length; i++) {
//                var results = response.rows[i].elements;
//                geocoder.geocode({'address': originList[i]},
//                showGeocodedAddressOnMap(false));
//                for (var j = 0; j < results.length; j++) {
//                    geocoder.geocode({'address': destinationList[j]},
//                    showGeocodedAddressOnMap(true));
//                    outputDiv.innerHTML += originList[i] + ' to ' + destinationList[j] +
//                            ': ' + results[j].distance.text + ' in ' +
//                            results[j].duration.text + '<br>';
//                }
//            }
//        }
//    });
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
//====================================
// Begin | Form Dang Ky & Dang Nhap
$(function() {
    
    var $formLogin = $('#login-form');
    var $formLost = $('#lost-form');
    var $formRegister = $('#register-form');
    var $divForms = $('#div-forms');
    var $modalAnimateTime = 300;
    var $msgAnimateTime = 150;
    var $msgShowTime = 2000;

    $("form").submit(function () {
        switch(this.id) {
            case "login-form":
                var $lg_username=$('#login_username').val();
                var $lg_password=$('#login_password').val();
                if ($lg_username == "ERROR") {
                    msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "error", "glyphicon-remove", "Login error");
                } else {
                    msgChange($('#div-login-msg'), $('#icon-login-msg'), $('#text-login-msg'), "success", "glyphicon-ok", "Login OK");
                }
                return false;
                break;
            case "lost-form":
                var $ls_email=$('#lost_email').val();
                if ($ls_email == "ERROR") {
                    msgChange($('#div-lost-msg'), $('#icon-lost-msg'), $('#text-lost-msg'), "error", "glyphicon-remove", "Send error");
                } else {
                    msgChange($('#div-lost-msg'), $('#icon-lost-msg'), $('#text-lost-msg'), "success", "glyphicon-ok", "Send OK");
                }
                return false;
                break;
            case "register-form":
                var $rg_username=$('#register_username').val();
                var $rg_email=$('#register_email').val();
                var $rg_password=$('#register_password').val();
                if ($rg_username == "ERROR") {
                    msgChange($('#div-register-msg'), $('#icon-register-msg'), $('#text-register-msg'), "error", "glyphicon-remove", "Register error");
                } else {
                    msgChange($('#div-register-msg'), $('#icon-register-msg'), $('#text-register-msg'), "success", "glyphicon-ok", "Register OK");
                }
                return false;
                break;
            default:
                return false;
        }
        return false;
    });
    
    $('#login_register_btn').click( function () { modalAnimate($formLogin, $formRegister) });
    $('#register_login_btn').click( function () { modalAnimate($formRegister, $formLogin); });
    $('#login_lost_btn').click( function () { modalAnimate($formLogin, $formLost); });
    $('#lost_login_btn').click( function () { modalAnimate($formLost, $formLogin); });
    $('#lost_register_btn').click( function () { modalAnimate($formLost, $formRegister); });
    $('#register_lost_btn').click( function () { modalAnimate($formRegister, $formLost); });
    
    function modalAnimate ($oldForm, $newForm) {
        var $oldH = $oldForm.height();
        var $newH = $newForm.height();
        $divForms.css("height",$oldH);
        $oldForm.fadeToggle($modalAnimateTime, function(){
            $divForms.animate({height: $newH}, $modalAnimateTime, function(){
                $newForm.fadeToggle($modalAnimateTime);
            });
        });
    }
    
    function msgFade ($msgId, $msgText) {
        $msgId.fadeOut($msgAnimateTime, function() {
            $(this).text($msgText).fadeIn($msgAnimateTime);
        });
    }
    
    function msgChange($divTag, $iconTag, $textTag, $divClass, $iconClass, $msgText) {
        var $msgOld = $divTag.text();
        msgFade($textTag, $msgText);
        $divTag.addClass($divClass);
        $iconTag.removeClass("glyphicon-chevron-right");
        $iconTag.addClass($iconClass + " " + $divClass);
        setTimeout(function() {
            msgFade($textTag, $msgOld);
            $divTag.removeClass($divClass);
            $iconTag.addClass("glyphicon-chevron-right");
            $iconTag.removeClass($iconClass + " " + $divClass);
  		}, $msgShowTime);
    }
});
// End | Form Dang Ky & Dang Nhap
//====================================
//====================================
// Begin | Form Quang ly tai khoan
$(document).ready(function(){
$("#mytable #checkall").click(function () {
        if ($("#mytable #checkall").is(':checked')) {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", true);
            });

        } else {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", false);
            });
        }
    });
    
    $("[data-toggle=tooltip]").tooltip();
});
// End | Form Quan ly tai khoan
//====================================
