<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2017/6/7
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>错误页面</title>
</head>
<body>

<h2>该需求没有SQL脚本，请单击<a href="${basePath}/demand/demandList">这里</a>返回需求列表页面</h2>

</body>
</html>
