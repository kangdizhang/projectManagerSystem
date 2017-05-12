<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/12 0012
  Time: 上午 11:10
  To change this template use File | Settings | File Templates.
--%>
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
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">项目名称</td>
        <td>${project.projectName}</td>
    </tr>
    <tr>
        <td class="tableleft">服务器IP</td>
        <td>${projectInfo.serverIp}</td>
    </tr>
    <tr>
        <td class="tableleft">数据库IP</td>
        <td>${projectInfo.dbServerIp}</td>
    </tr>
    <tr>
        <td class="tableleft">数据库用户名</td>
        <td>${projectInfo.dbUser}</td>
    </tr>
    <tr>
        <td class="tableleft">数据库密码</td>
        <td>${projectInfo.dbPwd}</td>
    </tr>
    <tr>
        <td class="tableleft">数据库端口号</td>
        <td>${projectInfo.dbPort}</td>
    </tr>
    <tr>
        <td class="tableleft">域名</td>
        <td>${projectInfo.hostName}</td>
    </tr>
    <tr>
        <td class="tableleft">ssh信息</td>
        <td>${projectInfo.ssh}</td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <c:choose>
                <c:when test="${projectInfo.id==null}">
                    <button type="button" onclick="window.location.href='${bathPath}/editProjectInfo?projectId=${project.id}&projectName=${project.projectName}'"
                            class="btn btn-primary">新增</button>
                </c:when>
                <c:otherwise>
                    <button type="button" onclick="window.location.href='${bathPath}/editProjectInfo?id=${projectInfo.id}&projectName=${project.projectName}&projectId=${project.id}&serverIp=${projectInfo.serverIp}&dbServerIp=${projectInfo.dbServerIp}&dbUser=${projectInfo.dbUser}&dbPwd=${projectInfo.dbPwd}&dbPort=${projectInfo.dbPort}&hostName=${projectInfo.hostName}&ssh=${projectInfo.ssh}'"
                            class="btn btn-primary">编辑
                    </button>
                </c:otherwise>
            </c:choose>
            &nbsp;&nbsp;
            <a class="btn btn-success" href="${bathPath}/projectList">返回列表</a>
        </td>
    </tr>
</table>
</body>
</html>