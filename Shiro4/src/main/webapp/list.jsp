<%--
  Created by IntelliJ IDEA.
  User: sang
  Date: 17-3-22
  Time: 下午8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>list</h1>
<h1><a href="/logout">登出</a></h1>
<h1><a href="/roleTest">roleTest</a></h1>
<shiro:hasRole name="user">
    <h1><a href="/user">user.jsp</a></h1>
</shiro:hasRole>
<shiro:hasRole name="admin">
    <h1><a href="/admin.jsp">admin.jsp</a></h1>
</shiro:hasRole>
</body>
</html>
