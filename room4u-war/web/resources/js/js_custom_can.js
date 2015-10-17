$(function () {

   $("#btnLogin2").click(function(){
        //$('#user_login_modal').modal('toggle');
        alert("can")
    });


    setActiveMenu();
    validateFormPostRoom();
    validateFormUserLogin();
    renderRoomImageHomePage();
    setActiveMenuSidebar()
    validateFormChangePassword();
    validateFormRegisterUser();
//    cleanModal();

    $('#slider').nivoSlider();
    //Collapse in FAQ page
    //$('.collapse').collapse()

});

function LoginEventHandler(data) {
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
            var message = $("#frmUserLogin\\:txtLoginResult").text();

            if (message !== "") {
                $('#user_login_modal').modal('toggle');
                //   growlmessage("Đăng nhập thành công!", 350);
                window.location.reload();
            } else {
                growlmessage("Tài khoản hoặc mật khẩu không đúng!", 350, "danger");
            }
            break;
    }

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

function renderRoomImageHomePage() {
    $(".homepage_box").find(".main_image").each(function () {
        var obj = jQuery.parseJSON($(this).attr("src"));
        $(this).attr("src", "/room4u-war/images/" + obj.thumbnail.toString());
    });
}

function setActiveMenu() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);

    $(".nav a").each(function () {
        if (this.href == window.location.href) {
            $(this).closest("li").addClass("active");
        }
    });

}

function validateFormRegisterUser() {
    $('#frmUserRegister').validate({
        rules: {
            "frmUserRegister:txtCustName": {
                minlength: 3,
                maxlength: 200,
                required: true
            },
            "frmUserRegister:txtCustAccount": {
                minlength: 3,
                maxlength: 200,
                required: true
            },
            "frmUserRegister:txtCustPass": {
                minlength: 3,
                maxlength: 200,
                required: true
            },
            "frmUserRegister:txtCustEmail": {
                required: true,
                email: true
            },
            "frmUserRegister:txtCustPhone": {
                number: true,
                required: true
            },
            "frmUserRegister:fileCustThumbnail": {
                required: true
            }
        },
        messages: {
            "frmPostRoom:roomThumbnail": {
//                required: 'Chọn hình làm đại diện',
//                accept: 'Not an image!'
            }
        },
        submitHandler: function (form) {



            setTimeout(function () {
                $.bootstrapGrowl("This is another test.", {type: 'success'});
            }, 1000);

            // form.submit();

//            var url = '<?php echo SET_SUBSCRIBER; ?>';
//            var datastring = $("form").serialize();
//            alert(datastring);
//            return false;
//            $.ajax({
//                type: "POST",
//                url: url,
//                data: datastring,
//                success: function (data) {
//                    //alert(data); return false;
//                    form.submit();
//                }
//            });
//            return false;

        },
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
                required: true
            },
            "frmUserLogin:txtPassword": {
                required: true
            }
        },
        submitHandler: function (form) {
            $("#frmUserLogin\\:btnLoginParams").click();
            //form.submit();
        },
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

var validate;
function validateFormChangePassword() {

    validate = $("#frmChangePassword").validate({
        rules: {
            "frmChangePassword:txtCurentPass": {
//                minlength: 1,
//                maxlength: 200,
                required: true
            },
            "frmChangePassword:txtNewPass": {
//                number: true,
//                min: 0,
//                max: 1000000000,
                required: true
            },
            "frmChangePassword:txtConfirmNewPass": {
//                number: true,
//                min: 0,
//                max: 1000000000,
                required: true
            }
        },
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

function setActiveMenuSidebar() {

    $("#personalInfo").css("display", "inline");
    $("#personalPass").css("display", "none");
    $("#orderedRoom").css("display", "none");
    $("#yourPostedRoom").css("display", "none");

    $(".profileSidebarMenu").find("a").each(function () {
        $(this).click(function () {
            $(this).addClass("active");
            $(this).siblings().removeClass("active");

            $("#personalInfo").css("display", "none");
            $("#personalPass").css("display", "none");
            $("#orderedRoom").css("display", "none");
            $("#yourPostedRoom").css("display", "none");

            var tabId = $(this).attr("href");//.replaceAll("#", "");
            $(tabId).css("display", "inline");

            // Reset form change user password
            validate.resetForm();

        });
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