<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<c:set var="category" value="all"/>
<a href="${pageContext.request.contextPath}/admin/catalog/<c:out value="${category}"/>">Catalog</a>
<a href="${pageContext.request.contextPath}/admin/news">News</a>
<a href="${pageContext.request.contextPath}/admin/orders">Orders</a>
<br><br>
</body>
</html>
