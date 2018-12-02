<%--
  Created by IntelliJ IDEA.
  User: Ideas.az
  Date: 18.08.2018
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   session.removeAttribute("login");
   session.invalidate();
   response.sendRedirect("login.jsp");
%>
