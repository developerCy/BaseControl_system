<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
        <%
        String path=request.getContextPath();
    %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
</head>

<body>
<div class="wrap">
    <jsp:include page="index_head.jsp"/>
    <div class="page-body">
        <jsp:include page="index_side.jsp"/>
        <div class="content">
            <div class="row animated fadeInUp">
                <div class="col-md-12">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="row animated bounce">
                                        <div class="col-sm-8 col-sm-offset-2">
                                            <div class="panel mt-xlg">
                                                <div class="panel-content">
                                                    <h1 class="error-number">oh No！</h1>
                                                    <h2 class="error-name">出错喽！</h2>
                                                    <p class="error-text">${error}</p>
                                                    <div class="row mt-lg">
                                                        <div class="col-sm-6  col-sm-offset-3">
                                                            <button class="btn btn-sm btn-darker-2 btn-block" onclick="history.back();">返回吧</button>
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
            <div class="row animated fadeInUp"></div>
        </div>
        <a href="#" class="scroll-to-top"><i class="fa fa-angle-double-up"></i></a>
    </div>
</div>
</body>

</html>
