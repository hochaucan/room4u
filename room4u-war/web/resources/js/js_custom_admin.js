$(function () {
//alert("can")
   // SetActiveMenu()

});

function SetActiveMenu() {
    var path = window.location.pathname;
    path = path.replace(/\/$/, "");
    path = decodeURIComponent(path);

    $(".nav a").each(function () {
        $(this).click(function () {
            alert(this.href )
            if (this.href == window.location.href.replace("Admin", "")) {
                // $(this).closest("li").removeClass()("active");
                $(this).closest("li").addClass("active");
            }
        });

    });

}