<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<table>
    <tr>
        <td><label>Номер:&nbsp;</label></td>
        <td><c:out value="${tempProduct.id}"/></td>
    </tr>
    <tr>
        <td><label>Товар:&nbsp;</label></td>
        <td><c:out value="${tempProduct.name}"/></td>
    </tr>
    <tr>
        <td><label>Описание:&nbsp;</label></td>
        <td><c:out value="${tempProduct.description}"/></td>
    </tr>
    <tr>
        <td><label>Категория:&nbsp;</label></td>
        <td><c:out value="${tempProduct.category}"/></td>
    </tr>
    <tr>
        <td><label>Количество:&nbsp;</label></td>
        <td><c:out value="${tempProduct.count}"/></td>
    </tr>
    <tr>
        <td><label>Цена:&nbsp;</label></td>
        <td><c:out value="${tempProduct.price}"/></td>
    </tr>
</table>