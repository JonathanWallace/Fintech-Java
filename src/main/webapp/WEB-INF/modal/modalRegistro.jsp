<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Modal -->
<div class="modal fade" id="regReceita" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Registro</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h2 class="mb-3">Informe os dados da nova movimentação</h2>
                <div class="col-lg-12">
                    <form action="">
                        <div class="row text-start">
                            <div class="col-lg-6 my-3">
                                <label for="nome" class="form-label"><i class="fa-regular fa-star"></i></i> Nome da movimentação</label>
                                <input type="text" class="form-control" id="nome" placeholder="Ex:Compras do Supermercado">
                            </div>
                            <div class="col-lg-6 my-3">
                                <label for="data" class="form-label"><i class="fa-solid fa-calendar-days"></i></i> Data da movimentação</label>
                                <input type="date" class="form-control" id="data" placeholder="Data da movimentações">
                            </div>
                            <div class="col-lg-6 my-3">
                                <label for="tipo" class="form-label"><i class="fa-solid fa-list-ul"></i> </i>Tipo</label>
                                <select class="form-select" aria-label="Default select example">
                                    <option selected disabled>Selecione</option>
                                    <option value="1">Receita</option>
                                    <option value="2">Gasto</option>
                                    <option value="3">Investimento</option>
                                </select>
                            </div>
                            <div class="col-lg-6 my-3">
                                <label for="valor" class="form-label"><i class="fa-solid fa-money-bill"></i> Valor(R$)</label>
                                <input type="number" step="0.01" class="form-control" id="valor" placeholder="0.00">
                            </div>
                            <div class="col-lg-12 my-2 d-flex justify-content-center">
                                <button type="submit" class="btn btn-success"><i class="fa-solid fa-check"></i> Registrar</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Fim do Modal -->