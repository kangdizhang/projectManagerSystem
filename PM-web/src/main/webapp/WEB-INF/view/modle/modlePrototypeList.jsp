<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/12 0012
  Time: 下午 2:50
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
    <title>模块原型列表</title>
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

        <%--模块名称：--%>
        <input type="text" name="modlePrototypeName" id="modlePrototypeName"class="abc input-default" placeholder="这里输入模块名称">
        &nbsp;&nbsp;&nbsp;&nbsp;

        <button type="button" onclick="reloadTable()" class="btn btn-primary">查询</button>
        &nbsp;&nbsp;&nbsp;&nbsp;

        <button type="button" onclick="window.location.href='${bathPath}/modlePrototype/editModlePrototype'" class="btn btn-primary">新增</button>
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
            url:'${basePath}/modlePrototype/list',
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
                {field: 'modlePrototypeName', title: '模块名称',align:'center'},
                {field: 'modlePrototypeDescribe', title: '模块描述',align:'center'},
                {field: 'versionList', title: '版本列表',align:'center',formatter: 'versionList', clickToSelect: false},
                {field: 'operate', title: '编辑', align: 'center', formatter: 'operateFormatter', clickToSelect: false}
            ]
        });
    });
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            modlePrototypeName: $("#modlePrototypeName").val()
        };
        return temp;
    };

    function reloadTable() {
        $table.bootstrapTable('refresh');
    }

    function versionList(value, row, index) {
        return [
            '<a href="${bathPath}/version/versionList?modleId='+row.id+'" data-toggle="tooltip" title="view">查看</a> '
        ].join('');
    }

    function operateFormatter(value, row, index) {
        return [
            '<a href="${bathPath}/modlePrototype/editModlePrototype?id=' + row.id+
            '" data-toggle="tooltip" title="Edit">修改</a> ',
            '<a href="${bathPath}/modlePrototype/delModlePrototype?id=' + row.id +
            '" data-toggle="tooltip" title="Del">删除</a> '
        ].join('');
    }

</script>
