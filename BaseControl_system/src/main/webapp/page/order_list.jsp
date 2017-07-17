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
                    <h4 class="section-subtitle"><b>交易明细列表</b></h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
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
                                                        <h4 class="subtitle color-lighter-1">总计交易金额</h4>
                                                        <h1 class="title">${order_success.total_amount}</h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <div class="panel widgetbox wbox-2 bg-light color-darker-2">
                                        <a href="#">
                                            <div class="panel-content">
                                                <div class="row">
                                                    <div class="col-xs-4">
                                                        <span class="icon fa fa-dollar color-darker-2"></span>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <h4 class="subtitle color-lighter-1">总计交易笔数</h4>
                                                        <h1 class="title">${order_success.total_num}</h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <div class="panel widgetbox wbox-2 bg-light color-darker-2">
                                        <a href="#">
                                            <div class="panel-content">
                                                <div class="row">
                                                    <div class="col-xs-4">
                                                        <span class="icon fa fa-dollar color-darker-2"></span>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <h4 class="subtitle color-lighter-1">总计退款金额</h4>
                                                        <h1 class="title">${order_refund.total_amount}</h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-lg-3">
                                    <div class="panel widgetbox wbox-2 bg-light color-darker-2">
                                        <a href="#">
                                            <div class="panel-content">
                                                <div class="row">
                                                    <div class="col-xs-4">
                                                        <span class="icon fa fa-dollar color-darker-2"></span>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <h4 class="subtitle color-lighter-1">总计退款笔数</h4>
                                                        <h1 class="title">${order_refund.total_num}</h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <!--商户门店列表-->
                                <div class="col-sm-12">
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
                                                            <th>渠道商名称</th>
                                                            <th>商户名称</th>
                                                            <th>门店名称</th>
                                                            <th>商户订单号</th>
                                                            <th>交易金额</th>
                                                            <th>交易状态</th>
                                                            <th>订单时间</th>
                                                            <th>付款时间</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${order_info_list}" var="order_info">
                                                            <tr>
                                                                <td>${order_info.iAgent_name}</td>
                                                                <td>${order_info.etps_name}</td>
                                                                <td>${order_info.store_name}</td>
                                                                <td>${order_info.out_trade_no}</td>
                                                                <td>${order_info.total_amount}</td>
                                                                <c:if test="${order_info.pay_status eq '1'}">
                                                                    <td style="color: green">交易成功</td>
                                                                </c:if>
                                                                <c:if test="${order_info.pay_status eq '2'}">
                                                                    <td style="color: red">退款</td>
                                                                </c:if>
                                                                <td>${order_info.order_date}</td>
                                                                <td>${order_info.pay_date}</td>
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
