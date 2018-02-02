<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <title>Add news</title>
</head>
<body>
<header>
    <jsp:include page="../res/menu.jsp"/>
</header>
<c:set var="attrUrl" value="${url}" scope="request"/>
<a href="${pageContext.request.contextPath}/${attrUrl}/news">Return</a><br>
<jsp:include page="../res/admin_news_input_form.jsp"/>
</body>
</html>
