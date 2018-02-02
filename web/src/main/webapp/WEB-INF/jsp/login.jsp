<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/"><spring:message code="button.return"/></a><br/>
<c:if test="${error}">
    <p>Invalid credentials.</p>
</c:if>
<c:remove var="error" scope="request"/>
<%--@elvariable id="user" type="com.gmail.vitaliapetsenak.shop.service.model.UserDTO"--%>
<form:form method="post" modelAttribute="user">
    <table>
        <form:errors path="login"/>
        <tr>
            <td><form:label path="login"><spring:message code="login.username"/></form:label></td>
            <td><form:input path="login"/></td>
        </tr>
        <tr>
            <td><form:label path="password"><spring:message code="login.password"/></form:label></td>
            <td><form:password path="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="<spring:message code="button.login"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>