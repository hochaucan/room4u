$(function () {
    SetActiveMenu()
    getDate();

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
});

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