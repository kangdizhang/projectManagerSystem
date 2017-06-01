<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/12 0012
  Time: 上午 9:10
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
    <title>用户信息列表</title>
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

        <%--用户名：--%>
        <input type="text" name="userName" id="userName"class="abc input-default" placeholder="这里输入用户名">
        &nbsp;&nbsp;&nbsp;&nbsp;

        <label for="userType">用户类型：</label>
        <select name="userType" id="userType" class="form-control">
            <option value="">所有</option>
            <option value="管理员">管理员</option>
            <option value="BOSS">BOSS</option>
            <option value="项目经理">项目经理</option>
            <option value="开发人员">开发人员</option>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;

        <%--项目负责人：--%>
        <%--<input type="text" name="projectLeader" id="projectLeader"class="abc input-default" placeholder="这里输入项目负责人">&nbsp;&nbsp;&nbsp;&nbsp;--%>

        <button type="button" onclick="reloadTable()" class="btn btn-primary">查询</button>
        <button type="button" onclick="window.location.href='${bathPath}/user/addUser'" class="btn btn-primary">新增</button>
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
            url:'${basePath}/user/list',
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
                {field: 'userName', title: '用户名',align:'center'},
                {field: 'pwd', title: '密码',align:'center'},
                {field: 'userType', title: '用户类型',align:'center'},
                {field: 'createTime', title: '创建时间',align:'center'},
                {field: 'lastLoginTime', title: '最后登录时间',align:'center'},
                {field: 'operate', title: '编辑', align: 'center', formatter: 'operateFormatter', clickToSelect: false}
            ]
        });
    });

    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            userName: $("#userName").val(),
            userType: $("#userType").val()
        };
        return temp;
    };

    function reloadTable() {
        $table.bootstrapTable('refresh');
    }

    function operateFormatter(value, row, index) {
        return [
            '<a href="${bathPath}/user/editUser?id='+row.id+
            '" data-toggle="tooltip" title="Edit">修改</a> ',
            '<a href="${bathPath}/user/delUser?id='+row.id+
            '" onclick="return issure();" data-toggle="tooltip" title="Del">删除</a> '
        ].join('');
    }

    function issure(){
        if(confirm("您确认删除该用户吗？")){
            return true;
        }
        return false;
    }
</script>
