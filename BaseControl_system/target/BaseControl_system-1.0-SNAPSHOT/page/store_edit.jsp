<%@ page import="util.DateUtil" %>
<%@ page import="common.Config" %>
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
            <div class="row animated fadeInLeft">
                <form  class="form-horizontal form-stripe" action="/store/store_info_edit?sign=new"
                      method="post">
                    <input type="hidden" value="${etps_info.iEtps_id}" name="iEtps_id">
                <div class="col-md-12">
                    <h4 class="section-subtitle"><b>门店信息</b> 录入</h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                        <h6 class="mb-xlg text-center"><b>请在此编辑</b></h6>
                                        <div class="form-group">
                                            <label for="store_name" class="col-sm-2 control-label">门店名称：<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${store_info.store_name}"
                                                       id="store_name" name="store_name" required placeholder="store_name"
                                                       type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="store_address" class="col-sm-2 control-label">门店地址：<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${store_info.store_address}"
                                                       id="store_address" name="store_address" required
                                                       placeholder="store_address" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="business_area" class="col-sm-2 control-label">门店经营内容：<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${store_info.business_area}"
                                                       id="business_area" name="business_area" required
                                                       placeholder="business_area" type="tel">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="shop_owner_name" class="col-sm-2 control-label">店长名称：<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${store_info.shop_owner_name}"
                                                       id="shop_owner_name" name="shop_owner_name" required
                                                       placeholder="shop_owner_name" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="alipay_shop_id" class="col-sm-2 control-label">口碑门店ID：</label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${store_info.alipay_shop_id}"
                                                       id="alipay_shop_id" name="alipay_shop_id" required
                                                       placeholder="口碑门店需要填此参数" type="text">
                                            </div>
                                        </div>
                                    <div class="col-sm-offset-2 col-sm-10" style="text-align: center">
                                        <c:if test="${sessionScope.login_type eq '商户'}">
                                            <button type="submit" class="btn btn-primary">确认</button>
                                        </c:if>
                                        <button type="button" class="btn btn-primary"
                                                onclick="window.history.go(-1)">取消
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
        <a href="#" class="scroll-to-top"><i class="fa fa-angle-double-up"></i></a>
    </div>
</div>
</body>
<link rel="stylesheet" href="<%=path%>/scripts/switch/honeySwitch.css">
<script src="<%=path%>/scripts/switch/honeySwitch.js"></script>
<script type="text/javascript">
</script>
</html>
