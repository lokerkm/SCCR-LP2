package dao;
//arrumar nome tabela banco de dados

import static dao.AdministradorDAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Administrador;
import modelo.Administrador;

public class AdministradorDAO {

    public static List<Administrador> obterAdministradores() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Administrador> administradores = new ArrayList<Administrador>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from administrador");
            while (rs.next()) {
                Administrador administrador = new Administrador(rs.getInt("codAdministrador"),
                        rs.getString("login"),
                        rs.getString("senha"));
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                administradores.add(administrador);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return administradores;
    }

    public static void fecharConexao(Connection conexao, Statement comando) {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void gravar(Administrador administrador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "INSERT INTO administrador"
                    + "(codAdministrador, login, senha) "
                    + "VALUES (?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, administrador.getCodAdministrador());
            comando.setString(2, administrador.getLogin());
            comando.setString(3, administrador.getSenha());

            comando.execute();
            comando.close();

            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Administrador administrador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "UPDATE administrador SET login=?,senha=? WHERE codAdministrador=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(3, administrador.getCodAdministrador());
            comando.setString(1, administrador.getLogin());
            comando.setString(2, administrador.getSenha());

            comando.execute();

            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Administrador administrador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from administrador where codAdministrador=" + administrador.getCodAdministrador();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Administrador obterAdministrador(int codAdministrador) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Administrador administrador = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from administrador  where codAdministrador=" + codAdministrador);
            rs.first();
            administrador = new Administrador(
                    rs.getInt("codAdministrador"),
                    rs.getString("login"),
                    rs.getString("senha"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return administrador;
    }
}
