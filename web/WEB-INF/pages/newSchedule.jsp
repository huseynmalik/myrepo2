<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 04.08.2018
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="lblDesign">Student: </div><select class="cmbDesign" id="studentComboId">
    <option value="" selected disabled>Select Student</option>
 <c:forEach items="${studentList}" var="sl">
      <option value="${sl.id}">${sl.name} ${sl.surname}</option>
 </c:forEach>
</select> <br>
<div class="lblDesign">Teacher: </div><select class="cmbDesign" id="teacherComboId">
    <option value="" selected disabled>Select Teacher</option>
    <c:forEach items="${teacherList}" var="tl">
        <option value="${tl.id}">${tl.name} ${tl.surname}</option>
    </c:forEach>
</select> <br>
<div class="lblDesign">Lesson: </div><select class="cmbDesign" id="lessonComboId">
    <option value="" selected disabled>Select Lesson</option>
    <c:forEach items="${lessonList}" var="ls">
        <option value="${ls.id}">${ls.lessonName}</option>
    </c:forEach>
</select>