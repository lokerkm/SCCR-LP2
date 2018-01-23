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
import modelo.Chip;
import modelo.Evento;
import modelo.Percurso;
import modelo.Inscricao;
import modelo.Kit;
import modelo.Pagamento;

public class ManterInscricaoController extends HttpServlet {

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
                                if (acao.equals("prepararConfirmarRetiradaKit")) {
                                    prepararConfirmarRetiradaKit(request, response);
                                } else {
                                    if (acao.equals("confirmarRetiradaKit")) {
                                        confirmarRetiradaKit(request, response);
                                    } else {
                                        if (acao.equals("prepararIncluirViaEvento")) {
                                            prepararIncluirViaEvento(request, response);
                                        } else {
                                            if (acao.equals("confirmarIncluirViaEvento")) {
                                                confirmarIncluirViaEvento(request, response);
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

    public void prepararIncluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        try {
            request.setAttribute("operacao", "Incluir");

            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            request.setAttribute("percursos", Percurso.obterPercursos());
            request.setAttribute("atletas", Atleta.obterAtletas());
            request.setAttribute("chips", Chip.obterChips());
            request.setAttribute("kits", Kit.obterKits());
            request.setAttribute("eventos", Evento.obterEventos());

            RequestDispatcher view = request.getRequestDispatcher("/manterInscricao.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
        /*catch (ClassNotFoundException ex){
            
         }*/
    }

    public void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {

        int codInscricao = Integer.parseInt(request.getParameter("txtCodInscricao"));

        int numeroPeito = Integer.parseInt(request.getParameter("txtNumeroPeito"));
        String tamCamisa = request.getParameter("txtTamanhoCamisa");
        Boolean statusRetirada = Boolean.parseBoolean(request.getParameter("txtTamanhoCamisa"));

        int codChip = Integer.parseInt(request.getParameter("optChip"));
        int codAtleta = Integer.parseInt(request.getParameter("optAtleta"));

        String dadosPercurso = request.getParameter("optPercurso");
        String[] parts = dadosPercurso.split("|");
        int codPercurso = Integer.parseInt(parts[0]);
        int codKit = Integer.parseInt(request.getParameter("optKit"));
        int codEvento = Integer.parseInt(request.getParameter("optEvento"));
        int codPagamento = Integer.parseInt(request.getParameter("txtCodPagamento"));
        String codigoBarras = "" + codPagamento + codPagamento + codPagamento;

        try {
            Atleta atleta = Atleta.obterAtleta(codAtleta);

            Percurso percurso = Percurso.obterPercurso(codPercurso, codEvento);
            Kit kit = Kit.obterKit(codKit, codEvento);
            Chip chip = Chip.obterChip(codChip);
            Pagamento pagamento = new Pagamento(codPagamento, codigoBarras);
            Inscricao inscricao = new Inscricao(codInscricao, statusRetirada, tamCamisa, numeroPeito, chip, atleta, percurso, pagamento, kit, tamCamisa);

            inscricao.setCodAtleta(codAtleta);
            inscricao.setCodKit(codKit);
            inscricao.setCodEventoKit(codEvento);
            inscricao.setCodPercurso(codPercurso);
            inscricao.setCodPagamento(codPagamento);
            inscricao.setCodPercursoEvento(codEvento);
            inscricao.setCodChip(codChip);
            inscricao.setPrecoTotal(inscricao.calcularValorTotal(codKit, codEvento));
            pagamento.setValorTotal(inscricao.getPrecoTotal());

            pagamento.gravar();
            inscricao.gravar();

            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            RequestDispatcher view = request.getRequestDispatcher("ManterPagamentoController?acao=prepararEditar&codPagamento=" + codPagamento + "&tipoLogin=" + tipoLogin);
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
            request.setAttribute("percursos", Percurso.obterPercursos());
            request.setAttribute("atletas", Atleta.obterAtletas());
            request.setAttribute("kits", Kit.obterKits());
            request.setAttribute("chips", Chip.obterChips());
            request.setAttribute("eventos", Evento.obterEventos());

            int codInscricao = Integer.parseInt(request.getParameter("codInscricao"));
            int codPercurso = Integer.parseInt(request.getParameter("codPercurso"));
            int codEvento = Integer.parseInt(request.getParameter("codEvento"));
            Inscricao inscricao = Inscricao.obterInscricao(codInscricao, codPercurso, codEvento);

            request.setAttribute("inscricao", inscricao);
            RequestDispatcher view = request.getRequestDispatcher("/manterInscricao.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {

        int codInscricao = Integer.parseInt(request.getParameter("txtCodInscricao"));

        int numeroPeito = Integer.parseInt(request.getParameter("txtNumeroPeito"));
        String tamCamisa = request.getParameter("txtTamanhoCamisa");
        Boolean statusRetirada = Boolean.parseBoolean(request.getParameter("txtTamanhoCamisa"));

        int codChip = Integer.parseInt(request.getParameter("optChip"));
        int codAtleta = Integer.parseInt(request.getParameter("optAtleta"));
        String dadosPercurso = request.getParameter("optPercurso");
        String[] parts = dadosPercurso.split("|");
        int codPercurso = Integer.parseInt(parts[0]);
        int codKit = Integer.parseInt(request.getParameter("optKit"));
        int codEvento = Integer.parseInt(request.getParameter("optEvento"));

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {
            Atleta atleta = Atleta.obterAtleta(codAtleta);

            Percurso percurso = Percurso.obterPercurso(codPercurso, codEvento);
            Kit kit = Kit.obterKit(codKit, codEvento);
            Chip chip = Chip.obterChip(codChip);

            Inscricao inscricao = new Inscricao(codInscricao, statusRetirada, tamCamisa, numeroPeito, chip, atleta, percurso, null/*pagamento*/, kit, tamCamisa);

            inscricao.setCodAtleta(codAtleta);
            inscricao.setCodKit(codKit);
            inscricao.setCodEventoKit(codEvento);
            inscricao.setCodPercurso(codPercurso);
            inscricao.setCodPercursoEvento(codEvento);
            inscricao.setCodChip(codChip);
//            inscricao.setCodPagamento(codPagamento);

            inscricao.excluir();

            RequestDispatcher view = request.getRequestDispatcher("PesquisaInscricaoController");
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
            request.setAttribute("percursos", Percurso.obterPercursos());
            request.setAttribute("atletas", Atleta.obterAtletas());
            request.setAttribute("kits", Kit.obterKits());
            request.setAttribute("chips", Chip.obterChips());
            request.setAttribute("eventos", Evento.obterEventos());

            int codInscricao = Integer.parseInt(request.getParameter("codInscricao"));
            int codPercurso = Integer.parseInt(request.getParameter("codPercurso"));
            int codEvento = Integer.parseInt(request.getParameter("codEvento"));
            Inscricao inscricao = Inscricao.obterInscricao(codInscricao, codPercurso, codEvento);

            request.setAttribute("inscricao", inscricao);
            RequestDispatcher view = request.getRequestDispatcher("/manterInscricao.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarEditar(HttpServletRequest request, HttpServletResponse response) {

        int codInscricao = Integer.parseInt(request.getParameter("txtCodInscricao"));

        int numeroPeito = Integer.parseInt(request.getParameter("txtNumeroPeito"));
        String tamCamisa = request.getParameter("txtTamanhoCamisa");
        Boolean statusRetirada = Boolean.parseBoolean(request.getParameter("txtTamanhoCamisa"));

        int codChip = Integer.parseInt(request.getParameter("optChip"));
        int codAtleta = Integer.parseInt(request.getParameter("optAtleta"));

        String dadosPercurso = request.getParameter("optPercurso");
        String[] parts = dadosPercurso.split("|");
        int codPercurso = Integer.parseInt(parts[0]);
        int codKit = Integer.parseInt(request.getParameter("optKit"));
        int codEvento = Integer.parseInt(request.getParameter("optEvento"));

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);

        try {
            Atleta atleta = Atleta.obterAtleta(codAtleta);

            Percurso percurso = Percurso.obterPercurso(codPercurso, codEvento);
            Kit kit = Kit.obterKit(codKit, codEvento);
            Chip chip = Chip.obterChip(codChip);

            Inscricao inscricao = new Inscricao(codInscricao, statusRetirada, tamCamisa, numeroPeito, chip, atleta, percurso, null/*pagamento*/, kit, tamCamisa);

            inscricao.setCodAtleta(codAtleta);
            inscricao.setCodKit(codKit);
            inscricao.setCodEventoKit(codEvento);
            inscricao.setCodPercurso(codPercurso);
            inscricao.setCodPercursoEvento(codEvento);
            inscricao.setCodChip(codChip);
//            inscricao.setCodPagamento(codPagamento);

            inscricao.alterar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaInscricaoController");
            view.forward(request, response);
        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararConfirmarRetiradaKit(HttpServletRequest request, HttpServletResponse response) {
        try {

            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            //  request.setAttribute("operacao", "Preparar");
            request.setAttribute("eventos", Evento.obterEventos());
            RequestDispatcher view = request.getRequestDispatcher("/confirmarRetiradaKit.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarRetiradaKit(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        int codInscricao = Integer.parseInt(request.getParameter("txtCodInscricao"));
        int codEvento = Integer.parseInt(request.getParameter("optEvento"));

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {
            Inscricao inscricao = Inscricao.obterInscricaoKit(codInscricao, codEvento);

            if (inscricao.getPagamento().isStatus()) {

                inscricao.setStatusRetirada(Boolean.TRUE);
                inscricao.alterar();

                RequestDispatcher view = request.getRequestDispatcher("PesquisaInscricaoController");
                view.forward(request, response);
            } else {

                RequestDispatcher view = request.getRequestDispatcher("PesquisaPagamentoController");
                view.forward(request, response);
            }

        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararIncluirViaEvento(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        try {
            request.setAttribute("operacao", "Incluir");

            request.setAttribute("atletas", Atleta.obterAtletas());
            request.setAttribute("chips", Chip.obterChips());

            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            int codEvento = Integer.parseInt(request.getParameter("codEvento"));
            Evento evento = Evento.obterEvento(codEvento);
            request.setAttribute("evento", evento);
            request.setAttribute("percursos", evento.getListaPercursos());
            request.setAttribute("kits", evento.getListaKits());

            RequestDispatcher view = request.getRequestDispatcher("/manterInscricaoViaEvento.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {

        } catch (IOException ex) {

        }
        /*catch (ClassNotFoundException ex){
            
         }*/
    }

    public void confirmarIncluirViaEvento(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {

        int codInscricao = Integer.parseInt(request.getParameter("txtCodInscricao"));

        int numeroPeito = Integer.parseInt(request.getParameter("txtNumeroPeito"));
        String tamCamisa = request.getParameter("txtTamanhoCamisa");
        Boolean statusRetirada = Boolean.parseBoolean(request.getParameter("txtTamanhoCamisa"));

        int codChip = Integer.parseInt(request.getParameter("optChip"));
        int codAtleta = Integer.parseInt(request.getParameter("optAtleta"));
        int codEvento = Integer.parseInt(request.getParameter("txtCodEvento"));
        int codPercurso = Integer.parseInt(request.getParameter("optPercurso"));
        int codKit = Integer.parseInt(request.getParameter("optKit"));
        int codPagamento = Integer.parseInt(request.getParameter("txtCodPagamento"));
        String codigoBarras = "" + codPagamento + codPagamento + codPagamento;

        try {
            Atleta atleta = Atleta.obterAtleta(codAtleta);

            Percurso percurso = Percurso.obterPercurso(codPercurso, codEvento);
            Kit kit = Kit.obterKit(codKit, codEvento);
            Chip chip = Chip.obterChip(codChip);
            Pagamento pagamento = new Pagamento(codPagamento, codigoBarras);
            Inscricao inscricao = new Inscricao(codInscricao, statusRetirada, tamCamisa, numeroPeito, chip, atleta, percurso, pagamento, kit, tamCamisa);

            inscricao.setCodAtleta(codAtleta);
            inscricao.setCodKit(codKit);
            inscricao.setCodEventoKit(codEvento);
            inscricao.setCodPercurso(codPercurso);
            inscricao.setCodPagamento(codPagamento);
            inscricao.setCodPercursoEvento(codEvento);
            inscricao.setCodChip(codChip);
            inscricao.setPrecoTotal(inscricao.calcularValorTotal(codKit, codEvento));
            pagamento.setValorTotal(inscricao.getPrecoTotal());

            pagamento.gravar();
            inscricao.gravar();

            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            RequestDispatcher view = request.getRequestDispatcher("ManterPagamentoController?acao=prepararEditar&codPagamento=" + codPagamento + "&tipoLogin=" + tipoLogin);
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
            Logger.getLogger(ManterInscricaoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterInscricaoController.class.getName()).log(Level.SEVERE, null, ex);
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
