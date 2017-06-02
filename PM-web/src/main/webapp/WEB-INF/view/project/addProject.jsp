<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>新建项目</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/Css/bootstrap-table.min.css"/>
    <link href="http://hovertree.com/ziyuan/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="http://hovertree.com/texiao/bootstrap/4/css/city-picker.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${basePath}/Js/jquery.js"></script>
    <script src="http://hovertree.com/texiao/bootstrap/4/js/city-picker.data.js"></script>
    <script src="http://hovertree.com/texiao/bootstrap/4/js/city-picker.js"></script>
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
<form action="/project/saveProject" method="post" name="form">
    <table class="table table-bordered table-hover definewidth m10">
        <input type="hidden" id="projectStatus" value="${project.projectStatus}">
        <input type="hidden" name="id" value="${project.id}">
        <tr>
            <td width="10%" class="tableleft">项目名称</td>
            <td width="35%"><input type="text" name="projectName" value="${project.projectName}"/><span
                    style="color: #a9302a">*</span></td>
            <td width="10%" class="tableleft">项目模块</td>
            <c:choose>
                <c:when test="${project.id != null}">
                    <td>
                        <c:forEach items="${list}" var="projectModleVO">
                            <span>${projectModleVO.modlePrototypeName}</span>&nbsp;&nbsp;&nbsp;&nbsp;
                            版本号：${projectModleVO.versionList[0].version}
                            <br>
                        </c:forEach>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <c:forEach items="${list}" var="modleVersionVO" varStatus="i">
                            <input type='checkbox' name='mpid' value='${i.index}'/>
                            <input type="hidden" name="modleId"
                                   value="${modleVersionVO.id}">${modleVersionVO.modlePrototypeName}
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <select name="version" style="width: 100px">
                                <option value="">请选择版本</option>
                                <c:forEach items="${modleVersionVO.versionList}" var="versionVO">
                                    <option value="${versionVO.id}">${versionVO.version}</option>
                                </c:forEach>
                            </select><br>
                        </c:forEach>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td width="10%" class="tableleft">项目所在地</td>
            <td width="35%">
                <div style="position: relative;">
                    <c:choose>
                        <c:when test="${project.region!=null and  project.region!=''}">
                            <input name="region" id="city-picker3" class="form-control" readonly type="text"
                                   value="${project.region}" data-toggle="city-picker"><br>
                        </c:when>
                        <c:otherwise>
                            <input name="region" id="city-picker3" class="form-control" readonly type="text"
                                   value="请选择省市区" data-toggle="city-picker"><br>
                        </c:otherwise>
                    </c:choose>
                </div
                <%--<div id="citySelect">
                    <select class="prov" id="province"></select>
                    <select class="city" disabled="disabled" id="city"></select>
                    <select class="dist" disabled="disabled" id="county"></select>
                </div>--%>
                <%--<select name="province" style="width: 100px;height: auto">
                    <option></option>
                </select>省&nbsp;&nbsp;
                <select name="" style="width: 100px;height: auto">
                    <option></option>
                </select>市&nbsp;&nbsp;
                <select style="width: 100px;height: auto">
                    <option></option>
                </select>区--%>
            </td>
            <td width="10%" class="tableleft">项目负责人</td>
            <td><input type="text" name="projectLeader" value="${project.projectLeader}"/><span
                    style="color: #a9302a">*</span></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">负责人电话</td>
            <td width="35%"><input type="text" name="phone" value="${project.phone}"/><span
                    style="color: #a9302a">*</span></td>
            <td width="10%" class="tableleft">负责人QQ</td>
            <td><input type="text" name="qq" value="${project.qq}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">负责人邮箱</td>
            <td width="35%"><input type="text" name="email" value="${project.email}"/><span
                    style="color: #a9302a">*</span></td>
            <td width="10%" class="tableleft">项目状态</td>
            <td>
                <input type="radio" name="projectStatus" value="0"/> 开发中&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="projectStatus" value="1"/> 升级中&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="projectStatus" value="2"/> 已完成&nbsp;&nbsp;&nbsp;&nbsp;
            </td>
        </tr>
        <c:choose>
            <c:when test="${project.id==null}">
                <tr>
                    <td width="10%" class="tableleft">服务器IP</td>
                    <td width="35%"><input type="text" name="serverIp" value="${param.serverIp}"/></td>
                    <td width="10%" class="tableleft">服务器系统</td>
                    <td><input type="text" name="OptSystem" value="${param.OptSystem}"/></td>
                </tr>
                <tr>
                    <td width="10%" class="tableleft">域名</td>
                    <td width="35%"><input type="text" name="hostName" value="${param.hostName}"/></td>
                    <td width="10%" class="tableleft">数据库IP</td>
                    <td><input type="text" name="dbServerIp" value="${param.dbServerIp}"/></td>
                </tr>
                <tr>
                    <td width="10%" class="tableleft">数据库用户名</td>
                    <td width="35%"><input type="text" name="dbUser" value="${param.dbUser}"/></td>
                    <td width="10%" class="tableleft">数据库密码</td>
                    <td><input type="text" name="dbPwd" value="${param.dbPwd}"/></td>
                </tr>
                <tr>
                    <td width="10%" class="tableleft">数据库端口号</td>
                    <td><input type="text" name="dbPort" value="${param.dbPort}"/></td>
                    <td width="10%" class="tableleft">ssh信息</td>
                    <td width="35%"><input type="text" name="ssh" value="${param.ssh}"/></td>
                </tr>
                <tr>
                    <td width="10%" class="tableleft">备注</td>
                    <td width="35%"><textarea name="note" style="width: auto;height: auto">${param.note}</textarea></td>
                    <td width="10%" class="tableleft"></td>
                    <td></td>
                </tr>
            </c:when>
        </c:choose>
        <tr>
            <td width="10%" class="tableleft">操作</td>
            <td width="35%">
                <button type="submit" onclick="submitForm()" class="btn btn-primary">保存</button>
                &nbsp;&nbsp;
                <a class="btn btn-success" href="${bathPath}/project/projectList">返回列表</a>
            </td>
        </tr>
    </table>
</form>
</body>
<script>
    $(function () {
        var projectStatus = $('#projectStatus').val();
        if (projectStatus == '0') {
            $("input[name='projectStatus'][value='0']").attr("checked", true);
        } else if (projectStatus == '1') {
            $("input[name='projectStatus'][value='1']").attr("checked", true);
        } else if (projectStatus == '2') {
            $("input[name='projectStatus'][value='2']").attr("checked", true);
        } else {
            $("input[name='projectStatus'][value='0']").attr("checked", true);
        }
    })
</script>
<script>
    $(function () {
        if (${msg != null}) {
            alert("${msg}");
        }
    })
</script>
</html>
