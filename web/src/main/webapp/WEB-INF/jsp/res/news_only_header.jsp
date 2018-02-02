<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<img src="${pageContext.request.contextPath}/image/<c:out value="${tempNews.id}"/>" name="track${tempNews.id}"
     alt="Loading <c:out value="${tempNews.id}"/>" width="600" height="400"><br>
<label><spring:message code="news.date"/>:&nbsp;</label><c:out value="${tempNews.timestamp}"/><br>
<c:out value="${tempNews.name}"/><br>
