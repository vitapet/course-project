<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<img src="${pageContext.request.contextPath}/image?name=${news.image}" name="track${news.id}"
     alt="Loading ${news.image}" width="600" height="400"><br>
<label>Дата публикации:&nbsp;</label><c:out value="${news.timestamp}"/><br>
<c:out value="${news.name}"/><br>
<c:out value="${news.description}"/><br>
<label>Автор:&nbsp;</label><c:out value="${news.user.firstName}"/>&nbsp;<c:out
        value="${news.user.surname}"/><br>
<br>
<p>Комментарии</p>
<c:forEach items="${comments}" var="comment">
    <c:out value="${comment.user.login}"/>&nbsp;
    <c:out value="${comment.date}"/><br>
    <c:out value="${comment.text}"/><br><br>
</c:forEach>
