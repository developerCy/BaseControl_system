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
<div class="wrap">
    <jsp:include page="../index_head.jsp"/>
    <div class="page-body">
        <jsp:include page="../index_side.jsp"/>
        <div class="content">
            <button class="btn btn-info btn-sm" style="margin-left: 10px;" data-toggle="modal" data-target="#create_user">添加用户
            </button>
            <table grid-manager="cccc"></table>
            <div class="row animated fadeInUp"></div>
        </div>
        <a href="#" class="scroll-to-top"><i class="fa fa-angle-double-up"></i></a>
    </div>
</div>

<!-- 创建用户弹出框 -->
<div class="modal fade" id="create_user" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">创建用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/user/edit?sign=new" method="post">
                    <div class="form-group">
                        <label for="user_name" class="col-sm-2 control-label">权限</label>
                        <div class="col-sm-10">
                            <select  class="form-control" id="login_type" name="login_type">
                                <option value="admin">管理员</option>
                                <option value="user" selected>用户</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user_name" class="col-sm-2 control-label">姓名<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" required class="form-control" id="user_name" name="user_name" placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input type="password" required class="form-control" id="password" name="password" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="email"  class="form-control" id="email" name="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">手机号<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input type="tel"  required class="form-control" id="phone" name="phone" placeholder="Phone">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">地址</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="address" name="address" placeholder="Address">
                        </div>
                    </div>
                    <div class="modal-footer" style="text-align: center">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">确认</button>
                    </div>
                </form>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--删除确认框-->
<div class="modal fade" id="del" tabindex="-1" role="dialog" aria-labelledby="modal-error-label">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header state modal-danger">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-error-label"><i class="fa fa-warning"></i>Danger Modal</h4>
            </div>
            <div class="modal-body">
                你确定要移除该用户吗？
                <input id="text" type="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="window.location.href='/user/edit?sign=del&user_name='+$('#text').val()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function del(user_name) {
        $('#del').modal({
            keyboard: true
        });
        $('#text').val(user_name);
    }
    //初始化表格数据
    $(function () {
        //获取签到内容
        var table = document.querySelector('table[grid-manager]');
        table.GM({
            ajax_url: '/user/edit?sign=sel'
            , ajax_type: 'get'
            , textAlign: "center"
            , supportCheckbox: false
            , supportRemind: true
            , supportExport: true
            , columnData: [
                {
                    key: 'logo',
                    width: '10%',
                    text: '头像',
                }, {
                    key: 'user_name',
                    width: '20%',
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
                    width: '5%',
                    text: '权限'
                }, {
                    key: 'email',
                    width: '20%',
                    text: '邮箱'
                },{
                    key: 'operation',
                    width: '5%',
                    text: 'operation',
                    template: function (user_name, rowObject) {  //operation:当前key所对应的单条数据；rowObject：单个一行完整数据
                        return '<a href="#" onclick="del(\'' + rowObject.user_name + '\');">移除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="window.location.href=\'/user/edit?sign=sel_one&user_name='+rowObject.user_name +'\'">编辑</a>';
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

</script>
</body>
</html>
