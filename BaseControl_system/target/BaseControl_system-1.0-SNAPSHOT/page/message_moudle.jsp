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
                    <h4 class="section-subtitle"><b>站内信发布</b></h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <form  class="form-horizontal form-stripe" action="/user/message_send"
                                                   method="post">
                                            <textarea class="form-control" style="height: 70%" rows="3" id="message" name="message" placeholder="Write a comment" maxlength="1000"></textarea>
                                            <span class="help-block"><i class="fa fa-info-circle mr-xs"></i>最多输入1000字 <span class="code">1000</span></span>
                                                <div class="col-sm-offset-2 col-sm-10" style="text-align: center">
                                                        <button type="submit" class="btn btn-primary" style="background-color: red">发送</button>
                                                    <button type="button" class="btn btn-primary"
                                                            onclick="window.history.go(-1)">取消
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--
                <div class="col-md-6">
                    <h4 class="section-subtitle"><b>通知对象选择</b></h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                    <form class="form-horizontal form-stripe">
                                        <div class="form-group">
                                            <label for="select2-example-basic" class="col-sm-2 control-label">渠道商选择</label>
                                            <div class="col-sm-10">
                                                <select name="country" id="select2-example-basic" class="form-control select2-hidden-accessible" style="width: 100%" tabindex="-1" aria-hidden="true">
                                                        <option value="AI" label="Anguilla">Anguilla</option>
                                                        <option value="UY" label="Uruguay">Uruguay</option>
                                                        <option value="VE" label="Venezuela">Venezuela</option>
                                                </select>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                -->
            </div>
        </div>
        <a href="#" class="scroll-to-top"><i class="fa fa-angle-double-up"></i></a>
    </div>
</div>
</body>
</html>
