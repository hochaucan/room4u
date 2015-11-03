$(function () {

//    validateFormAddFAQ();
    validateFormUpdateFAQ();
    checkUpdateValues();
//alert("can")


});

function displayReqDelete(render) {
    var id = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeleteReq\\:hdReqIdDel").val(id);
}

function displayRoomDelete(render) {
    var id = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeleteRoom\\:hdRoomIdDel").val(id);
}

function displayComDelete(render) {
    var id = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeleteCom\\:hdComIdDel").val(id);
}

function displayFAQUpdate(render) {
    //alert(   $(render).html());"
    var id = $(render).closest("tr").find("td:eq(0)").html();
    var question = $(render).closest("tr").find("td:eq(1  )").html();
    var answer = $(render).closest("tr").find("td:eq(2)").html();

    $("#frmUpdateFAQ\\:hdFaqId").val(id);
    $("#frmUpdateFAQ\\:txtQuestion").val(question);
    $("#frmUpdateFAQ\\:txtAnswer").val(answer);

    // alert(id)
}

function displayFAQDelete(render) {
    var id = $(render).closest("tr").find("td:eq(0)").html();
    $("#frmDeleteFAQ\\:hdFaqIdDel").val(id);
}



function validateFormUpdateFAQ() {

    $('#frmUpdateFAQ').validate({
        rules: {
            "frmUpdateFAQ:txtQuestion": {
                minlength: 3,
                maxlength: 200,
                required: true

            },
            "frmUpdateFAQ:txtAnswer": {
                minlength: 3,
                maxlength: 200,
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

// Custom message for Jquery validation
jQuery.extend(jQuery.validator.messages, {
    required: "Vui lòng không để trống",
    maxlength: jQuery.validator.format("Vui lòng nhập hơn {0} ký tự."),
    minlength: jQuery.validator.format("Vui lòng nhập ít hơn {0} ký tự."),
});

