$(function () {
    SetActiveMenu()
    getDate();
//    alert("can")
    validateFormAddFAQ();

// Adding comma to the price

// Assign Order Room Status when page load.
//alert($("#slRoomOrderStatus").val())
    $("#frmOrderRoomReport\\:txtOrderRoomStatus").val($("#slRoomOrderStatus option:selected").text());
    $(".registerRoomAddress2").each(function () {
        $(this).text(JSON.parse($(this).text()).fullAddress);
    })

    $(".registerRoomAddress").each(function () {
        $(this).text(JSON.parse($(this).text()).address);
    })
    $(".registerRoomRadius").each(function () {
        $(this).text(JSON.parse($(this).text()).Radius);
    })

//     $(".registerRoomAddress").text(JSON.parse($(".registerRoomAddress").text()).address)
    $(".currency").digits();

    renderDataTable("#frmAdminPostRoom\\:tbAdminPostRoom");
    renderDataTable("#frmAdminRegisterRoom table");
    renderDataTable("#frmAdminComment table");

});

function validateFormAddFAQ() {
    
    
     $('#frmAddFAQ').validate({
        rules: {
            "frmAddFAQ:txtQuestion": {
                minlength: 3,
                maxlength: 200,
                required: true
            },
            "frmAddFAQ:txtAnswer": {
                minlength: 3,
                maxlength: 200,
                required: true,
                noSpace: true

            }
        },
        messages: {
           
        },
        submitHandler: function (form) {

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


function renderDataTable(table) {
    $(table).DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
        "language": {
            "lengthMenu": "Chọn _MENU_ dòng mỗi trang",
            "zeroRecords": "Không có dữ liệu",
            "info": "Trang _PAGE_ of _PAGES_",
            "infoEmpty": "Không có dữ liệu",
            "infoFiltered": "(filtered from _MAX_ total records)"
        }
    });
}


function assignOrderStatus(render) {
//    alert($("#slRoomOrderStatus option:selected").text())
    $("#frmOrderRoomReport\\:txtOrderRoomStatus").val($("#slRoomOrderStatus option:selected").text());
}

function getDate() {
    $('#dtpBookDate').datetimepicker({
        format: 'MM/YYYY',
//        disabledDates: disableDateData,
        //useStrict:true  
        ignoreReadonly: true,
        viewMode: 'months',
//        minDate: currentDate,
//        showClear: true
//        keepOpen: true
    });

    $('#dtpPostDate').datetimepicker({
        format: 'MM/YYYY',
        ignoreReadonly: true,
        viewMode: 'months',
    });
    $('#dtpCommentDate').datetimepicker({
        format: 'MM/YYYY',
        ignoreReadonly: true,
        viewMode: 'months',
    });

    $('#dtpUserDate').datetimepicker({
        format: 'MM/YYYY',
        ignoreReadonly: true,
        viewMode: 'months',
    });


    $('#dtpRegisterRoomDate').datetimepicker({
        format: 'MM/YYYY',
        ignoreReadonly: true,
        viewMode: 'months',
    });
}


function SetActiveMenu() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);

    $(".nav a").each(function () {
        $(this).click(function () {
//            alert(this.href)
//            alert(window.location.href)
            if (this.href == window.location.href) {
                // $(this).closest("li").removeClass()("active");
                $(this).closest("li").addClass("active");
            }
        });

    });

}

$.fn.digits = function () {
    return this.each(function () {
        $(this).text($(this).text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,"));
    })
}