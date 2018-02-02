<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<form:form method="post" action="${pageContext.request.contextPath}/${attrUrl}/news/${news.id}/edit"
           enctype="multipart/form-data" modelAttribute="news">
    <img src="${pageContext.request.contextPath}/image/<c:out value="${news.id}"/>" name="track${news.id}"
         alt="Loading ${news.id}" width="600" height="400"><br>
    <form:hidden path="id"/>
    <form:hidden path="timestamp"/>
    <table>
        <tr>
            <td>Фото</td>
            <td><form:input path="file" type="file"/></td>
            <td><form:errors path="file"/></td>
        </tr>
        <tr>
            <td>Заголовок</td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name"/></td>
        </tr>
        <tr>
            <td>Текст новости</td>
            <td><form:input path="description"/></td>
            <td><form:errors path="description"/></td>
        </tr>
        <tr>
            <td>Автор</td>
            <td><form:input path="author"/></td>
            <td><form:errors path="author"/></td>
        </tr>
    </table>
    <button type="submit">Save</button>
</form:form>
