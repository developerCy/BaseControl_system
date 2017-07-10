<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="util.UserUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <div class="header-section hidden-xs" id="notice-headerbox">
            <div class="notice" id="alerts-notice">
                <i class="fa fa-bell-o" aria-hidden="true"></i>
                <span>4</span>
                <div class="dropdown-box basic">
                    <div class="drop-header">
                        <h3><i class="fa fa-bell-o" aria-hidden="true"></i> Notifications</h3>
                        <span class="number">4</span>
                    </div>
                    <div class="drop-content">
                        <div class="widget-list list-left-element list-sm">
                            <ul>
                                <li>
                                    <a href="#">
                                        <div class="left-element"><i class="fa fa-warning color-warning"></i></div>
                                        <div class="text">
                                            <span class="title">8 Bugs</span>
                                            <span class="subtitle">reported today</span>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="left-element"><i class="fa fa-flag color-danger"></i></div>
                                        <div class="text">
                                            <span class="title">Error</span>
                                            <span class="subtitle">sevidor C down</span>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="left-element"><i class="fa fa-cog color-dark"></i></div>
                                        <div class="text">
                                            <span class="title">New Configuration</span>
                                            <span class="subtitle"></span>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="left-element"><i class="fa fa-tasks color-success"></i></div>
                                        <div class="text">
                                            <span class="title">14 Task</span>
                                            <span class="subtitle">completed</span>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="left-element"><i class="fa fa-envelope color-primary"></i></div>
                                        <div class="text">
                                            <span class="title">21 Menssage</span>
                                            <span class="subtitle"> (see more)</span>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="drop-footer">
                        <a>See all notifications</a>
                    </div>
                </div>
            </div>
            <div class="header-separator"></div>
        </div>
        <div class="header-section" id="user-headerbox">
            <div class="user-header-wrap">
                <div class="user-photo">
                    <img src="<%=path%>/scripts/main_scripts/images/user-avatar.jpg" alt="Jane Doe" />
                </div>
                <div class="user-info">
                    <span class="user-name">${sessionScope.user_name}</span>
                    <span class="user-profile">${sessionScope.login_type}</span>
                </div>
                <i class="fa fa-plus icon-open" aria-hidden="true"></i>
                <i class="fa fa-minus icon-close" aria-hidden="true"></i>
            </div>
            <div class="user-options dropdown-box">
                <div class="drop-content basic">
                    <ul>
                        <li><a href="<%=path%>/user/edit?sign=sel_one&user_name=<%=UserUtil.getUser_name(request)%>"><i class="fa fa-cog" aria-hidden="true"></i> 设置</a></li>
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