<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 05.08.2018
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="lblDesign">Student: </div><select class="cmbDesign" id="studentComboIdU">
    <option value=""  disabled selected>Select Student</option>
    <c:forEach items="${studentList}" var="sl">
        <option value="${sl.id}">${sl.name} ${sl.surname}</option>
    </c:forEach>
</select> <br>
<div class="lblDesign">Teacher: </div><select class="cmbDesign" id="teacherComboIdU">
    <option value=""  disabled selected>Select Teacher</option>
    <c:forEach items="${teacherList}" var="tl">
        <option value="${tl.id}">${tl.name} ${tl.surname}</option>
    </c:forEach>
</select> <br>
<div class="lblDesign">Lesson: </div><select class="cmbDesign" id="lessonComboIdU">
    <option value=""  disabled selected>Select Lesson</option>
    <c:forEach items="${lessonList}" var="ls">
        <option value="${ls.id}">${ls.lessonName}</option>
    </c:forEach>
</select>

<script type="text/javascript">
    $(function () {
        $('#studentComboIdU').val('${schedule.student.id}');
        $('#teacherComboIdU').val('${schedule.teacher.id}');
        $('#lessonComboIdU').val('${schedule.lesson.id}');
    });
</script>
