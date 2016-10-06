<%--
  Created by IntelliJ IDEA.
  User: knize
  Date: 31.08.16
  Time: 5:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="../../templates/head.jsp" %>
</head>
<body>
<%@include file="../../templates/scripts.jsp" %>

<%@include file="../../templates/header.jsp" %>
<div class="parallax-container">
    <div class="parallax"><img src="../../../img/hyperloopInMoscow.jpg"></div>
</div>
<div class="section white">
    <div class="row container">
        <h2 class="header">Parallax</h2>
        <p class="grey-text text-darken-3 lighten-3">
            Parallax is an effect where the background content or image in this case, is moved at a different speed
            than the foreground content while scrolling.
        </p>
    </div>
</div>
<div class="parallax-container">
    <div class="parallax"><img src="../../../img/bridge.jpg"></div>
</div>
<div class="section white">
    <div class="row container">
        <h2 class="header">Parallax</h2>
        <p class="grey-text text-darken-3 lighten-3">
            Parallax is an effect where the background content or image in this case, is moved at a different speed
            than the foreground content while scrolling.
        </p>
    </div>
</div>
<div class="parallax-container">
    <div class="parallax"><img src="../../../img/testCNBC.jpg"></div>
</div>
<script>
    $(document).ready(function () {
        $('.parallax').parallax();
    });
</script>

<%@include file="../../templates/footer.jsp" %>
