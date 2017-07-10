<%@ page import="util.DateUtil" %>
<%@ page import="common.Config" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
    %>
    <title><%=Config.TITLE%></title>
</head>
<body>
<div class="wrap">
    <jsp:include page="../index_head.jsp"/>
    <div class="page-body">
        <jsp:include page="../index_side.jsp"/>
        <div class="content">
            <div class="row animated fadeInLeft">
                <div class="col-md-12">
                    <h4 class="section-subtitle"><b>渠道商信息</b></h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                    <button class="btn btn-info btn-sm" style="margin-left: 10px;" data-toggle="modal"
                                            data-target="#create_user">添加渠道商
                                    </button>
                                    <div class="col-sm-12">
                                        <h4 class="section-subtitle"><b>公告：</b></h4>
                                        <p class="section-text">渠道商返佣费率按 <span class="code">xxxx计算</span></p>
                                        <div class="panel">
                                            <div class="panel-content">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-hover table-bordered text-center">
                                                        <thead>
                                                        <tr>
                                                            <th>渠道商</th>
                                                            <th>类型</th>
                                                            <th>手机号</th>
                                                            <th>Email</th>
                                                            <th>状态</th>
                                                            <th>创建时间</th>
                                                            <th>修改时间</th>
                                                            <th>功能</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${userinfo_list}" var="user_info">
                                                            <tr>
                                                                <td>${user_info.user_name}</td>
                                                                <td>${user_info.login_type}</td>
                                                                <td>${user_info.phone}</td>
                                                                <td>${user_info.email}</td>
                                                                <td>${user_info.status}</td>
                                                                <td>${user_info.create_time}</td>
                                                                <td>${user_info.modify_time}</td>
                                                                <td>
                                                                    <div class="btn-group btn-group-xs">
                                                                        <button class="btn btn-transparent" data-toggle="tooltip" data-placement="top" and title="编辑" onclick="window.location.href='/user/edit?sign=sel_one&user_name=${user_info.user_name}'"><i class="fa fa-pencil"></i></button>
                                                                        <button class="btn btn-transparent" data-toggle="tooltip" data-placement="top" and title="删除" onclick="del('${user_info.user_name}');"><i class="fa fa-times"></i></button>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
                <h4 class="modal-title">创建渠道商</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/user/edit?sign=new" method="post">
                    <div class="form-group">
                        <label for="user_name" class="col-sm-2 control-label">权限<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <select class="form-control" id="login_type" name="login_type" required>
                                <option value="" selected>请选择</option>
                                <option value="管理员">管理员</option>
                                <option value="渠道商" selected>渠道商</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user_name" class="col-sm-2 control-label">状态<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <select class="form-control" id="status" name="status" required>
                                <option value="有效" selected>有效</option>
                                <option value="无效">无效</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user_name" class="col-sm-2 control-label">姓名<span
                                style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" required class="form-control" id="user_name" name="user_name"
                                   placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input type="password" required class="form-control" id="password" name="pass_word"
                                   placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">手机号<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input type="tel" required class="form-control" id="phone" name="phone" placeholder="Phone">
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
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-error-label"><i class="fa fa-warning"></i>Danger Modal</h4>
            </div>
            <div class="modal-body">
                你确定要移除该用户吗？
                <input id="text" type="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal"
                        onclick="window.location.href='/user/edit?sign=del&user_name='+$('#text').val()">确定
                </button>
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
</script>
</body>
</html>
