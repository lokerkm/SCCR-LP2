package modelo;


import dao.AdministradorDAO;
import java.sql.SQLException;

import java.util.List;

public class Administrador {

    private int codAdministrador;
    private String login;
    private String senha;

    public Administrador(int codAdministrador, String login, String senha) {
        this.codAdministrador = codAdministrador;
        this.login = login;
        this.senha = senha;
    }

    public int getCodAdministrador() {
        return codAdministrador;
    }

    public void setCodAdministrador(int codAdministrador) {
        this.codAdministrador = codAdministrador;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static List<Administrador> obterAdministrador()
            throws ClassNotFoundException {
        return AdministradorDAO.obterAdministradores();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        AdministradorDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        AdministradorDAO.alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        AdministradorDAO.excluir(this);
    }

    public static Administrador obterAdministrador(int codAdministrador) throws ClassNotFoundException {
        return AdministradorDAO.obterAdministrador(codAdministrador);
    }
}
