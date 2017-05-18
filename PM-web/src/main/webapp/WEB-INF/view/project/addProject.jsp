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
<form action="/project/saveProject" method="get" name="form">
    <table class="table table-bordered table-hover definewidth m10">
        <input type="hidden" id="projectStatus" value="${project.projectStatus}">
        <input type="hidden" name="id" value="${project.id}">
        <tr>
            <td width="10%" class="tableleft">项目名称</td>
            <td><input type="text" name="projectName" value="${project.projectName}"/></td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">项目模块</td>
            <c:choose>
                <c:when test="${project.id != null}">
                    <td>
                        <c:forEach items="${list}" var="modle">
                            <p>${modle.modleName}</p>
                        </c:forEach>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <c:forEach items="${list}" var="modlePrototype">
                            <label class='checkbox inline'><input type='checkbox' name='mpid' value='${modlePrototype.id}' />${modlePrototype.modlePrototypeName}</label>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td class="tableleft">项目负责人</td>
            <td><input type="text" name="projectLeader" value="${project.projectLeader}"/></td>
        </tr>
        <tr>
            <td class="tableleft">负责人电话</td>
            <td><input type="text" name="phone" value="${project.phone}"/></td>
        </tr>
        <tr>
            <td class="tableleft">负责人QQ</td>
            <td><input type="text" name="qq" value="${project.qq}"/></td>
        </tr>
        <tr>
            <td class="tableleft">负责人邮箱</td>
            <td><input type="text" name="email" value="${project.email}"/></td>
        </tr>
        <tr>
            <td class="tableleft">项目状态</td>
            <td>
                <input type="radio" name="projectStatus" value="0"/> 开发中
                <input type="radio" name="projectStatus" value="1"/> 升级中
                <input type="radio" name="projectStatus" value="2"/> 已完成
            </td>
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

    //    function submitForm() {
    //        $.ajax({
    //            type: 'GET',
    //            url: '/project/saveProject',
    //            data: $('form').serialize(),
    //            dataType: 'json',
    //            success: function (data) {
    //                alert('1');
    //            },
    //            error:function (data) {
    //                alert('2');
    //            }
    //        });
    //    }
</script>
</html>
