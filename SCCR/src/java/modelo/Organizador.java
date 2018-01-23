package modelo;

import dao.OrganizadorDAO;
import java.sql.SQLException;
import java.util.List;

public class Organizador extends Usuario {

    private int codOrganizador;

    public Organizador(int codOrganizador, String nome, String cpf, String rg, String dataNascimento, boolean sexo, String senha, String email, String telFixo, String telCel, int codUsuario, Endereco endereco) {
        super(nome, cpf, rg, dataNascimento, sexo, senha, email, telFixo, telCel, codUsuario, endereco);
        this.codOrganizador = codOrganizador;
    }

    public void gravar() throws SQLException,
            ClassNotFoundException {
        OrganizadorDAO.gravar(this);
    }

    public void alterar() throws SQLException,
            ClassNotFoundException {
        OrganizadorDAO.alterar(this);
    }

    public void excluir() throws SQLException,
            ClassNotFoundException {
        OrganizadorDAO.excluir(this);
    }

    public int getCodOrganizador() {
        return codOrganizador;
    }

    public void setCodOrganizador(int codOrganizador) {
        this.codOrganizador = codOrganizador;
    }

    public static Organizador obterOrganizador(int codOrganizador) throws ClassNotFoundException {
        return OrganizadorDAO.obterOrganizador(codOrganizador);
    }

    public static List<Organizador> obterOrganizadores()
            throws ClassNotFoundException {
        return OrganizadorDAO.obterOrganizadores();
    }

}
