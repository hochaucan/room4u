$(function () {
    setActiveMenu();
    validateFormPostRoom();
    validateFormUserLogin();
    renderRoomImageHomePage();
    setActiveMenuSidebar();
    validateFormChangePassword();
    validateFormRegisterUser();
    validateFormBookRoom();
    getBookRoomDateRange();
    roomRating();

    //Add booked date to cart
    addRoomToCart();
    
    // Add thousand comma to Room Price
  //alert($(".roomdetail_price").html())
   var priceWithThousand = commaSeparateNumber( $(".roomdetail_price").html())
   $(".roomdetail_price").html(priceWithThousand);

//    cleanModal();
    $('#slider').nivoSlider();

    //Collapse in FAQ page
    //$('.collapse').collapse()
});

function commaSeparateNumber(val){
    while (/(\d+)(\d{3})/.test(val.toString())){
      val = val.toString().replace(/(\d+)(\d{3})/, '$1'+','+'$2');
    }
    return val;
  }

function assignDeletedComment(render) {
    var deletedComId = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeletedCom\\:txtDeletedComId").val(deletedComId);
}

function assignDeletedAccomId(render) {

    var deletedAccomId = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeletedAccom\\:txtDeletedAccomId").val(deletedAccomId);

}

function deleteComSuccess(data) {
    var status = data.status;
    switch (status) {
        case "begin": // Before the ajax request is sent.
            // ...
            break;
        case "complete": // After the ajax response is arrived.
            // ...
            break;
        case "success": // After update of HTML DOM based on ajax response..
            var message = $("#frmDeletedCom\\:txtDeletedComResult").text();
            // alert(message)
            if (message === "success") {
                $('#mdDeleteCom').modal('toggle');
                growlmessage("<span class='glyphicon glyphicon-ok'></span>Xóa bình luận thành công", 300, "success");

                // window.location.reload();
            } else {
                growlmessage("Lỗi xóa bình luận", 300, "danger");
            }


            break;
    }
}

function updateAccomSuccess(data) {
    var status = data.status;
    switch (status) {
        case "begin": // Before the ajax request is sent.
            // ...
            break;
        case "complete": // After the ajax response is arrived.
            // ...
            break;
        case "success": // After update of HTML DOM based on ajax response..
            var message = $("#txtUpdateRoomResult").text();
            // alert(message)
            if (message !== "") {

                var accomName = jQuery.parseJSON(message).accomName;
                var accomDescription = jQuery.parseJSON(message).description;
                var accomPrice = jQuery.parseJSON(message).price;
                var accomNoOfBed = jQuery.parseJSON(message).noOfBed;
                var accomNoOfPerson = jQuery.parseJSON(message).noOfPersons;
                var accomNoOfToilet = jQuery.parseJSON(message).noOfToilet;

                $("#frmEditAccom\\:updateAccomName").val(accomName);
                $("#frmEditAccom\\:updateRoomDescription").val(accomDescription);
                $("#frmEditAccom\\:updateRoomPrice").val(accomPrice);
                $("#frmEditAccom\\:updateRoomNumberOfBed").val(accomNoOfBed);
                $("#frmEditAccom\\:updateRoomNumOfPerson").val(accomNoOfPerson);
                $("#frmEditAccom\\:updateRoomNumToilet").val(accomNoOfToilet);


                $('#roomEdit').modal('toggle');
//                window.location.reload();
            }

            break;
    }
}

function deleteAccomSuccess(data) {
    var status = data.status;
    switch (status) {
        case "begin": // Before the ajax request is sent.
            // ...
            break;
        case "complete": // After the ajax response is arrived.
            // ...
            break;
        case "success": // After update of HTML DOM based on ajax response..
            var message = $("#frmDeletedAccom\\:txtDeletedRoomResult").text();
            // alert(message)
            if (message === "success") {
                renderRoomImageHomePage();
                $('#roomDelete').modal('toggle');
                growlmessage("<span class='glyphicon glyphicon-ok'></span>Xóa phòng thành công", 300, "success");
                // window.location.reload();
            }


            break;
    }
}

function displayUpdateRoomModal() {
    var status = data.status;
    switch (status) {
        case "begin": // Before the ajax request is sent.
            // ...
            break;
        case "complete": // After the ajax response is arrived.
            // ...
            break;
        case "success": // After update of HTML DOM based on ajax response..

            $('#roomEdit').modal('toggle');

            break;
    }
}
var cart = new Array();
function addRoomToCart() {

    $("#btnBookRoom").click(function () {
        var fromDate = $("#dtpBookFrom input").val();
        var ToDate = $("#dtpBookTo input").val();
        if (fromDate === "" && ToDate === "") {
            return;
        }

        var price = parseInt($(".roomdetail_price").text());
        var d1 = $('#dtpBookFrom').data("DateTimePicker").date();
        var d2 = $('#dtpBookTo').data("DateTimePicker").date();
        var dateCount = (d2 - d1) / 86400000;


        cart.push({"No": cart.length + 1, "FromDate": fromDate, "ToDate": ToDate, "Price": price * dateCount});
        cartRender();


    });
// alert(JSON.stringify($.totalStorage('scores', scores)));

}

