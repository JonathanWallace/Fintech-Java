<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Projeto Fintech</title>

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet" />

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- Bootstrap CSS-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />

    <!-- CSS Local-->
    <link rel="stylesheet" href="css/style.css" />
</head>

<body>
<header>
    <!-- Include do header contendo barra de navegação -->
    <%@include file="header.jsp"%>
</header>
<section class="container my-5" id="form-contas">
    <div class="card text-center p-2">
    <c:if test="${empty conta}">
        <div class="card-header text-center">
            <h1>Criar Conta</h1>
        </div>
        <form action="conta?acao=criar" method="POST">
    </c:if>
    <c:if test="${not empty conta}">
        <div class="card-header text-center">
            <h1>Alterar Conta</h1>
        </div>
        <form action="conta?acao=alterarCommit" method="POST">
    </c:if>
        <div class="mb-3">
            <label for="nomeConta" class="form-label">Nome da Conta</label>
            <input type="text" class="form-control" id="nomeConta" name="nomeConta" value="${conta.nomeConta}">
            <input type="hidden" name="codigoConta" value="${conta.codigoConta}">
        </div>
        <label class="form-label">Tipo</label>
        <select class="form-select mb-3" aria-label="Default select example" name="tipoConta">
            <option value="${conta.tipoConta}" selected>${conta.tipoConta}</option>
            <option value="CONTA CORRENTE">Conta-Corrente</option>
            <option value="POUPANÇA">Poupança</option>
            <option value="INVESTIMENTO">Investimentos</option>
        </select>
        <a href="conta" class="btn btn-danger">Cancelar</a>
        <c:if test="${empty conta}">
            <button type="submit" class="btn btn-success">Criar</button>
        </c:if>
        <c:if test="${not empty conta}">
            <button type="submit" class="btn btn-success">Alterar</button>
        </c:if>

    </form>
    </div>
</section>
<!-- Include das modals -->
<%@include file="WEB-INF/modal/modalRegistro.jsp"%>
<%@include file="WEB-INF/modal/modalLogin.jsp"%>

<footer class="container-fluid bg-blue-2 mt-5" id="contato">
    <%@include file="footer.jsp"%>
</footer>

<!-- Bootstrap JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>