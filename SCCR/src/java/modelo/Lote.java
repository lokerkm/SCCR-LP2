package modelo;

import dao.LoteDAO;
import java.sql.SQLException;
import java.util.List;

public class Lote {

    private int codLote;
    private String dataInicial;
    private String dataFinal;
    private float valor;
    private Evento evento;
    
    private int codEvento;

    public Lote(int codLote, String dataInicial, String dataFinal, float valor, Evento evento) {
        this.codLote = codLote;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.valor = valor;
        this.evento = evento;
    }


    public int getCodLote() {
        return codLote;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public void setCodLote(int codLote) {
        this.codLote = codLote;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public static List<Lote> obterLotes()
            throws ClassNotFoundException {
        return LoteDAO.obterLotes();
    }

    public static Lote obterLote(int codLote, int codEvento)
            throws ClassNotFoundException, SQLException {
        return LoteDAO.obterLote(codLote, codEvento);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        LoteDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        LoteDAO.alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        LoteDAO.excluir(this);
    }

}
