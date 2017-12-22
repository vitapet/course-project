<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<br>
<form action="${pageContext.request.contextPath}/root/users/update" method="post">
    <input type="hidden" name="id" value="${userObj.id}">
    <label>Логин:&nbsp;</label><input type="text" name="login" value="<c:out value="${userObj.login}"/>" disabled><br>
    <label>Пароль:&nbsp;</label><input type="text" name="password" value="<c:out value="${userObj.password}"/>"><br>
    <label>Роль:&nbsp;</label>
    <select name="userRole">
        <c:forEach items="${roleList}" var="role">
            <c:choose>
                <c:when test="${role eq userObj.role}">
                    <option selected><c:out value="${role.role}"/></option>
                </c:when>
                <c:otherwise>
                    <option><c:out value="${role.role}"/></option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select><br>
    <label>Статус:&nbsp;</label>
    <select name="userStatus" required>
        <c:forEach items="${statusList}" var="status">
            <c:choose>
                <c:when test="${status eq userObj.status}">
                    <option selected><c:out value="${status.status}"/></option>
                </c:when>
                <c:otherwise>
                    <option><c:out value="${status.status}"/></option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select><br>
    <label>Имя:&nbsp;</label><input type="text" name="firstName" value="<c:out value="${userObj.firstName}"/>"
                                    disabled><br>
    <label>Фамилия:&nbsp;</label><input type="text" name="surname" value="<c:out value="${userObj.surname}"/>"
                                        disabled><br>
    <label>Дата рождения:&nbsp;</label><input type="date" name="birthDate"
                                              value="<c:out value="${userObj.birthDate}"/>" disabled><br>
    <label>Телефон:&nbsp;</label><input type="text" name="phone" value="<c:out value="${userObj.phone}"/>" disabled><br>
    <label>Страна:&nbsp;</label><input type="text" name="country" value="<c:out value="${userObj.country}"/>" disabled><br>
    <label>Город:&nbsp;</label><input type="text" name="city" value="<c:out value="${userObj.city}"/>" disabled><br>
    <label>Улица:&nbsp;</label><input type="text" name="street" value="<c:out value="${userObj.street}"/>" disabled><br>
    <label>Дом:&nbsp;</label><input type="text" name="house" value="<c:out value="${userObj.house}"/>" disabled><br>
    <label>Корпус:&nbsp;</label><input type="text" name="block" value="<c:out value="${userObj.block}"/>" disabled><br>
    <label>Квартира:&nbsp;</label><input type="text" name="apartment"
                                         value="<c:out value="${userObj.apartment}"/>" disabled><br><br>
    <button type="submit">Save</button>
</form>
</body>
</html>
