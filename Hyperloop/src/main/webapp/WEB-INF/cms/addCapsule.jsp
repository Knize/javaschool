
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:cmsTemplate>
    <div class="container">
        <h4>Station adding</h4>
        <table class="responsive-table centered">
            <thead>
            <tr>
                <th data-field="branch_index">Branch</th>
                <th data-field="station_index">Station Index</th>
                <th data-field="station_name">Station Name</th>
                <th data-field="timezone">Time zone</th>
                <th data-field="range">Range to next</th>
                <th data-field="buttons">Delete/add</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>1</td>
                <td>Berlin</td>
                <td>CET - UTC +2</td>
                <td>0</td>
                <td><a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">clear</i></a></td>
            </tr>
            <tr>
                <td>1</td>
                <td>2</td>
                <td>Moscow</td>
                <td>MSK - UTC +3</td>
                <td>1620</td>
                <td><a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">clear</i></a></td>
            </tr>
            <tr>
                <td>1</td>
                <td>3</td>
                <td>Samara</td>
                <td>SAMT - UTC +4</td>
                <td>860</td>
                <td><a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">clear</i></a></td>
            </tr>
            <tr>
                <td>1</td>
                <td>4</td>
                <td>Astana</td>
                <td>EKST - UTC +6</td>
                <td>1470</td>
                <td><a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">clear</i></a></td>
            </tr>
            <tr>
                <td>1</td>
                <td>5</td>
                <td>Novosibirsk</td>
                <td>KRAT - UTC +7</td>
                <td>890</td>
                <td><a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">clear</i></a></td>
            </tr>
            <tr>
                <td>1</td>
                <td>6</td>
                <td>Ulaanbaatar</td>
                <td>CST - UTC +8</td>
                <td>1820</td>
                <td><a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">clear</i></a></td>
            </tr>
            <tr>
                <td>1</td>
                <td>7</td>
                <td>Beijing</td>
                <td>CST - UTC +8</td>
                <td>2550</td>
                <td><a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">clear</i></a></td>
            </tr>
            <tr>
                <form action="">
                    <td><input type="text"></td>
                    <td><input type="text"></td>
                    <td><input type="text"></td>
                    <td><input type="text"></td>
                    <td><input type="text"></td>
                    <td><a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a></td>
                </form>
            </tr>
            </tbody>
        </table>
    </div>
</t:cmsTemplate>