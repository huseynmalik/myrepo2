<%@ page import="az.orient.course.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 14.07.2018
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#studentTableId').DataTable();
    });
</script>

<% List<Student> studentList = (List<Student>) request.getAttribute("studentList");%>

<table id="studentTableId" class="display" style="width: 100%">
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Address</th>
        <th>Date of birth</th>
        <th>Phone</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
 <tbody>
 <% for (Student student: studentList) { %>
 <tr>
     <td><%= student.getR()%></td>
     <td><%= student.getName()%></td>
     <td><%= student.getSurname()%></td>
     <td><%= student.getAddress()%></td>
     <td><%= student.getDob()%></td>
     <td><%= student.getPhone()%></td>
     <td><a href="javascript: editStudent('<%= student.getId()%>');">Edit</a></td>
     <td><a href="javascript: deleteStudent('<%= student.getId()%>','<%= student.getName()%>','<%= student.getSurname()%>');">Delete</a></td>
 </tr>
 <% } %>
 </tbody>

</table>

