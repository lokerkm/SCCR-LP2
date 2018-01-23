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
import modelo.DistribuicaoDePontos;
import modelo.Ranking;

/**
 *
 * @author kevin
 */
public class ManterDistribuicaoDePontosController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
             String tipoLogin = request.getParameter("tipoLogin");
             int codRanking = Integer.parseInt(request.getParameter("codRanking"));
             request.setAttribute("codRanking", codRanking);
            request.setAttribute("tipoLogin", tipoLogin);
            
            request.setAttribute("operacao", "Incluir");
            request.setAttribute("rankings", Ranking.obterRankings());
            RequestDispatcher view = request.getRequestDispatcher("/manterDistribuicaoDePontos.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
            //} catch (ClassNotFoundException ex) {
        }
    }
    
    public void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int codDistribuicaoDePontos = Integer.parseInt(request.getParameter("txtCodDistribuicaoDePontos"));
        int colocacao = Integer.parseInt(request.getParameter("txtColocacao"));
        float pontos = Float.parseFloat(request.getParameter("txtPontos"));
        int codRanking =  Integer.parseInt(request.getParameter("optRanking"));
       
         String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
        try {
           Ranking ranking = Ranking.obterRanking(codRanking);
            DistribuicaoDePontos distribuicaoDePontos = new DistribuicaoDePontos(codDistribuicaoDePontos, colocacao, pontos, ranking);
            distribuicaoDePontos.setCodRanking(codRanking);
            distribuicaoDePontos.gravar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaDistribuicaoDoRankingController");
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

            int codDistribuicaoDePontos = Integer.parseInt(request.getParameter("codDistribuicaoDePontos"));
           int codRanking = Integer.parseInt(request.getParameter("codRanking"));
             request.setAttribute("codRanking", codRanking);
            DistribuicaoDePontos distribuicaoDePontos =  DistribuicaoDePontos.obterDistribuicaoDePontos(codDistribuicaoDePontos,codRanking);
            request.setAttribute("distribuicao", distribuicaoDePontos);
            
            request.setAttribute("rankings", Ranking.obterRankings());

            RequestDispatcher view = request.getRequestDispatcher("/manterDistribuicaoDePontos.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }
    
    public void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        int codDistribuicaoDePontos = Integer.parseInt(request.getParameter("txtCodDistribuicaoDePontos"));
        int colocacao = Integer.parseInt(request.getParameter("txtColocacao"));
        float pontos = Float.parseFloat(request.getParameter("txtPontos"));
        int codRanking = Integer.parseInt(request.getParameter("optRanking"));
       
        Ranking ranking = Ranking.obterRanking(codRanking);
        
         String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            
        try {
           
            DistribuicaoDePontos distribuicaoDePontos = new DistribuicaoDePontos(codDistribuicaoDePontos, colocacao, pontos, ranking);
            distribuicaoDePontos.setCodRanking(codRanking);
            distribuicaoDePontos.excluir();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaDistribuicaoDoRankingController");
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

            request.setAttribute("rankings", Ranking.obterRankings());
            int codDistribuicaoDePontos = Integer.parseInt(request.getParameter("codDistribuicaoDePontos"));
           int codRanking = Integer.parseInt(request.getParameter("codRanking"));
             request.setAttribute("codRanking", codRanking);
            DistribuicaoDePontos distribuicaoDePontos =  DistribuicaoDePontos.obterDistribuicaoDePontos(codDistribuicaoDePontos, codRanking);
            request.setAttribute("distribuicao", distribuicaoDePontos);

            RequestDispatcher view = request.getRequestDispatcher("/manterDistribuicaoDePontos.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }
    
    public void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int codDistribuicaoDePontos = Integer.parseInt(request.getParameter("txtCodDistribuicaoDePontos"));
        int colocacao = Integer.parseInt(request.getParameter("txtColocacao"));
        float pontos = Float.parseFloat(request.getParameter("txtPontos"));
        
        int codRanking =  Integer.parseInt(request.getParameter("optRanking"));
        
         String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
        try {
           Ranking ranking = Ranking.obterRanking(codRanking);
            DistribuicaoDePontos distribuicaoDePontos = new DistribuicaoDePontos(codDistribuicaoDePontos, colocacao, pontos, ranking);
            distribuicaoDePontos.setCodRanking(codRanking);
            distribuicaoDePontos.alterar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaDistribuicaoDoRankingController");
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
            Logger.getLogger(ManterDistribuicaoDePontosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterDistribuicaoDePontosController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterDistribuicaoDePontosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterDistribuicaoDePontosController.class.getName()).log(Level.SEVERE, null, ex);
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
