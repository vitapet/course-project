<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<label>Номер:&nbsp;</label><c:out value="${tempGoods.id}"/><br>
<label>Товар:&nbsp;</label><c:out value="${tempGoods.name}"/><br>
<label>Описание:&nbsp;</label><c:out value="${tempGoods.description}"/><br>
<label>Категория:&nbsp;</label><c:out value="${tempGoods.category}"/><br>
<label>Количество:&nbsp;</label><c:out value="${tempGoods.count}"/><br>
<label>Цена:&nbsp;</label><c:out value="${tempGoods.price}"/><br>
