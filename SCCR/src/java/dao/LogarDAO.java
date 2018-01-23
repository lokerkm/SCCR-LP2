package dao;

import static dao.AdministradorDAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LogarDAO {

    public static boolean logarAdiministrador(String usuario, String senha) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "select count(*) quantidade from administrador "
                    + "where login = ? and senha = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando = conexao.prepareStatement(sql);
            comando.setString(1, usuario);
            comando.setString(2, senha);
            comando.execute();
            ResultSet rs = comando.getResultSet();
            rs.first();
            int i = rs.getInt("quantidade");
            if (i == 1) {
                comando.close();
                conexao.close();
                return true;
            }

            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
        return false;
    }

    public static boolean logarAtleta(String usuario, String senha) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "select count(*) quantidade from atleta,usuario "
                    + "where atleta.usuario_codUsuario= usuario.codUsuario "
                    + "AND email = ? and senha =?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando = conexao.prepareStatement(sql);
            comando.setString(1, usuario);
            comando.setString(2, senha);
            comando.execute();
            ResultSet rs = comando.getResultSet();
            rs.first();
            int i = rs.getInt("quantidade");
            if (i == 1) {
                comando.close();
                conexao.close();
                return true;
            }

            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
        return false;
    }

    public static boolean logarOrganizador(String usuario, String senha) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "select count(*) quantidade from organizador,usuario "
                    + "where organizador.usuario_codUsuario= usuario.codUsuario "
                    + "AND email = ? and senha =?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando = conexao.prepareStatement(sql);
            comando.setString(1, usuario);
            comando.setString(2, senha);
            comando.execute();
            ResultSet rs = comando.getResultSet();
            rs.first();
            int i = rs.getInt("quantidade");
            if (i == 1) {
                comando.close();
                conexao.close();
                return true;
            }

            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
        return false;
    }
}
