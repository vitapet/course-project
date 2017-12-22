<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Admin News</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/admin">Return</a><br>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>
<p>Новости</p>
<a href="${pageContext.request.contextPath}/admin/news/add">Add</a><br>
<form action="${pageContext.request.contextPath}/admin/news" method="post">
    <c:choose>
        <c:when test="${empty newsList}">
            <p>There are no news.</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${newsList}" var="news">
                <c:set var="tempNews" value="${news}" scope="request"/>
                <input type="radio" name="id" value="<c:out value="${news.id}"/>">
                <jsp:include page="../res/news_only_header.jsp"/>
                <a href="${pageContext.request.contextPath}/admin/news/selected?id=${news.id}">Подробнее</a><br>
                <br>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <c:if test="${not empty newsList}">
        <button type="submit">Delete</button>
    </c:if>
</form>
</body>
</html>
