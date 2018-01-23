/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Atleta;
import modelo.Ranking;
import modelo.Pontuacao;
import modelo.Pontuacao;
import modelo.Ranking;

public class ManterPontuacaoController extends HttpServlet {

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
                            }
                        }

                    }
                }
            }

        }
    }

    public void prepararIncluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        try {
            request.setAttribute("operacao", "Incluir");
            request.setAttribute("rankings", Ranking.obterRankings());
            request.setAttribute("atletas", Atleta.obterAtletas());
             String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            
            RequestDispatcher view = request.getRequestDispatcher("/manterPontuacao.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
            //} catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int codPontuacao = Integer.parseInt(request.getParameter("txtCodPontuacao"));

        Float ponto = Float.parseFloat(request.getParameter("txtPontuacao"));

        int codRanking = Integer.parseInt(request.getParameter("optRanking"));
        int codAtleta = Integer.parseInt(request.getParameter("optAtleta"));

         String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
        try {
            Ranking ranking = Ranking.obterRanking(codRanking);
            Atleta atleta = Atleta.obterAtleta(codAtleta);

            Pontuacao pontuacao = new Pontuacao(atleta, ranking, codPontuacao, ponto);
            pontuacao.setCodRanking(codRanking);
            pontuacao.setCodAtleta(codAtleta);
            pontuacao.setCodRanking(codRanking);
            pontuacao.gravar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaPontuacaoController");
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

            int codPontuacao = Integer.parseInt(request.getParameter("codPontuacao"));
            int codRanking = Integer.parseInt(request.getParameter("codRanking"));
            Pontuacao pontuacao = Pontuacao.obterPontuacao(codPontuacao,codRanking);
            request.setAttribute("pontuacao", pontuacao);
            request.setAttribute("atletas", Atleta.obterAtletas());
            request.setAttribute("rankings", Ranking.obterRankings());

            RequestDispatcher view = request.getRequestDispatcher("/manterPontuacao.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }

    public void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codPontuacao = Integer.parseInt(request.getParameter("txtCodPontuacao"));
        float pontos = Float.parseFloat(request.getParameter("txtPontuacao"));
        int codRanking = Integer.parseInt(request.getParameter("optRanking"));
         Ranking ranking = Ranking.obterRanking(codRanking);
        Pontuacao pontuacao = new Pontuacao(null,ranking, codPontuacao, pontos);
        pontuacao.setCodRanking(codRanking);
        
         String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
        try {

            pontuacao.excluir();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaPontuacaoController");
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

            int codPontuacao = Integer.parseInt(request.getParameter("codPontuacao"));
            int codRanking = Integer.parseInt(request.getParameter("codRanking"));
            Pontuacao pontuacao = Pontuacao.obterPontuacao(codPontuacao,codRanking);
            request.setAttribute("pontuacao", pontuacao);
            request.setAttribute("atletas", Atleta.obterAtletas());
            request.setAttribute("rankings", Ranking.obterRankings());

            RequestDispatcher view = request.getRequestDispatcher("/manterPontuacao.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }

    public void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codPontuacao = Integer.parseInt(request.getParameter("txtCodPontuacao"));
        float pontos = Float.parseFloat(request.getParameter("txtPontuacao"));
        int codAtleta = Integer.parseInt(request.getParameter("optAtleta"));
        int codRanking = Integer.parseInt(request.getParameter("optRanking"));
         Ranking ranking = Ranking.obterRanking(codRanking);
         Atleta atleta = Atleta.obterAtleta(codAtleta);

        Pontuacao pontuacao = new Pontuacao(atleta, ranking, codPontuacao, pontos);
        
         String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
        try {
            pontuacao.setCodAtleta(codAtleta);
            
            pontuacao.setCodRanking(codRanking);
            pontuacao.alterar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaPontuacaoController");
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
            Logger.getLogger(ManterPontuacaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterPontuacaoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterPontuacaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterPontuacaoController.class.getName()).log(Level.SEVERE, null, ex);
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
