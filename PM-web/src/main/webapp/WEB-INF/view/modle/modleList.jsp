<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/15 0015
  Time: 下午 2:43
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
    <title>项目模块列表</title>
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
        <!-- 选择项目 -->
        <select id="projectName" name="projectName">
            <option  name="projectId" value="">请选择项目</option>
            <c:forEach items="${list}" var="Project">
                <c:choose>
                    <c:when test="${projectId == Project.id}">
                        <option name="projectId" selected="selected" value="${Project.id}">${Project.projectName}</option>
                    </c:when>
                    <c:otherwise>
                        <option name="projectId" value="${Project.id}">${Project.projectName}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <%--模块名称：--%>
        <input type="text" name="projecModleName" id="projecModleName"class="abc input-default" placeholder="这里输入模块名称">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" onclick="reloadTable()" class="btn btn-primary">查询</button>
        &nbsp;&nbsp;&nbsp;&nbsp;

        <button type="button" onclick="addModle()" class="btn btn-primary">新增</button>
        <%--<button type="button" onclick="window.location.href='${bathPath}/modle/addModle?projectId=${projectId}'" class="btn btn-primary">新增</button>--%>
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
            url:'${basePath}/modle/list',
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
                {field: 'modleName', title: '模块名称',align:'center'},
                {field: 'modleDescribe', title: '模块描述',align:'center'},
                {field: 'version', title: '版本号',align:'center'},
                {field: 'versionDesc', title: '版本描述',align:'center'},
                {field: 'versionNum', title: '父版本号', align: 'center'},
                {field: 'versionList', title: '版本列表', align: 'center', formatter: 'versionListFormatter', events: 'versionListEvents', clickToSelect: false},
                {field: 'operate', title: '编辑', align: 'center', formatter: 'operateFormatter', clickToSelect: false}
            ]
        });
    });
    oTableInit.queryParams = function (params){

        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            projectId: $("#projectName option:selected").val(),
            modleName:$("#projecModleName").val()
        };
//        var id = $("#projectName option:selected").val();
//        if(id != null && id != ''){
//        }
        return temp;
    };

    function reloadTable() {
        $table.bootstrapTable('refresh');
    }

    function operateFormatter(value, row, index) {
        return [
            '<a href="${bathPath}/modle/editModle?id='+row.id+
            '" data-toggle="tooltip" title="Edit">修改</a> ',
            '<a href="${bathPath}/modle/deleteModle?id='+row.id+ '&projectId='+row.projectId+
            '&projectName=' + row.projectName +
            '" onclick="return issure();" data-toggle="tooltip" title="Del">删除</a> '
        ].join('');
    }
    function versionListFormatter(value, row, index) {
        return [
            '<a href="${bathPath}/version/versionList?modleId='+row.modleId+'" data-toggle="tooltip" title="View">查看</a>　'
        ].join('');
    }


    function issure(){
        if(confirm("您确认删除该数据吗？")){
            return true;
        }
        return false;
    }

    function addModle() {
        var projectId = $("#projectName option:selected").val();
        window.location.href='${bathPath}/modle/addModle?projectId='+projectId;
    }
</script>
