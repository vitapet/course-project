<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<form action="${pageContext.request.contextPath}/${attrUrl}/news/<c:out value="${newsObj.id}"/>" method="post">
    <img src="${pageContext.request.contextPath}/image/<c:out value="${newsObj.id}"/>" name="track${newsObj.id}"
         alt="Loading " width="600" height="400"><br>
    <label>Дата публикации:&nbsp;</label>
    <c:out value="${newsObj.timestamp}"/><br>
    <c:out value="${newsObj.name}"/><br>
    <c:out value="${newsObj.description}"/><br>
    <label>Автор:&nbsp;</label>${newsObj.user.firstName}&nbsp;${newsObj.user.surname}<br>
    <br>
    <p>Комментарии</p>
    <c:forEach items="${newsObj.comments}" var="comment">
        <c:out value="${comment.user.login}"/>&nbsp;
        <c:out value="${comment.timestamp}"/>
        <button type="submit" class="btn btn-default" name="delete" value="${comment.id}">Delete</button>
        <br>
        <c:out value="${comment.text}"/><br><br>
    </c:forEach>
</form>
