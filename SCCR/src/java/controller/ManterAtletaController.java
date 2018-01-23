/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Atleta;
import modelo.Chip;
import modelo.Endereco;
import modelo.Evento;
import modelo.Inscricao;
import modelo.Percurso;
import modelo.Pontuacao;
import modelo.Ranking;

public class ManterAtletaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String acao = request.getParameter("acao");
        if (acao.equals("prepararIncluir")) {
            prepararIncluir(request, response);
        } else {
            if (acao.equals("confirmarIncluir")) {
                confirmarIncluir(request, response);
            } else {
                if (acao.equals("prepararExcluir")) {
                    prepararExcluir(request, response);
                } else {
                    if (acao.equals("confirmarExcluir")) {
                        confirmarExcluir(request, response);
                    } else {
                        if (acao.equals("prepararEditar")) {
                            prepararEditar(request, response);
                        } else {
                            if (acao.equals("confirmarEditar")) {
                                confirmarEditar(request, response);
                            } else {
                                if (acao.equals("prepararSimularResultado")) {
                                    prepararSimularResultado(request, response);
                                } else {
                                    if (acao.equals("confirmaSimularResultado")) {
                                        confirmaSimularResultado(request, response);
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    public void prepararIncluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            request.setAttribute("operacao", "Incluir");

            RequestDispatcher view = request.getRequestDispatcher("/manterAtleta.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        }
    }

    public void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) {

        int codAtleta = Integer.parseInt(request.getParameter("txtCodAtleta"));
        String nomeEmerg = request.getParameter("txtNomeContatoEmergencia");
        String telEmerg = request.getParameter("txtTelefoneContatoEmergencia");
        String apelido = request.getParameter("txtApelidoAtleta");

        String nome = request.getParameter("txtNomeAtleta");
        String cpf = request.getParameter("txtCpf");
        String rg = request.getParameter("txtIdentidade");
        String dataNascimento = request.getParameter("txtDataNascimento");
        boolean sexo = Boolean.parseBoolean(request.getParameter("optSexoAtleta"));
        String senha = request.getParameter("txtSenha");
        String email = request.getParameter("txtEmail");
        String telFixo = request.getParameter("txtTelefoneFixo");
        String telCel = request.getParameter("txtTelefoneCelular");
        int codUsuario = Integer.parseInt(request.getParameter("txtCodUsuario"));

        int codEndereco = Integer.parseInt(request.getParameter("txtCodEndereco"));
        String cep = request.getParameter("txtCepEndereco");
        String logradouro = request.getParameter("txtLogradouro");
        String cidade = request.getParameter("txtCidade");
        String uf = request.getParameter("txtUfEndereco");
        String numeroLogradouro = request.getParameter("txtNumeroLogradouro");
        String complementoLogradouro = request.getParameter("txtComplementoLogradouro");
        String bairro = request.getParameter("txtBairro");

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {
            Endereco endereco = new Endereco(codEndereco, cep, logradouro, cidade, uf, numeroLogradouro, complementoLogradouro, bairro);
            endereco.gravar();

            Atleta atleta = new Atleta(codAtleta, nomeEmerg, telEmerg, apelido, nome, cpf, rg,
                    dataNascimento, sexo, senha, email, telFixo, telCel, codUsuario, endereco);
            System.out.println(atleta.getApelido());

            atleta.setCodEndereco(codEndereco);

            atleta.gravar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaAtletaController");
            view.forward(request, response);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararExcluir(HttpServletRequest request, HttpServletResponse response) {
        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            request.setAttribute("operacao", "Excluir");

            int codAtleta = Integer.parseInt(request.getParameter("codAtleta"));
            int codEndereco = Integer.parseInt(request.getParameter("codEndereco"));
            Endereco endereco = Endereco.obterEndereco(codEndereco);
            Atleta atleta = Atleta.obterAtleta(codAtleta);
            request.setAttribute("atleta", atleta);
            request.setAttribute("endereco", endereco);
            RequestDispatcher view = request.getRequestDispatcher("/manterAtleta.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }

    public void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codAtleta = Integer.parseInt(request.getParameter("txtCodAtleta"));
        String nomeEmerg = request.getParameter("txtNomeContatoEmergencia");
        String telEmerg = request.getParameter("txtTelefoneContatoEmergencia");
        String nome = request.getParameter("txtNomeAtleta");
        String cpf = request.getParameter("txtCpf");
        String rg = request.getParameter("txtIdentidade");
        String dataNascimento = request.getParameter("txtDataNascimento");
        boolean sexo = Boolean.parseBoolean(request.getParameter("optSexoAtleta"));
        String senha = request.getParameter("txtSenha");
        String email = request.getParameter("txtEmail");
        String telFixo = request.getParameter("txtTelefoneFixo");
        String telCel = request.getParameter("txtTelefoneCelular");
        int codUsuario = Integer.parseInt(request.getParameter("txtCodUsuario"));

        Atleta atleta = new Atleta(codAtleta, nomeEmerg, telEmerg, telFixo, codAtleta, nome, cpf, rg, dataNascimento, sexo, senha, email, telFixo, telCel, codUsuario, null); //atleta.setCodEndereco(enderecoCod);

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);

        try {

            atleta.excluir();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaAtletaController");
            view.forward(request, response);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararEditar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            request.setAttribute("operacao", "Editar");

            int codAtleta = Integer.parseInt(request.getParameter("codAtleta"));
            int codEndereco = Integer.parseInt(request.getParameter("codEndereco"));
            Endereco endereco = Endereco.obterEndereco(codEndereco);
            Atleta atleta = Atleta.obterAtleta(codAtleta);
            request.setAttribute("atleta", atleta);
            request.setAttribute("endereco", endereco);
            RequestDispatcher view = request.getRequestDispatcher("/manterAtleta.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }

    public void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codAtleta = Integer.parseInt(request.getParameter("txtCodAtleta"));
        String nomeEmerg = request.getParameter("txtNomeContatoEmergencia");
        String telEmerg = request.getParameter("txtTelefoneContatoEmergencia");
        String nome = request.getParameter("txtNomeAtleta");
        String cpf = request.getParameter("txtCpf");
        String rg = request.getParameter("txtIdentidade");
        String dataNascimento = request.getParameter("txtDataNascimento");
        boolean sexo = Boolean.parseBoolean(request.getParameter("optSexoAtleta"));
        String senha = request.getParameter("txtSenha");
        String email = request.getParameter("txtEmail");
        String telFixo = request.getParameter("txtTelefoneFixo");
        String telCel = request.getParameter("txtTelefoneCelular");
        int codUsuario = Integer.parseInt(request.getParameter("txtCodUsuario"));

        int codEndereco = Integer.parseInt(request.getParameter("txtCodEndereco"));
        String cep = request.getParameter("txtCepEndereco");
        String logradouro = request.getParameter("txtLogradouro");
        String cidade = request.getParameter("txtCidade");
        String uf = request.getParameter("txtUfEndereco");
        String numeroLogradouro = request.getParameter("txtNumeroLogradouro");
        String complementoLogradouro = request.getParameter("txtComplementoLogradouro");
        String bairro = request.getParameter("txtBairro");

        Endereco endereco = new Endereco(codEndereco, cep, logradouro, cidade, uf, numeroLogradouro, complementoLogradouro, bairro);
        Atleta atleta = new Atleta(codAtleta, nomeEmerg, telEmerg, telFixo, codAtleta, nome, cpf, rg, dataNascimento, sexo, senha, email, telFixo, telCel, codUsuario, endereco);
        atleta.setCodEndereco(codEndereco);

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {

            endereco.alterar();
            atleta.alterar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaAtletaController");
            view.forward(request, response);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararSimularResultado(HttpServletRequest request, HttpServletResponse response) {
        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            request.setAttribute("eventos", Evento.obterEventos());
            request.setAttribute("atletas", Atleta.obterAtletas());
            request.setAttribute("chips", Chip.obterChips());

            RequestDispatcher view = request.getRequestDispatcher("/simularResultadoAtleta.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }

    public void confirmaSimularResultado(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codEvento = Integer.parseInt(request.getParameter("optEvento"));
        int codChip = Integer.parseInt(request.getParameter("optChip"));
        int codAtleta = Integer.parseInt(request.getParameter("optAtleta"));
        int posicao = Integer.parseInt(request.getParameter("txtPosicao"));
        Float tempo = Float.parseFloat(request.getParameter("txtTempo"));
        int codPontuacao = Integer.parseInt(request.getParameter("txtCodPontuacao"));
        Float pace;

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {

            Inscricao inscricao = Inscricao.obterInscricaoSimularAtleta(codChip, codEvento, codAtleta);
            Percurso percurso = Percurso.obterPercurso(inscricao.getCodPercurso(), inscricao.getCodPercursoEvento());
            Atleta atleta = Atleta.obterAtleta(codAtleta);

            pace = tempo / percurso.getQuilometragem();
            atleta.setPace(pace);
            atleta.alterarPace();

            ArrayList<Ranking> rankings = (ArrayList<Ranking>) Ranking.obterRankingsEvento(codEvento);
            for (int i = 0; i < rankings.size(); i++) {
                for (int j = 0; j < rankings.get(i).getDistribuicaoPontos().size(); j++) {
                    if (rankings.get(i).getDistribuicaoPontos().get(j).getColocacao() == posicao) {
                        Pontuacao pontuacao = new Pontuacao(atleta, rankings.get(i),codPontuacao , rankings.get(i).getDistribuicaoPontos().get(j).getPontos());
                        pontuacao.setCodAtleta(codAtleta);
                        pontuacao.setCodRanking(rankings.get(i).getCodRanking());

                        pontuacao.gravar();
                    }
                }

            }
            RequestDispatcher view = request.getRequestDispatcher("PesquisaAtletaController");
            view.forward(request, response);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterAtletaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManterAtletaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
