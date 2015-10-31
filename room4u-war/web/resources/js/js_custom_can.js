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
    renderReceipt();
    validateFormEditRoom();
    addRoomToCart();
    renderRoomAddressInRoomDetail();


    cleanModal();
    $('#slider').nivoSlider();

    //Collapse in FAQ page
    //$('.collapse').collapse()
});

function renderRoomAddressInRoomDetail() {
    var roomAddressJson = $("#txtAccomAddress").text();
    if (roomAddressJson !== "") {
        $("#txtAccomAddress").text(JSON.parse(roomAddressJson).fullAddress);
    }
}

function assignDeletedComment(render) {
    var deletedComId = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeletedCom\\:txtDeletedComId").val(deletedComId);
}

function assignDeletedRoomOrderId(render) {

    var deletedRoomOrderId = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeletedRoomOrder\\:txtDeletedRoomOrderId").val(deletedRoomOrderId);

}

function assignDeletedCustomerOrderId(render) {
    var deletedCustomerOrderId = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeleteCustomerOrderRoomDelete\\:txtDeletedCustomerOrderRoomId").val(deletedCustomerOrderId);
}

function assignDeletedAccomId(render) {

    var deletedAccomId = $(render).closest("tr").find("td:eq(1)").html();
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
            var message = $("#frmUpdateAccom\\:txtUpdateRoomResult").text();
            // alert(message)
            if (message !== "") {

                var accomId = jQuery.parseJSON(message).accomId;
                var accomName = jQuery.parseJSON(message).accomName;
                var accomDescription = jQuery.parseJSON(message).description;
                var accomPrice = jQuery.parseJSON(message).price;
                var accomNoOfBed = jQuery.parseJSON(message).noOfBed;
                var accomNoOfPerson = jQuery.parseJSON(message).noOfPersons;
                var accomNoOfToilet = jQuery.parseJSON(message).noOfToilet;
                var thumbnail = jQuery.parseJSON(jQuery.parseJSON(message).images).thumbnail;
                var image1 = jQuery.parseJSON(jQuery.parseJSON(message).images).slider1;
                var image2 = jQuery.parseJSON(jQuery.parseJSON(message).images).slider2;
                var image3 = jQuery.parseJSON(jQuery.parseJSON(message).images).slider3;

                $("#frmEditAccom\\:updateAccomName").val(accomName);
                $("#frmEditAccom\\:updateRoomDescription").val(accomDescription);
                $("#frmEditAccom\\:updateRoomPrice").val(accomPrice);
                $("#frmEditAccom\\:updateRoomNumberOfBed").val(accomNoOfBed);
                $("#frmEditAccom\\:updateRoomNumOfPerson").val(accomNoOfPerson);
                $("#frmEditAccom\\:updateRoomNumToilet").val(accomNoOfToilet);
                $("#frmEditAccom\\:txtCurrentUpdateRomId").val(accomId);

                $("#imgRoomThumbnailDisplay").attr("src", "/room4u-war/images/" + thumbnail);
                $("#imgRoomImage1Display").attr("src", "/room4u-war/images/" + image1);
                $("#imgRoomImage2Display").attr("src", "/room4u-war/images/" + image2);
                $("#imgRoomImage3Display").attr("src", "/room4u-war/images/" + image3);

                $('#roomEdit').modal('toggle');
//                window.location.reload();
            }

            break;
    }
}

function editRoomSuccess(data) {
    var status = data.status;
    switch (status) {
        case "begin": // Before the ajax request is sent.
            // ...
            break;
        case "complete": // After the ajax response is arrived.
            // ...
            break;
        case "success": // After update of HTML DOM based on ajax response..
            var message = $("#frmEditAccom\\:txteditRoomSuccess").text();
            // alert(message)
            if (message === "success") {
                $('#roomEdit').modal('toggle');
                growlmessage("<span class='glyphicon glyphicon-ok'></span>Cập nhật thành công", 300, "success");
                // window.location.reload();
            }
            break;
    }
}

function deleteCustomerOrderRoomSuccess(data) {
    var status = data.status;
    switch (status) {
        case "begin": // Before the ajax request is sent.
            // ...
            break;
        case "complete": // After the ajax response is arrived.
            // ...
            break;
        case "success": // After update of HTML DOM based on ajax response..
            var message = $("#frmDeleteCustomerOrderRoomDelete\\:txtDeletedCustomerOrderRoomResult").text();
            // alert(message)
            if (message === "success") {
                //  renderRoomImageHomePage();
                renderReceipt();
                $('#roomCustomerOrderDelete').modal('toggle');
                growlmessage("<span class='glyphicon glyphicon-ok'></span>Xóa hóa đơn thành công", 300, "success");
                // window.location.reload();
            }


            break;
    }
}

