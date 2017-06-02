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
<form action="/optStaff/saveOptStaff" method="post" name="form">
    <table class="table table-bordered table-hover definewidth m10">
        <<input type="hidden" name="id" value="${optStaff.id}">
        <tr>
            <td width="10%" class="tableleft">运维人员名</td>
            <td width="35%"><input type="text" name="optStaffName" value="${optStaff.optStaffName}" /><span style="color: RED">*</span></td>
            <td width="10%" class="tableleft">运维项目</td>
            <td>
                <select style="width: 200px" name="projectId" id="projectId">
                    <option value="">请选择运维项目</option>
                    <c:forEach var="project" items="${list}">
                        <option value="${project.id}">${project.projectName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">手机号</td>
            <td width="35%"><input type="text" name="tel" value="${optStaff.tel}"/><span style="color: RED">*</span></td>
            <td width="10%" class="tableleft">QQ</td>
            <td><input type="text" name="qq" value="${optStaff.qq}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">邮箱</td>
            <td width="35%"><input type="text" name="email" value="${optStaff.email}"><span style="color: RED">*</span></td>
            <td width="10%" class="tableleft">操作</td>
            <td width="35%">
                <button type="submit" class="btn btn-primary">保存</button>
                &nbsp;&nbsp;
                <a class="btn btn-success" href="${bathPath}/optStaff/optStaffList">返回列表</a>
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
        if (${optStaff.projectId != null}) {
            $("#projectId").val("${optStaff.projectId}")
        }
    })
</script>
</html>