<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

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
<c:if test="${not empty message}">
    <div class="alert alert-success text-center" role="alert">
            ${message}
    </div>
</c:if>
<c:if test="${not empty erro}">
    <div class="alert alert-danger text-center" role="alert">
            ${erro}
    </div>
</c:if>
<section class="container my-5" id="contas">
    <div class="card">
        <div class="card-header text-center">
            <h1>Contas</h1>
        </div>
            <div class="card-body">
            <table class="table table-striped table-hover">
                <thead>
                    <tr class="text-center">
                        <th scope="col">Nome da Conta</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Saldo</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                    <tbody>
                    <c:forEach items="${contas}" var="conta">
                        <tr class="text-center text-uppercase fw-bold">
                            <td style="vertical-align: middle;">
                                <a class="nav-link" href="transacao?acao=listar&codigoConta=${conta.codigoConta}">${conta.nomeConta}</a>
                            </td>
                            <td style="vertical-align: middle;">
                                ${conta.tipoConta}
                            </td>
                            <td style="vertical-align: middle;">
                                <fmt:formatNumber value="${conta.saldoConta}" pattern="¤##,###,##0.00" type="currency" currencySymbol="R$" />
                            </td>
                            <td style="vertical-align: middle;">
                                <a href="transacao?acao=listar&codigoConta=${conta.codigoConta}" class="btn btn-success" title="Listar transações da conta"><i class="fa-solid fa-scroll"></i></a>
                                <a href="conta?acao=alterarLoad&codigoConta=${conta.codigoConta}" class="btn btn-warning" title="Alterar dados da conta"><i class="fa-solid fa-pen-to-square"></i></a>
                                <a href="conta?acao=excluir&id=${conta.codigoConta}" class="btn btn-danger" title="Excluir conta"><i class="fa-solid fa-trash"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
            </table>
            <div class="text-center">
                <a href="form-contas.jsp" class="btn btn-primary">Nova Conta</a>
            </div>
        </div>
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