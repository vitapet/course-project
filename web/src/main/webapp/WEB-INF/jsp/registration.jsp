<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
    <jsp:include page="res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/"><spring:message code="button.return"/></a><br/>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="request"/>
</c:if>
<br/>
<%--@elvariable id="userDTO" type="com.gmail.vitaliapetsenak.shop.service.model.UserDTO"--%>
<form:form method="post" action="registration" modelAttribute="userDTO">
    <c:set var="birthDate" value="test" scope="request"/>
    <jsp:include page="res/user_info_empty.jsp"/>
    <table>
        <tr>
            <td><label><spring:message code="registration.password.check"/></label></td>
            <td><form:password id="passwordCheck" path="passwordCheck"/></td>
            <td><form:errors path="passwordCheck"/></td>
        </tr>
    </table>
    <br/>
    <input type="submit" value="<spring:message code="button.submit"/>"/>
</form:form>
</body>
</html>