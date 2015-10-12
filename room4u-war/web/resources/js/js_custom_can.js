$(function () {
    SetActiveMenu();


    $('#frmPostRoom').validate({
        rules: {
            "frmPostRoom:accomName": {
                minlength: 3,
                maxlength: 15,
                required: true
                
                
            },
            lastname: {
                minlength: 3,
                maxlength: 15,
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


    // Slider room in room detail page
    $('#slider').nivoSlider();

    //Collapse in FAQ page
    //$('.collapse').collapse()
});

function SetActiveMenu() {
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