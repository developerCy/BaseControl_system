<%@ page import="util.DateUtil" %>
<%@ page import="common.Config" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div class="row animated fadeInRight">
                <div class="col-md-12">
                    <h4 class="section-subtitle"><b>商户信息列表</b></h4>
                    <div class="panel">
                        <div class="panel-content">
                            <div class="row">
                                <div class="col-sm-12">
                                    <c:if test="${sessionScope.login_type eq '渠道商'}">
                                        <button class="btn btn-info btn-sm" style="margin-left: 10px;" onclick="window.location.href='/page/etps_edit.jsp'">商户录入</button>
                                    </c:if>
                                    <div class="col-sm-12">
                                        <h4 class="section-subtitle"><b>公告：</b></h4>
                                        <p class="section-text">Add to the table the class <span class="code">.table-bordered</span></p>
                                        <div class="panel">
                                            <div class="panel-content">
                                                <div class="table-responsive">
                                                    <table class="table table-striped table-hover table-bordered text-center">
                                                        <thead>
                                                        <tr>
                                                            <th>渠道商</th>
                                                            <th>商户</th>
                                                            <th>联系人</th>
                                                            <th>手机号</th>
                                                            <th>状态</th>
                                                            <th>创建时间</th>
                                                            <th>功能</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${etps_info_list}" var="etps_info">
                                                            <tr>
                                                                <td>${etps_info.user_name}</td>
                                                                <td>${etps_info.etps_name}</td>
                                                                <td>${etps_info.contact_name}</td>
                                                                <td>${etps_info.contact_phone}</td>
                                                                <td>${etps_info.status}</td>
                                                                <td>${etps_info.create_time}</td>
                                                                <td>
                                                                    <div class="btn-group btn-group-xs">
                                                                        <button class="btn btn-transparent"><i class="fa fa-eye"></i>
                                                                        </button>
                                                                        <button class="btn btn-transparent"><i class="fa fa-pencil"></i>
                                                                        </button>
                                                                        <button class="btn btn-transparent"><i class="fa fa-times"></i>
                                                                        </button>
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
            <div class="row animated fadeInUp"></div>
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
                <h4 class="modal-title" id="modal-error-label"><i class="fa fa-warning"></i>Danger Modal</h4>
            </div>
            <div class="modal-body">
                你确定要移除该用户吗？
                <input id="text" type="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal"
                        onclick="window.location.href='/user/edit?sign=del&user_name='+$('#text').val()">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function del(user_name) {
        $('#del').modal({
            keyboard: true
        });
        $('#text').val(user_name);
    }
    //初始化表格数据
    $(function () {
        //获取签到内容
        var table = document.querySelector('table[grid-manager]');
        table.GM({
            ajax_url: '/user/edit?sign=sel'
            , ajax_type: 'get'
            , textAlign: "center"
            , supportCheckbox: false
            , supportRemind: true
            , supportExport: true
            , columnData: [
               {
                    key: 'user_name',
                    width: '20%',
                    text: '用户名'
                }, {
                    key: 'phone',
                    width: '20%',
                    text: '手机号'
                }, {
                    key: 'address',
                    width: '20%',
                    text: '地址'
                }, {
                    key: 'login_type',
                    width: '10%',
                    text: '权限'
                },
                {
                    key: 'status',
                    width: '10%',
                    text: '状态'
                },
                {
                    key: 'email',
                    width: '20%',
                    text: '邮箱'
                },
                {
                    key: 'create_time',
                    width: '20%',
                    text: '创建时间'
                },
                {
                    key: 'modify_time',
                    width: '20%',
                    text: '最后修改时间'
                },{
                    key: 'operation',
                    width: '10%',
                    text: 'operation',
                    template: function (user_name, rowObject) {  //operation:当前key所对应的单条数据；rowObject：单个一行完整数据
                        return '<a href="#" onclick="del(\'' + rowObject.user_name + '\');">移除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="window.location.href=\'/user/edit?sign=sel_one&user_name=' + rowObject.user_name + '\'">编辑</a>';
                    }

                }
            ]
            , pagingBefore: function (query) {
                console.log('Event: 分页前', query);
            }
            , pagingAfter: function (query) {
                console.log('Event: 分页后', query);
            }
            , sortingBefore: function (query) {
                console.log('Event: 排序前', query);
            }
            , sortingAfter: function (query) {
                console.log('Event: 排序后', query);
            }
            , ajax_success: function (data) {
                console.log('Event: ajax_success', data);
            }
        });

        // 日期格式化,不是插件的代码,只用于处理时间格式化
        Date.prototype.format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "D+": this.getDate(), //日
                "d+": this.getDate(), //日
                "H+": this.getHours(), //小时
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/([Y,y]+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        }
    })

</script>
</body>
</html>
