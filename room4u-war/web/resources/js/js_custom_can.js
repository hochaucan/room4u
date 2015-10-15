$(function () {

    // Bootstrap Glowl Message
    // $.bootstrapGrowl("Welcome to Room4U.", {type: "success"});
//    
//    setTimeout(function() {
//        $.bootstrapGrowl("This is another test.", { type: 'success' });
//    }, 1000);
//    
//    setTimeout(function() {
//        $.bootstrapGrowl("Danger, Danger!", {
//            type: 'danger',
//            align: 'center',
//            width: 'auto',
//            allow_dismiss: false
//        });
//    }, 2000);
//    
//    setTimeout(function() {
//        $.bootstrapGrowl("Danger, Danger!", {
//            type: 'info',
//            align: 'left',
//            stackup_spacing: 30
//        });
//    }, 3000);

    setActiveMenu();
    validateFormPostRoom();
    validateFormUserLogin();
    renderRoomImageHomePage();


//    cleanModal();


    $('#slider').nivoSlider();
    //Collapse in FAQ page
    //$('.collapse').collapse()

});

// Render room images in home page.
function renderRoomImageHomePage() {
    $(".homepage_box").find(".main_image").each(function () {
        var obj = jQuery.parseJSON($(this).attr("src"));
        $(this).attr("src", "/room4u-war/images/" + obj.thumbnail.toString());
    });
}
// Set Active Menu
function setActiveMenu() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);

    $(".nav a").each(function () {
        if (this.href == window.location.href) {
            // $(this).closest("li").removeClass()("active");
            $(this).closest("li").addClass("active");
        }
    });

}

// Validate Form Post Room
function validateFormPostRoom() {
    $('#frmPostRoom').validate({
        rules: {
            "frmPostRoom:accomName": {
                minlength: 3,
                maxlength: 200,
                required: true


            },
            "frmPostRoom:roomPrice": {
                number: true,
                min: 0,
                max: 1000000000,
                required: true
            },
            "frmPostRoom:hourseNumber": {
                digits: true,
                min: 0,
                max: 1000000000,
                required: true
            },
            "frmPostRoom:roomStreet": {
                minlength: 3,
                required: true
            },
            "frmPostRoom:roomWard": {
                minlength: 1,
                required: true
            },
            "frmPostRoom:roomDistrict": {
                minlength: 1,
                required: true
            },
            "frmPostRoom:roomCity": {
                minlength: 1,
                required: true
            },
            "frmPostRoom:roomDescription": {
                minlength: 3,
                required: true
            },
            "frmPostRoom:numberOfBed": {
                digits: true,
                min: 0,
                required: true
            },
            "frmPostRoom:numberOfPerson": {
                digits: true,
                min: 0,
                required: true
            },
            "frmPostRoom:roomToilet": {
                digits: true,
                min: 0,
                required: true
            },
            "frmPostRoom:roomThumbnail": {
                required: true
//                accept: "image/jpg,image/jpeg,image/png,image/gif"
            },
            "frmPostRoom:roomFile1": {
                required: true
            },
            "frmPostRoom:roomFile2": {
                required: true
            },
            "frmPostRoom:roomFile3": {
                required: true
            }
        },
        messages: {
            "frmPostRoom:roomThumbnail": {
//                required: 'Chọn hình làm đại diện',
//                accept: 'Not an image!'
            }
        },
//        submitHandler: function (form) {
//
//            setTimeout(function () {
//                $.bootstrapGrowl("This is another test.", {type: 'success'});
//            }, 1000);
//
//            form.submit();
//
////            var url = '<?php echo SET_SUBSCRIBER; ?>';
////            var datastring = $("form").serialize();
////            alert(datastring);
////            return false;
////            $.ajax({
////                type: "POST",
////                url: url,
////                data: datastring,
////                success: function (data) {
////                    //alert(data); return false;
////                    form.submit();
////                }
////            });
////            return false;
//
//        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            if (element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });

}

function validateFormUserLogin() {

    $('#frmUserLogin').validate({
        rules: {
            "frmUserLogin:txtUserName": {
//                minlength: 1,
//                maxlength: 200,
                required: true


            },
            "frmUserLogin:txtPassword": {
//                number: true,
//                min: 0,
//                max: 1000000000,
                required: true
            }
        },
//        messages: {
//            "frmUserLogin:txtUserName": {
////                required: 'Chọn hình làm đại diện',
////                accept: 'Not an image!'
//            }
//        },
//        submitHandler: function (form) {
//
//            setTimeout(function () {
//                $.bootstrapGrowl("This is another test.", {type: 'success'});
//            }, 1000);
//
//            form.submit();
//
////            var url = '<?php echo SET_SUBSCRIBER; ?>';
////            var datastring = $("form").serialize();
////            alert(datastring);
////            return false;
////            $.ajax({
////                type: "POST",
////                url: url,
////                data: datastring,
////                success: function (data) {
////                    //alert(data); return false;
////                    form.submit();
////                }
////            });
////            return false;
//
//        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            if (element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });
}
// Clean modal when close
function cleanModal() {
    $(".modal").on('hide.bs.modal', function () {
        $(this).find("#frmPostRoom")[0].reset();
    });

}

// Custom message for Jquery validation
jQuery.extend(jQuery.validator.messages, {
    required: "Vui lòng không để trống",
    remote: "Please fix this field.",
    email: "Vui lòng nhập đúng địa chỉ Email.",
    url: "Please enter a valid URL.",
    date: "Please enter a valid date.",
    dateISO: "Please enter a valid date (ISO).",
    number: "Vui lòng nhập số.",
    digits: "Vui lòng nhập số nguyên dương.",
    creditcard: "Please enter a valid credit card number.",
    equalTo: "Vui lòng nhập lại.",
    accept: "Please enter a value with a valid extension.",
    maxlength: jQuery.validator.format("Vui lòng nhập hơn {0} ký tự."),
    minlength: jQuery.validator.format("Vui lòng nhập ít hơn {0} ký tự."),
    rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
    range: jQuery.validator.format("Vui lòng nhập giắ trị từ {0} đến {1}."),
    max: jQuery.validator.format("Vui lòng nhập giá trị nhỏ hơn hoặc bằng {0}."),
    min: jQuery.validator.format("Vui lòng nhập giá trị lơn hơn hoặc bằng {0}.")
});