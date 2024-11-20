package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.EntidadeNaoEncontradaException;
import br.com.fiap.fintech.model.Categoria;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Transacao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@WebServlet("/transacao")
public class TransacaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String acao = req.getParameter("acao");
        if (acao != null) {
            switch (acao) {
                case "form":
                    carregarForm(req, resp);
                    break;
                case "excluir":
                    excluir(req, resp);
                    break;
                default:
                    listar(req, resp);
                    break;
            }
        }else {
            listar(req, resp);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String acao = req.getParameter("acao");
        if (acao != null) {
            switch (acao) {
                case "criar":
                    criar(req, resp);
                    break;
                case "alterar":
                    alterar(req, resp);
                    break;
            }
        }
    }
    private void carregarForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigoConta = Integer.parseInt(req.getParameter("codigoConta"));
        req.setAttribute("codigoConta", codigoConta);
        String codT = req.getParameter("codTransacao");

        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String dataAtual = formatter.format(agora);
        req.setAttribute("dataAtual", dataAtual);

        try{
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            if (codT != null){
                int codTransacao = Integer.parseInt(codT);
                Transacao transacao = transacaoDAO.pesquisarTransacao(codTransacao);
                req.setAttribute("transacao", transacao);
            }
            transacaoDAO.fecharConexao();

            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.listarCategoria();
            categoriaDAO.fecharConexao();

            req.setAttribute("categorias", categorias);
            req.getRequestDispatcher("form-transacoes.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (EntidadeNaoEncontradaException e) {
            throw new RuntimeException(e);
        }
    }
    private void alterar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codTransacao = Integer.parseInt(req.getParameter("codTransacao"));
        int codigoConta = Integer.parseInt(req.getParameter("codigoConta"));
        req.setAttribute("codigoConta", codigoConta);
        try {
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            Transacao transacao = transacaoDAO.pesquisarTransacao(codTransacao);
            transacao.setTipo_transacao(req.getParameter("tipoTransacao"));
            transacao.setVlr_transacao(Double.parseDouble(req.getParameter("transacaoValor")));
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            transacao.setContaCategoria(categoriaDAO.pesquisarCategoria(Integer.parseInt(req.getParameter("transacaoCategoria"))));
            categoriaDAO.fecharConexao();
            String dataHora = req.getParameter("dataTransacao");
            Timestamp dataTransacao;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(dataHora, formatter);
            dataTransacao = Timestamp.valueOf(localDateTime);
            transacao.setDt_transacao(dataTransacao);

            transacaoDAO.alterarTransacao(transacao);
            transacaoDAO.fecharConexao();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EntidadeNaoEncontradaException e) {
            e.printStackTrace();
        } finally {
            listar(req, resp);
        }
    }
    private void criar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int transacaoCategoria = Integer.parseInt(req.getParameter("transacaoCategoria"));
        int codigoConta = Integer.parseInt(req.getParameter("codigoConta"));
        double transacaoValor = Double.parseDouble(req.getParameter("transacaoValor"));
        String dataHora = req.getParameter("dataTransacao");
        Timestamp dataTransacao = new Timestamp(new Date().getTime());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dataHora, formatter);
            dataTransacao = Timestamp.valueOf(localDateTime);
        } catch (DateTimeParseException e) {

        }
        String tipoTransacao = req.getParameter("tipoTransacao");

        try{
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            TransacaoDAO transacaoDAO = new TransacaoDAO();

            Categoria categoria = categoriaDAO.pesquisarCategoria(transacaoCategoria);
            categoriaDAO.fecharConexao();

            Transacao transacao = new Transacao(codigoConta, categoria, dataTransacao, transacaoValor, "teste", tipoTransacao);

            transacaoDAO.cadastrarTransacao(transacao);
            transacaoDAO.fecharConexao();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (EntidadeNaoEncontradaException e) {
            throw new RuntimeException(e);
        }

        listar(req, resp);
    }
    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codTransacao = Integer.parseInt(req.getParameter("codTransacao"));
        try {
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            transacaoDAO.deletarTransacao(codTransacao);
            transacaoDAO.fecharConexao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (EntidadeNaoEncontradaException e) {
            throw new RuntimeException(e);
        } finally {
            listar(req, resp);
        }
    }
    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigoUsuario = Integer.parseInt(req.getSession().getAttribute("id_usuario").toString());
        int codigoConta = Integer.parseInt(req.getParameter("codigoConta"));
        req.setAttribute("codigoConta", codigoConta);

        try {
            ContaDAO contaDAO = new ContaDAO();
            Conta conta = contaDAO.pesquisarConta(codigoConta, codigoUsuario);
            contaDAO.fecharConexao();

            TransacaoDAO transacaoDAO = new TransacaoDAO();
            List<Transacao> transacoes = transacaoDAO.listarTransacoes(conta);
            transacaoDAO.fecharConexao();

            req.setAttribute("transacoes", transacoes);
            req.getRequestDispatcher("transacoes.jsp").forward(req, resp);
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            req.getRequestDispatcher("conta").forward(req, resp);
            throw new RuntimeException(e);
        }
    }
}
