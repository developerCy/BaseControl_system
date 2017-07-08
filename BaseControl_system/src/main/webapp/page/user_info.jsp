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
            <div class="row animated fadeInUp">
                <div class="col-md-12">
                    <h4 class="section-subtitle"><b>用户信息</b> 编辑</h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                    <form class="form-horizontal form-stripe" action="/user/edit?sign=edit" method="post">
                                        <h6 class="mb-xlg text-center"><b>请在此编辑</b></h6>
                                        <div class="form-group">
                                            <label for="user_name" class="col-sm-2 control-label">用户名<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${user_info.user_name}" id="user_name" name="user_name" readonly placeholder="Name" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="login_type" class="col-sm-2 control-label">权限<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <select class="form-control" id="login_type" name="login_type">
                                                    <c:if test="${user_info.login_type eq 'admin'}">
                                                        <option value="admin" selected>管理员</option>
                                                        <option value="user" selected>用户</option>
                                                    </c:if>
                                                    <c:if test="${user_info.login_type eq 'user'}">
                                                        <option value="user" selected>用户</option>
                                                        <option value="admin">管理员</option>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="pass_word" class="col-sm-2 control-label">密码<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input value="" class="form-control" id="pass_word" required name="pass_word" placeholder="Pass_word" type="password">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="email3" class="col-sm-2 control-label">Email</label>
                                            <div class="col-sm-10">
                                                <input value="${user_info.email}" class="form-control" id="email3" name="email" placeholder="Email" type="email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="phone" class="col-sm-2 control-label">手机号<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" id="phone" name="phone" required placeholder="Phone" type="tel" value="${user_info.phone}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="address" class="col-sm-2 control-label">地址</label>
                                            <div class="col-sm-10">
                                                <input class="form-control" id="address"  placeholder="Address" name="address" type="tel" value="${user_info.address}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-10" style="text-align: center">
                                                <button type="submit" class="btn btn-primary">确认</button>
                                                <button type="button" class="btn btn-primary" onclick="window.history.go(-1)">取消</button>
                                            </div>
                                        </div>
                                    </form>
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
</body>
</html>
