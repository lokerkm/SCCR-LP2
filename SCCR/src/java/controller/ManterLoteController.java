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
import modelo.Evento;
import modelo.Lote;
import modelo.Evento;
import modelo.Usuario;

public class ManterLoteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
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
            request.setAttribute("eventos", Evento.obterEventos());
            
             String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            RequestDispatcher view = request.getRequestDispatcher("/manterLote.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
            //} catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) {

        int codLote = Integer.parseInt(request.getParameter("txtCodLote"));
        String dataInicial = request.getParameter("txtDataInicial");
        String dataFinal = request.getParameter("txtDataFinal");
        float valor = Float.parseFloat(request.getParameter("txtValor"));

        int codEvento = Integer.parseInt(request.getParameter("optEvento"));

         String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            
        try {
            Evento evento = Evento.obterEvento(codEvento);
            Lote lote = new Lote(codLote, dataInicial, dataFinal, valor, evento);

            lote.setCodEvento(codEvento);

            lote.gravar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaLoteController");
            view.forward(request, response);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararExcluir(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
 String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            
            request.setAttribute("operacao", "Excluir");
            request.setAttribute("eventos", Evento.obterEventos());
            
            int codLote = Integer.parseInt(request.getParameter("codLote"));
            int codEvento = Integer.parseInt(request.getParameter("codEvento"));
            
            Lote lote = Lote.obterLote(codLote, codEvento);

            request.setAttribute("lote", lote);
            RequestDispatcher view = request.getRequestDispatcher("/manterLote.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codLote = Integer.parseInt(request.getParameter("txtCodLote"));
        String dataInicial = request.getParameter("txtDataInicial");
        String dataFinal = request.getParameter("txtDataFinal");
        float valor = Float.parseFloat(request.getParameter("txtValor"));
        
        int codEvento = Integer.parseInt(request.getParameter("optEvento"));

         String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
        try {
            Evento evento = Evento.obterEvento(codEvento);
            Lote lote = new Lote(codLote, dataInicial, dataFinal, valor, evento);
            lote.setCodEvento(codEvento);
            
            lote.excluir();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaLoteController");
            view.forward(request, response);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararEditar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
             String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            
            request.setAttribute("operacao", "Editar");
            request.setAttribute("eventos", Evento.obterEventos());

            int codLote = Integer.parseInt(request.getParameter("codLote"));
            int codEvento = Integer.parseInt(request.getParameter("codEvento"));
            
            Lote lote = Lote.obterLote(codLote, codEvento);

            request.setAttribute("lote", lote);

            RequestDispatcher view = request.getRequestDispatcher("/manterLote.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        
        int codLote = Integer.parseInt(request.getParameter("txtCodLote"));
        String dataInicial = request.getParameter("txtDataInicial");
        String dataFinal = request.getParameter("txtDataFinal");
        float valor = Float.parseFloat(request.getParameter("txtValor"));
        
        int codEvento = Integer.parseInt(request.getParameter("optEvento"));

         String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            
        try {
            Evento evento = Evento.obterEvento(codEvento);
            Lote lote = new Lote(codLote, dataInicial, dataFinal, valor, evento);

            lote.setCodEvento(codEvento);

            lote.alterar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaLoteController");
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
            Logger.getLogger(ManterLoteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterLoteController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterLoteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManterLoteController.class.getName()).log(Level.SEVERE, null, ex);
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
