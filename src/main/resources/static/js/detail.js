


//在黑幕层阻挡touch事件
function init() {
    $("#login_input_bg").on("touchmove",function(e) {
        e.preventDefault();
    });

    $("#input_cross_mark").click(function() {
        $("#login_input_bg").css("display","none");
        $("#login_input_box").css("display","none");
    });

    $("#detail_login_link").click(function() {
        $("#login_input_bg").css("display","block");
        $("#login_input_box").css("display","block");
    });

    $("#sign_cross_mark").click(function() {
        $("#login_input_bg").css("display","none");
        $("#sign_input_box").css("display","none");
    });

    $(".l_button").click(function() {
        if($(this).attr("itemId") > 0){
            if($(this).attr("uid") > 0){
                signNow($(this).attr("itemId"), $(this).attr("uid"));
            }else{
                $("#login_input_bg").css("display","block");
                $("#sign_input_box").css("display","block");
                $("#sign_button_box").attr("itemId",$(this).attr("itemId"));
            }
        }
    });

    $("#return_login_link").click(function() {
        location.href = "/index";
    });

    $("#r_button").click(function() {
        location.href = "/index";
    });


    $("#login_button_box").click(function(){

        var gender = 2;
        if($("#gender_male_radio").attr("checked") == "checked"){
            gender = 1;
        }else if($("#gender_female_radio").attr("checked") == "checked"){
            gender = 2;
        }

        var postdata={
            "phoneNumber":$("#phone_input_line").val(),
            "familyName":$("#family_name_input").val(),
            "givenName":$("#given_name_input").val(),
            "gender":gender
        };

        if($("#phone_input_line").val() == null || $("#phone_input_line").val() == ''
            || $("#family_name_input").val() == null || $("#family_name_input").val() == ''
            || gender == null || gender == ''){
            alert("电话、姓氏、性别尚未填写");
            return;
        }

        $.ajax({
            url:'/user/login',
            type:'POST',
            async:true,
            data:JSON.stringify(postdata),
            timeout:6000,
            dataType:'json',
            contentType:"application/json",
            success:function(res){
                if(res.code === 200){
                    $("#login_input_bg").css("display","none");
                    $("#login_input_box").css("display","none");
                    refresh();
                }else{
                    alert(res.message);
                }
            },
            error:function(data){
                alert(data.statusText);
            }
        });
    });

    $("#sign_button_box").click(function(){

        var gender = 2;
        if($("#sign_male_radio").attr("checked") == "checked"){
            gender = 1;
        }else if($("#sign_female_radio").attr("checked") == "checked"){
            gender = 2;
        }

        var commodityId =  $(this).attr("itemId");

        if($("#phone_sign_line").val() == null || $("#phone_sign_line").val() == ''
            || $("#family_name_sign").val() == null || $("#family_name_sign").val() == ''
            || gender == null || gender == ''){
            alert("电话、姓氏、性别尚未填写");
            return;
        }

        var postdata={
            "phoneNumber":$("#phone_sign_line").val(),
            "familyName":$("#family_name_sign").val(),
            "givenName":$("#given_name_sign").val(),
            "gender":gender
        };

        $.ajax({
            url:'/user/sign?commodityId=' + commodityId,
            type:'POST',
            async:true,
            data:JSON.stringify(postdata),
            timeout:6000,
            dataType:'json',
            contentType:"application/json",
            success:function(res){
                if(res.code === 200){
                    $("#login_input_bg").css("display","none");
                    $("#sign_input_box").css("display","none");
                    refresh();
                }else{
                    alert(res.message);
                }
            },
            error:function(data){
                alert(data.statusText);
            }
        });
    });
}

function refresh(){
    window.location.reload();//刷新当前页面.
}

function signNow(commodityId, uid){


    $.ajax({
        url:'/user/signNow?commodityId=' + commodityId,
        type:'POST',
        async:true,
        data:{
            commodityId : commodityId,
            uid : uid
        },
        timeout:6000,
        dataType:'json',
        //contentType:"application/json",
        success:function(res){
            if(res.code === 200){
                refresh();
            }else{
                alert(res.message);
            }
        },
        error:function(data){
            alert(data.statusText);
        }
    });
}




