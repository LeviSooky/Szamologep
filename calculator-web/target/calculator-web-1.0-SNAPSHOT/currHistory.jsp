<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<jsp:include page="/CurrencyHistoryServlet"/>
<html>

<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

    <title>History</title>
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
                <a class="nav-link" href="history.jsp">History</a>
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Készítette: Soóky Levente Zsolt</a>
            </div>
        </div>
    </div>,
</nav>
<div>
    <table class="table table-dark table-borderless" >
        <thead>
        <tr>
            <th scope="col">mennyit</th>
            <th scope="col">mirol</th>
            <th scope="col">ans</th>
            <th scope="col">mire</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${requestScope.currLista}">
            <tr>
                <td>${item.mennyit}</td>
                <td>${item.mirol}</td>
                <td>${item.ans}</td>
                <td>${item.mire}</td>
            </tr>

        </c:forEach>


        </tbody>
    </table>
</div>

</body>


</html>