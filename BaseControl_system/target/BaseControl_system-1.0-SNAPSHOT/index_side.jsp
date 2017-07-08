
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        String path=request.getContextPath();
    %>
    <link rel="apple-touch-icon" sizes="120x120" href="<%=path%>/scripts/main_scripts/favicon/apple-icon-120x120.png">
    <link rel="icon" type="image/png" sizes="192x192" href="<%=path%>/scripts/main_scripts/favicon/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="<%=path%>/scripts/main_scripts/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="<%=path%>/scripts/main_scripts/favicon/favicon-16x16.png">
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="<%=path%>/scripts/main_scripts/vendor/animate.css/animate.css">
    <link rel="stylesheet" href="<%=path%>/scripts/main_scripts/vendor/toastr/toastr.min.css">
    <link rel="stylesheet" href="<%=path%>/scripts/main_scripts/vendor/magnific-popup/magnific-popup.css">
    <link rel="stylesheet" href="<%=path%>/scripts/main_scripts/stylesheets/css/style.css">

</head>
<body>
<div class="left-sidebar">
    <div class="left-sidebar-header">
        <div class="left-sidebar-title">主菜单</div>
        <div class="left-sidebar-toggle c-hamburger c-hamburger--htla hidden-xs" data-toggle-class="left-sidebar-collapsed" data-target="html">
            <span></span>
        </div>
    </div>
    <div id="left-nav" class="nano">
        <div class="nano-content" tabindex="0" style="right: -17px;">
            <nav>
                <ul class="nav" id="main-nav">
                    <li class="active-item"><a href="index.html"><i class="fa fa-home" aria-hidden="true"></i><span>主页</span></a></li>
                    <li class="has-child-item close-item">
                        <a><i class="fa fa-cubes" aria-hidden="true"></i><span>UI Elements</span></a>
                        <ul class="nav child-nav level-1">
                            <li><a href="ui-elements_panels.html">Panels</a></li>
                            <li><a href="ui-elements_accordions.html">Accordions</a></li>
                            <li><a href="ui-elements_tabs.html">Tabs</a></li>
                            <li><a href="ui-elements_buttons.html">Buttons</a></li>
                            <li><a href="ui-elements_typography.html">Typography</a></li>
                            <li><a href="ui-elements_alerts.html">Alerts</a></li>
                            <li><a href="ui-elements_modals.html">Modals</a></li>
                            <li class="has-child-item close-item">
                                <a>Notifications</a>
                                <ul class="nav child-nav level-2 ">
                                    <li><a href="ui-elements_notifications-pnotify.html">PNotify</a></li>
                                    <li><a href="ui-elements_notifications-toastr.html">Toastr</a></li>
                                </ul>
                            </li>
                            <li><a href="ui-elements_animations-appear.html">Animations</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
<script src="<%=path%>/scripts/main_scripts/vendor/nano-scroller/nano-scroller.js"></script>
<script src="<%=path%>/scripts/main_scripts/javascripts/template-script.min.js"></script>
<script src="<%=path%>/scripts/main_scripts/javascripts/template-init.min.js"></script>
<script src="<%=path%>/scripts/main_scripts/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>
<!--弹窗-->
<link rel="stylesheet" href="<%=path%>/scripts/message_css_js/css/iziToast.css">
<script src="<%=path%>/scripts/message_css_js/js/iziToast.js"></script>
<!--表单检查-->
<script type="text/javascript" src="<%=path%>/scripts/chart_validate/jquery.validate.js"></script>
</html>
