<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/25 0025
  Time: 下午 4:59
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

<h2>没有项目，请单击<a href="${basePath}/project/addProject">这里</a>以新建项目</h2>

</body>
</html>
