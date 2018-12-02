<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 12.08.2018
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<option value="0" selected>Select Teacher</option>
<c:forEach items="${teacherList}" var="tl">
    <option value="${tl.id}">${tl.name} ${tl.surname}</option>
</c:forEach>
