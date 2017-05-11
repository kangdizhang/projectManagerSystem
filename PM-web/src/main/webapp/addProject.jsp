<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/10 0010
  Time: 下午 2:40
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
    <title>新建项目</title>
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
<form action="/project/saveProject" method="get">
    <input type="hidden" name="id" value="${param.id}">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">项目名称</td>
            <td><input type="text" name="projectName" value="${param.projectName}"/></td>
        </tr>
        <tr>
            <td class="tableleft">项目负责人</td>
            <td><input type="text" name="projectLeader" value="${param.projectLeader}"/></td>
        </tr>
        <tr>
            <td class="tableleft">负责人电话</td>
            <td><input type="text" name="phone"  value="${param.phone}"/></td>
        </tr>
        <tr>
            <td class="tableleft">负责人QQ</td>
            <td><input type="text" name="QQ"  value="${param.QQ}"/></td>
        </tr><tr>
            <td class="tableleft">负责人邮箱</td>
            <td><input type="text" name="email"  value="${param.email}"/></td>
        </tr>
        <tr>
            <td class="tableleft">项目状态</td>
            <td>
                <input type="hidden" id="projectStatus" value="${param.projectStatus}">
                <input type="radio" name="projectStatus" value="0"/> 开发中
                <input type="radio" name="projectStatus" value="1"/> 升级中
                <input type="radio" name="projectStatus" value="2"/> 已完成
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    $(function () {
        var projectStatus = $('#projectStatus').val();
        if(projectStatus=='0'){
            $("input[name='projectStatus'][value='0']").attr("checked",true);
        }else if(projectStatus=='1'){
            $("input[name='projectStatus'][value='1']").attr("checked",true);
        }else{
            $("input[name='projectStatus'][value='2']").attr("checked",true);
        }
    })
</script>
</html>
