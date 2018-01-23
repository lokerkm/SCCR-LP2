package modelo;

import dao.KitDAO;
import dao.LocalDeRetiradaDAO;
import java.sql.SQLException;
import java.util.List;

public class LocalDeRetirada {

    private int codLocalDeRetirada;
    private String horaInicial;
    private String horaFinal;
    private String dataInicial;
    private String dataFinal;
    private Endereco endereco;
    private String linkMapa;
    
    private int codEndereco;

    public LocalDeRetirada(int codLocalDeRetirada, String horaInicial, String horaFinal, String dataInicial, String dataFinal, Endereco endereco, String linkMapa) {
        this.codLocalDeRetirada = codLocalDeRetirada;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.endereco = endereco;
        this.linkMapa = linkMapa;
    }

    public String getLinkMapa() {
        return linkMapa;
    }

    public void setLinkMapa(String linkMapa) {
        this.linkMapa = linkMapa;
    }

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }

    public int getCodLocalDeRetirada() {
        return codLocalDeRetirada;
    }

    public void setCodLocalDeRetirada(int codLocalDeRetirada) {
        this.codLocalDeRetirada = codLocalDeRetirada;
    }

    public String getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return this.dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getHoraInicial() {
        return this.horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static List<LocalDeRetirada> obterLocaisDeRetirada()
            throws ClassNotFoundException {
        return LocalDeRetiradaDAO.obterLocaisDeRetirada();
    }

    public static LocalDeRetirada obterLocalDeRetirada(int codLocalDeRetirada)
            throws ClassNotFoundException, SQLException {
        return LocalDeRetiradaDAO.obterLocalDeRetirada(codLocalDeRetirada);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        LocalDeRetiradaDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        LocalDeRetiradaDAO.alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        LocalDeRetiradaDAO.excluir(this);
    }

}
