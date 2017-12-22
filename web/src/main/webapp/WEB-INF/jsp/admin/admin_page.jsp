<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<br>
<a href="${pageContext.request.contextPath}/admin/catalog">Catalog</a>
<a href="${pageContext.request.contextPath}/admin/news">News</a>
<%--<a href="${pageContext.request.contextPath}/admin/users">Users</a>--%>
<a href="${pageContext.request.contextPath}/admin/purchases">Purchases</a>
<br><br>
</body>
</html>
