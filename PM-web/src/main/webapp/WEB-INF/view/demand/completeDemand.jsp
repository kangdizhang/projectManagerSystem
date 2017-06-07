<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2017/6/5
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>需求完成页</title>
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
    <form name="form1" action="/demand/completeDemand" method="post" enctype="multipart/form-data">
        <input type="hidden" name="projectId" value="${projectId}">
        <input type="hidden" name="id" value="${id}">
        <table class="table table-bordered table-hover definewidth m10">
            <tr>
                <td width="10%" class="tableleft">SQL脚本</td>
                <td width="35%"><input name="sqlfile" type="file"></td>
                <td width="10%" class="tableleft">备注说明</td>
                <td><textarea name="versionDesc" id="versionDesc"></textarea><span style="color: RED">*</span> </td>
            </tr>
            <tr>
                <td width="10%" class="tableleft">操作</td>
                <td width="35%" colspan="2">
                    <button type="submit" onclick="return chkform()" class="btn btn-primary" value="提交">提交</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="btn btn-success" href="${bathPath}/demand/demandList?projectId=${projectId}">返回列表</a>
                </td>
            </tr>
        </table>
    </form>
</body>
<script>
    function chkform() {
        if (document.getElementById("versionDesc").value == "") {
            alert("备注说明不能为空！");
            return false;
        }
    }
</script>
<script>
    $(function () {
        if (${msg != null}) {
            alert("${msg}");
        }
    })
</script>
</html>
