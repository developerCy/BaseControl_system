<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="util.UserUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <%
        String path=request.getContextPath();
    %>
    <title>Title</title>
</head>
<body>
<div class="page-header">
    <div class="leftside-header">
        <div class="logo">
            <a href="index.html" class="on-click">
                <img alt="logo" src="<%=path%>/scripts/main_scripts/images/header-logo.png" />
            </a>
        </div>
        <div id="menu-toggle" class="visible-xs toggle-left-sidebar" data-toggle-class="left-sidebar-open" data-target="html">
            <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
        </div>
    </div>
    <div class="rightside-header">
        <div class="header-middle"></div>
        <c:if test="${sessionScope.login_type eq '渠道商'}">
            <div class="header-section hidden-xs" id="notice-headerbox">
                <div class="notice" id="alerts-notice">
                    <i class="fa fa-bell-o" aria-hidden="true"></i>
                    <span>${sessionScope.message_num}</span>
                    <div class="dropdown-box basic">
                        <div class="drop-header">
                            <h3><i class="fa fa-bell-o" aria-hidden="true"></i> 站内信</h3>
                            <span class="number">${sessionScope.message_num}</span>
                        </div>
                        <div class="drop-content">
                            <div class="widget-list list-left-element list-sm">
                                <c:if test="${sessionScope.message_num != 0}">
                                    <ul id="message">
                                        <li>
                                            <a href="#">
                                                <div class="left-element"><i class="fa fa-envelope color-primary"></i></div>
                                                <div class="text" onclick="window.location.href='/user/my_message'">
                                                    <span class="title">${sessionScope.message_num} 条消息</span>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="header-separator"></div>
            </div>
        </c:if>
        <div class="header-section" id="user-headerbox">
            <div class="user-header-wrap">
                <div class="user-photo">
                    <img src="<%=path%>/scripts/main_scripts/images/wolf.gif" height="35px;"alt="Jane Doe" />
                </div>
                <div class="user-info">
                    <span class="user-name"><%=UserUtil.getUser_name(request)%></span>
                    <span class="user-profile"><%=UserUtil.getLogin_type(request)%></span>
                </div>
                <i class="fa fa-plus icon-open" aria-hidden="true"></i>
                <i class="fa fa-minus icon-close" aria-hidden="true"></i>
            </div>
            <div class="user-options dropdown-box">
                <div class="drop-content basic">
                    <ul>
                        <c:if test="${sessionScope.login_type !='商户'}">
                            <li><a href="<%=path%>/user/edit?sign=sel_one&iAgent_id=<%=UserUtil.getUser_id(request)%>"><i class="fa fa-cog" aria-hidden="true"></i> 设置</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
        <div class="header-separator"></div>
        <div class="header-section">
            <a href="/user/logout"><i class="fa fa-sign-out log-out" aria-hidden="true"></i></a>
        </div>
    </div>
</div>
</body>
</html>