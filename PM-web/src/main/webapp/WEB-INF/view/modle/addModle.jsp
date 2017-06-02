<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/15 0015
  Time: 下午 3:51
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
    <title>新增项目模块</title>
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
<form action="/modle/saveModle" method="post" name="form">
    <input type="hidden" name="projectId" value="${projectId}">
    <table class="table table-bordered table-hover definewidth m10">
        <c:choose>
            <c:when test="${msg!=null}">
                <tr>
                    <td width="10%" class="tableleft">提示信息</td>
                    <td>
                        <p style="color: crimson">${msg}</p>
                    </td>
                </tr>
                <tr>
                    <td class="tableleft">
                        项目名称
                    </td>
                    <td>
                        ${project.projectName}
                    </td>
                </tr>
                <tr>
                    <td class="tableleft"></td>
                    <td>
                        <a class="btn btn-success" href="${bathPath}/modlePrototype/editModlePrototype">添加模块原型</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="btn btn-success" href="${bathPath}/modle/modleList?projectId=${projectId}">返回列表</a>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                <td class="tableleft">
                    项目名称
                </td>
                <td>
                    <p name="projectName">${project.projectName}</p>
                </td>
            </tr>
                <tr>
                    <td width="10%" class="tableleft">模块选择</td>
                    <td>
                        <c:forEach items="${list}" var="modlePrototype" varStatus="i">
                            <input type='checkbox' name='mpid' value='${i.index}'/>
                            <input type='hidden' name='modleId' value='${modlePrototype.id}' />${modlePrototype.modlePrototypeName}
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            版本号：<select name="version" style="width: 100px">
                            <c:forEach items="${modlePrototype.versionList}" var="versionVO">
                                <option value="${versionVO.id}">${versionVO.version}</option>
                            </c:forEach>
                            </select><br/>
                        </c:forEach>

                    </td>
                </tr>
                <tr>
                    <td class="tableleft"></td>
                    <td>
                        <button type="submit" class="btn btn-primary">保存</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="btn btn-success" href="${bathPath}/modle/modleList?projectId=${projectId}">返回列表</a>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</form>
</body>
<script>
    $(function () {
        if (${mesg != null}) {
            alert("${mesg}");
        }
    })
</script>
</html>
