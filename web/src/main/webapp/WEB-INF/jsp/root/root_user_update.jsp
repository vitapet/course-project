<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Users update</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<a href="${pageContext.request.contextPath}/root/users">Return</a><br>
<sec:authentication property="principal.role" var="role"/>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>
<form:form method="post" modelAttribute="user">
    <table>
        <form:hidden path="id"/>
        <tr>
            <td><label>Логин</label></td>
            <td><form:input path="login" readonly="true"/></td>
            <td><form:errors path="login"/></td>
        </tr>
        <tr>
            <td><label>Пароль</label></td>
            <td><form:password path="password"/></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td><label>Имя</label></td>
            <td><form:input path="firstName" readonly="true"/></td>
            <td><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td><label>Фамилия</label></td>
            <td><form:input path="surname" readonly="true"/></td>
            <td><form:errors path="surname"/></td>
        </tr>
        <tr>
            <td><label>Дата рождения</label></td>
            <td><form:input path="birthDate" readonly="true" type="date"/></td>
            <td><form:errors path="birthDate"/></td>
        </tr>
        <tr>
            <td><label>Телефон</label></td>
            <td><form:input path="phone" readonly="true"/></td>
            <td><form:errors path="phone"/></td>
        </tr>
        <tr>
            <td><label>Страна</label></td>
            <td><form:input path="country" readonly="true"/></td>
            <td><form:errors path="country"/></td>
        </tr>
        <tr>
            <td><label>Город</label></td>
            <td><form:input path="city" readonly="true"/></td>
            <td><form:errors path="city"/></td>
        </tr>
        <tr>
            <td><label>Улица</label></td>
            <td><form:input path="street" readonly="true"/></td>
            <td><form:errors path="street"/></td>
        </tr>
        <tr>
            <td><label>Дом</label></td>
            <td><form:input path="house" readonly="true"/></td>
            <td><form:errors path="house"/></td>
        </tr>
        <tr>
            <td><label>Корпус</label></td>
            <td><form:input path="block" readonly="true"/></td>
            <td><form:errors path="block"/></td>
        </tr>
        <tr>
            <td><label>Квартира</label></td>
            <td><form:input path="apartment" readonly="true"/></td>
            <td><form:errors path="apartment"/></td>
        </tr>
        <tr>
            <td><label>Role</label></td>
            <td><form:select path="role">
                <form:options items="${roles}"/>
            </form:select></td>
            <td><form:errors path="role"/></td>
        </tr>
        <tr>
            <td><label>Status</label></td>
            <td><form:select path="status">
                <form:options items="${statuses}"/>
            </form:select></td>
            <td><form:errors path="status"/></td>
        </tr>
        <tr>
            <td><label>Repeat password</label></td>
            <td><form:password path="passwordCheck"/></td>
            <td><form:errors path="passwordCheck"/></td>
        </tr>
    </table>
    <button type="submit">Save</button>
</form:form>
</body>
</html>
