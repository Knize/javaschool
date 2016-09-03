<%@ page import="ru.knize.hyperloop.entities.StationsGraphEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.knize.hyperloop.entities.StationEntity" %>
<%@ page import="ru.knize.hyperloop.entities.TimezoneEntity" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:cmsTemplate>
    <%
        List<TimezoneEntity> timezoneEntityList =
                (List<TimezoneEntity>) request.getAttribute("TimezonesList");
        for (TimezoneEntity te : timezoneEntityList) {%>
    <%=te.getTimezoneName() %><br>
    <%
        }
    %>
    <h4>Station adding</h4>
    <form method="post" action="cms/addStation">
        <div class="input-field">
            <select>
                <option value="" disabled selected>Choose branch</option>
                <option value="1">Berlin - Beijing</option>
                <option value="2">Saint-Petersburg - Kiev</option>
            </select>
            <label>Branch</label>
        </div>
        <p><label>
            Station name:
            <input name="stationName" type="text"/>
        </label></p>
        <input type="submit" class="btn">
    </form>
    <script>
        $(document).ready(function () {
            $('select').material_select();
        });
    </script>
</t:cmsTemplate>