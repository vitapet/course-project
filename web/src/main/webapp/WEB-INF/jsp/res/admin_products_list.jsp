<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:if test="${not empty message}">
    <p><c:out value="${message}"/></p>
    <c:remove var="message" scope="session"/>
</c:if>
<a href="${pageContext.request.contextPath}/${attrUrl}/catalog/add">Add</a><br>
<br>
<p>${curCategory}</p>

<c:forEach items="${categories}" var="category">
    <a name="id" href="${pageContext.request.contextPath}/${attrUrl}/catalog/<c:out value="${category}"/>"><c:out
            value="${category}"/></a>
</c:forEach>
<c:set var="categoryAll" value="all"/>
<a name="id" href="${pageContext.request.contextPath}/${attrUrl}/catalog/<c:out value="${categoryAll}"/>">All</a><br>
<input type="hidden" name="curCategory" value="<c:out value="${curCategory}"/>"/>
<c:choose>
    <c:when test="${empty products}">
        <p>There are no goods at this category.</p>
    </c:when>
    <c:otherwise>
        <c:forEach items="${products}" var="product">
            <c:set var="tempProduct" value="${product}" scope="request"/>
            <jsp:include page="../res/product_info.jsp"/>
            <label>Status&nbsp;</label><c:out value="${product.status}"/><br>
            <input type="hidden" name="copiedId" value="${product.id}">
            <a href="${pageContext.request.contextPath}/${attrUrl}/catalog/<c:out value="${product.id}"/>/update">Update</a>
            <a href="${pageContext.request.contextPath}/${attrUrl}/catalog/<c:out value="${product.id}"/>/delete">Delete</a>
            <a href="${pageContext.request.contextPath}/${attrUrl}/catalog/<c:out value="${product.id}"/>/copy">Copy</a><br>
            <br>
        </c:forEach>
    </c:otherwise>
</c:choose>
