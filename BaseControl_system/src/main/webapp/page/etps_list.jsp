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
                    <h4 class="section-subtitle"><b>商户信息列表</b></h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                    <c:if test="${sessionScope.login_type eq '渠道商'}">
                                        <button class="btn btn-info btn-sm" style="margin-left: 10px;"
                                                onclick="window.location.href='/page/etps_edit.jsp'">商户录入
                                        </button>
                                    </c:if>
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
                                                            <th>联系人</th>
                                                            <th>手机号</th>
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
                                                                <td>${etps_info.contact_name}</td>
                                                                <td>${etps_info.contact_phone}</td>
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
                                                                        <c:if test="${sessionScope.login_type eq '渠道商'}">
                                                                            <button class="btn btn-transparent"
                                                                                    data-toggle="tooltip"
                                                                                    data-placement="top" and title="编辑"
                                                                                    onclick="window.location.href='/etps/getEtps_info?sign=update&iEtps_id=${etps_info.iEtps_id}'">
                                                                                <i class="fa fa-pencil"></i></button>
                                                                            <button class="btn btn-transparent"
                                                                                    data-toggle="tooltip"
                                                                                    data-placement="top" and title="重置密码"
                                                                                    onclick="reset_pwd(${etps_info.iEtps_id});">
                                                                                <i class="fa fa-key"></i></button>
                                                                        </c:if>
                                                                        <c:if test="${sessionScope.login_type eq '管理员'}">
                                                                            <button class="btn btn-transparent"
                                                                                    data-toggle="tooltip"
                                                                                    data-placement="top" and title="预览"
                                                                                    onclick="window.location.href='/etps/getEtps_info?iEtps_id=${etps_info.iEtps_id}'">
                                                                                <i class="fa fa-eye"></i></button>
                                                                        </c:if>
                                                                        <button class="btn btn-transparent"
                                                                                data-toggle="tooltip"
                                                                                data-placement="top" and title="删除"
                                                                                onclick="del('${etps_info.iEtps_id}');">
                                                                            <i class="fa fa-times"></i></button>
                                                                        <c:if test="${etps_info.check_status eq '审核成功'}">
                                                                            <button class="btn btn-transparent"
                                                                                    data-placement="top" and title="授权"
                                                                                    data-toggle="modal" data-target="#sm-modal" onclick="create_this();">
                                                                                <i class="fa fa-key"></i></button>
                                                                        </c:if>
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
<div class="modal fade" id="del" tabindex="-1" role="dialog" aria-labelledby="modal-error-label">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header state modal-danger">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-error-label"><i class="fa fa-warning"></i>Are you sure?</h4>
            </div>
            <div class="modal-body">
                你确定要移除该商户吗？
                <input id="text" type="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal"
                        onclick="window.location.href='/etps/delEtps_info?iEtps_id='+$('#text').val()">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--重置密码-->
<div class="modal fade" id="reset_pwd" tabindex="-1" role="dialog" aria-labelledby="modal-error-label">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header state modal-danger">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modal-error-label2"><i class="fa fa-warning"></i>Are you sure?</h4>
            </div>
            <div class="modal-body">
                你确定要重置该商户的登录密码吗？
                <input id="text2" type="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal"
                        onclick="sure_reset($('#text2').val())">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!--二维码-->
<div class="modal fade" id="sm-modal" tabindex="-1" role="dialog" aria-labelledby="modal-small-label">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header state modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="modal-small-label">下发商户授权</h4>
            </div>
            <div class="modal-body" id="qr" style="text-align: center;padding-left: 17%">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!--密码弹出框-->
<div class="modal fade" id="pwd" tabindex="-1" role="dialog" aria-labelledby="modal-small-label">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header state modal-primary">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="modal-small-label2">重置密码</h4>
            </div>
            <div class="modal-body" id="pwd_alert" style="text-align: center;padding-left: 17%;font-size: larger;font-weight: bold">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function del(iEtps_id) {
        $('#del').modal({
            keyboard: true
        });
        $('#text').val(iEtps_id);
    }
    function create_this() {
        document.getElementById("qr").innerHTML="";
        var qrcode = new QRCode(document.getElementById("qr"), {
            width : 200,//设置宽高
            height : 200
        });
        qrcode.makeCode("<%=AliPayConfig.ISV_AUTH%>");
    }
    function reset_pwd(iEtps_id) {
        $('#reset_pwd').modal({
            keyboard: true
        });
        $('#text2').val(iEtps_id);
    }
    function sure_reset(iEtps_id) {
        $.ajax({
            url:"/etps/reset_pwd",
            type:"post",
            data:{"iEtps_id":iEtps_id},
            dataType:"json",
            success:function (result) {
                if(result.code=="1"){
                    $('#pwd').modal({
                        keyboard: true
                    });
                    $('#pwd_alert').html("请注意保管："+result.desc);
                }else{
                    iziToast.error({
                        message:result.desc,
                        position:'topRight',
                        timeout:'3000'
                    });
                }
            }

        });
    }
</script>
</body>
</html>
