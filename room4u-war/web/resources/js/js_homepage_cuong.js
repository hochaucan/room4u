$(function() {
    validateFormContact();

    $('#slider').nivoSlider();

});

function validateFormContact() {
    $('#frmContact').validate({
        rules: {
            "frmContact:contactName": {
                minlength: 3,
                maxlength: 200,
                required: true
            },
            "frmContact:contactEmail": {
                minlength: 3,
                maxlength: 200,
                required: true,
                email: true
            },
            
            "frmContact:contactContent": {
                minlength: 3,
                maxlength: 200,
                required: true
            },
            
            highlight: function(element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            unhighlight: function(element) {
                $(element).closest('.form-group').removeClass('has-error');
            },
            errorElement: 'span',
            errorClass: 'help-block',
            errorPlacement: function(error, element) {
                if (element.parent('.input-group').length) {
                    error.insertAfter(element.parent());
                } else {
                    error.insertAfter(element);
                }
            }
        }});
}

function checkSendMail(data) {
//prependId="false" for <h:form>

    var status = data.status;
    switch (status) {
        case "begin": // Before the ajax request is sent.
            // ...
            break;
        case "complete": // After the ajax response is arrived.
            // ...
            break;
        case "success": // After update of HTML DOM based on ajax response..
            var message = $("#frmContact\\:txtSendMailResult").text();
            if (message === "success") {
                growlmessage("<span class='glyphicon glyphicon-ok'></span> Mail đã được gửi thành công!", 350, "success");
            }
            else {
                growlmessage("Loi!", 350, "info");
            }
            break;
    }

}

function displayReqDelete(render) {
    var id = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeleteReq\\:hdReqIdDel").val(id);
}

function checkSendRequire(data) {
 //prependId="false" for <h:form>
 
     var status = data.status;
     switch (status) {
         case "begin": // Before the ajax request is sent.
             // ...
             break;
         case "complete": // After the ajax response is arrived.
             // ...
             break;
         case "success": // After update of HTML DOM based on ajax response..
             var message = $("#frmRegisterRoom\\:txtRequireResult").text();
             if (message === "success") {
                 growlmessage("<span class='glyphicon glyphicon-ok'></span> Đăng ký phòng thành công!", 350, "success");
                 $("#user_register_room_modal").modal("toggle");
             }else if (message === "requiredlogin") {
                 growlmessage("<span class='glyphicon glyphicon-ban-circle'></span> Bạn vui lòng đăng nhập!</span>", 350, "info");
             }else {
                 growlmessage("Lỗi!", 350, "info");
             }
             break;
     }
 
 }

function getAddress()
{
     var geocoder = new google.maps.Geocoder();
    //alert("can")
    geocoder.geocode({
        "latLng": lngLat
    }, function (results, status) {
        console.log(results, status);
        if (status == google.maps.GeocoderStatus.OK) {
            console.log(results);
            var lat = "#yourRequiredRoom\\:longtitude",
                    lng = "#yourRequiredRoom\\:lattitude",
                    placeName = results[0].address_components[0].long_name,
                    latlng = new google.maps.LatLng(lat, lng);

            $("#yourRequiredRoom\\:txtAddress").val(results[0].formatted_address);
        }
        });
}

 function growlmessage(message, width, messageType) {
     $.bootstrapGrowl(message, {
         ele: 'body', // which element to append to
         type: messageType, // (null, 'info', 'danger', 'success')
         offset: {from: 'top', amount: 20}, // 'top', or 'bottom'
         align: 'right', // ('left', 'right', or 'center')
         width: width, // (integer, or 'auto')
         delay: 4000, // Time while the message will be displayed. It's not equivalent to the *demo* timeOut!
         allow_dismiss: true, // If true then will display a cross to close the popup.
         stackup_spacing: 10 // spacing between consecutively stacked growls.
 
     });
 }
 
jQuery.extend(jQuery.validator.messages, {
    required: "Vui lòng không để trống",
    maxlength: jQuery.validator.format("Vui lòng nhập hơn {0} ký tự."),
    minlength: jQuery.validator.format("Vui lòng nhập ít hơn {0} ký tự."),
    email: "Vui lòng nhập đúng địa chỉ Email."
});
