<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8";pageEncoding="UTF-8">
    <title>XXX管理系统</title>
</head>
<!-- 背景特效 -->
<link rel="stylesheet" href="scripts/welcome_css_js/css/reset.css">
<link rel="stylesheet" href="scripts/welcome_css_js/css/supersized.css">
<link rel="stylesheet" href="scripts/welcome_css_js/css/style.css">
<script src="http://www.jq22.com/jquery/jquery-1.9.1.js"></script>
<script src="scripts/welcome_css_js/js/supersized.3.2.7.min.js"></script>
<script src="scripts/welcome_css_js/js/supersized-init.js"></script>
<!--弹窗-->
<link rel="stylesheet" href="scripts/message_css_js/css/iziToast.css">
<script src="scripts/message_css_js/js/iziToast.js"></script>
<body oncontextmenu="return false">
<div class="page-container" style="padding-top: 50px;">
    <h1>XXX管理系统</h1>
    <div style="margin-top: 50px">
        <form action="" method="post">
            <div>
                <input  id="username" type="text" name="username" class="username" placeholder="用户名" autocomplete="off"/>
            </div>
            <div>
                <input id="password" type="password"  name="password" class="password" placeholder="密码" />
            </div>
            <button id="submit" type="button" onclick="login($('#username').val(),$('#password').val());">登陆</button>
        </form>
        <div style="margin-top: 50px;">
            <p>If we can only encounter each other rather than stay with each other,then I wish we had never encountered.</p>
            <p style="margin-top:20px;">如果只是遇见，不能停留，不如不遇见。</p>
        </div>
    </div>
</div>
</body>
<script type="application/javascript">
    function login(username,password) {
        if(username=="" || password ==""){
            iziToast.warning({
                message:'用户名密码不能为空！',
                timeout:'1000'
            });
            return;
        }
        $.ajax({
            url:"/user/login",
            dataType:"json",
            type:"post",
            data:{"username":username,"password":password},
            success:function (result) {
                if(result.code=="1"){
                    iziToast.success({
                        message:'登陆成功！',
                        timeout:'1000'
                    });
                    window.location.href="page/main.jsp";
                }else{
                    iziToast.error({
                        message:result.desc,
                        timeout:'1000'
                    });
                }
            }
        });
    }
    //回车提交事件
    $("body").keydown(function(event) {
        if (event.keyCode == "13") {//keyCode=13是回车键
            login($('#username').val(), $('#password').val());
        }
    });
    function attention() {
        iziToast.show({
            class: 'test',
            color: 'dark',
            icon: 'icon-contacts',
            message: '嗨！,不能为空噢！',
            position: 'topCenter',
            transitionIn: 'flipInX',
            transitionOut: 'flipOutX',
            progressBarColor: 'rgb(0, 255, 184)',
            image: 'scripts/message_css_js/img/avatar.jpg',
            imageWidth: 70,
            layout:2,
            onClose: function(){
                console.info('onClose');
            },
            iconColor: 'rgb(0, 255, 184)'
        });
    }
</script>
</html>