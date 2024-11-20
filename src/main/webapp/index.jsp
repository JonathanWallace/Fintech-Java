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

<!-- Informação do Dashboard -->
<c:if test="${not empty erro}">
    <div class="alert alert-danger text-center" role="alert">
        ${erro}
    </div>
</c:if>
<c:if test="${not empty message}">
    <div class="alert alert-success text-center" role="alert">
            ${message}
    </div>
</c:if>

<section class="container mt-5 sobre" id="sobre">
    <div class="row d-flex justify-content-center align-items-center">
        <div class="col-lg-6">
            <h2 class="my-title display-5 my-4">Dashboard</h2>
            <h3 class="mb-4">Veja informações e resumos de Receitas, Gastos e Investimentos em tempo real.</h3>
            <c:if test="${not empty id_usuario}">
                <a href="conta" class="btn btn-primary btn-lg">Acessar Contas <i class="fa-solid fa-arrow-right-long ms-1"></i></a>
            </c:if>
            <c:if test="${empty id_usuario}">
                <a href="conta" class="btn btn-primary btn-lg" aria-current="page" data-bs-toggle="modal" data-bs-target="#regLogin" href="#">Realizar login <i class="fa-solid fa-right-to-bracket"></i></a>
            </c:if>
        </div>
        <div class="col-lg-6 text-center mt-3">
            <img src="images/dashboard.png" alt="Imagem" class="img-fluid rounded-5">
        </div>
    </div>
</section>
<!-- Fim da Informação do Dashboard -->
<c:if test="${not empty id_usuario}">
<!-- Tipos de Registros -->
<section class="container my-5" id="solucoes">
    <h3 class="my-title text-center my-mt">Movimentações</h3>
    <h4 class="text-center my-title mb-4">Escolha uma opção para listar suas movimentações</h4>
    <div class="row d-flex align-items-center justify-items-center">
        <div class="col-lg-4 my-2">
            <div class="card text-center p-4">
                <i class="fa-solid fa-wallet icon"></i>
                <div class="card-body">
                    <h3 class="card-title">Receita</h3>
                    <p class="card-text">
                        Aqui você informa os valores que você recebeu. Pense em salários, trabalhos extras, vendas, presentes em dinheiro, etc. Essa função te ajuda a acompanhar o dinheiro que entrou na sua conta e entender de onde veio a sua riqueza.
                    </p>
                    <a href="#" class="btn btn-primary">Listar Receitas</a>
                </div>
            </div>
        </div>
        <div class="col-lg-4 my-2">
            <div class="card text-center p-4">
                <i class="fa-solid fa-sack-dollar icon"></i>
                <div class="card-body">
                    <h3 class="card-title">Gasto</h3>
                    <p class="card-text">
                        Aqui você anota tudo o que você gastou. Supermercado, restaurantes, transporte, contas, compras online... Todos os seus gastos devem ser registrados para que você tenha controle sobre para onde seu dinheiro está indo.
                    </p>
                    <a href="#" class="btn btn-primary">Listar Gastos</a>
                </div>
            </div>
        </div>
        <div class="col-lg-4 my-2">
            <div class="card text-center p-4">
                <i class="fa-solid fa-piggy-bank icon"></i>
                <div class="card-body">
                    <h3 class="card-title">Investimentos</h3>
                    <p class="card-text">
                        Aqui você acompanha o dinheiro que você aplicou para gerar um retorno futuro. Fundos de investimento, ações, imóveis, etc. Registrar seus investimentos te permite acompanhar o seu crescimento e tomar decisões mais inteligentes sobre seu dinheiro.
                    </p>
                    <a href="#" class="btn btn-primary">Listar Investimentos</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Fim do Tipos de Registros -->
</c:if>
<!-- Include das modals -->
<%@include file="WEB-INF/modal/modalRegistro.jsp"%>
<%@include file="WEB-INF/modal/modalLogin.jsp"%>
<%@include file="WEB-INF/modal/modalCadastro.jsp"%>

<footer class="container-fluid bg-blue-2 mt-5" id="contato">
    <%@include file="footer.jsp"%>
</footer>

<!-- Bootstrap JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>