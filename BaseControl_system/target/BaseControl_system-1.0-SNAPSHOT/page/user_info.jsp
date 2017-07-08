<%@ page import="util.DateUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        String path = request.getContextPath();
    %>
</head>
<body>
<div class="wrap">
    <jsp:include page="../index_head.jsp"/>
    <div class="page-body">
        <jsp:include page="../index_side.jsp"/>
        <div class="content">
            <form  action="/user/edit?sign=edit" method="post">
                <div class="form-group">
                    <label for="user_name" class="col-sm-2 control-label">权限</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="login_type" name="login_type">
                            <option value="admin">管理员</option>
                            <option value="user" selected>用户</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="user_name" class="col-sm-2 control-label">姓名<span style="color: red">*</span></label>
                    <div class="col-sm-10">
                        <input type="text" required class="form-control" id="user_name" name="user_name"
                               placeholder="Name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">密码<span style="color: red">*</span></label>
                    <div class="col-sm-10">
                        <input type="password" required class="form-control" id="password" name="password"
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
            <div class="row animated fadeInUp"></div>
        </div>
        <a href="#" class="scroll-to-top"><i class="fa fa-angle-double-up"></i></a>
    </div>
</div>
</body>
</html>
