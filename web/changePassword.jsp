<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 19.08.2018
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 18.08.2018
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Change Password</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="loginTemplate/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginTemplate/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginTemplate/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginTemplate/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginTemplate/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginTemplate/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="loginTemplate/css/util.css">
    <link rel="stylesheet" type="text/css" href="loginTemplate/css/main.css">

    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->

    <%--   <script type="text/javascript">
           history.pushState(null, null, 'login.jsp');
           window.addEventListener('popstate', function(event) {
               history.pushState(null, null, 'login.jsp');
           });
       </script>--%>

</head>
<body>
<%

    if (session.getAttribute("login") != null) {
        response.sendRedirect("main.jsp");
    }
%>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="loginTemplate/images/img-01.png" alt="IMG">
            </div>

            <form action="cs?action=changePassword" method="post" class="login100-form validate-form">
					<span class="login100-form-title">
						Change Password
					</span>

                <div class="wrap-input100 validate-input" >
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                </div>
                <input type="hidden" name="token" value="${token}">

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">
                        Change Password
                    </button>
                </div>



                <div class="text-center p-t-136">
                    <a class="txt2" href="login.jsp">
                        Sign In
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>




<!--===============================================================================================-->
<script src="loginTemplate/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="loginTemplate/vendor/bootstrap/js/popper.js"></script>
<script src="loginTemplate/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="loginTemplate/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="loginTemplate/vendor/tilt/tilt.jquery.min.js"></script>
<script >
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>





