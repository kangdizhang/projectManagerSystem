<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/15 0015
  Time: 下午 3:23
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
    <title>修改项目模块</title>
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
<form action="/modle/updateModle" method="get" name="form">
    <table class="table table-bordered table-hover definewidth m10">
        <input type="hidden" name="id" value="${projectModleVO.id}">
        <input type="hidden" name="projectId" value="${projectModleVO.projectId}">
        <input type="hidden" name="modleId" value="${projectModleVO.modleId}">
        <tr>
            <td width="10%" class="tableleft">模块所属项目</td>
            <td width="35%"><span name="projectName">${projectModleVO.projectName}</span></td>
            <td width="10%" class="tableleft">模块名称</td>
            <td>${projectModleVO.modleName}&nbsp;&nbsp;&nbsp;&nbsp;
                版本号：<select name="version" style="width:100px">
                    <c:forEach var="versionVO" items="${versionVOList}">
                        <c:choose>
                            <c:when test="${projectModleVO.version==versionVO.version}">
                                <option value="${versionVO.version}" selected="selected">${versionVO.version}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${versionVO.version}">${versionVO.version}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">项目模块描述</td>
            <td width="35%">${projectModleVO.modleDescribe}</td>
            <c:choose>
                <c:when test="${msg!=null}">
                    <td class="tableleft">提示信息</td>
                    <td>
                        <p style="color: crimson">${msg}</p>
                    </td>
                </c:when>
                <c:otherwise>
                    <td class="tableleft"></td>
                    <td></td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td colspan="3">
                <button type="submit" class="btn btn-primary">保存</button>
                &nbsp;&nbsp;
                <a class="btn btn-success" href="${bathPath}/modle/modleList?projectId=${projectModleVO.projectId}">返回列表</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
