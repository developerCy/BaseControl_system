<%@ page import="util.DateUtil" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
    <%
        String path = request.getContextPath();
    %>
    <title>
        校园卫生管理与监督平台
    </title>
    <!--表格-->
    <link rel="stylesheet" type="text/css" href="<%=path%>/scripts/chart/css/GridManager.css">
    <script type="text/javascript" src="<%=path%>/scripts/chart/js/GridManager.js"></script>

</head>
<body>
<jsp:include  flush="true" page="/main_head.jsp"/>
<div style="margin-bottom: 10px;margin-left: 10px;">
    <button  id="sign_1" value="0"  class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">
        开始工作签到</button>
    <button  id="sign_2" value="1" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal_2">
        结束工作签到</button>
</div>

<table grid-manager="cccc"></table>

<!-- 签到弹出框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">签到</h4>
            </div>
            <div class="modal-body">您确定要签到吗？现在时间：<%=DateUtil.getTimestamp(DateUtil.SIMPLE)%></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="submit_work_start()">签到</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 签到弹出框 -->
<div class="modal fade" id="myModal_2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel_2" data-dismiss="modal">签到</h4>
            </div>
            <div class="modal-body">您确定要签到吗？现在时间：<%=DateUtil.getTimestamp(DateUtil.SIMPLE)%></div>
            <div class="modal-body">
                <ul class="editInfos" style="text-align: left">
                    <li><label><font color="#ff0000">* </font>上午工作内容：
                        <input id="morning_work" style="width:400px;" type="text" name="morning_work" required value="" class="ipt"/></label>
                    </li>
                    <li><label><font color="#ff0000">* </font>下午工作内容：
                        <input id="afternoon_work" style="width:400px;" type="text" name="afternoon_work" required value="" class="ipt"/></label>
                    </li>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="submit_work_end()">签到</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript">
    //开始工作签到
    function submit_work_start() {
        $.ajax({
            url:"/user/work_sign",
            dataType:"json",
            type:"get",
            success:function (result) {
                if(result.code=="1"){
                    iziToast.success({
                        message:"签到成功!",
                        timeout:"1000"
                    });
                    window.location.reload();
                }else{
                    iziToast.error({
                        message:result.desc,
                        timeout:"1000"
                    });
                }

            }
        });
    }
    //结束工作签到
    function submit_work_end() {
        var morning_work=$('#morning_work').val();
        var afternoon_work=$('#afternoon_work').val();
        $.ajax({
            url:"/user/work_sign",
            dataType:"json",
            type:"post",
            data:{"morning_work":morning_work,"afternoon_work":afternoon_work,"work_end":"yes"},
            success:function (result) {
                if(result.code=="1"){
                    iziToast.success({
                        message:"结束工作签到成功!",
                        timeout:"1000"
                    });
                    $('#sign_1').val("0");
                    $('#sign_2').val("1");
                    window.location.reload();
                }else{
                    iziToast.error({
                        message:result.desc,
                        timeout:"1000"
                    });
                }

            }
        });

    }
    //初始化表格数据
    $(function () {
        //获取签到内容
        var table = document.querySelector('table[grid-manager]');
        table.GM({
            ajax_url: '/user/get_work_sign'
            ,ajax_type: 'get'
            ,textAlign:'center'
            ,supportCheckbox: false
            ,columnData: [{
                key: 'user_name',
                width: '10%',
                text: '用户'
            },{
                key: 'work_start',
                width: '20%',
                text: '开始工作时间'
            },{
                key: 'work_end',
                width: '20%',
                text: '结束工作时间'
            },{
                key: 'morning_work',
                width: '25%',
                text: '上午工作内容'
            },{
                key: 'afternoon_work',
                width: '25%',
                text: '下午工作内容'
            }
                /*
                {
                key: 'operation',
                remind: 'the operation',
                sorting: '',
                width: '100px',
                text: 'operation',
                template: function(operation, rowObject){  //operation:当前key所对应的单条数据；rowObject：单个一行完整数据
                    return '<a href=javascript:alert("这是一个按纽");>'+operation+'</a>';
                }
            }
            */
            ]
            ,pagingBefore:function(query){
                console.log('Event: 分页前', query);
            }
            ,pagingAfter: function(query){
                console.log('Event: 分页后', query);
            }
            ,sortingBefore:function(query){
                console.log('Event: 排序前', query);
            }
            ,sortingAfter: function(query){
                console.log('Event: 排序后', query);
            }
            ,ajax_success: function(data){
                console.log('Event: ajax_success', data);
            }
        });

        // 日期格式化,不是插件的代码,只用于处理时间格式化
        Date.prototype.format = function(fmt){
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
            if (/([Y,y]+)/.test(fmt)){
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o){
                if(new RegExp("(" + k + ")").test(fmt)){
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        }
    })
</script>
</html>