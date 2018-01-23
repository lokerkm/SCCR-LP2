package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Organizador;
import modelo.Inscricao;

public class OrganizadorDAO {

    public static List<Organizador> obterOrganizadores() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Organizador> organizadores = new ArrayList<Organizador>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from organizador, usuario where usuario.codUsuario = organizador.usuario_codUsuario");//select * from organizador, usuario where usuario.codUsuario = organizador.usuario_codUsuario
            while (rs.next()) {
                Organizador organizador = new Organizador(
                        rs.getInt("codOrganizador"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("rg"),
                        rs.getString("dataNascimento"),
                        rs.getBoolean("sexo"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getString("telFixo"),
                        rs.getString("telCel"),
                        rs.getInt("usuario_codUsuario"),
                        null);
                organizador.setCodEndereco(rs.getInt("endereco_codEndereco"));
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                organizadores.add(organizador);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return organizadores;
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

    public static void gravar(Organizador organizador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "INSERT INTO usuario"
                    + "(codUsuario, nome, cpf, rg, dataNascimento, sexo, senha, email, "
                    + "telFixo, telCel, endereco_codEndereco) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, organizador.getCodUsuario());
            comando.setString(2, organizador.getNome());
            comando.setString(3, organizador.getCpf());
            comando.setString(4, organizador.getRg());
            comando.setString(5, organizador.getDataNascimento());
            comando.setBoolean(6, organizador.isSexo());
            comando.setString(7, organizador.getSenha());
            comando.setString(8, organizador.getEmail());
            comando.setString(9, organizador.getTelFixo());
            comando.setString(10, organizador.getTelCel());
            comando.setInt(11, organizador.getEndereco().getCodEndereco());
            comando.execute();
            comando.close();
            String sql2 = "INSERT INTO organizador(codOrganizador,usuario_codUsuario) "
                    + "VALUES (?,?)";
            PreparedStatement comando2 = conexao.prepareStatement(sql2);
            comando2.setInt(1, organizador.getCodOrganizador());
            comando2.setInt(2, organizador.getCodUsuario());
            comando2.execute();

            comando2.close();

            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Organizador organizador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "UPDATE usuario SET nome=?,cpf=?,rg=?,dataNascimento=?,"
                    + "sexo=?,senha=?,email=?,telFixo=?,telCel=?,endereco_codEndereco=? WHERE codUsuario=?";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, organizador.getNome());
            comando.setString(2, organizador.getCpf());
            comando.setString(3, organizador.getRg());
            comando.setString(4, organizador.getDataNascimento());
            comando.setBoolean(5, organizador.isSexo());
            comando.setString(6, organizador.getSenha());
            comando.setString(7, organizador.getEmail());
            comando.setString(8, organizador.getTelFixo());
            comando.setString(9, organizador.getTelCel());
            comando.setInt(10, organizador.getEndereco().getCodEndereco());
            comando.setInt(11, organizador.getCodUsuario());
            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Organizador organizador) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();

            stringSQL = "delete from organizador where codOrganizador= " + organizador.getCodOrganizador();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
            stringSQL = "delete from usuario where codUsuario= " + organizador.getCodUsuario();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Organizador obterOrganizador(int codOrganizador) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Organizador organizador = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from organizador, usuario  where codOrganizador=" + codOrganizador + " and usuario.codUsuario=organizador.usuario_codUsuario");
            rs.first();
            organizador = new Organizador(
                    rs.getInt("codOrganizador"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("rg"),
                    rs.getString("dataNascimento"),
                    rs.getBoolean("sexo"),
                    rs.getString("senha"),
                    rs.getString("email"),
                    rs.getString("telFixo"),
                    rs.getString("telCel"),
                    rs.getInt("usuario_codUsuario"),
                    null);
            organizador.setCodEndereco(rs.getInt("endereco_codEndereco"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return organizador;
    }
}
