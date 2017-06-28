<%@ page import="util.DateUtil" %>
<%@ page import="util.UserUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
    %>
    <title>
        校园卫生管理与监督平台
    </title>
    <script src="http://www.jq22.com/jquery/jquery-2.1.1.js"></script>
    <script src="http://www.jq22.com/jquery/jquery-ui-1.10.2.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
    <link rel="stylesheet" href="<%=path%>/scripts/main_scripts/css/style.css">
    <link rel="stylesheet" href="<%=path%>/scripts/main_scripts/css/se7en-font.css">
    <!--消息弹窗-->
    <link rel="stylesheet" href="<%=path%>/scripts/message_css_js/css/iziToast.css">
    <script src="<%=path%>/scripts/message_css_js/js/iziToast.js"></script>
    <!--时间选择控件-->
    <script type="text/JavaScript" src="<%=path%>/scripts/My97DatePicker/WdatePicker.js"></script>
    <!--表单校验-->
    <script type="application/javascript" src="<%=path%>/scripts/chart_validate/jquery.validate.js"></script>

    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
</head>
<body>
<div class="modal-shiftfix">
    <!-- Navigation -->
    <div class="navbar navbar-fixed-top scroll-hide">
        <div class="container-fluid top-bar">
            <div class="pull-right">
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown messages hidden-xs">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span aria-hidden="true"
                                                                                         class="se7en-envelope"></span>
                            <div class="sr-only">
                                Messages
                            </div>
                            <p class="counter">
                                3
                            </p>
                        </a>
                        <ul class="dropdown-menu messages">
                            <li><a href="#">
                                <img width="z" height="34"
                                     src="<%=path%>/scripts/main_scripts/images/avatar-male2.png"/>Could we meet today?
                                I wanted...</a>
                            </li>
                            <li><a href="#">
                                <img width="34" height="34"
                                     src="<%=path%>/scripts/main_scripts/images/avatar-female.png"/>Important data needs
                                your analysis...</a>
                            </li>
                            <li><a href="#">
                                <img width="34" height="34"
                                     src="<%=path%>/scripts/main_scripts/images/avatar-male2.png"/>Buy Se7en today, it's
                                a great theme...</a>
                            </li>
                        </ul>
                    </li>

                    <li class="dropdown user hidden-xs"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <img width="34" height="34" src="<%=path%>/scripts/main_scripts/images/avatar-male.jpg"/>${sessionScope.user_name}<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <c:if test="${sessionScope.login_type eq 'admin'}">
                                <li><a href="<%=path%>/page/user_group.jsp">
                                    <i class="icon-user"></i>用户组</a>
                                </li>
                            </c:if>
                            <li><a href="javascript:get_user();">
                                <i class="icon-gear"></i>账户设置</a>
                            </li>
                            <li><a href="javascript:window.location.href='/user/logout'">
                                <i class="icon-signout"></i>退出</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <button class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar"></span><span
                    class="icon-bar"></span></button>
            <a class="logo" href="index-2.html">se7en</a>
            <form class="navbar-form form-inline col-lg-2 hidden-xs">
                <input class="form-control" placeholder="Search" type="text">
            </form>
        </div>
        <div class="container-fluid main-nav clearfix">
            <div class="nav-collapse">
                <ul class="nav">
                    <li>
                        <a href="index-2.html"><span aria-hidden="true" class="se7en-home"></span>首页</a>
                    </li>
                    <li><a data-toggle="dropdown" href="#">
                        <span aria-hidden="true" class="se7en-feed"></span>日程管理<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="<%=path%>/page/schedule.jsp">我的计划</a>
                            </li>
                            <li>
                                <a href="<%=path%>/page/work_sign.jsp">签到</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown"><a data-toggle="dropdown" href="#">
                        <span aria-hidden="true" class="se7en-star"></span>xxx<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="buttons.html">Buttons</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown"><a data-toggle="dropdown" href="#">
                        <span aria-hidden="true" class="se7en-forms"></span>xxx<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="form-components.html">
                                <span class="notifications label label-warning">New</span>
                                <p>
                                    Form Components
                                </p></a>

                            </li>
                        </ul>
                    </li>
                    <li class="dropdown"><a data-toggle="dropdown" href="#">
                        <span aria-hidden="true" class="se7en-tables"></span>xxx<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="tables.html">Basic tables</a>
                            </li>
                        </ul>
                    </li>
                    <li><a href="charts.html">
                        <span aria-hidden="true" class="se7en-charts"></span>xxx</a>
                    </li>
                    <li class="dropdown"><a data-toggle="dropdown" class="current" href="#">
                        <span aria-hidden="true" class="se7en-pages"></span>xxx<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="chat.html">
                                <span class="notifications label label-warning">New</span>
                                <p>
                                    Chat
                                </p></a>

                            </li>
                        </ul>
                    </li>
                    <li><a href="gallery.html">
                        <span aria-hidden="true" class="se7en-gallery"></span>xxx</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
    <!-- End Navigation -->

<div class="modal fade" id="user_info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">用户设置</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group text-center">
                        <label for="user_name" class="col-sm-2">用户名：</label>
                        <div class="col-sm-10">
                            <input id="user_name" readonly="readonly" type="text" style="width: 400px;" name="user_name" required
                                   class="form-control" placeholder="请输入用户名"/></label>
                        </div>
                    </div>
                    <c:if test="${sessionScope.login_type eq 'admin'}">
                        <div class="form-group">
                            <label for="login_type" class="col-sm-2">权限：</label>
                            <div class="col-sm-10">
                                <select  style="width: 400px;" class="form-control" id="login_type" name="login_type">
                                    <option value="admin">管理员</option>
                                    <option value="user">用户</option>
                                </select>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label for="password" class="col-sm-2">密码：</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="password" style="width:400px;" type="password"
                                   name="password" required value=""/></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2">电话号码：</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="phone" style="width:400px;" type="tel" name="phone" required
                                   value=""/></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-sm-2">地址：</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="address" style="width:400px;" type="text" name="address"
                                   required value=""/></label>
                        </div>
                    </div>
                    <div class="modal-footer" style="text-align: center">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal"
                                onclick="edit_user($('#user_name').val(),$('#password').val(),$('#phone').val(),$('#address').val(),$('#login_type').val());">
                            确认
                        </button>
                    </div>
                </form>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript">
    function get_user() {
        $.ajax({
            url:'/user/edit?sign=sel_one',
            type:'get',
            dataType:'json',
            success:function (result) {
                if(result.code=='1'){
                    $('#user_name').val(result.data.user_name);
                    $('#password').val(result.data.password);
                    $('#phone').val(result.data.phone);
                    $('#address').val(result.data.address)
                    $('#user_info').modal({
                        keyboard:true
                    });
                }else{
                    iziToast.error({
                        message:result.desc,
                        timeout:"1000"
                    });
                }
            }
        });
    }
    function edit_user(user_name, password, phone, address,login_type) {
        if (user_name == "" || password == "" || phone == "" || address == "") {
            iziToast.info({
                message: "不能为空",
                timeout: "1000"
            });
            return;
        }
        $.ajax({
            url: "/user/edit?sign=edit",
            dataType: "json",
            type: "post",
            data: {"user_name": user_name, "password": password, "phone": phone, "address": address, "sign": "new","login_type":login_type},
            success: function (result) {
                if (result.code == "1") {
                    iziToast.success({
                        message: "修改成功！",
                        timeout: "1000"
                    });
                    window.location.reload();
                } else {
                    iziToast.error({
                        message: result.desc,
                        timeout: "1000"
                    });
                }
            }
        });
    }
</script>
</html>