<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%
        String path=request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String realPath=request.getRealPath("/");
    %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> - 运维提醒</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=path%>/scripts/500/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="<%=path%>/scripts/500/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path%>/scripts/500/animate.css" rel="stylesheet">
    <link href="<%=path%>/scripts/500/style.css?v=4.1.0" rel="stylesheet">
    <!-- 全局js -->
    <script src="<%=path%>/scripts/500/js/jquery.min.js?v=2.1.4"></script>
    <script src="<%=path%>/scripts/500/js/bootstrap.min.js?v=3.3.6"></script>
</head>
<body class="gray-bg">

    <div class="middle-box text-center animated fadeInDown">
        <h1>Sorry</h1>
        <h3 class="font-bold" style="font-size: large">不好意思</h3>

        <div class="error-desc" style="font-size: large">
            服务器出了点小问题，请联系管理员  17712613272
        </div>
    </div>

</body>

</html>
