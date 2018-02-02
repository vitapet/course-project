<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>
<p>Новости</p>
<a href="${pageContext.request.contextPath}/${attrUrl}/news/add">Add</a><br>
<c:forEach items="${newsList}" var="news">
    <c:set var="tempNews" value="${news}" scope="request"/>
    <jsp:include page="../res/news_only_header.jsp"/>
    <a href="${pageContext.request.contextPath}/${attrUrl}/news/<c:out value="${news.id}"/>">Подробнее</a><br>
    <br>
</c:forEach>