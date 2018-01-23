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
import modelo.DistribuicaoDePontos;
import modelo.Evento;
import modelo.Pontuacao;
import modelo.Ranking;

public class ManterRankingController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String acao = request.getParameter("acao");
        if (acao.equals("prepararIncluir")) {
            prepararIncluir(request, response);
        } else {
            if (acao.equals("confirmarIncluir")) {
                confirmarIncluir(request, response);
            } else {
                if (acao.equals("prepararEditar")) {
                    prepararEditar(request, response);
                } else {
                    if (acao.equals("confirmarEditar")) {
                        confirmarEditar(request, response);
                    } else {
                        if (acao.equals("prepararExcluir")) {
                            prepararExcluir(request, response);
                        } else {
                            if (acao.equals("confirmarExcluir")) {
                                confirmarExcluir(request, response);
                            } else {

                                if (acao.equals("confirmarExcluirEventoDoRanking")) {
                                    confirmarExcluirEventoDoRanking(request, response);
                                } else {

                                    if (acao.equals("prepararIncluirEventoDoRanking")) {
                                        prepararIncluirEventoDoRanking(request, response);
                                    } else {

                                        if (acao.equals("confirmarIncluirEventoDoRanking")) {
                                            confirmarIncluirEventoDoRanking(request, response);
                                        } else {
                                            if (acao.equals("gerarColocacao")) {
                                                gerarColocacao(request, response);
                                            }
                                        }

                                    }

                                }
                            }

                        }
                    }
                }
            }

        }
    }

    public void prepararIncluir(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("operacao", "Incluir");
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            RequestDispatcher view = request.getRequestDispatcher("/manterRanking.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
            //} catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int codRanking = Integer.parseInt(request.getParameter("txtCodRanking"));
        String nomeRanking = request.getParameter("txtRanking");

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {

            Ranking ranking = new Ranking(codRanking, nomeRanking);
            ranking.gravar();
            for (int i = 0; i < 3; i++) {

                float pontuacao = Float.parseFloat(request.getParameter("txtPosicao" + i));
                DistribuicaoDePontos distribuicao = new DistribuicaoDePontos(i + 1, i + 1, pontuacao, ranking);
                distribuicao.setCodRanking(codRanking);
                distribuicao.gravar();

            }

            RequestDispatcher view = request.getRequestDispatcher("PesquisaRankingController");
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

            int codRanking = Integer.parseInt(request.getParameter("codRanking"));
            Ranking ranking = Ranking.obterRanking(codRanking);
            request.setAttribute("distribuicao", Ranking.obterDistribuicao(codRanking));

            request.setAttribute("ranking", ranking);

            RequestDispatcher view = request.getRequestDispatcher("/manterRanking.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }

    public void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codRanking = Integer.parseInt(request.getParameter("txtCodRanking"));
        String nome = request.getParameter("txtRanking");

        Ranking ranking = new Ranking(codRanking, nome);

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {

            DistribuicaoDePontos.excluirDoRanking(codRanking);
            ranking.excluir();

            RequestDispatcher view = request.getRequestDispatcher("PesquisaRankingController");
            view.forward(request, response);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararIncluirEventoDoRanking(HttpServletRequest request, HttpServletResponse response) {
        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            request.setAttribute("operacao", "Excluir");

            int codRanking = Integer.parseInt(request.getParameter("codRanking"));
            request.setAttribute("codRanking", codRanking);
            request.setAttribute("eventos", Evento.obterEventos());

            RequestDispatcher view = request.getRequestDispatcher("/manterEventoDoRanking.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }

    public void confirmarIncluirEventoDoRanking(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int codRanking = Integer.parseInt(request.getParameter("codRanking"));
        request.setAttribute("codRanking", codRanking);
        int codEvento = Integer.parseInt(request.getParameter("optEvento"));

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {

            Ranking.incluirEventoDoRaking(codEvento, codRanking);

            RequestDispatcher view = request.getRequestDispatcher("PesquisaEventoDoRankingController");
            view.forward(request, response);

        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void confirmarExcluirEventoDoRanking(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codRanking = Integer.parseInt(request.getParameter("codRanking"));
        request.setAttribute("codRanking", codRanking);
        int codEvento = Integer.parseInt(request.getParameter("codEvento"));

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {
            Ranking.excluirEventoDoRaking(codEvento, codRanking);

            RequestDispatcher view = request.getRequestDispatcher("PesquisaEventoDoRankingController");
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

            int codRanking = Integer.parseInt(request.getParameter("codRanking"));
            Ranking ranking = Ranking.obterRanking(codRanking);

            request.setAttribute("distribuicao", Ranking.obterDistribuicao(codRanking));
            request.setAttribute("ranking", ranking);

            RequestDispatcher view = request.getRequestDispatcher("/manterRanking.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }

    public void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codRanking = Integer.parseInt(request.getParameter("txtCodRanking"));
        String nome = request.getParameter("txtRanking");
        Ranking ranking = new Ranking(codRanking, nome);

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {
            ranking.alterar();

            RequestDispatcher view = request.getRequestDispatcher("PesquisaRankingController");
            view.forward(request, response);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void gerarColocacao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        try {

            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            request.setAttribute("operacao", "Editar");

            int codRanking = Integer.parseInt(request.getParameter("codRanking"));
            Ranking ranking = Ranking.obterRanking(codRanking);
            ArrayList<Pontuacao> pontuacoes = (ArrayList<Pontuacao>) Pontuacao.obterPontuacoesRanking(codRanking);

            request.setAttribute("ranking", ranking);
            request.setAttribute("atletas", Atleta.obterAtletas());
            request.setAttribute("pontuacoes", pontuacoes);

            RequestDispatcher view = request.getRequestDispatcher("/pesquisaColocacao.jsp");
            view.forward(request, response);
        } catch (IOException ex) {
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
            Logger.getLogger(ManterRankingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterRankingController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterRankingController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterRankingController.class.getName()).log(Level.SEVERE, null, ex);
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
