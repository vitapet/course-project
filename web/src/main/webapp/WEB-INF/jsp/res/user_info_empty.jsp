<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<p>Персональные данные</p>
<label>Логин:&nbsp;</label><input type="text" name="login" required value="<c:out value="${userObj.login}"/>"><br>
<label>Пароль:&nbsp;</label><input type="text" name="password" required
                                   value="<c:out value="${userObj.password}"/>"><br>
<label>Имя:&nbsp;</label><input type="text" name="firstName" required value="<c:out value="${userObj.firstName}"/>"><br>
<label>Фамилия:&nbsp;</label><input type="text" name="surname" required value="<c:out value="${userObj.surname}"/>"><br>
<label>Дата рождения:&nbsp;</label><input type="date" input name="birthDate" required
                                          value="<c:out value="${userObj.birthDate}"/>"><br>
<label>Телефон:&nbsp;</label><input type="text" name="phone" required value="<c:out value="${userObj.phone}"/>"><br>
<label>Страна:&nbsp;</label><input type="text" name="country" required value="<c:out value="${userObj.country}"/>"><br>
<label>Город:&nbsp;</label><input type="text" name="city" required value="<c:out value="${userObj.city}"/>"><br>
<label>Улица:&nbsp;</label><input type="text" name="street" required value="<c:out value="${userObj.street}"/>"><br>
<label>Дом:&nbsp;</label><input type="text" name="house" required value="<c:out value="${userObj.house}"/>"><br>
<label>Корпус:&nbsp;</label><input type="text" name="block" required value="<c:out value="${userObj.block}"/>"><br>
<label>Квартира:&nbsp;</label><input type="text" name="apartment" required
                                     value="<c:out value="${userObj.apartment}"/>"><br>
<br>
