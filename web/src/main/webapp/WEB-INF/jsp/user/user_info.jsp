<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<a href="${pageContext.request.contextPath}/user/info/purchases">Purchases</a>
<p>Персональные данные</p>
<label>Логин:&nbsp;</label><c:out value="${user.login}"/><br>
<label>Пароль:&nbsp;</label><c:out value="${user.password}"/><br>
<label>Имя:&nbsp;</label><c:out value="${user.firstName}"/><br>
<label>Фамилия:&nbsp;</label><c:out value="${user.surname}"/><br>
<label>Дата рождения:&nbsp;</label><c:out value="${user.birthDate}"/><br>
<label>Телефон:&nbsp;</label><c:out value="${user.phone}"/><br>
<label>Страна:&nbsp;</label><c:out value="${user.country}"/><br>
<label>Город:&nbsp;</label><c:out value="${user.city}"/><br>
<label>Улица:&nbsp;</label><c:out value="${user.street}"/><br>
<label>Дом:&nbsp;</label><c:out value="${user.house}"/><br>
<label>Корпус:&nbsp;</label><c:out value="${user.block}"/><br>
<label>Квартира:&nbsp;</label><c:out value="${user.apartment}"/><br>
<a href="${pageContext.request.contextPath}/user/info/update">Update</a>
<br>
</body>
</html>
