<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 18.07.2018
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:out value="${scheduleList}"></c:out>--%>
<script type="text/javascript">
    $(function () {
       $('#scheduleTableId').DataTable();

        $( "#accordion" ).accordion({
            collapsible: true
        });

        $('.txtDesign').datepicker({
            changeMonth: true,
            changeYear: true
        });


            $('#advLessonComboId').change(function () {
                getTeacherComboByLessonId($(this).val());
            });

            $('#advSearchBtnId').click(function () {
                advancedSearchSchedule();
            });


    });
</script>

<div id="accordion">
    <h3>Advanced Search</h3>
    <div>
      <select class="cmbDesign" id="advLessonComboId">
           <option value="0" selected>Select Lesson</option>
          <c:forEach items="${lessonList}" var="ll">
             <option value="${ll.id}">${ll.lessonName}</option>
          </c:forEach>
      </select>
      <select class="cmbDesign" id="advTeacherComboId">
          <option value="0" selected>Select Teacher</option>
          <c:forEach items="${teacherList}" var="tl">
              <option value="${tl.id}">${tl.name} ${tl.surname}</option>
          </c:forEach>
      </select> <br>
      <input type="text" class="txtDesign" placeholder="Begin Date" id="advBeginDateId" />
        <input type="text" class="txtDesign" placeholder="End Date" id="advEndDateId" /> &nbsp;
        <input type="button" value="Search" id="advSearchBtnId">
    </div>

</div>

<div id="scheduleDivId">
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

</div>
