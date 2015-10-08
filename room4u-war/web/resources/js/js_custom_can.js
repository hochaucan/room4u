$(function () {

    // Slider room in room detail page
    $('#slider').nivoSlider();

    SetActiveMenu();

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