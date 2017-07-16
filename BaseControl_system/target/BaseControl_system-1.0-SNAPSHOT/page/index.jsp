<%@ page import="util.DateUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
    <%
        String path = request.getContextPath();
    %>
    <title>Base_control</title>

</head>

<body>
<div class="wrap">
    <jsp:include page="../index_head.jsp"/>
    <div class="page-body">
        <jsp:include page="../index_side.jsp"/>
        <div class="content">
            <div class="row animated fadeInUp"></div>
        </div>
        <a href="#" class="scroll-to-top"><i class="fa fa-angle-double-up"></i></a>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        iziToast.success({
            message:'欢迎登陆Base_Control！',
            timeout:'3000',
            color:'blue',
            position:'bottomRight'
        });
    });

</script>
</html>