function deleteRoomOrderSuccess(data) {
    var status = data.status;
    switch (status) {
        case "begin": // Before the ajax request is sent.
            // ...
            break;
        case "complete": // After the ajax response is arrived.
            // ...
            break;
        case "success": // After update of HTML DOM based on ajax response..
            var message = $("#frmDeletedRoomOrder\\:txtDeletedRoomOrderResult").text();
            // alert(message)
            if (message === "success") {
                //  renderRoomImageHomePage();
                renderReceipt();
                $('#roomOrderDelete').modal('toggle');
                growlmessage("<span class='glyphicon glyphicon-ok'></span>Xóa hóa đơn thành công", 300, "success");
                // window.location.reload();
            }


            break;
    }
}

function renderReceipt() {
    $(".thousandComma").digits();
    $("#pgDeleteCustomerOrderRoom").find("tr").find("td:eq(3)").each(function () {
        //$(this).prev("td").prev("td").digits();
        if ($(this).html() === "Đã xóa") {
            $(this).closest("tr").css("opacity", "0.7");
//            $(this).next("td").find("a").addClass("disabled");
//            $(this).next("td").next("td").find("a").addClass("disabled");
        }
    });
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

                $('#roomDelete').modal('toggle');
                growlmessage("<span class='glyphicon glyphicon-ok'></span>Xóa phòng thành công", 300, "success");
//                renderRoomImageHomePage();
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
    cart = [];
    $("#btnBookRoom").click(function () {
        var fromDate = $("#dtpBookFrom input").val();
        var ToDate = $("#dtpBookTo input").val();

        if (fromDate === "" || ToDate === "") {
            return;
        }

        var price = parseInt($(".roomdetail_price_number").text());
        var d1 = $('#dtpBookFrom').data("DateTimePicker").date();
        var d2 = $('#dtpBookTo').data("DateTimePicker").date();
        var dateCount = (d2 - d1) / 86400000;
        var isDuplicated = false;


        for (var i = 0; i < cart.length; i++) {
            if (fromDate === cart[i].FromDate && ToDate === cart[i].ToDate) {
                isDuplicated = true;
            }
        }

        if (!isDuplicated) {
            cart.push({"FromDate": fromDate, "ToDate": ToDate, "Price": price * (dateCount + 1)});
            cartRender();
        }
    });
// alert(JSON.stringify($.totalStorage('scores', scores)));

}

function cartRender() {
    var html = "";
    var totalPrice = 0;
    for (var i = 0; i < cart.length; i++) {
//            html += "<tr><td>" + cart[i].No + '</td><td>' + cart[i].FromDate + '</td><td>' + cart[i].ToDate + '</td><td><a class="btn btn-default"  data-toggle="modal" data-target="#customerDelete" ><span class="glyphicon glyphicon-trash"></span></a></td></tr>';
        html += "<tr><td>" + (i + 1) + '</td><td>' + cart[i].FromDate + '</td><td>'
                + cart[i].ToDate + '</td><td class="cartPriceItem">'
                + parseInt(cart[i].Price) + '</td><td><a onclick="removeCartItem(this)"  style="cursor:pointer"><span class="glyphicon glyphicon-trash deleteCartItem" ></span></a></td></tr>';
        totalPrice += parseInt(cart[i].Price);
    }

    if (cart.length !== 0) {
        $(".roomdetail_cart").css("display", "inline");
    } else {
        $(".roomdetail_cart").css("display", "none");
    }
    $("#frmBookRoom\\:txtBookRoomDataJson").val(JSON.stringify(cart));
    $(".roomdetail_cart table tbody").html(html);
    $(".cartPriceItem").digits();
    $(".totalRoomPrice").html("TỔNG : " + totalPrice + " đồng").digits();
}

function removeCartItem(render) {
    var cartIndex = $(render).closest("tr").find("td:eq(0)").text();
    cart.splice(cartIndex - 1, 1);
    cartRender();
}

function removeAllCart() {
    cart = [];
    cartRender();
}

function getBookRoomDateRange() {
//    var disableDateData = new Array();
//    disableDateData.push("10/23/2015");
//    disableDateData.push("10/25/2015");
    var d = new Date();

    var month = d.getMonth() + 1;
    var day = d.getDate();

    var currentDate =
            (('' + month).length < 2 ? '0' : '') + month + '/' +
            (('' + day).length < 2 ? '0' : '') + day + '/'
            + d.getFullYear();

    $('#dtpBookFrom').datetimepicker({
        format: 'DD/MM/YYYY',
//        disabledDates: disableDateData,
        //useStrict:true  
        ignoreReadonly: true,
        minDate: currentDate,
//        showClear: true
//        keepOpen: true
    });

    $('#dtpBookTo').datetimepicker({
        format: 'DD/MM/YYYY',
        useCurrent: false, //Important! See issue #1075
        ignoreReadonly: true,
        minDate: new Date(),
//        showClear: true
//        keepOpen: true

    });
    $("#dtpBookFrom").on("dp.change", function (e) {
        $('#dtpBookTo').data("DateTimePicker").minDate(e.date);
    });
    $("#dtpBookTo").on("dp.change", function (e) {
        $('#dtpBookFrom').data("DateTimePicker").maxDate(e.date);
    });
}

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
                $(".roomdetail_cart").css("display", "none");
                $("#frmBookRoom\\:txtBookFrom").val("");
                $("#frmBookRoom\\:txtBookTo").val("");
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
                growlmessage("Tài khoản hoặc mật khẩu không đúng!", 370, "info");
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

