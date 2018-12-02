<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 16.06.2018
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course</title>
    <script type="text/javascript" src="js/jquery/jquery-latest.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.layout-latest.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/combo.js"></script>
    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">

<%--    <script type="text/javascript">
        history.pushState(null, null, 'main.jsp');
        window.addEventListener('popstate', function(event) {
            history.pushState(null, null, 'main.jsp');
        });
    </script>--%>

</head>
<body>
<%

/*if (session.getAttribute("login") == null) {
    response.sendRedirect("login.jsp");
} *//*else {
    response.sendRedirect("main.jsp");
}*/
%>
<div class="ui-layout-north">
    <h1 style="text-align: center">Course</h1>
    <div style="float: right">Welcome, ${login.name} ${login.surname}
    <a href="logout.jsp"><img src="images/logout.png" height="40" width="40"></a>
    </div>
</div>
<div class="ui-layout-west">
    <c:choose>
        <c:when test="${login.role.roleName eq 'ROLE_STUDENT'}">
            <input type="button" value="Teacher Data" id="teacherDataBtnId" class="btnDesign"  /> <br>
            <input type="button" value="Lesson Data" id="lessonDataBtnId" class="btnDesign" /> <br>
        </c:when>
        <c:when test="${login.role.roleName eq 'ROLE_TEACHER'}">
            <input type="button" value="Student Data" id="studentDataBtnId" class="btnDesign" /> <br>
            <input type="button" value="Lesson Data" id="lessonDataBtnId" class="btnDesign" /> <br>
            <input type="button" value="Schedule Data" id="scheduleDataBtnId" class="btnDesign" /> <br>
        </c:when>
        <c:when test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <input type="button" value="Student Data" id="studentDataBtnId" class="btnDesign" /> <br>
            <input type="button" value="Teacher Data" id="teacherDataBtnId" class="btnDesign"  /> <br>
            <input type="button" value="Lesson Data" id="lessonDataBtnId" class="btnDesign" /> <br>
            <input type="button" value="Schedule Data" id="scheduleDataBtnId" class="btnDesign" /> <br>
        </c:when>
    </c:choose>

</div>
<div class="ui-layout-center">

</div>

<div class="ui-layout-east">
    <input type="button" value="New" id="newBtnId" class="btnDesign1" />
    <input type="text" id="keywordId" placeholder="Search..." style="width: 62%;" />
    <input type="button" value="Search" id="searchBtnId" style="width: 32%" />
</div>

<div class="ui-layout-south">
    <div style="text-align: center">Copyright © Fuad Pashabeyli</div>
</div>

<div id="newStudentDialogId"></div>
<div id="newTeacherDialogId"></div>
<div id="newScheduleDialogId"></div>
<div id="editStudentDialogId"></div>
<div id="editScheduleDialogId"></div>
</body>
</html>