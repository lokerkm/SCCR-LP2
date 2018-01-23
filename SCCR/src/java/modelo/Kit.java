package modelo;

import dao.KitDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Kit {

    private int codKit;
    private String descricao;
    private boolean camisa;
    private String produtos;
    private float valorKit;
    private Evento evento;
    private LocalDeRetirada localDeRetirada;

    private int codEvento;
    private int codLocalDeRetirada;

    public Kit(int codKit, String descricao, boolean camisa, String produtos, float valorKit, Evento evento, LocalDeRetirada localDeRetirada) {
        this.codKit = codKit;
        this.descricao = descricao;
        this.camisa = camisa;
        this.produtos = produtos;
        this.valorKit = valorKit;
        this.evento = evento;
        this.localDeRetirada = localDeRetirada;
    }

    public float getValorKit() {
        return valorKit;
    }

    public void setValorKit(float valorKit) {
        this.valorKit = valorKit;
    }

    public LocalDeRetirada getLocalDeRetirada() {
        return localDeRetirada;
    }

    public void setLocalDeRetirada(LocalDeRetirada localDeRetirada) {
        this.localDeRetirada = localDeRetirada;
    }

    public int getCodLocalDeRetirada() {
        return codLocalDeRetirada;
    }

    public void setCodLocalDeRetirada(int codLocalDeRetirada) {
        this.codLocalDeRetirada = codLocalDeRetirada;
    }
    
    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public int getCodEvento() {
        return codEvento;
    }

    public int getCodKit() {
        return codKit;
    }

    public void setCodKit(int codKit) {
        this.codKit = codKit;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isCamisa() {
        return camisa;
    }

    public void setCamisa(boolean camisa) {
        this.camisa = camisa;
    }

    public String getProdutos() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos = produtos;
    }

    public float getPreco() {
        return valorKit;
    }

    public void setPreco(float preco) {
        this.valorKit = preco;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public static List<Kit> obterKits()
            throws ClassNotFoundException {
        return KitDAO.obterKits();
    }

    public static Kit obterKit(int codKit, int codEvento)
            throws ClassNotFoundException {
        return KitDAO.obterKit(codKit, codEvento);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        KitDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        KitDAO.alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        KitDAO.excluir(this);
    }

    public boolean getCamisa() {
        return camisa;
    }
}
