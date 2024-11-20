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
    <div class="card text-center col-12">
    <c:if test="${empty transacao}">
        <div class="card-header text-center">
            <h1>Registrar Transação</h1>
        </div>
        <form class="d-flex row p-2" action="transacao?acao=criar" method="POST">
    </c:if>
    <c:if test="${not empty transacao}">
        <div class="card-header text-center">
            <h1>Alterar Transação</h1>
        </div>
        <form class="d-flex row p-2" action="transacao?acao=alterar" method="POST">
    </c:if>
            <div class="mb-3 col-6">
                <label class="form-label">Categoria</label>
                <select class="form-select" aria-label="Default select example" name="transacaoCategoria">
                    <c:if test="${not empty transacao}">
                        <option value="${transacao.contaCategoria.codigoCategoria}" selected>${transacao.contaCategoria.nomeCategoria}</option>
                        <c:forEach items="${categorias}" var="categoria">
                            <option value="${categoria.codigoCategoria}">${categoria.nomeCategoria}</option>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty transacao}">
                        <c:forEach items="${categorias}" var="categoria" varStatus="loop">
                            <c:if test="${loop.first}">
                                <option value="${categoria.codigoCategoria}" selected>${categoria.nomeCategoria}</option>
                            </c:if>
                            <c:if test="${not loop.first}">
                                <option value="${categoria.codigoCategoria}">${categoria.nomeCategoria}</option>
                            </c:if>
                        </c:forEach>
                    </c:if>

                </select>
            </div>
            <div class="mb-3 col-6">
                <label for="transacaoValor" class="form-label">Valor</label>
                <input type="number" class="form-control" id="transacaoValor" name="transacaoValor" value="${transacao.vlr_transacao}" step="0.01" required>
            </div>
            <div class="mb-3 col-6">
                <label for="dataDataTransacao" class="form-label">Data/Hora</label>
                <c:if test="${empty transacao}">
                    <input type="datetime-local" class="form-control" id="dataDataTransacao" name="dataTransacao" value="${dataAtual}">
                    <input type="hidden" name="codigoConta" value="${codigoConta}">
                </c:if>
                <c:if test="${not empty transacao}">
                    <input type="datetime-local" class="form-control" id="dataDataTransacao" name="dataTransacao" value="${transacao.dt_transacao}">
                    <input type="hidden" name="codigoConta" value="${transacao.codConta}">
                    <input type="hidden" name="codTransacao" value="${transacao.codTransacao}">
                </c:if>

            </div>
            <div class="mb-3 col-6">
                <label class="form-label">Tipo</label>
                <select class="form-select" aria-label="Default select example" name="tipoTransacao">
                    <c:if test="${not empty transacao}">
                        <option value="${transacao.tipo_transacao}" selected>${transacao.tipo_transacao}</option>
                        <option value="DESPESA">DESPESA</option>
                        <option value="RECEITA">RECEITA</option>
                        <option value="INVESTIMENTO">INVESTIMENTO</option>
                    </c:if>
                    <c:if test="${empty transacao}">
                        <option value="DESPESA" selected>DESPESA</option>
                        <option value="RECEITA">RECEITA</option>
                        <option value="INVESTIMENTO">INVESTIMENTO</option>
                    </c:if>

                </select>
            </div>
            <div class="mb-3 col-12">
                <a href="transacao?codigoConta=${codigoConta}" class="btn btn-danger">Cancelar</a>
            <c:if test="${empty transacao}">
                <button type="submit" class="btn btn-success">Criar</button>
            </c:if>
            <c:if test="${not empty transacao}">
                <button type="submit" class="btn btn-success">Alterar</button>
            </c:if>
            </div>
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