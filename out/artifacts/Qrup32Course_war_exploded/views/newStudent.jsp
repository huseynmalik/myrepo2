<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 21.07.2018
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
       $('#dobId').datepicker({
           changeMonth: true,
           changeYear: true
       })
    });
</script>

<div class="lblDesign">Name</div>
<input type="text" id="nameId"><br>
<div class="lblDesign">Surname</div>
<input type="text" id="surnameId"><br>
<div class="lblDesign">Dob</div>
<input type="text" id="dobId"><br>
<div class="lblDesign">Phone</div>
<input type="text" id="phoneId"><br>
<div class="lblDesign">Email</div>
<input type="email" id="emailId"><br>
<div class="lblDesign">Address</div>
<input type="text" id="addressId"><br>
