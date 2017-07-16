<%@ page import="util.DateUtil" %>
<%@ page import="common.Config" %>
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
            <div class="row">
                <div class="col-md-6 col-lg-4">
                    <div>
                        <div class="profile-photo">
                            <img alt="Jane Doe" src="<%=path%>/scripts/main_scripts/images/wolf.gif">
                        </div>
                        <div class="user-header-info">
                            <h2 class="user-name">${sessionScope.iAgent_name}</h2>
                            <h5 class="user-position">${sessionScope.login_type}</h5>
                        </div>
                    </div>
                    <div class="panel bg-scale-0 b-primary bt-sm mt-xl">
                        <div class="panel-content">
                            <h4 class=""><b>个人信息</b></h4>
                            <ul class="user-contact-info ph-sm">
                                <li><b><i class="color-primary mr-sm fa fa-envelope"></i></b><%=UserUtil.getEmail(request)%></li>
                                <li><b><i class="color-primary mr-sm fa fa-phone"></i></b><%=UserUtil.getPhone(request)%></li>
                                <li><b><i class="color-primary mr-sm fa fa-globe"></i></b><%=UserUtil.getAddress(request)%></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-8">
                    <div class="timeline animated fadeInUp">
                        <c:forEach items="${message_list}" var="message">
                            <div class="timeline-box" id="${message.id}">
                                <div class="timeline-icon bg-primary">
                                    <i class="fa fa-file"></i>
                                </div>
                                <div class="timeline-content">
                                    <h4 class="tl-title">站内信消息</h4>${message.message}
                                    <button class="btn btn-transparent"
                                            data-toggle="tooltip"
                                            data-placement="top" and title="删除"
                                            onclick="del_message(${message.id});">
                                        <i class="fa fa-times"></i></button>
                                </div>
                                <div class="timeline-footer">
                                    <span>${message.create_time}</span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <a href="#" class="scroll-to-top"><i class="fa fa-angle-double-up"></i></a>
    </div>
</div>
</body>
<script type="text/javascript">
    function del_message(id) {
        $.ajax({
            url:"/user/del_message",
            type:"post",
            dataType:"json",
            data:{"id":id},
            success:function (result) {
                if(result.code=='1000'){
                    iziToast.success({
                        message:'已清除！',
                        position:'topRight',
                        timeout:'3000'
                    });
                    $('#'+id).hide();
                }
            }
        });
    }
</script>
</html>
