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
        <tr>
            <td width="10%" class="tableleft">所属项目</td>
            <c:choose>
                <c:when test="${projectInfoVO!=null}">
                    <input type="hidden" name="id" value="${projectInfoVO.id}">
                    <input type="hidden" name="projectId" value="${projectInfoVO.projectId}">
                    <td width="35%">${projectInfoVO.projectName}</td>
                </c:when>
                <c:otherwise>
                    <td width="35%">
                        <select name="projectId">
                            <option value="0">请选择项目</option>
                            <c:forEach var="project" items="list">
                                <option value="${project.id}">${project.projectName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </c:otherwise>
            </c:choose>
            <td class="tableleft" width="10%">服务器系统</td>
            <td><input type="text" name="optSystem" value="${projectVO.optSystem}"></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">服务器IP</td>
            <td width="35%"><input type="text" name="serverIp" value="${projectVO.serverIp}"/></td>
            <td width="10%" class="tableleft">域名</td>
            <td><input type="text" name="hostName" value="${projectVO.hostName}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">数据库IP</td>
            <td width="35%"><input type="text" name="dbServerIp" value="${projectVO.dbServerIp}"/></td>
            <td width="10%" class="tableleft">数据库用户名</td>
            <td><input type="text" name="dbUser" value="${projectVO.dbUser}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">数据库密码</td>
            <td width="35%"><input type="text" name="dbPwd" value="${projectVO.dbPwd}"/></td>
            <td width="10%" class="tableleft">数据库端口号</td>
            <td><input type="text" name="dbPort" value="${projectVO.dbPort}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">ssh信息</td>
            <td width="35%"><input type="text" name="ssh" value="${projectVO.ssh}"/></td>
            <c:choose>
                <c:when test="${msg!=null}">
                    <td width="10%" class="tableleft">提示信息</td>
                    <td>
                        <p style="color: crimson">${msg}</p>
                    </td>
                </c:when>
                <c:otherwise>
                    <td width="10%"></td>
                    <td></td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td width="10%" class="tableleft">操作</td>
            <td width="35%">
                <button type="submit" class="btn btn-primary">保存</button>
                &nbsp;&nbsp;
                <a class="btn btn-success" href="${bathPath}/projectInfo/projectInfoList">返回列表</a>
            </td>
            <td width="10%"></td>
            <td></td>
        </tr>
    </table>
</form>
</body>
<script>

</script>
</html>