function cartRender() {
    var html = "";
    for (var i = 0; i < cart.length; i++) {
//            html += "<tr><td>" + cart[i].No + '</td><td>' + cart[i].FromDate + '</td><td>' + cart[i].ToDate + '</td><td><a class="btn btn-default"  data-toggle="modal" data-target="#customerDelete" ><span class="glyphicon glyphicon-trash"></span></a></td></tr>';
        html += "<tr><td>" + cart[i].No + '</td><td>' + cart[i].FromDate + '</td><td>'
                + cart[i].ToDate + '</td><td>'
                + cart[i].Price + '</td><td><a onclick="removeCartItem(this)"  style="cursor:pointer"><span class="glyphicon glyphicon-trash deleteCartItem" ></span></a></td></tr>';
    }
    $(".roomdetail_cart").css("display", "inline");
    $(".roomdetail_cart table tbody").html(html);
}

function removeCartItem(render) {
    // alert("Oanh")
    var cartIndex = $(render).closest("tr").find("td:eq(0)").text();
    cart.splice(cartIndex, 1);
    cartRender();
}

function getBookRoomDateRange() {
    var disableDateData = new Array();
    disableDateData.push("10/23/2015");
    disableDateData.push("10/25/2015");

    $('#dtpBookFrom').datetimepicker({
        format: 'DD/MM/YYYY',
        disabledDates: disableDateData,
        //useStrict:true  
        ignoreReadonly: true,
//        minDate: new Date()
//        keepOpen: true
    });

    $('#dtpBookTo').datetimepicker({
        format: 'DD/MM/YYYY',
        useCurrent: false, //Important! See issue #1075
        ignoreReadonly: true
//        keepOpen: true

    });
    $("#dtpBookFrom").on("dp.change", function (e) {
        $('#dtpBookTo').data("DateTimePicker").minDate(e.date);
    });
    $("#dtpBookTo").on("dp.change", function (e) {
        $('#dtpBookFrom').data("DateTimePicker").maxDate(e.date);
    });
}


//    $(".deleteCartItem").each(function(){
//        $(this).click(function(){
//            $(this).closest("tbody").remove("tr");
//        });
//    });

function roomRating() {
    $(".auto-submit-star").each(function () {
        var cbValue = $(this).val();
        var rateValue = $("#frmRoomRating\\:displayRate").val();
        if (cbValue === rateValue) {
            $(this).attr("checked", "checked");
        }
    });


    $(".auto-submit-star").rating({
        callback: function (value, link) {
            $("#frmRoomRating\\:roomRatingSelected").val(value);
            $("#frmRoomRating\\:btnBookRating").click();

// 'this' is the hidden form element holding the current value
            // 'value' is the value selected
            // 'element' points to the link element that received the click.

//alert("The value selected was '" + value + "'\n\nWith this callback function I can automatically submit the form with this code:\nthis.form.submit();");

// To submit the form automatically:
            //this.form.submit();

            // To submit the form via ajax:
            //$(this.form).ajaxSubmit();
        }
    });
}

function checkUserLoginForBookRoom(data) {
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
            var message = $("#frmBookRoom\\:txtBookRoomResult").text();
            if (message === "success") {
// $('#user_login_modal').modal('toggle');
                growlmessage("<span class='glyphicon glyphicon-ok'></span> Đặt phòng thành công!", 350, "success");
                //window.location.reload();
            } else if (message === "requiredlogin") {
                growlmessage("<span class='glyphicon glyphicon-ban-circle'></span> Bạn vui lòng đăng nhập!</span>", 350, "info");
            }
            else {
                growlmessage("Loi!", 350, "info");
            }
            break;
    }

}

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
                //growlmessage("Đăng nhập thành công!", 350);
                window.location.reload();
            } else {
                growlmessage("Tài khoản hoặc mật khẩu không đúng!", 350, "danger");
            }
            break;
    }

}

//function registerRoom() {
//    $("#user_register_room_modal").modal('toggle');
//    growlmessage('Đăng ký phòng thành công', 350, 'info');
//}

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
    $(".main_image").each(function () {
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



//            setTimeout(function () {
//                $.bootstrapGrowl("Đã gửi đăng ký. Vui lòng kiểm tra email để kích hoạt tài khoản.", {type: 'success'});
//            }, 1000);
            form.submit();

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

function validateFormBookRoom() {
    $('#frmBookRoom').validate({
        rules: {
            "frmBookRoom:txtBookFrom": {
                required: true
            },
            "frmBookRoom:txtBookTo": {
                required: true
            }
        },
        submitHandler: function (form) {
            //addRoomToCart();
//            $("#frmUserLogin\\:btnLoginParams").click();
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
    $("#yourReport").css("display", "none");
    $("#yourComment").css("display", "none");
     $("#yourRequiredRoom").css("display", "none");

    $(".profileSidebarMenu").find("a").each(function () {
        $(this).click(function () {
            $(this).addClass("active");
            $(this).siblings().removeClass("active");
            $("#personalInfo").css("display", "none");
            $("#personalPass").css("display", "none");
            $("#orderedRoom").css("display", "none");
            $("#yourPostedRoom").css("display", "none");
            $("#yourReport").css("display", "none");
            $("#yourComment").css("display", "none");
             $("#yourRequiredRoom").css("display", "none");

            var tabId = $(this).attr("href"); //.replaceAll("#", "");
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

