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
                    <h4 class="section-subtitle"><b>商户基本信息</b> 录入</h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                    <form class="form-horizontal form-stripe" action="/etps/etpsinfo_save" method="post">
                                        <h6 class="mb-xlg text-center"><b>请在此编辑</b></h6>
                                        <div class="form-group">
                                            <label for="etps_name" class="col-sm-2 control-label">商户名称<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.etps_name}" id="etps_name" name="etps_name" required  placeholder="etps_name" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="contact_name" class="col-sm-2 control-label">联系人姓名：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.contact_name}" id="contact_name" name="contact_name" required placeholder="contact_name" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="contact_phone" class="col-sm-2 control-label">联系人手机号：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.contact_phone}" id="contact_phone" name="contact_phone" required placeholder="contact_phone" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="contact_address" class="col-sm-2 control-label">联系人地址：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.contact_address}" id="contact_address" name="contact_address" required  placeholder="contact_address" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="etps_login_name" class="col-sm-2 control-label">商户登录名：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.etps_login_name}" id="etps_login_name" name="etps_login_name" required placeholder="etps_login_name" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="status" class="col-sm-2 control-label">状态<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <select class="form-control" id="status" name="status" required>
                                                        <option value="有效" selected>有效</option>
                                                        <option value="无效">无效</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="email3" class="col-sm-2 control-label">Email</label>
                                            <div class="col-sm-10">
                                                <input value="${etps_info.email}" class="form-control" id="email3" name="email" placeholder="Email" type="email">
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
                <div class="col-md-12">
                    <h4 class="section-subtitle"><b>商户入网信息</b> 录入</h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                    <form class="form-horizontal form-stripe" action="/etps/etpsinfo_save" method="post">
                                        <h6 class="mb-xlg text-center"><b>请在此编辑</b></h6>
                                        <div class="form-group">
                                            <label  class="col-sm-2 control-label">支付渠道：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <div>
                                                    <div class="cell-right" style="float: left"><span class="switch-off" id="alipay"></span>支付宝&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                                    <div class="cell-right" style="float: left"><span class="switch-off" id="weixin"></span>微信&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                                    <input class="input_style checkbox_bg checkbox_bg_check" hidden type="checkbox" name="channel" value="10">
                                                    <input class="input_style checkbox_bg checkbox_bg_check" hidden type="checkbox" name="channel" value="20">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="alipay_rate" class="col-sm-2 control-label">支付宝费率(0-1.0)：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.alipay_rate}" id="alipay_rate" name="alipay_rate" required placeholder="alipay_rate" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="alipay_account" class="col-sm-2 control-label">支付宝账户：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.alipay_account}" id="alipay_account" name="alipay_account" required placeholder="alipay_account" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="alipay_account_name" class="col-sm-2 control-label">支付宝账户名称：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.alipay_account_name}" id="alipay_account_name" name="alipay_account_name" required placeholder="alipay_account_name" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="alipay_pid" class="col-sm-2 control-label">支付宝PID：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.alipay_pid}" id="alipay_pid" name="alipay_pid" required placeholder="alipay_pid" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="alipay_appid" class="col-sm-2 control-label">支付宝APPID：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.alipay_appid}" id="alipay_appid" name="alipay_appid" required placeholder="alipay_appid" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="alipay_rate" class="col-sm-2 control-label">key：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.alipay_rate}" id="alipay_rate" name="alipay_rate" required placeholder="alipay_rate" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="alipay_rate" class="col-sm-2 control-label">shop_id：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.alipay_rate}" id="alipay_rate" name="alipay_rate" required placeholder="alipay_rate" type="text">
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
<link rel="stylesheet" href="<%=path%>/scripts/switch/honeySwitch.css">
<script src="<%=path%>/scripts/switch/honeySwitch.js"></script>
</html>
