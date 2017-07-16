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
                <form  class="form-horizontal form-stripe" action="/etps/etpsinfo_save"
                      method="post">
                    <input type="hidden" value="${update}" name="sign">
                    <input type="hidden" value="${etps_info.iEtps_id}" name="iEtps_id">
                <div class="col-md-12">
                    <h4 class="section-subtitle"><b>商户基本信息</b> 录入</h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                        <h6 class="mb-xlg text-center"><b>请在此编辑</b></h6>
                                        <div class="form-group">
                                            <label for="etps_name" class="col-sm-2 control-label">商户名称<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.etps_name}"
                                                       id="etps_name" name="etps_name" required placeholder="etps_name"
                                                       type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="contact_name" class="col-sm-2 control-label">联系人姓名：<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.contact_name}"
                                                       id="contact_name" name="contact_name" required
                                                       placeholder="contact_name" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="contact_phone" class="col-sm-2 control-label">联系人手机号：<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.contact_phone}"
                                                       id="contact_phone" name="contact_phone" required
                                                       placeholder="contact_phone" type="tel">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="contact_address" class="col-sm-2 control-label">联系人地址：<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.contact_address}"
                                                       id="contact_address" name="contact_address" required
                                                       placeholder="contact_address" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="etps_login_name" class="col-sm-2 control-label">商户登录名：<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input class="form-control" value="${etps_info.etps_login_name}"
                                                       id="etps_login_name" name="etps_login_name" required
                                                       placeholder="etps_login_name" type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="email3" class="col-sm-2 control-label">Email<span
                                                    style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <input value="${etps_info.contact_mail}" class="form-control" id="email3"
                                                       name="contact_mail" required placeholder="contact_mail" type="email">
                                            </div>
                                        </div>
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
                                        <h6 class="mb-xlg text-center"><b>请在此编辑</b></h6>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">支付渠道：<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <div>
                                                    <div class="cell-right" style="float: left"><span class="switch-off"
                                                                                                      id="alipay"></span>支付宝&nbsp;&nbsp;&nbsp;&nbsp;
                                                    </div>
                                                    <div class="cell-right" style="float: left"><span class="switch-off"
                                                                                                      id="weixin"></span>微信&nbsp;&nbsp;&nbsp;&nbsp;
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="alipay_area" style="display: none">
                                            <div class="form-group">
                                                <label for="alipay_rate"
                                                       class="col-sm-2 control-label">支付宝费率(0-1.0)：<span
                                                        style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <input class="form-control" value="${etps_info.alipay_rate}"
                                                           id="alipay_rate" name="alipay_rate" required
                                                           placeholder="alipay_rate" type="number" step="0.01" min='0.01' max='0.99'>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="alipay_account" class="col-sm-2 control-label">支付宝账户：<span
                                                        style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <input class="form-control" value="${etps_info.alipay_account}"
                                                           id="alipay_account" name="alipay_account" required
                                                           placeholder="alipay_account" type="text">
                                                </div>
                                            </div>
                                            <c:if test="${check eq 'check'}">
                                            <div class="form-group">
                                                <label for="check_status" class="col-sm-2 control-label">审核状态：<span
                                                        style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <select class="form-control" value="${etps_info.check_status}"
                                                            id="check_status" name="check_status" required>
                                                        <option value="待审核">待审核</option>
                                                        <option value="审核中">审核中</option>
                                                        <option value="审核成功">审核成功</option>
                                                        <option value="驳回">驳回</option>
                                                    </select>
                                                </div>
                                            </div>
                                            </c:if>
                                            <c:if test="${check eq 'check'}">
                                                <div class="form-group">
                                                    <label for="check_desc" class="col-sm-2 control-label">审核描述</label>
                                                    <div class="col-sm-10">
                                                        <textarea class="form-control" rows="3" id="check_desc" name="check_desc" placeholder="Write a comment" maxlength="100"></textarea>
                                                        <span class="help-block"><i class="fa fa-info-circle mr-xs"></i>最多输入100字 <span class="code">100</span></span>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="col-sm-offset-2 col-sm-10" style="text-align: center">
                                                <c:if test="${sessionScope.login_type eq '渠道商'}">
                                                <button type="submit" class="btn btn-primary">确认</button>
                                                </c:if>
                                                <c:if test="${check eq 'check'}">
                                                    <button type="button" onclick="check_this($('#check_status').val(),${etps_info.iEtps_id},$('#check_desc').val());" class="btn btn-primary">审核提交</button>
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
                </div>
                </form>
            </div>
        </div>
        <a href="#" class="scroll-to-top"><i class="fa fa-angle-double-up"></i></a>
    </div>
</div>
<div class="modal fade" id="info-modal" tabindex="-1" role="dialog" aria-labelledby="modal-info-label" style="display: none;">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header state modal-info">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="modal-info-label"><i class="fa fa-info"></i>Sorry</h4>
            </div>
            <div class="modal-body">
                非常抱歉,该渠道后续有精力了再开通！谢谢
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">Ok</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
<link rel="stylesheet" href="<%=path%>/scripts/switch/honeySwitch.css">
<script src="<%=path%>/scripts/switch/honeySwitch.js"></script>
<script type="text/javascript">
    function check_this(check_status,iEtps_id,check_desc) {
        window.location.href="/etps/check?check_status="+check_status+"&iEtps_id="+iEtps_id+"&check_desc="+check_desc;
    }
    $(function () {
        if($('#alipay_rate').val()!=""){
            honeySwitch.showOn("#alipay");
            $('#alipay_area').show();
        }
        switchEvent("#alipay", function () {
                $('#alipay_area').show();
            }, function () {
                $('#alipay_area').hide();
            }
        );
        switchEvent("#weixin", function () {
               $('#info-modal').modal({
                   keyboard: true
               });
            honeySwitch.showOff("#weixin");
            }, function () {
            }
        );
    });
</script>
</html>
