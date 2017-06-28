<%@ page import="util.DateUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
    %>

    <!--表格-->
    <link rel="stylesheet" type="text/css" href="<%=path%>/scripts/chart/css/GridManager.css">
    <script type="text/javascript" src="<%=path%>/scripts/chart/js/GridManager.js"></script>
</head>
<body>
<jsp:include page="../main_head.jsp"></jsp:include>
<button class="btn btn-info btn-sm" style="margin-left: 10px;" data-toggle="modal" data-target="#create_user">添加用户
</button>
<table grid-manager="cccc"></table>
<!-- 创建用户弹出框 -->
<div class="modal fade" id="create_user" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">创建用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group text-center">
                        <label for="user_name_2" class="col-sm-2">用户名：</label>
                        <div class="col-sm-10">
                            <input id="user_name_2" type="text" style="width: 400px;" name="user_name" required
                                   class="form-control" placeholder="请输入用户名"/></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="login_type_2" class="col-sm-2">权限：</label>
                        <div class="col-sm-10">
                            <select style="width: 400px;" class="form-control" id="login_type_2" name="login_type">
                                <option value="admin">管理员</option>
                                <option value="user">用户</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password_2" class="col-sm-2">密码：</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="password_2" style="width:400px;" type="password"
                                   name="password_2" required/></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone_2" class="col-sm-2">电话号码：</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="phone_2" style="width:400px;" type="tel" name="phone" required
                            /></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address_2" class="col-sm-2">地址：</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="address_2" style="width:400px;" type="text" name="address"
                                   required/></label>
                        </div>
                    </div>
                </form>
                <div class="modal-footer" style="text-align: center">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal"
                            onclick="create_user($('#user_name_2').val(),$('#password_2').val(),$('#phone_2').val(),$('#address_2').val(),$('#login_type_2').val());">
                        确认
                    </button>
                </div>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 签到弹出框 -->
<div class="modal fade" id="del_confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认</h4>
            </div>
            <div  class="modal-body">你确定要删除该用户吗</div>
            <input type="hidden" value="" id="text"/>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="del_confirm($('#text').val())">我想好了</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript">
    function create_user(user_name, password, phone, address,login_type) {
        if (user_name == "" || password == "") {
            iziToast.info({
                message: "不能为空",
                timeout: "1000"
            });
            return;
        }
        $.ajax({
            url: "/user/edit?sign=new",
            dataType: "json",
            type: "post",
            data: {"user_name": user_name, "password": password, "phone": phone, "address": address,"login_type":login_type,"sign": "new"},
            success: function (result) {
                if (result.code == "1") {
                    iziToast.success({
                        message: "创建成功！",
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
    //初始化表格数据
    $(function () {
        //获取签到内容
        var table = document.querySelector('table[grid-manager]');
        table.GM({
            ajax_url: '/user/edit?sign=sel'
            , ajax_type: 'get'
            , textAlign: 'center'
            , supportCheckbox: false
            , supportRemind: true
            , supportExport: true
            , columnData: [
                {
                    key: 'logo',
                    width: '20%',
                    text: '头像',
                }, {
                    key: 'user_name',
                    width: '10%',
                    text: '用户名'
                }, {
                    key: 'phone',
                    width: '20%',
                    text: '手机号'
                }, {
                    key: 'address',
                    width: '20%',
                    text: '地址'
                }, {
                    key: 'login_type',
                    width: '20%',
                    text: '权限'
                }, {
                    key: 'operation',
                    width: '10%',
                    text: 'operation',
                    template: function (user_name, rowObject) {  //operation:当前key所对应的单条数据；rowObject：单个一行完整数据
                        return '<a href="#" onclick="del(\'' + rowObject.user_name + '\');">移除</a>';
                    }

                }
            ]
            , pagingBefore: function (query) {
                console.log('Event: 分页前', query);
            }
            , pagingAfter: function (query) {
                console.log('Event: 分页后', query);
            }
            , sortingBefore: function (query) {
                console.log('Event: 排序前', query);
            }
            , sortingAfter: function (query) {
                console.log('Event: 排序后', query);
            }
            , ajax_success: function (data) {
                console.log('Event: ajax_success', data);
            }
        });

        // 日期格式化,不是插件的代码,只用于处理时间格式化
        Date.prototype.format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "D+": this.getDate(), //日
                "d+": this.getDate(), //日
                "H+": this.getHours(), //小时
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/([Y,y]+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        }
    })
    function del(user_name) {
        $('#del_confirm').modal({
            keyboard: true
        });
        $('#text').val(user_name);
    }
    function del_confirm(user_name) {
        $.ajax({
            url: "/user/edit?sign=del&user_name=" + user_name,
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.code == "1") {
                    iziToast.error({
                        message: "删除成功！"
                    });
                    window.location.reload();
                } else {
                    iziToast.error({
                        message: result.desc
                    });
                }
            }
        });
    }
</script>
</body>
</html>
