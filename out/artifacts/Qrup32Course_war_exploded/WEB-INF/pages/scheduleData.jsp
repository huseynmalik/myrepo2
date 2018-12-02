<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 12.08.2018
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
       $('#scheduleTableId').DataTable();
    });
</script>


<table id="scheduleTableId" class="display" style="width: 100%">
    <thead>
    <tr>
        <th>#</th>
        <th>Student Fullname</th>
        <th>Lesson Name</th>
        <th>Teacher Fullname</th>
        <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${scheduleList}" var="sl">
        <tr>
            <td>${sl.r}</td>
            <td>${sl.student.name} ${sl.student.surname}</td>
            <td>${sl.lesson.lessonName}</td>
            <td>${sl.teacher.name} ${sl.teacher.surname}</td>
            <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <td><a href="javascript: editSchedule('${sl.id}');">Edit</a></td>
            <td><a href="">Delete</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>