<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<header>
    <jsp:include page="res/menu.jsp"/>
    <a type="button" href="${pageContext.request.contextPath}/registration"><spring:message code="button.registration"/></a>
</header>
<br><br>
<p>Новости</p>
<c:forEach items="${newsList}" var="news">
    <c:set var="tempNews" value="${news}" scope="request"/>
    <jsp:include page="res/news_only_header.jsp"/>
    <a href="${pageContext.request.contextPath}/news/selected/<c:out value="${news.id}"/>"><spring:message code="button.more.info"/></a><br>
    <br>
</c:forEach>

<table>
    <tr>
        <td>
            <c:if test="${pagination.curPage>2}">
                <a href="${pageContext.request.contextPath}/<c:out value="${pagination.uri}"/>/<c:out value="${pagination.firstPageNum}"/>"><c:out
                        value="${pagination.first}"/></a>
            </c:if>
        </td>
        <td>
            <c:forEach items="${pagination.pages}" var="item">
                <a href="${pageContext.request.contextPath}/<c:out value="${pagination.uri}"/>/<c:out value="${item}"/>">${item}</a>
            </c:forEach>
        </td>
        <td>
            <c:if test="${pagination.curPage<(pagination.count-1)}">
                <a href="${pageContext.request.contextPath}/<c:out value="${pagination.uri}"/>/<c:out value="${pagination.lastPageNum}"/>"><c:out
                        value="${pagination.last}"/></a>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>