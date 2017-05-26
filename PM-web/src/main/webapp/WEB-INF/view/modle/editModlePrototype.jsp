<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/12 0012
  Time: 下午 3:22
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
    <title>模块原型</title>
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
<form action="/modlePrototype/saveProjectInfo" method="get" name="form">
    <table class="table table-bordered table-hover definewidth m10">
        <input type="hidden" name="id" value="${modlePrototype.id}">
        <tr>
            <td width="10%" class="tableleft">模块原型名称</td>
            <td><input type="text" name="modlePrototypeName" value="${modlePrototype.modlePrototypeName}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">模块原型描述</td>
            <td><textarea name="modlePrototypeDescribe">${modlePrototype.modlePrototypeDescribe}</textarea> </td>
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
                <button type="submit" class="btn btn-primary">保存</button>
                &nbsp;&nbsp;
                <a class="btn btn-success" href="${bathPath}/modlePrototype/modlePrototypeList">返回列表</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
