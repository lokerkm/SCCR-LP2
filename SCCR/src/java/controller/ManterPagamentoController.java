package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import modelo.Atleta;
import modelo.Inscricao;
import modelo.Pagamento;
import modelo.Endereco;
import modelo.GravaArquivoTexto;

//imports do bopepo
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.pdf.CodigoDeBarras;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

public class ManterPagamentoController extends HttpServlet {

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
                                if (acao.equals("confirmarPagamento")) {
                                    confirmarPagamento(request, response);
                                } else {
                                    if (acao.equals("prepararConfirmarPagamento")) {
                                        prepararConfirmarPagamento(request, response);
                                    } else {
                                        if (acao.equals("gerarBoleto")) {
                                            gerarBoleto(request, response);
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
            request.setAttribute("administradores", Pagamento.obterPagamento());

            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            RequestDispatcher view = request.getRequestDispatcher("/manterPagamento.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
            //} catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarIncluir(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int codPagamento = Integer.parseInt(request.getParameter("txtCodPagamento"));
        float valorTotal = Float.parseFloat(request.getParameter("txtValor"));
        boolean status = Boolean.parseBoolean(request.getParameter("optStatus"));
        String tipoPagamento = request.getParameter("optTipoPagamento");
        String codigoBarra = request.getParameter("txtCodigoBarra");

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {

            Pagamento pagamento = new Pagamento(codPagamento, valorTotal, status, tipoPagamento, codigoBarra);
            if ("cartao".equals(tipoPagamento)) {
                pagamento.setStatus(true);
            }
            pagamento.gravar();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaPagamentoController");
            view.forward(request, response);

        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararExcluir(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            request.setAttribute("operacao", "Excluir");
            int codPagamento = Integer.parseInt(request.getParameter("codPagamento"));
            Pagamento pagamento = Pagamento.obterPagamento(codPagamento);
            request.setAttribute("pagamento", pagamento);
            //request.setAttribute("administradores", Pagamento.obterPagamento());
            RequestDispatcher view = request.getRequestDispatcher("/manterPagamento.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
            //} catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int codPagamento = Integer.parseInt(request.getParameter("txtCodPagamento"));
        float valorTotal = Float.parseFloat(request.getParameter("txtValor"));
        String tipoPagamento = request.getParameter("optTipoPagamento");
        String codigoBarra = request.getParameter("txtCodigoBarra");

        String tipoLogin = request.getParameter("tipoLogin");
        request.setAttribute("tipoLogin", tipoLogin);
        try {

            Pagamento pagamento = new Pagamento(codPagamento, valorTotal, tipoPagamento, codigoBarra);

            pagamento.excluir();
            RequestDispatcher view = request.getRequestDispatcher("PesquisaPagamentoController");
            view.forward(request, response);

        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararEditar(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);

            request.setAttribute("operacao", "Editar");
            int codPagamento = Integer.parseInt(request.getParameter("codPagamento"));
            Pagamento pagamento = Pagamento.obterPagamento(codPagamento);
            request.setAttribute("pagamento", pagamento);
            //request.setAttribute("administradores", Pagamento.obterPagamento());
            RequestDispatcher view = request.getRequestDispatcher("/manterPagamento.jsp");
            view.forward(request, response);
        } catch (ServletException ex) {
        } catch (IOException ex) {
            //} catch (ClassNotFoundException ex) {
        }
    }

    public void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int codPagamento = Integer.parseInt(request.getParameter("txtCodPagamento"));
        float valorTotal = Float.parseFloat(request.getParameter("txtValor"));

        String tipoPagamento = request.getParameter("optTipoPagamento");
        String codigoBarra = request.getParameter("txtCodigoBarras");
        if ("cartao".equals(tipoPagamento)) {
        }
        try {

            Pagamento pagamento = new Pagamento(codPagamento, valorTotal, tipoPagamento, codigoBarra);
            if ("cartao".equals(tipoPagamento)) {
                pagamento.setStatus(true);
            }
            pagamento.alterar();

            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            RequestDispatcher view = request.getRequestDispatcher("PesquisaPagamentoController?tipoLogin" + tipoLogin);
            view.forward(request, response);

        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void confirmarPagamento(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        int codigoBarra = Integer.parseInt(request.getParameter("txtCodigoBarra"));

        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
           
            Pagamento.alterarStatus(codigoBarra);
            RequestDispatcher view = request.getRequestDispatcher("/PesquisaPagamentoController");
            view.forward(request, response);

        } catch (IOException ex) {
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (ServletException ex) {
        }
    }

    public void prepararConfirmarPagamento(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            RequestDispatcher view = request.getRequestDispatcher("/confirmarPagamento.jsp");
            view.forward(request, response);

        } catch (IOException ex) {
        } catch (ServletException ex) {
        }
    }

    private void gerarBoleto(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        int codPagamento = Integer.parseInt(request.getParameter("txtCodPagamento"));
        float valorTotal = Float.parseFloat(request.getParameter("txtValorTotal"));

        Inscricao inscricao;
        inscricao = Inscricao.obterInscricaoPagamento(codPagamento);
        Atleta atleta;
        atleta = Atleta.obterAtleta(inscricao.getCodAtleta());
        atleta.getCodEndereco();
        modelo.Endereco endereco;
        endereco = modelo.Endereco.obterEndereco(atleta.getCodEndereco());
        Pagamento pagamento = Pagamento.obterPagamento(codPagamento);

        //aqui começa a gerar o boleto usando o bopepo
        Cedente cedente = new Cedente("Controle de corrida de rua", "00.000.208/0001-00");
        Sacado sacado = new Sacado(atleta.getNome(), atleta.getCpf());

        org.jrimum.domkee.comum.pessoa.endereco.Endereco enderecoSacado = new org.jrimum.domkee.comum.pessoa.endereco.Endereco();

        enderecoSacado.setUF(UnidadeFederativa.MG);
        enderecoSacado.setLocalidade(endereco.getCidade());
        enderecoSacado.setCep(new CEP(endereco.getCep()));
        enderecoSacado.setBairro(endereco.getBairro());
        enderecoSacado.setLogradouro(endereco.getLogradouro());
        enderecoSacado.setNumero(endereco.getNumeroLogradouro());
        sacado.addEndereco(enderecoSacado);

        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(1234, "1"));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento("123456");
        titulo.setNossoNumero("99345678912");
        titulo.setDigitoDoNossoNumero("5");
        titulo.setValor(BigDecimal.valueOf(valorTotal));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.valueOf(valorTotal));

        Boleto boleto = new Boleto(titulo);

        boleto.setLocalPagamento("Pagável preferencialmente na Rede Bradesco ou em "
                + "qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Senhor sacado, favor realizar o pagamento enquanto corre");

        //aqui muda o codigo de barras
        String codigoNumerico = pagamento.getCodigoBarra();
        boleto.addTextosExtras("txtFcLinhaDigitavel", codigoNumerico);
        boleto.addTextosExtras("txtRsLinhaDigitavel", codigoNumerico);

        BoletoViewer boletoViewer = new BoletoViewer(boleto);

        byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=boleto.pdf");

        OutputStream output = response.getOutputStream();
        output.write(pdfAsBytes);

        response.flushBuffer();

        try {
            String tipoLogin = request.getParameter("tipoLogin");
            request.setAttribute("tipoLogin", tipoLogin);
            request.setAttribute("operacao", "Editar");
            RequestDispatcher view = request.getRequestDispatcher("/manterPagamento.jsp");
            view.forward(request, response);

        } catch (ServletException ex) {
        } catch (IOException ex) {
            //} catch (ClassNotFoundException ex) {
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
            Logger.getLogger(ManterPagamentoController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(ManterPagamentoController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterPagamentoController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(ManterPagamentoController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
