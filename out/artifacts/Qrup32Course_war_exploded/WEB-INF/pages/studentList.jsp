<%@ page import="az.orient.course.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 08.07.2018
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course</title>
    <script type="text/javascript" src="js/main.js"></script>
    <link rel="stylesheet" type="text/css" href="css/layout.css">


</head>
<body>
<div id="container">
    <div id="header">
        <h1 style="margin-bottom:0;">Course</h1>
    </div>
    <div id="menu">
        <%--<input type="button" value="Student Data" id="studentDataBtnId" class="btnDesign" onclick="alert('ok');" /> <br>
        <input type="button" value="Teacher Data" id="teacherDataBtnId" class="btnDesign" onclick="testFunc(45,25);" /> <br>
        <input type="button" value="Lesson Data" id="lessonDataBtnId" class="btnDesign" /> <br>
        <input type="button" value="Schedule Data" id="scheduleDataBtnId" class="btnDesign" /> <br>--%>
            <a class="button" href="cs?action=getStudentList">Student Data</a> <br>
            <a class="button" href="cs?action=getTeacherList">Teacher Data</a> <br>
    </div>
    <div id="content">


        <% List<Student> studentList = (List<Student>) request.getAttribute("studentList");%>

        <table id="studentTableId" border="1" style="width: 100%">
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Address</th>
                <th>Date of birth</th>
                <th>Phone</th>
            </tr>
            <% for (Student student: studentList) { %>
            <tr>
                <td><%= student.getId()%></td>
                <td><%= student.getName()%></td>
                <td><%= student.getSurname()%></td>
                <td><%= student.getAddress()%></td>
                <td><%= student.getDob()%></td>
                <td><%= student.getPhone()%></td>
            </tr>
            <% } %>
        </table>

    </div>
    <div id="footer">
        Copyright © Fuad Pashabeyli
    </div> </div>

</body>
</html>
