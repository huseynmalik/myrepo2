<%@ page import="az.orient.course.model.Teacher" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 18.07.2018
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#teacherTableId').DataTable();
    });
</script>

<% List<Teacher> teacherList = (List<Teacher>) request.getAttribute("teacherList");%>

<table id="teacherTableId" class="display" style="width: 100%">
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Address</th>
        <th>Date of birth</th>
        <th>Phone</th>
    </tr>
    </thead>
    <tbody>
    <% for (Teacher teacher: teacherList) { %>
    <tr>
        <td><%= teacher.getR()%></td>
        <td><%= teacher.getName()%></td>
        <td><%= teacher.getSurname()%></td>
        <td><%= teacher.getAddress()%></td>
        <td><%= teacher.getDob()%></td>
        <td><%= teacher.getPhone()%></td>
    </tr>
    <% } %>
    </tbody>

</table>
