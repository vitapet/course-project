<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--@elvariable id="news" type="com.gmail.vitaliapetsenak.shop.service.model.NewsDTO"--%>
<img src="${pageContext.request.contextPath}/image/<c:out value="${news.id}"/>" name="track${news.id}"
     alt="Loading " width="600" height="400"><br>
<label><spring:message code="news.date"/>:&nbsp;</label>
<c:out value="${news.timestamp}"/><br>
<c:out value="${news.name}"/><br>
<c:out value="${news.description}"/><br>
<label><spring:message code="news.author"/>:&nbsp;</label>${news.user.firstName}&nbsp;${news.user.surname}<br>
<br>
<p><spring:message code="news.comments"/></p>
<c:forEach items="${news.comments}" var="comment">
    <c:out value="${comment.user.login}"/>&nbsp;
    <c:out value="${comment.timestamp}"/><br>
    <c:out value="${comment.text}"/><br><br>
</c:forEach>
