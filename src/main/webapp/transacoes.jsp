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
<section class="container my-5" id="transacoes">
    <div class="card p-2">
        <div class="card-header text-center">
            <h1>Transações</h1>
        </div>
            <table class="table table-striped table-hover text-center">
                <thead>
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Categoria</th>
                        <th scope="col">Data/Hora</th>
                        <th scope="col">Valor</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Ações</th>
                    </tr>
                </thead>
                    <tbody>
                    <c:forEach items="${transacoes}" var="transacao">
                        <tr>
                            <td style="vertical-align: middle;" class="text-center">${transacao.codTransacao}</td>
                            <td style="vertical-align: middle;">${transacao.contaCategoria.nomeCategoria}</td>
                            <td style="vertical-align: middle;"><fmt:formatDate value="${transacao.dt_transacao}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                            <c:if test="${transacao.tipo_transacao ne 'DESPESA'}">
                                <td style="vertical-align: middle;" class="text-success"><fmt:formatNumber value="${transacao.vlr_transacao}" pattern="¤##,###,##0.00" type="currency" currencySymbol="R$" /></td>
                            </c:if>
                            <c:if test="${transacao.tipo_transacao eq 'DESPESA'}">
                                <td style="vertical-align: middle;" class="text-danger"><fmt:formatNumber value="${transacao.vlr_transacao}" pattern="¤##,###,##0.00" type="currency" currencySymbol="R$" /></td>
                            </c:if>
                            <td style="vertical-align: middle;">${transacao.tipo_transacao}</td>
                            <td style="vertical-align: middle;">
                                <a href="transacao?acao=form&codTransacao=${transacao.codTransacao}&codigoConta=${transacao.codConta}" class="btn btn-warning" title="Alterar transação"><i class="fa-solid fa-pen-to-square"></i></a>
                                <a href="transacao?acao=excluir&codTransacao=${transacao.codTransacao}&codigoConta=${transacao.codConta}" class="btn btn-danger" title="Excluir transação"><i class="fa-solid fa-trash"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
            </table>
            <div class="text-center">
                <a href="conta" class="btn btn-danger">Voltar</a>
                <a href="transacao?acao=form&codigoConta=${codigoConta}" class="btn btn-primary">Registrar Nova Transação</a>
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