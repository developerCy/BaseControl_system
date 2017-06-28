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
</head>
<body>
<jsp:include page="/main_head.jsp"/>
<!--创建日程按钮-->
<button class="btn btn-info btn-sm" style="margin-left: 10px;" onclick="open_window()">创建日程</button>
<div id='calendar'></div>
<!--创建日程-->
<div class="modal fade" id="create_schedule" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">创建日程</h4>
            </div>
            <div style="padding: 100px 100px 10px;">
                <form class="bs-example bs-example-form" role="form">
                    <div class="input-group">
                        <span class="input-group-addon">日程：</span>
                        <input type="text" name="title" id="title" class="form-control" placeholder="请输入您的日程..">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">日期：</span>
                        <input id="sdate" name="startTime"
                               class="Wdate form-control" placeholder="请输入日程日期.."
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </div>
                    <input type="hidden" value="" id="id"/>
                    <br>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="submit" type="button" class="btn btn-primary" data-dismiss="modal"
                        onclick="create($('#title').val(),$('#sdate').val(),$('#id').val())">我想好了
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<!-- 日程管理样式及JS -->
<link rel="stylesheet" href="<%=path%>/scripts/fullcalendar-3.3.1/fullcalendar.min.css">
<script src='<%=path%>/scripts/fullcalendar-3.3.1/lib/moment.min.js'></script>
<script src='<%=path%>/scripts/fullcalendar-3.3.1/fullcalendar.js'></script>
<script src='<%=path%>/scripts/fullcalendar-3.3.1/locale/zh-cn.js'></script>
<script>
    //弹出日程创建
    function open_window() {
        $('#title').val("");
        $('#sdate').val("");
        $('#id').val("");
        $('#submit').next().remove();
        $('#create_schedule').modal({
            keyboard: true
        });
    }
    //创建日程
    function create(title, date, id) {
        $.ajax({
            url: "/Schedule/create_schedule",
            dataType: "json",
            type: "post",
            data: {"title": title, "startTime": date, "id": id},
            success: function (result) {
                if (result.code == '1') {
                    window.location.reload();
                } else {
                    alert(result.desc);
                }
            }
        });
    }
    function del() {
        var id = $('#id').val();
        $.ajax({
            url: "/Schedule/del_schedule?id=" + id,
            dataType: "json",
            type: "get",
            success: function (result) {
                if (result.code == '1') {
                    window.location.reload();
                } else {
                    alert(result.desc);
                }
            }
        });
    }
    $(function () {
        var y=0;
        //国际化默认值为'en'，代表使用英文
        var initialLangCode = 'zh';
        //初始化FullCalendar
        $('#calendar').fullCalendar({
            events: function (start, end, timezone, callback) {
                var event_list = [];
                $.ajax({
                    url: '/Schedule/get_scheduleList',
                    dataType: 'json',
                    type: "get",
                    success: function (doc) {
                        for (var i = 0; i < (doc.data).length; i++) {
                            event_list.push({
                                id: (doc.data)[i].id,
                                title: (doc.data)[i].title,
                                start: (doc.data)[i].startTime // will be parsed
                            });
                        }
                        callback(event_list);
                    }
                });
            },
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            weekends: true,
            weekMode: 'liquid',
            defaultView: 'month',
            allDayText: '全天',
            businessHours: true,
            defaultEventMinutes: 120,
            eventLimit: true,
            dayClick: function (date) {
                console.log('dayClick触发的时间为：', date.format());
            },
            //设置是否可被单击或者拖动选择
            selectable: false,
            //点击或者拖动选择时，是否显示时间范围的提示信息，该属性只在agenda视图里可用
            selectHelper: false,
            //点击或者拖动选中之后，点击日历外的空白区域是否取消选中状态 true为取消 false为不取消，只有重新选择时才会取消
            unselectAuto: false,
            select: function (start, end) {
                console.log('select触发的开始时间为：', start.format());
                console.log('select触发的结束时间为：', end.format());
            },
            eventClick: function (event) {
                $('#title').val(event.title);
                $('#sdate').val(event.start.format('YYYY-MM-DD HH:mm:ss'));
                $('#id').val(event.id);
                if(y==0){
                    $('.modal-footer').append('<button  type="button" class="btn btn-default" onclick="del()" data-dismiss="modal">删除</button>');
                    y=1;
                }
                $('#create_schedule').modal({
                    keyboard: true
                });
                console.log('eventClick中选中Event的id属性值为：', event.id);
                console.log('eventClick中选中Event的title属性值为：', event.title);
                console.log('eventClick中选中Event的start属性值为：', event.start.format('YYYY-MM-DD HH:mm'));
            },
            eventMouseover: function (event) {
                console.log('鼠标经过 ...');
                console.log('eventMouseover被执行，选中Event的title属性值为：', event.title);
            },
            eventMouseout: function (event) {
                console.log('eventMouseout被执行，选中Event的title属性值为：', event.title);
                console.log('鼠标离开 ...');
            },
            //Event是否可被拖动或者拖拽
            editable: false,
            //Event被拖动时的不透明度
            dragOpacity: 0.5,
            eventDrop: function (event, dayDelta, revertFunc) {
                console.log('eventDrop --- start ---');
                console.log('eventDrop被执行，Event的title属性值为：', event.title);
                if (dayDelta._days != 0) {
                    console.log('eventDrop被执行，Event的start和end时间改变了：', dayDelta._days + '天！');
                } else if (dayDelta._milliseconds != 0) {
                    console.log('eventDrop被执行，Event的start和end时间改变了：', dayDelta._milliseconds / 1000 + '秒！');
                } else {
                    console.log('eventDrop被执行，Event的start和end时间没有改变！');
                }
                console.log('eventDrop --- end ---');
            },
            eventResize: function (event, dayDelta, revertFunc) {
                console.log(' --- start --- eventResize');
                console.log('eventResize被执行，Event的title属性值为：', event.title);
                if (dayDelta._days != 0) {
                    console.log('eventResize被执行，Event的start和end时间改变了：', dayDelta._days + '天！');
                } else if (dayDelta._milliseconds != 0) {
                    console.log('eventResize被执行，Event的start和end时间改变了：', dayDelta._milliseconds / 1000 + '秒！');
                } else {
                    console.log('eventResize被执行，Event的start和end时间没有改变！');
                }
                console.log('--- end --- eventResize');
            },
        });
    });
</script>
</html>