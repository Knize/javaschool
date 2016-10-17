<%--
  Created by IntelliJ IDEA.
  User: knize
  Date: 03.09.16
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<link href="<%= request.getContextPath() %>/css/footer.css" rel="stylesheet" type="text/css"
      media="screen,projection"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="page-footer">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Travel with hyperloop</h5>
                <p class="grey-text text-lighten-4">
                    New way to travel fast, cheap and safe.
                </p>
            </div>
            <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                    <li><a class="grey-text text-lighten-3" href="/media">Media</a></li>
                    <li><a class="grey-text text-lighten-3" href="/aboutUs">About us</a></li>
                    <li><a class="grey-text text-lighten-3" href="/buyTickets">Buy tickets</a></li>
                    <li><a class="grey-text text-lighten-3" href="/schedule">Watch schedule</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2016 Copyright
            <a class="grey-text text-lighten-4 right" href="http://www.t-systems.ru/">Powered by T-Systems</a>
        </div>
    </div>
</footer>
