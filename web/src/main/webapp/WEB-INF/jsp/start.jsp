<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<header>
    <jsp:include page="res/menu.jsp"/>
    <a type="button" href="${pageContext.request.contextPath}/registration">Registration</a>
</header>
<br><br>
<p>Новости</p>
<c:forEach items="${newsList}" var="news">
    <c:set var="tempNews" value="${news}" scope="request"/>
    <jsp:include page="res/news_only_header.jsp"/>
    <a href="${pageContext.request.contextPath}/news?id=${news.id}">Подробнее</a><br>
    <br>
</c:forEach>
</body>
</html>
