package modelo;

import dao.PagamentoDAO;
import java.sql.SQLException;
import java.util.List;

public class Pagamento {

    private int codPagamento;
    private float valorTotal;
    private boolean status;
    private String tipoPagamento;
    private String codigoBarra;

    public Pagamento(int codPagamento, float valorTotal, boolean status, String tipoPagamento, String codigoBarra) {
        this.codPagamento = codPagamento;
        this.valorTotal = valorTotal;
        this.status = status;
        this.tipoPagamento = tipoPagamento;
        this.codigoBarra = codigoBarra;
    }

    public Pagamento(int codPagamento, float valorTotal, String tipoPagamento, String codigoBarra) {
        this.codPagamento = codPagamento;
        this.valorTotal = valorTotal;
        this.tipoPagamento = tipoPagamento;
        this.codigoBarra = codigoBarra;
    }

    public Pagamento(int codPagamento) {
        this.codPagamento = codPagamento;
    }

    public Pagamento(int codPagamento, String codBarras) {
        this.codPagamento = codPagamento;
        this.codigoBarra = codBarras;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public int getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(int codPagamento) {
        this.codPagamento = codPagamento;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static List<Pagamento> obterPagamento()
            throws ClassNotFoundException {
        return PagamentoDAO.obterPagamentos();
    }

    public void gravar() throws SQLException,
            ClassNotFoundException {
        PagamentoDAO.gravar(this);
    }

    public void alterar() throws SQLException,
            ClassNotFoundException {
        PagamentoDAO.alterar(this);
    }

    public void excluir() throws SQLException,
            ClassNotFoundException {
        PagamentoDAO.excluir(this);
    }

    public static Pagamento obterPagamento(int codPagamento) throws ClassNotFoundException {
        return PagamentoDAO.obterPagamento(codPagamento);
    }

    public static void alterarStatus(int codigoBarra) throws SQLException,
            ClassNotFoundException {
        PagamentoDAO.alterarStatus(codigoBarra);
    }
}
