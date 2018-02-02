<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="username"/>
    <c:out value="${username}"/>
    <a type="button" href="<c:url value="/logout"/>"><spring:message code="button.logout"/></a>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <a type="button" href="<c:url value="/login"/>"><spring:message code="button.login"/></a>
</sec:authorize>