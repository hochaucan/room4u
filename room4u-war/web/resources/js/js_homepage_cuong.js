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

jQuery.extend(jQuery.validator.messages, {
    required: "Vui lòng không để trống",
    maxlength: jQuery.validator.format("Vui lòng nhập hơn {0} ký tự."),
    minlength: jQuery.validator.format("Vui lòng nhập ít hơn {0} ký tự."),
    email: "Vui lòng nhập đúng địa chỉ Email."
});
