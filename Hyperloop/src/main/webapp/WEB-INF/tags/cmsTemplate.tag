<%--
  Created by IntelliJ IDEA.
  User: knize
  Date: 25.08.16
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="CMS Template Tag" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Hyperloop CMS</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="<%= request.getContextPath() %>/css/materialize.css" rel="stylesheet" type="text/css"
          media="screen,projection"/>
    <link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css"
          media="screen,projection"/>
</head>

<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/bin/materialize.min.js"></script>
<div class="page-flexbox-wrapper">
    <header>
        <nav>
            <div class="nav-wrapper">
                <a href="/" class="brand-logo">Hyperloop</a>
                <ul id="nav-mobile" class="right hide-on-med-and-down">
                    <li><a href="/cms/addStation">Add station</a></li>
                    <li><a href="/cms/addTrain">Add train</a></li>
                    <li><a href="/cms/watchTrains">Watch trains</a></li>
                </ul>
            </div>
        </nav>
    </header>
    <main>
        <jsp:doBody/>
    </main>

</div>
</body>
</html>
