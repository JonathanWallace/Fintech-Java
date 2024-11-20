package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.exception.EntidadeNaoEncontradaException;
import br.com.fiap.fintech.model.Conta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/conta")
public class ContaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if (acao != null) {
            switch (acao) {
                case "alterarLoad":
                    alterarLoad(req, resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if (acao != null) {
            switch (acao) {
                case "criar":
                    criar(req, resp);
                    break;
                case "alterarCommit":
                    alterarCommit(req, resp);
                    break;
                case "excluir":
                    excluir(req, resp);
                    break;
                default:
                    listar(req, resp);
                    break;
            }
        }

    }

    private void alterarLoad(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        int id_usuario = Integer.parseInt(session.getAttribute("id_usuario").toString());
        int codigoConta = Integer.parseInt(req.getParameter("codigoConta"));

        try {
            ContaDAO contaDAO = new ContaDAO();
            Conta conta = contaDAO.pesquisarConta(codigoConta, id_usuario);
            contaDAO.fecharConexao();

            req.setAttribute("conta", conta);
        } catch (SQLException e) {
            session.setAttribute("erro", "Erro na criação da conta!");
            resp.sendRedirect("conta");
            throw new RuntimeException(e);
        } catch (EntidadeNaoEncontradaException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("form-contas.jsp").forward(req, resp);
    }

    private void alterarCommit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        int id_usuario = Integer.parseInt(session.getAttribute("id_usuario").toString());
        int codigoConta = Integer.parseInt(req.getParameter("codigoConta"));

        try {
            ContaDAO contaDAO = new ContaDAO();
            Conta conta = contaDAO.pesquisarConta(codigoConta, id_usuario);
            conta.setTipoConta(req.getParameter("tipoConta"));
            conta.setNomeConta(req.getParameter("nomeConta"));
            contaDAO.alterarConta(conta);
            contaDAO.fecharConexao();
            session.setAttribute("message", "Conta alterada com sucesso!");
            resp.sendRedirect("conta");
        } catch (SQLException | EntidadeNaoEncontradaException e) {
            session.setAttribute("erro", "Erro na alteração da conta!");
            resp.sendRedirect("conta");
            throw new RuntimeException(e);
        }
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Conta> contas;
        if (session != null) {
            String mensagem = (String) session.getAttribute("mensagem");
            String erro = (String) session.getAttribute("erro");
            if (mensagem != null) {
                req.setAttribute("message", mensagem);
                session.removeAttribute("message");
            }
            if (erro != null) {
                req.setAttribute("erro", erro);
                session.removeAttribute("erro");
            }
        }
        if (session.getAttribute("id_usuario") != null) {
            int id_usuario = Integer.parseInt(session.getAttribute("id_usuario").toString());
            try {
                ContaDAO contaDAO = new ContaDAO();
                contas = contaDAO.listarContas(id_usuario);
                contaDAO.fecharConexao();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("contas", contas);
            req.getRequestDispatcher("contas.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    private void criar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id_usuario = Integer.parseInt(session.getAttribute("id_usuario").toString());
        String nomeConta = req.getParameter("nomeConta");
        String tipoConta = req.getParameter("tipoConta");
        System.out.println(id_usuario + " " + nomeConta + " " + tipoConta);
        try{
            ContaDAO contaDAO = new ContaDAO();
            Conta conta = new Conta(id_usuario, nomeConta, tipoConta);
            contaDAO.cadastrarConta(conta);
            contaDAO.fecharConexao();
            session.setAttribute("message", "Conta criada com sucesso!");
        } catch (SQLException e) {
            session.setAttribute("erro", "Erro na criação da conta!");
            throw new RuntimeException(e);
        }

        resp.sendRedirect("conta");
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        try {
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.deletarConta(codigo);
            session.setAttribute("message", "Conta excluida com sucesso!");
            contaDAO.fecharConexao();
        } catch (SQLException e) {
            session.setAttribute("erro", "Erro na exclusão da conta!");
            throw new RuntimeException(e);
        }

        resp.sendRedirect("conta");
    }
}
