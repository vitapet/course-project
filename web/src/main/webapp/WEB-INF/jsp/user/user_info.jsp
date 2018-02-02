<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>User info</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
    <jsp:include page="../res/cart.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/user">Return</a><br>
<br>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="request"/>
</c:if>
<a href="${pageContext.request.contextPath}/user/info/orders">Orders</a>
<p>Персональные данные</p>
<%--@elvariable id="userDTO" type="com.gmail.vitaliapetsenak.shop.service.model.UserDTO"--%>
<form:form method="post" action="info" modelAttribute="userDTO">
    <c:set var="birthDate" value="test" scope="request"/>
    <jsp:include page="../res/user_info_empty.jsp"/>
    <table>
        <tr>
            <td><label>Повторите пароль</label></td>
            <td><form:password id="passwordCheck" path="passwordCheck"/></td>
            <td><form:errors path="passwordCheck"/></td>
        </tr>
    </table>
    <br/>
    <input type="submit" value="Save"/>
</form:form>
<br>
</body>
</html>
