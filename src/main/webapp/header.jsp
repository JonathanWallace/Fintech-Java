<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!-- Barra de navegação -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container">
    <a class="navbar-brand" href="index.jsp">Projeto Fintech</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Inicio</a>
        </li>
        <c:if test="${not empty id_usuario}">
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="conta">Contas</a>
          </li>
          <!-- Devido à falta de tempo não consegui implementar a função de listar por "TIPO" de transação. Mas tem a lista completa em cada conta. -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Listar</a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#">Receitas</a></li>
              <hr class="dro-divider" />
              <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#">Gastos</a></li>
              <hr class="dro-divider" />
              <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#">Investimentos</a></li>
            </ul>
          </li>
        </c:if>
      </ul>
      <span class="navbar-text ms-auto me-3">
        ${nome_usuario}
      </span>
      <ul class="navbar-nav">
        <li class="nav-item">
          <c:if test="${nome_usuario == null}" >
            <a class="btn btn-outline-primary" aria-current="page" data-bs-toggle="modal" data-bs-target="#regLogin" href="#">Entrar</a>
          </c:if>
          <c:if test="${nome_usuario != null}" >
            <a class="btn btn-outline-danger" aria-current="page" href="usuario?acao=sair">Sair</a>
          </c:if>
        </li>
      </ul>
    </div>
  </div>
</nav>
<!-- Fim Barra de navegação -->