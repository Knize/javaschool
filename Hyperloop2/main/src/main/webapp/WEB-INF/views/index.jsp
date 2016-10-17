<%--
  Created by IntelliJ IDEA.
  User: knize
  Date: 25.08.16
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@include file="/WEB-INF/templates/head.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/templates/scripts.jsp" %>

<%@include file="/WEB-INF/templates/header.jsp" %>
<div class="page-flexbox-wrapper">
    <main>
        <div class="parallax-container">
            <div class="parallax"><img src="../../img/main1.png"></div>
        </div>
        <div class="section white">
            <div class="row container">
                <h2 class="header">Travel with hyperloop</h2>
                <p class="flow-text grey-text text-darken-3 lighten-3">
                    Hyperloop is a tradename and a registered trademark of the
                    Space Exploration Technologies Corporation (SpaceX) for the high speed transportation of passengers
                    and goods in tubes in which capsules are propelled by linear induction motors and air
                    compressors.
                </p>
                <p class="flow-text grey-text text-darken-3 lighten-3">
                    Recently there has been a resurgence in interest in pneumatic tube transportation systems since
                    being reintroduced, using updated technologies, by Elon Musk, incorporating reduced-pressure tubes
                    in which pressurized capsules ride on an air cushion driven by linear induction motors and air
                    compressors.
                </p>
                <p class="flow-text grey-text text-darken-3 lighten-3">
                    The outline of the original Hyperloop concept was made public by the release of a preliminary design
                    document in August 2013, which included a notional route running from the Los Angeles region to the
                    San Francisco Bay Area, paralleling the Interstate 5 corridor for most of its length. Preliminary
                    analysis indicated that such a route might obtain an expected journey departureTime of 35 minutes, meaning
                    that passengers would traverse the 350-mile (560 km) route at an average speed of around 600 mph
                    (970 km/h), with a top speed of 760 mph (1,200 km/h). Preliminary cost estimates for the LA–SF
                    notional route were included in the white paper—US$6 billion for a passenger-only version, and
                    US$7.5 billion for a somewhat larger-diameter version transporting passengers and vehicles —although
                    transportation analysts doubted that the system could be constructed on that budget.
                </p>
                <p class="flow-text grey-text text-darken-3 lighten-3">
                    Hyperloop technology has been explicitly open-sourced by Musk and SpaceX, and others have been
                    encouraged to take the ideas and further develop them. To that end, several companies have been
                    formed, and dozens of interdisciplinary student-led teams are working to advance the technology.
                </p>
                <p class="flow-text grey-text text-darken-3 lighten-3">
                    Designs for test tracks and capsules are currently being developed, with construction of a
                    full-scale prototype 5-mile (8 km) track scheduled to start in 2016. In addition, a subscale pod
                    design competition on a very short, 1 mile (1.6 km), test track was built in Nevada – the first
                    tests of the scale model occurred in May 2016.
                </p>
            </div>
        </div>
        <div class="parallax-container">
            <div class="parallax"><img src="../../img/green.jpg"></div>
        </div>
        <script>$(document).ready(function () {
            $('.parallax').parallax();
        });</script>
    </main>
</div>
<%@include file="/WEB-INF/templates/footer.jsp" %>
