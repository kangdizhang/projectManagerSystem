<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/10 0010
  Time: 下午 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>项目列表</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/style.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/bootstrap-table.min.css" />
    <script type="text/javascript" src="${basePath}/Js/jquery.js"></script>
    <script type="text/javascript" src="${basePath}/Js/bootstrap.js"></script>
    <script type="text/javascript" src="${basePath}/Js/ckform.js"></script>
    <script type="text/javascript" src="${basePath}/Js/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="${basePath}/Js/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" src="${basePath}/Js/common.js"></script>
    <script type="text/javascript" src="${basePath}/Js/common.js"></script>


    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
<form class="form-inline definewidth m20" action="" method="get">
    <div class="form-group">

        <%--项目状态：--%>
        <label for="projectStatus">项目状态：</label>
        <select name="projectStatus" id="projectStatus" class="form-control">
            <option value="">所有</option>
            <option value="0">开发中</option>
            <option value="1">升级中</option>
            <option value="2">已完成</option>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;

        <%--项目名称：--%>
        <input type="text" name="projectName" id="projectName"class="abc input-default" placeholder="这里输入项目名称">
            &nbsp;&nbsp;&nbsp;&nbsp;

        <%--项目负责人：--%>
        <input type="text" name="projectLeader" id="projectLeader"class="abc input-default" placeholder="这里输入项目负责人">
            &nbsp;&nbsp;&nbsp;&nbsp;

        <button type="button" onclick="reloadTable()" class="btn btn-primary">查询</button>
            &nbsp;&nbsp;&nbsp;&nbsp;

        <button type="button" onclick="window.location.href='${bathPath}/project/addProject'" class="btn btn-primary">新增</button>
    </div>
</form>
<table id="table"></table>
</body>
</html>
<script>
    var oTableInit = new Object();
    var $table = $('#table');
    oTableInit.Init = $(function() {
        // bootstrap table初始化
        $table.bootstrapTable({
            //method:"get",
            //url: '${basePath}/optManage/findOptRecordList',
            url:'${basePath}/project/list',
            queryParams:oTableInit.queryParams,
            striped: true,
            search: false,
            cache:false,
            dataType:'json',
            showRefresh: true,
            showColumns: true,
            showToggle:true,
            //showPaginationSwitch:false,
            minimumCountColumns: 2,
            clickToSelect: true,
            //detailView: true,
            // detailFormatter:'detailFormatter',
            //detailFormatter: dataformatobj,
            pagination: true,
            pageNumber:1,
            pageSize:10,
           // paginationLoop: false,
            pageList: [10, 25, 50, 100],
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            idField: 'id',
            /*    sortName: 'channelName',
             sortOrder: 'desc',*/
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'id', title:'编号',align:'center'},
                {field: 'projectName', title: '项目名称',align:'center'},
                {field: 'projectLeader', title: '项目负责人',align:'center'},
                {field: 'phone', title: '负责人电话',align:'center'},
                {field: 'qq', title: '负责人QQ',align:'center'},
                {field: 'email', title: '负责人邮箱',align:'center'},
                {field: 'operateTime', title: '最后操作时间',align:'center'},
                {field: 'projectStatus', title: '项目状态',align:'center'},
                {field: 'operate', title: '编辑', align: 'center', formatter: 'operateFormatter', clickToSelect: false},
                {field: 'projectInfo', title: '基础信息', align: 'center', formatter: 'projectInfoFormatter', events: 'projectInfoEvents', clickToSelect: false},
                {field: 'modleList', title: '模块列表', align: 'center', formatter: 'modleListFormatter', events: 'modleListEvents', clickToSelect: false},
                {field: 'demandList', title: '需求列表', align: 'center', formatter: 'demandListFormatter', events: 'demandListEvents', clickToSelect: false}
            ]
        });
    });
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            projectStatus: $("#projectStatus").val(),
            projectName: $("#projectName").val(),
            projectLeader: $("#projectLeader").val()
        };
        return temp;
    };



    function reloadTable() {
        $table.bootstrapTable('refresh');
    }

    function operateFormatter(value, row, index) {
        var projectStatus = '';
        if(row.projectStatus == '开发中'){
            projectStatus = '0';
        }else if(row.projectStatus == '升级中'){
            projectStatus = '1';
        }else{
            projectStatus = '2';
        }
        return [
            '<a href="${bathPath}/project/addProject?id='+row.id+
//            '&projectName='+row.projectName +
//            '&projectLeader='+row.projectLeader+
//            '&phone='+row.phone+
//            '&qq='+row.qq+
//            '&email='+row.email+
//            '&projectStatus='+projectStatus+'' +
            '" data-toggle="tooltip" title="Edit">修改</a> ',
            '<a href="${bathPath}/project/deleteProject?id='+row.id+'"data-toggle="tooltip" title="Del">删除</a> '
        ].join('');
    }

    function projectInfoFormatter(value, row, index) {
        return [
            '<a href="${bathPath}/projectInfo/viewProjectInfo?id='+row.id+'&projectName='+row.projectName+'" data-toggle="tooltip" title="View">查看</a>'
        ].join('');
    }

    function modleListFormatter(value, row, index) {
        return [
            '<a href="${bathPath}/modleList?projectId='+row.id+'" data-toggle="tooltip" title="View">查看</a>　'
        ].join('');
    }

    function demandListFormatter(value, row, index) {
        return [
            '<a href="${bathPath}/demandList?projectId='+row.id+'" data-toggle="tooltip" title="View">查看</a>　'
        ].join('');
    }

</script>

