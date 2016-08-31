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
    <title>Persons list</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="css/materialize.css" rel="stylesheet" type="text/css" media="screen,projection"/>
</head>

<body>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/bin/materialize.min.js"></script>
<nav>
    <div class="nav-wrapper">
        <a href="test.jsp" class="brand-logo">Hyperloop</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="../cms/addStation.jsp">Add station</a></li>
            <li><a href="../cms/addTrain.jsp">Add train</a></li>
            <li><a href="../cms/watchTrains.jsp">Watch trains</a></li>
        </ul>
    </div>
</nav>
<jsp:doBody/>
<footer class="page-footer">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Hyperloop</h5>
                <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 1</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 2</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>
                    <li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2016 Copyright
            <a class="grey-text text-lighten-4 right" href="#!">Powered by T-Systems</a>
        </div>
    </div>
</footer>
</body>
</html>
