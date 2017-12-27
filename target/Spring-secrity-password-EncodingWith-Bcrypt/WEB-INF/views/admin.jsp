<%--
  Created by IntelliJ IDEA.
  User: Anlu
  Date: 2017/12/9
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
<div class="success">
    Dear <strong>${user}</strong>, Welcome to Admin Page.
    <br/>
    Would you like to <a href="<c:url value='/newUser' />">Add Some Users</a> to keep yourself busy?
    <br/>
    <a href="<c:url value="/logout" />">Logout</a>
</div>
</body>
</html>
