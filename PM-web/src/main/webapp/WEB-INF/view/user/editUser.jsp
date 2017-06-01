<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>项目配置信息</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/bootstrap-table.min.css"/>
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
<form action="/user/saveUser" method="get" name="form">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">用户名</td>
            <td width="35%"><input type="text" name="userName" value="${user0.userName}" /></td>
            <td width="10%" class="tableleft">用户类型</td>
            <td>
                <select style="width: 200px" name="userType" id="userType">
                    <option value="">请选择用户类型</option>
                    <option value="管理员">管理员</option>
                    <option value="BOSS">BOSS</option>
                    <option value="项目经理">项目经理</option>
                    <option value="开发人员">开发人员</option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">密码</td>
            <td width="35%"><input type="password" name="pwd" value="${user0.pwd}"/></td>
            <td width="10%" class="tableleft">确认密码</td>
            <td><input type="password" name="pwds" value="${user0.pwd}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">操作</td>
            <td width="35%">
                <button type="submit" class="btn btn-primary">保存</button>
                &nbsp;&nbsp;
                <a class="btn btn-success" href="${bathPath}/user/userList">返回列表</a>
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    $(function () {
        if (${msg != null}) {
            alert("${msg}");
        }
    })
</script>
<script type="text/javascript">
    $(function(){
        if (${user0.userType != null}) {
            $("#userType").val("${user0.userType}")
        }
    })
</script>
</html>