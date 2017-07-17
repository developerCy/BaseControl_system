<%@ page import="util.DateUtil" %>
<%@ page import="common.Config" %>
<%@ page import="common.AliPayConfig" %>
<%@ page import="util.UserUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
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
            <div class="row animated fadeInRight">
                <div class="col-md-12">
                    <h4 class="section-subtitle"><b>门店信息列表</b></h4>
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
                                                        <h4 class="subtitle color-lighter-1">总计门店</h4>
                                                        <h1 class="title color-light">${count}</h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <!--商户门店列表-->
                                <div class="col-sm-12">
                                    <div class="col-sm-12">
                                        <c:if test="${sessionScope.login_type eq '商户'}">
                                            <button class="btn btn-info btn-sm" style="margin-left: 10px;"
                                                    onclick="window.location.href='/page/store_edit.jsp'">新增门店
                                            </button>
                                        </c:if>
                                        <h4 class="section-subtitle"><b>公告：</b></h4>
                                        <p class="section-text">Add to the table the class <span class="code">.table-bordered</span>
                                        </p>
                                        <div class="panel">
                                            <div class="panel-content">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-hover table-bordered text-center">
                                                        <thead>
                                                        <tr>
                                                            <th>商户ID</th>
                                                            <th>商户</th>
                                                            <th>门店ID</th>
                                                            <th>门店名称</th>
                                                            <th>地址</th>
                                                            <th>经营内容</th>
                                                            <th>店长名称</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${store_info_list}" var="store_info">
                                                            <tr>
                                                                <td>${store_info.iEtps_id}</td>
                                                                <td>${store_info.etps_name}</td>
                                                                <td>${store_info.store_id}</td>
                                                                <td>${store_info.store_name}</td>
                                                                <td>${store_info.store_address}</td>
                                                                <td>${store_info.business_area}</td>
                                                                <td>${store_info.shop_owner_name}</td>
                                                                <td>
                                                                    <div class="btn-group btn-group-xs">
                                                                            <button class="btn btn-transparent"
                                                                                    onclick="window.location.href='/store/getStore_info?store_id=${store_info.store_id}&sign=update'">
                                                                                <i class="fa fa-eye"></i></button>
                                                                        <button class="btn btn-transparent"
                                                                                data-toggle="tooltip"
                                                                                data-placement="top" and title="删除"
                                                                                onclick="del_store('${store_info.store_id}');">
                                                                            <i class="fa fa-times"></i></button>
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
<!--删除确认框-->
<div class="modal fade" id="del_store" tabindex="-1" role="dialog" aria-labelledby="modal-error-label">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header state modal-danger">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-error-label"><i class="fa fa-warning"></i>Danger Modal</h4>
            </div>
            <div class="modal-body">
                你确定要移除该门店吗？
                <input id="text" type="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal"
                        onclick="window.location.href='/store/del_store?store_id='+$('#text').val()">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function del_store(store_id) {
        $('#del_store').modal({
            keyboard:true
        });
        $('#text').val(store_id);
    }
</script>
</html>
