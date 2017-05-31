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
        <c:if test="${projectId==null}">
            <input type="hidden" name="projectId" value="${param.projectId}">
        </c:if>
        <c:if test="${projectId!=null}">
            <input type="hidden" name="projectId" value="${projectId}">
            <input type="hidden" name="demandId" value="${demandId}">
        </c:if>
    <input type="hidden" name="id" value="${demand.id}">
    <input type="hidden" name="demandStatus" value="${demand.demandStatus}">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td class="tableleft">关联模块选择</td>
            <td>
                <c:choose>
                    <c:when test="${list!=null}">
                        <c:forEach items="${ModleVOList}" var="modleVO">
                            <label class='checkbox inline'><input type='checkbox' name='mdid' value='${modleVO.modleId}' />${modleVO.modleName}</label>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                        <c:forEach items="${list}" var="modle">
                            <label class='checkbox inline'><input type='checkbox' name='mdid' value='${modle.modleId}' checked="checked" />${modle.modleName}</label>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${ModleVOList}" var="modleVO">
                            <label class='checkbox inline'><input type='checkbox' name='mdid' value='${modleVO.modleId}' />${modleVO.modleName}</label>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">版本号</td>
            <c:choose>
                <c:when test="${demand.version!=null}">
                    <td>
                        ${demand.version}
                        <input type="hidden" name="version" value="${demand.version}">
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <input type="text" name="version" value="${demand.version}"><span style="color: RED">*</span>
                    </td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td width="10%" class="tableleft">需求名称</td>
            <td>
                <input type="text" name="demandName" value="${demand.demandName}"><span style="color: RED">*</span>
            </td>
        </tr>
        <c:if test="${demand.putUserId!=null}">
            <tr>
                <td width="10%" class="tableleft">提出人</td>
                <td>
                        ${demand.putUserId}
                    <input type="hidden" name="putUserId" value="${demand.putUserId}">
                </td>
            </tr>
        </c:if>
        <c:if test="${demand.putTime!=null}">
            <tr>
                <td width="10%" class="tableleft">提出时间</td>
                <td>
                    ${demand.putTime}
                    <input type="hidden" name="putTime" value="${demand.putTime}">
                </td>
            </tr>
        </c:if>
        <tr>
            <td width="10%" class="tableleft">需求描述</td>
            <td>
                <input type="text" name="demandDescribe" value="${demand.demandDescribe}"><span style="color: RED">*</span>
            </td>
        </tr>
        <tr>
            <td width="10%" class="tableleft">预期完成时间</td>
            <td>
                <input type="text" name="exceptEndTime" value="${demand.exceptEndTime}"><span style="color: RED">*</span>
            </td>
        </tr>
        <c:if test="${demand.actualEndTime!=null}">
            <tr>
                <td width="10%" class="tableleft">实际完成时间</td>
                <td>
                   ${demand.actualEndTime}
                </td>
            </tr>
        </c:if>
        <c:if test="${demand.completeUserId!=null}">
            <tr>
                <td width="10%" class="tableleft">完成人</td>
                <td>
                   ${demand.completeUserId}
                </td>
            </tr>
        </c:if>
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
<script>
    $(function () {
        if (${errorMsg != null}) {
            alert("${errorMsg}");
        }
    })
</script>
</html>
