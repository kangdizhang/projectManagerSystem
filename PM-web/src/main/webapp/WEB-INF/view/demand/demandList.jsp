<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/19 0019
  Time: 上午 9:04
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
    <title>需求列表</title>
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

<input type="hidden" name="projectId" id="projectId" value="${param.projectId}">

<form class="form-inline definewidth m20" action="" method="get">
    <div class="form-group">
        <%--需求状态：--%>
        <label for="demandState">需求状态：</label>
        <select name="demandState" id="demandState" class="form-control">
            <option value="">所有</option>
            <option value="0">开发中</option>
            <option value="1">已完成</option>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;

        <%--需求名称：--%>
        <input type="text" name="demandName" id="modleName"class="abc input-default" placeholder="这里输入需求名称">
        &nbsp;&nbsp;&nbsp;&nbsp;

        <button type="button" onclick="reloadTable()" class="btn btn-primary">查询</button>
        &nbsp;&nbsp;&nbsp;&nbsp;

        <button type="button" onclick="window.location.href='${bathPath}/demand/addDemand?projectId=${param.projectId}'" class="btn btn-primary">新增</button>
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
            url:'${basePath}/demand/list',
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
                {field: 'demandName', title: '模块名称',align:'center'},
                {field: 'demandDescribe', title: '模块描述',align:'center'},
                {field: 'putUserId', title: '模块状态',align:'center'},
                {field: 'putTime', title: '模块状态',align:'center'},
                {field: 'exceptEndTime', title: '模块状态',align:'center'},
                {field: 'actualEndTime', title: '模块状态',align:'center'},
                {field: 'completeUserId', title: '模块状态',align:'center'},
                {field: 'operate', title: '编辑', align: 'center', formatter: 'operateFormatter', clickToSelect: false}
            ]
        });
    });
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            projectId:${param.projectId},
            modleName: $("#modleName").val(),
            modleState:$("#modleState").val()
        };
        return temp;
    };



    function reloadTable() {
        $table.bootstrapTable('refresh');
    }

    function operateFormatter(value, row, index) {
        return [
            '<a href="${bathPath}/editModle?id='+row.id+
            '&modleName='+row.modleName +
            '&projectId=${param.projectId}' +
            '&modleState='+row.modleState +
            '&modleDescribe='+row.modleDescribe+'" data-toggle="tooltip" title="Edit">修改</a> ',
            '<a href="${bathPath}/modle/deleteModle?id='+row.id+
            '&projectId=${param.projectId}' +
            '"data-toggle="tooltip" title="Del">删除</a> '
        ].join('');
    }

</script>
