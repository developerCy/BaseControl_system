<%@ page import="util.DateUtil" %>
<%@ page import="common.Config" %>
<%@ page import="common.AliPayConfig" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <%
        String path = request.getContextPath();
    %>
    <title><%=Config.TITLE%>
    </title>

</head>
<body>
<div class="wrap">
    <jsp:include page="../index_head.jsp"/>
    <div class="page-body">
        <jsp:include page="../index_side.jsp"/>
        <div class="content">
            <div class="row animated fadeInRight">
                <div class="col-md-12">
                    <h4 class="section-subtitle"><b>入网审核列表</b></h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                    <!--统计条-->
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <div class="panel widgetbox wbox-2 bg-darker-1">
                                        <a href="#">
                                            <div class="panel-content">
                                                <div class="row">
                                                    <div class="col-xs-4">
                                                        <span class="icon fa fa-globe color-lighter-1"></span>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <h4 class="subtitle color-lighter-1">总计入网</h4>
                                                        <h1 class="title color-light">${count_all}</h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <!--统计条-->
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <div class="panel widgetbox wbox-2 bg-darker-2 color-light">
                                        <a href="#">
                                            <div class="panel-content">
                                                <div class="row">
                                                    <div class="col-xs-4">
                                                        <span class="icon fa fa-envelope color-lighter-1"></span>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <h4 class="subtitle color-lighter-1">待审核</h4>
                                                        <h1 class="title color-light">${wait_check}</h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <!--统计条-->
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <div class="panel widgetbox wbox-2 bg-lighter-2 color-light">
                                        <a href="#">
                                            <div class="panel-content">
                                                <div class="row">
                                                    <div class="col-xs-4">
                                                        <span class="icon fa fa-user color-darker-2"></span>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <h4 class="subtitle color-darker-2">已开通</h4>
                                                        <h1 class="title color-w">${checked}</h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <!--统计条-->
                                    <div class="col-sm-6 col-md-4 col-lg-3">
                                        <div class="panel widgetbox wbox-2 bg-light color-darker-2">
                                            <a href="#">
                                                <div class="panel-content">
                                                    <div class="row">
                                                        <div class="col-xs-4">
                                                            <span class="icon fa fa-dollar color-darker-2"></span>
                                                        </div>
                                                        <div class="col-xs-8">
                                                            <h4 class="subtitle">Total</h4>
                                                            <h1 class="title"> 14.550,00</h1>
                                                        </div>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                    </div>
                                <!--商户审核列表-->
                                <div class="col-sm-12">
                                    <button class="btn btn-info btn-sm" style="margin-left: 10px;"
                                            onclick="window.location.href='/etps/etps_out'">商户导出
                                    </button>
                                    <div class="col-sm-12">
                                        <h4 class="section-subtitle"><b>公告：</b></h4>
                                        <p class="section-text">Add to the table the class <span class="code">.table-bordered</span>
                                        </p>
                                        <div class="panel">
                                            <div class="panel-content">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-hover table-bordered text-center">
                                                        <thead>
                                                        <tr>
                                                            <th>渠道商</th>
                                                            <th>商户ID</th>
                                                            <th>商户</th>
                                                            <th>状态</th>
                                                            <th>审核状态</th>
                                                            <th>创建时间</th>
                                                            <th>功能</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${etps_info_list}" var="etps_info">
                                                            <tr>
                                                                <td>${etps_info.iAgent_name}</td>
                                                                <td>${etps_info.iEtps_id}</td>
                                                                <td>${etps_info.etps_name}</td>
                                                                <c:if test="${etps_info.status eq '新入网'}">
                                                                    <td style="color: red">新入网</td>
                                                                </c:if>
                                                                <c:if test="${etps_info.status eq '已开通'}">
                                                                    <td style="color: green">已开通</td>
                                                                </c:if>
                                                                <c:if test="${etps_info.check_status != '审核成功'}">
                                                                    <td style="color: red">${etps_info.check_status}</td>
                                                                </c:if>
                                                                <c:if test="${etps_info.check_status eq '审核成功'}">
                                                                    <td style="color: green">${etps_info.check_status}</td>
                                                                </c:if>
                                                                <td>${etps_info.create_time}</td>
                                                                <td>
                                                                    <div class="btn-group btn-group-xs">
                                                                            <button class="btn btn-transparent"
                                                                                    data-toggle="tooltip"
                                                                                    data-placement="top" and title="审核"
                                                                                    onclick="window.location.href='/etps/getEtps_info?sign=check&iEtps_id=${etps_info.iEtps_id}'">
                                                                                <i class="fa fa-eye"></i></button>
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
        </div>
        <a href="#" class="scroll-to-top"><i class="fa fa-angle-double-up"></i></a>
    </div>
</div>
</body>
</html>
