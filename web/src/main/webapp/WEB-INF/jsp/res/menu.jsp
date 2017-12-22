<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty user.login}">
        <a type="button" href="${pageContext.request.contextPath}/login">Login</a>
    </c:when>
    <c:otherwise>
        <a href=""><c:out value="${user.login}"/></a>
        <a type="button" href="${pageContext.request.contextPath}/logout">Logout</a>
    </c:otherwise>
</c:choose>
