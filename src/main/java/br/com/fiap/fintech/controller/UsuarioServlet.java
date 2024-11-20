package br.com.fiap.fintech.controller;

import java.io.*;

import java.sql.SQLException;



import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.EntidadeNaoEncontradaException;

import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        String acao = request.getParameter("acao");
        if (acao != null) {
            switch (acao) {
                case "sair":
                    sair(request, response);
                    break;
            }
        }else {
            try{
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario = usuarioDAO.pesquisarUsuario(email, senha);
                usuarioDAO.fecharConexao();
                HttpSession session = request.getSession();
                session.setAttribute("id_usuario", usuario.getCodigoUsuario());
                session.setAttribute("nome_usuario", usuario.getNome());
                request.getRequestDispatcher("conta").forward(request, response);
            }catch (SQLException | EntidadeNaoEncontradaException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Usuario não encontrado.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String senhaConfirm = request.getParameter("senhaConfirm");
        if (senha.equals(senhaConfirm)) {
            try {
                Usuario user = new Usuario(email, senha, nome);
                UsuarioDAO userDao = new UsuarioDAO();
                userDao.criarUsuario(user);
                userDao.fecharConexao();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (EntidadeNaoEncontradaException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("message", "Usuário criado com sucesso!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {
            request.setAttribute("erro", "Digite a mesma senha na confirmação.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void sair(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("id_usuario");
        session.removeAttribute("nome_usuario");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}