package modelo;

import dao.EnderecoDAO;
import dao.EnderecoDAO;
import java.sql.SQLException;
import java.util.List;

public class Endereco {

    private int codEndereco;
    private String cep;
    private String logradouro;
    private String cidade;
    private String uf;
    private String numeroLogradouro;
    private String complementoLogradouro;
    private String bairro;

    public Endereco(int codEndereco, String cep, String logradouro, String cidade, String uf, String numeroLogradouro, String complementoLogradouro, String bairro) {
        this.codEndereco = codEndereco;
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uf = uf;
        this.numeroLogradouro = numeroLogradouro;
        this.complementoLogradouro = complementoLogradouro;
        this.bairro = bairro;
    }

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNumeroLogradouro() {
        return numeroLogradouro;
    }

    public void setNumeroLogradouro(String numeroLogradouro) {
        this.numeroLogradouro = numeroLogradouro;
    }
    
    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String Bairro) {
        this.bairro = bairro;
    }

    public String getComplementoLogradouro() {
        return complementoLogradouro;
    }

    public void setComplementoLogradouro(String complementoLogradouro) {
        this.complementoLogradouro = complementoLogradouro;
    }

    public static List<Endereco> obterEnderecos()
            throws ClassNotFoundException {
        return EnderecoDAO.obterEnderecos();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        EnderecoDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        EnderecoDAO.alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        EnderecoDAO.excluir(this);
    }

    public static Endereco obterEndereco(int codEndereco) throws ClassNotFoundException {
        return EnderecoDAO.obterEndereco(codEndereco);
    }
}
