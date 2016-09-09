<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.CapsuleEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <%@include file="../templates/head.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/templates/scripts.jsp" %>
<%@include file="/WEB-INF/templates/cmsHeader.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <h2>Buy tickets</h2>
        <form action="/cms/watchTickets" method="get">
            <div class="row">
                <div class="input-field col s6">
                    <input name="capsule_number" placeholder="Enter capsule number" id="capsule_number" type="number" class="validate">
                    <label for="capsule_number">Capsule Number</label>
                </div>
                <input class="btn" type="submit">
            </div>
        </form>
        <script>
            $(document).ready(function () {
                $('select').material_select();
            });
        </script>



    </main>
</div>
</body>
<%@include file="/WEB-INF/templates/footer.jsp" %>
</html>
