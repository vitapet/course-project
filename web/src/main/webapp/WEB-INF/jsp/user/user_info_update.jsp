<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Update Info</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
    <jsp:include page="../res/cart.jsp"/>
</header>
<br>
<p>Персональные данные</p>
<form action="${pageContext.request.contextPath}/user/info/update" method="post">
    <label>Логин:&nbsp;</label><input type="text" name="login" value="<c:out value="${user.login}"/>"><br>
    <label>Пароль:&nbsp;</label><input type="text" name="password" value="<c:out value="${user.password}"/>"><br>
    <label>Имя:&nbsp;</label><input type="text" name="firstName" value="<c:out value="${user.firstName}"/>"><br>
    <label>Фамилия:&nbsp;</label><input type="text" name="surname" value="<c:out value="${user.surname}"/>"><br>
    <label>Дата рождения:&nbsp;</label><input type="date" name="birthDate"
                                              value="<c:out value="${user.birthDate}"/>"><br>
    <label>Телефон:&nbsp;</label><input type="text" name="phone" value="<c:out value="${user.phone}"/>"><br>
    <label>Страна:&nbsp;</label><input type="text" name="country" value="<c:out value="${user.country}"/>"><br>
    <label>Город:&nbsp;</label><input type="text" name="city" value="<c:out value="${user.city}"/>"><br>
    <label>Улица:&nbsp;</label><input type="text" name="street" value="<c:out value="${user.street}"/>"><br>
    <label>Дом:&nbsp;</label><input type="text" name="house" value="<c:out value="${user.house}"/>"><br>
    <label>Корпус:&nbsp;</label><input type="text" name="block" value="<c:out value="${user.block}"/>"><br>
    <label>Квартира:&nbsp;</label><input type="text" name="apartment"
                                         value="<c:out value="${user.apartment}"/>"><br><br>
    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/user/info">Return</a><br>
</form>
</body>
</html>
