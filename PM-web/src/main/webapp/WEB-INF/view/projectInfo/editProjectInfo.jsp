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
<form action="/projectInfo/saveProjectInfo" method="get" name="form">
    <table class="table table-bordered table-hover definewidth m10">
        <input type="hidden" name="id" value="${param.id}">
        <tr>
            <td width="10%" class="tableleft">项目名称</td>
            <td>${param.projectName}</td>
        </tr>
        <tr>
            <td class="tableleft">服务器IP</td>
            <td><input type="text" name="serverIp" value="${param.serverIp}"/></td>
        </tr>
        <tr>
            <td class="tableleft">数据库IP</td>
            <td><input type="text" name="dbServerIp" value="${param.dbServerIp}"/></td>
        </tr>
        <tr>
            <td class="tableleft">数据库用户名</td>
            <td><input type="text" name="dbUserId" value="${param.dbUserId}"/></td>
        </tr>
        <tr>
            <td class="tableleft">数据库密码</td>
            <td><input type="text" name="dbPwd" value="${param.dbPwd}"/></td>
        </tr>
        <tr>
            <td class="tableleft">数据库端口号</td>
            <td><input type="text" name="dbPort" value="${param.dbPort}"/></td>
        </tr>
        <tr>
            <td class="tableleft">域名</td>
            <td><input type="text" name="hostName" value="${param.hostName}"/></td>
        </tr>
        <tr>
            <td class="tableleft">ssh信息</td>
            <td><input type="text" name="email" value="${param.ssh}"/></td>
        </tr>
        <c:choose>
            <c:when test="${msg!=null}">
                <tr>
                    <td class="tableleft">提示信息</td>
                    <td>
                        <p style="color: crimson">${msg}</p>
                    </td>
                </tr>
            </c:when>
        </c:choose>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" onclick="submitForm()" class="btn btn-primary">保存</button>
                &nbsp;&nbsp;
                <a class="btn btn-success" href="${bathPath}/projectList">返回列表</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
