package modelo;

import dao.AtletaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Atleta extends Usuario {

    private int codAtleta;
    private String nomeEmerg;
    private String telEmerg;
    private String apelido;
    private float pace;
    private ArrayList<Pontuacao> listaPontuacoes;

    private ArrayList listaCodPontuacoes;

    public Atleta(int codAtleta, String nomeEmerg, String telEmerg, String apelido, float pace, String nome, String cpf, String rg, String dataNascimento, boolean sexo, String senha, String email, String telFixo, String telCel, int codUsuario, Endereco endereco) {
        super(nome, cpf, rg, dataNascimento, sexo, senha, email, telFixo, telCel, codUsuario, endereco);
        this.codAtleta = codAtleta;
        this.nomeEmerg = nomeEmerg;
        this.telEmerg = telEmerg;
        this.apelido = apelido;
        this.pace = pace;
    }

    public Atleta(int codAtleta, String nomeEmerg, String telEmerg, String apelido, String nome, String cpf, String rg, String dataNascimento, boolean sexo, String senha, String email, String telFixo, String telCel, int codUsuario, Endereco endereco) {
        super(nome, cpf, rg, dataNascimento, sexo, senha, email, telFixo, telCel, codUsuario, endereco);
        this.codAtleta = codAtleta;
        this.nomeEmerg = nomeEmerg;
        this.telEmerg = telEmerg;
        this.apelido = apelido;

    }

    public ArrayList<Pontuacao> getListaPontuacoes() {
        return listaPontuacoes;
    }

    public void setListaPontuacoes(ArrayList<Pontuacao> listaPontuacoes) {
        this.listaPontuacoes = listaPontuacoes;
    }

    public ArrayList getCodPontuacoes() {
        return listaCodPontuacoes;
    }

    public void setCodPontuacoes(ArrayList codPontuacoes) {
        this.listaCodPontuacoes = codPontuacoes;
    }

    public static List<Atleta> obterAtletas()
            throws ClassNotFoundException {
        return AtletaDAO.obterAtletas();
    }

    public int getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(int codAtleta) {
        this.codAtleta = codAtleta;
    }

    public String getNomeEmerg() {
        return nomeEmerg;
    }

    public void setNomeEmerg(String nomeEmerg) {
        this.nomeEmerg = nomeEmerg;
    }

    public String getTelEmerg() {
        return telEmerg;
    }

    public void setTelEmerg(String telEmerg) {
        this.telEmerg = telEmerg;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public float getPace() {
        return pace;
    }

    public void setPace(float pace) {
        this.pace = pace;
    }

    public void gravar() throws SQLException, ClassNotFoundException {

        AtletaDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        AtletaDAO.alterar(this);
    }

    public void alterarPace() throws SQLException, ClassNotFoundException {
        AtletaDAO.alterarPace(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        AtletaDAO.excluir(this);
    }

    public static Atleta obterAtleta(int codAtleta) throws ClassNotFoundException {
        return AtletaDAO.obterAtleta(codAtleta);
    }
}
