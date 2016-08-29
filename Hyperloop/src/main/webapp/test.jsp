<%@ page import="ru.knize.hyperloop.entities.PersonEntity" %>
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
    <title>Persons list</title>
    <link href="css/materialize.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <meta charset="utf-8">
</head>
<body>
<%
    List<PersonEntity> personEntityList = (List<PersonEntity>) request.getAttribute("PersonEntityList");
    for (PersonEntity pe : personEntityList) {
%>
<%= pe.getName() %><br>
<%
    }
%>

<h4>Add new Person</h4>
<form method="POST" action="/test" accept-charset="UTF-8">
    <p><label>
        Person Email redeploy enabled:
        <input name="email" type="email"/>
    </label></p>
    <p><label>
        Person Name:
        <input name="name" type="text">
    </label></p>
    <input type="submit" class="btn">
</form>
</body>
</html>
