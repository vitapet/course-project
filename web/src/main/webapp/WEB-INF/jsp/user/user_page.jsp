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
<c:set var="category" value="all"/>
<a href="${pageContext.request.contextPath}/user/catalog/<c:out value="${category}"/>">Catalog</a>
<a href="<c:url value="/user/info"/>">Personal Information</a>
<br><br>
<p>Новости</p>
<c:forEach items="${newsList}" var="news">
    <c:set var="tempNews" value="${news}" scope="request"/>
    <jsp:include page="../res/news_only_header.jsp"/>
    <a href="user/news/<c:out value="${news.id}"/>">Подробнее</a><br>
    <br>
</c:forEach>
</body>
</html>