//validate khoang trong - space
jQuery.validator.addMethod("noSpace", function (value, element) {
    return value.indexOf(" ") < 0 && value != "";
}, "Username không được có khoảng trắng");

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
                required: true,
                noSpace: true

            },
            "frmUserRegister:txtCustPass": {
//                minlength: 3,
//                maxlength: 200,
                rangelength: [4, 10],
                required: true,
                noSpace: true
//                pattern: /^AR\d{4}$/
            },
            "frmUserRegister:txtCustPassConfirm": {
                equalTo: "#frmUserRegister\\:txtCustPass"
            },
            "frmUserRegister:txtCustEmail": {
                required: true,
                email: true
            },
            "frmUserRegister:txtCustPhone": {
                number: true,
                required: true,
                pattern: /^(09\d{8})|(01\d{9})||([3-7]\d{7})$/
            },
            "frmUserRegister:fileCustThumbnail": {
                required: true,
            }
        },
        messages: {
            "frmUserRegister:txtCustPass": {
                rangelength: 'Vui lòng nhập từ 4 đến 10 ký tự',
//                accept: 'Not an image!'
            }
//            "frmUserRegister:txtCustPhone": {
//                pattern: "Nhập sô điện thoại Việt Nam"
//            }
        },
        submitHandler: function (form) {
            form.submit();


//            setTimeout(function () {
//                $.bootstrapGrowl("Đã gửi đăng ký. Vui lòng kiểm tra email để kích hoạt tài khoản.", {type: 'success'});
//            }, 1000);
            //form.submit();

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


function validateFormEditRoom() {
    $('#frmEditAccom').validate({
        rules: {
            "frmEditAccom:updateAccomName": {
                minlength: 3,
                maxlength: 200,
                required: true
            },
            "frmEditAccom:updateRoomPrice": {
                number: true,
                min: 0,
                max: 1000000000,
                required: true
            },
            "frmEditAccom:updateRoomDescription": {
                minlength: 3,
                required: true
            },
            "frmEditAccom:updateRoomNumberOfBed": {
                digits: true,
                min: 0,
                required: true
            },
            "frmEditAccom:updateRoomNumOfPerson": {
                digits: true,
                min: 0,
                required: true
            },
            "frmEditAccom:updateRoomNumToilet": {
                digits: true,
                min: 0,
                required: true
            },
//            "frmPostRoom:roomThumbnail": {
//                required: true
////                accept: "image/jpg,image/jpeg,image/png,image/gif"
//            },
//            "frmPostRoom:roomFile1": {
//                required: true
//            },
//            "frmPostRoom:roomFile2": {
//                required: true
//            },
//            "frmPostRoom:roomFile3": {
//                required: true
//            }
        },
        messages: {
//            "frmPostRoom:roomThumbnail": {
//                required: 'Chọn hình làm đại diện',
//                accept: 'Not an image!'
//            }
        },
        submitHandler: function (form) {
            $("#frmEditAccom\\:btnEditRoomSubmit").click();
//            form.submit();

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
            "txtRoomAddress": {
                required: true
            },
//            "frmPostRoom:hourseNumber": {
//                required: true
//            },
//            "frmPostRoom:roomStreet": {
//                minlength: 3,
//                required: true
//            },
//            "frmPostRoom:roomWard": {
//                minlength: 1,
//                required: true
//            },
//            "frmPostRoom:roomDistrict": {
//                minlength: 1,
//                required: true
//            },
//            "frmPostRoom:roomCity": {
//                minlength: 1,
//                required: true
//            },
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
        submitHandler: function (form) {
//            getLngLatBaseOnAddress();
            $("#frmPostRoom\\:btnSubmitPostRoom").click();
//            $("#modal_post_room").modal("toggle");
            window.location.reload();

//            form.submit();

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

function validateFormBookRoom() {
    // Add thousand comma to Room Price
    $(".roomdetail_price").digits();

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
                equalTo: "#frmChangePassword\\:txtNewPass",
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
//    $("#modal_post_room").on('hide.bs.modal', function () {
//        $(this).find("#frmPostRoom")[0].reset();
//    });

    $("#user_login_modal").on('hide.bs.modal', function () {
        $(this).find("#frmUserLogin")[0].reset();
    });

    $("#user_register_modal").on('hide.bs.modal', function () {
        $(this).find("#frmUserRegister")[0].reset();
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
    $("#yourCustomerOrder").css("display", "none");


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
            $("#yourCustomerOrder").css("display", "none");

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

$.fn.digits = function () {
    return this.each(function () {
        $(this).text($(this).text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
    })
}
