<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="hu" >
<head>
    <meta charset="UTF-8">
    <title>Unit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link href="styles.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">Calculator</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="currency.jsp">Currency</a>
                <a class="nav-link" href="unit.jsp">Unit</a>
                <a class="nav-link" href="unitHistory.jsp">History</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Készítette: Soóky Levente Zsolt</a>
            </div>
        </div>
    </div>
</nav>

<form name="calculator" method="get" action="UnitServlet">
    <label for="from">Choose your unit:</label>
    <select name="from" id="from">
        <option value="C°">C°</option>
        <option value="F°">F°</option>
        <option value="mg">mg</option>
        <option value="g">g</option>
        <option value="dkg">dkg</option>
        <option value="kg">kg</option>
        <option value="t">t</option>
        <option value="hex">hex</option>
        <option value="dec">dec</option>
        <option value="bin">bin</option>
        <option value="oct">oct</option>
    </select>
    <label for="to">Choose the wanted unit:</label>
    <select name="to" id="to" >
        <option value="C°">C°</option>
        <option value="F°">F°</option>
        <option value="mg">mg</option>
        <option value="g">g</option>
        <option value="dkg">dkg</option>
        <option value="kg">kg</option>
        <option value="t">t</option>
        <option value="hex">hex</option>
        <option value="dec">dec</option>
        <option value="bin">bin</option>
        <option value="oct">oct</option>
    </select>
    <table>
        <tr>
            <td colspan="4">

                <input typeof="text" id="display" name="currResult" value="${requestScope.unitResult}">

            </td>
        </tr>
        <tr>
            <td><input type="submit" name="1" value="1">
            <td><input type="submit" name="2" value="2">
            <td><input type="submit" name="3" value="3">
            <td><input type="submit" class="operator" name="ANS" value="ANS">
        </tr>
        <tr>
            <td><input type="submit" name="4" value="4">
            <td><input type="submit" name="5" value="5">
            <td><input type="submit" name="6" value="6">
          <td> <input type="submit" class="operator" name="DEL" value="DEL" ></td>

        </tr>
        <tr>
            <td><input type="submit" name="7" value="7">
            <td><input type="submit" name="8" value="8">
            <td><input type="submit" name="9" value="9">
            <td><input type="submit" class="operator" name="." value="."></td>

        </tr>
        <tr>
            <td><input type="submit" id="clear" name="C" value="C">
            <td><input type="submit" name="0" value="0">
            <td><input type="submit" name="=" value="=">

        </tr>
    </table>
</form>
</body>

</html>