<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 04.08.2018
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#dobIdU').datepicker({
            changeMonth: true,
            changeYear: true
        })
    });
</script>

<div class="lblDesign">Name</div>
<input type="text" id="nameIdU" value="${student.name}"><br>
<div class="lblDesign">Surname</div>
<input type="text" id="surnameIdU" value="${student.surname}"><br>
<div class="lblDesign">Dob</div>
<input type="text" id="dobIdU" value="${student.dob}"><br>
<div class="lblDesign">Phone</div>
<input type="text" id="phoneIdU" value="${student.phone}"><br>
<div class="lblDesign">Email</div>
<input type="email" id="emailIdU" value="${student.email}"><br>
<div class="lblDesign">Address</div>
<input type="text" id="addressIdU" value="${student.address}"><br>
