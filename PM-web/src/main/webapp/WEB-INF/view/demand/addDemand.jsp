<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/19 0019
  Time: 下午 5:35
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
    <title>新增需求</title>
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
<form action="/demand/saveDemand" method="post" name="form">
    <input type="hidden" name="projectId" value="${param.projectId}">
    <table class="table table-bordered table-hover definewidth m10">
                <%--<tr>
                    <td width="10%" class="tableleft">提示信息</td>
                    <td>
                        <p style="color: crimson">${msg}</p>
                    </td>
                </tr>--%>
                <tr>
                    <td class="tableleft">关联模块选择</td>
                    <td>
                        <c:forEach items="${list}" var="modle">
                            <label class='checkbox inline'><input type='checkbox' name='mdid' value='${modle.id}' />${modle.modleName}</label>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td width="10%" class="tableleft">版本号</td>
                    <td>
                        <input type="text" name="version" value="${demand.version}">
                    </td>
                </tr>
                <tr>
                    <td width="10%" class="tableleft">需求名称</td>
                    <td>
                        <input type="text" name="demandName" value="${demand.demandName}">
                    </td>
                </tr>
                <tr>
                    <td width="10%" class="tableleft">需求描述</td>
                    <td>
                        <input type="text" name="demandName" value="${demand.demandName}">
                    </td>
                </tr>
                <tr>
                    <td width="10%" class="tableleft">预期完成时间</td>
                    <td>
                        <input type="text" name="exceptEndTime" value="${demand.exceptEndTime}">
                    </td>
                </tr>
                <tr>
                    <td width="10%" class="tableleft">完成人</td>
                    <td>
                        <input type="text" name="completeUserId" value="${demand.completeUserId}">
                    </td>
                </tr>
                <tr>
                    <td class="tableleft"></td>
                    <td>
                        <button type="submit" class="btn btn-primary">保存</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="btn btn-success" href="${bathPath}/demandList?projectId=${param.projectId}">返回列表</a>
                    </td>
                </tr>
    </table>
</form>
</body>
</html>
