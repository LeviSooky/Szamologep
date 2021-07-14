<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Currency</title>
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
                <a class="nav-link" href="currHistory.jsp">History</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Készítette: Soóky Levente Zsolt</a>
            </div>
        </div>
    </div>
</nav>
<form name="calculator" method="get" action="CurrServlet">
    <label for="from">Choose your currency:</label>
    <select name="from" id="from">
        <option value="eur">eur</option>
        <option value="huf">huf</option>
        <option value="usd">usd</option>
        <option value="gbp">gbp</option>
        <option value="cad">cad</option>
        <option value="bgn">bgn</option>
        <option value="egp">egp</option>
        <option value="cny">cny</option>
        <option value="chf">chf</option>
        <option value="jpy">jpy</option>
        <option value="mxn">mxn</option>
        <option value="aed">aed</option>
        <option value="afn">afn</option>
        <option value="all">all</option>
        <option value="amd">amd</option>
        <option value="ang">ang</option>
        <option value="aoa">aoa</option>
        <option value="ars">ars</option>
        <option value="aud">aud</option>
        <option value="awg">awg</option>
        <option value="azn">azn</option>
        <option value="bam">bam</option>
        <option value="bbd">bbd</option>
        <option value="bdt">bdt</option>
        <option value="czk">czk</option>
        <option value="sek">sek</option>
        <option value="rsd">rsd</option>
        <option value="rub">rub</option>
        <option value="pln">pln</option>
        <option value="ron">ron</option>
        <option value="dkk">dkk</option>
        <option value="try">try</option>

    </select>
    <label for="to">Choose the wanted currency:</label>
    <select name="to" id="to" >
        <option value="eur">eur</option>
        <option value="huf">huf</option>
        <option value="usd">usd</option>
        <option value="gbp">gbp</option>
        <option value="cad">cad</option>
        <option value="bgn">bgn</option>
        <option value="egp">egp</option>
        <option value="cny">cny</option>
        <option value="chf">chf</option>
        <option value="jpy">jpy</option>
        <option value="mxn">mxn</option>
        <option value="aed">aed</option>
        <option value="afn">afn</option>
        <option value="all">all</option>
        <option value="amd">amd</option>
        <option value="ang">ang</option>
        <option value="aoa">aoa</option>
        <option value="ars">ars</option>
        <option value="aud">aud</option>
        <option value="awg">awg</option>
        <option value="azn">azn</option>
        <option value="bam">bam</option>
        <option value="bbd">bbd</option>
        <option value="bdt">bdt</option>
        <option value="czk">czk</option>
        <option value="sek">sek</option>
        <option value="rsd">rsd</option>
        <option value="rub">rub</option>
        <option value="pln">pln</option>
        <option value="ron">ron</option>
        <option value="dkk">dkk</option>
        <option value="try">try</option>
    </select>
    <table>
        <tr>
            <td colspan="4">

                <input typeof="text" id="display" name="currResult" value="${requestScope.currResult}">

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
           <td><input type="submit" class="operator" name="DEL" value="DEL" ></td>

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