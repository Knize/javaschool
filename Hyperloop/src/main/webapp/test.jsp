<%@ page import="ru.knize.hyperloop.entities.PassengerEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: knize
  Date: 25.08.16
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Passengers list</title>
</head>
<body>
<%
    List<PassengerEntity> passengerEntityList = (List<PassengerEntity>) request.getAttribute("passengerEntityList");
    for (PassengerEntity pe : passengerEntityList) {
%>
<%= pe.getName() %>(<%= pe.getEmail()%>)<br>
<%
    }
%>

<h4>Add new passenger</h4>
<form method="POST" action="/test">
    <p><label>
        Passenger Email:
        <input name="email" type="email"/>
    </label></p>
    <p><label>
        Passenger Name:
        <input name="name" type="text">
    </label></p>
    <input type="submit">
</form>
</body>
</html>
