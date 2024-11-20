<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Modal -->
<div class="modal fade" id="regLogin" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Login</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h2 class="mb-3">Informe os dados para entrar</h2>
                <div class="col-lg-12">
                    <form action="usuario">
                        <div class="row">
                            <div class="col-lg-9 my-3 text-start mx-auto">
                                <label for="email" class="form-label"><i class="fa-solid fa-envelope"></i> Email:</label>
                                <input type="text" class="form-control" id="email" name="email" placeholder="">
                                <hr>
                                <label for="senha" class="form-label"><i class="fa-solid fa-key"></i> Senha:</label>
                                <input type="password" class="form-control" id="senha" name="senha" placeholder="">
                            </div>
                            <div class="col-lg-12 my-2 d-flex justify-content-center">
                                <button type="submit" class="btn btn-success"><i class="fa-solid fa-check"></i> Entrar</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" aria-current="page" data-bs-toggle="modal" data-bs-target="#regRegistro" href="#">Novo Usuário</button>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Fim do Modal -->