


//在黑幕层阻挡touch事件
function init() {
    $("#login_input_bg").on("touchmove",function(e) {
        e.preventDefault();
    });

    $("#input_cross_mark").click(function() {
        $("#login_input_bg").css("display","none");
        $("#login_input_box").css("display","none");
    });

    $("#login_link").click(function() {
        $("#login_input_bg").css("display","block");
        $("#login_input_box").css("display","block");
    });
}



