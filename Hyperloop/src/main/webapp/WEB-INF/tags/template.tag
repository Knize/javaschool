<%--
  Created by IntelliJ IDEA.
  User: knize
  Date: 25.08.16
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Main Template Tag" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Hyperloop</title>
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
                    <li><a href="/aboutUs">About us</a></li>
                    <li><a href="#">Yadya</a></li>
                    <li><a href="#">Buy tickets</a></li>
                    <li><a href="/cms">Enter cms</a></li>
                </ul>
            </div>
        </nav>
    </header>
    <main>
        <jsp:doBody/>
    </main>
    <footer class="page-footer">
        <div class="container">
            <div class="row">
                <div class="col l6 s12">
                    <h5 class="white-text">Hyperloop</h5>
                    <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer
                        content.</p>
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
</div>
</body>
</html>
