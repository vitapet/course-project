<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Shop</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
    <jsp:include page="../res/cart.jsp"/>
</header>
<br>
<a href="${pageContext.request.contextPath}/user/catalog">Catalog</a>
<a href="${pageContext.request.contextPath}/user/info">Personal Information</a>
<br><br>
<p>Новости</p>
<c:forEach items="${newsList}" var="news">
    <c:set var="tempNews" value="${news}" scope="request"/>
    <jsp:include page="../res/news_only_header.jsp"/>
    <a href="${pageContext.request.contextPath}/user/news?id=${news.id}">Подробнее</a><br>
    <br>
</c:forEach>
</body>
</html>
