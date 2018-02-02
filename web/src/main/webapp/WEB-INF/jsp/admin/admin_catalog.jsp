<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Admin Catalog</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<c:set var="attrUrl" value="${url}" scope="request"/>
<a href="${pageContext.request.contextPath}/${attrUrl}">Return</a><br>
<jsp:include page="../res/admin_products_list.jsp"/>
</body>
</html>
