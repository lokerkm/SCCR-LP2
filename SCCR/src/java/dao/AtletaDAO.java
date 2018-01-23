package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Atleta;
import modelo.Inscricao;

public class AtletaDAO {

    public static List<Atleta> obterAtletas() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Atleta> atletas = new ArrayList<Atleta>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from atleta, usuario where usuario.codUsuario = atleta.usuario_codUsuario");//select * from atleta, usuario where usuario.codUsuario = atleta.usuario_codUsuario
            while (rs.next()) {
                Atleta atleta = new Atleta(
                        rs.getInt("codAtleta"),
                        rs.getString("nomeEmerg"),
                        rs.getString("telEmerg"),
                        rs.getString("apelido"),
                        rs.getFloat("pace"),
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
                atleta.setCodEndereco(rs.getInt("endereco_codEndereco"));

                Boolean TESTE = rs.getBoolean("sexo");
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                atletas.add(atleta);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return atletas;
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

    public static void gravar(Atleta atleta) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "INSERT INTO usuario"
                    + "(codUsuario, nome, cpf, rg, dataNascimento, sexo, senha, email, "
                    + "telFixo, telCel, endereco_codEndereco) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, atleta.getCodUsuario());
            comando.setString(2, atleta.getNome());
            comando.setString(3, atleta.getCpf());
            comando.setString(4, atleta.getRg());
            comando.setString(5, atleta.getDataNascimento());
            comando.setBoolean(6, atleta.isSexo());
            comando.setString(7, atleta.getSenha());
            comando.setString(8, atleta.getEmail());
            comando.setString(9, atleta.getTelFixo());
            comando.setString(10, atleta.getTelCel());
            comando.setInt(11, atleta.getEndereco().getCodEndereco());
            comando.execute();
            comando.close();
            String sql2 = "INSERT INTO atleta(codAtleta, nomeEmerg, telEmerg, pace, apelido, usuario_codUsuario) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement comando2 = conexao.prepareStatement(sql2);
            comando2.setInt(1, atleta.getCodAtleta());
            comando2.setString(2, atleta.getNomeEmerg());
            comando2.setString(3, atleta.getTelEmerg());
            comando2.setFloat(4, atleta.getPace());
            comando2.setString(5, atleta.getApelido());
            comando2.setInt(6, atleta.getCodUsuario());
            comando2.execute();

            comando2.close();

            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Atleta atleta) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "UPDATE usuario SET nome=?,cpf=?,rg=?,dataNascimento=?,"
                    + "sexo=?,senha=?,email=?,telFixo=?,telCel=?,endereco_codEndereco=? WHERE codUsuario=?";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, atleta.getNome());
            comando.setString(2, atleta.getCpf());
            comando.setString(3, atleta.getRg());
            comando.setString(4, atleta.getDataNascimento());
            Boolean testeAntes = atleta.isSexo();
            comando.setBoolean(5, atleta.isSexo());
            Boolean testeDepois = atleta.isSexo();
            comando.setString(6, atleta.getSenha());
            comando.setString(7, atleta.getEmail());
            comando.setString(8, atleta.getTelFixo());
            comando.setString(9, atleta.getTelCel());
            comando.setInt(10, atleta.getEndereco().getCodEndereco());
            comando.setInt(11, atleta.getCodUsuario());
            comando.execute();

            sql = "UPDATE atleta SET nomeEmerg=?,telEmerg=?,pace=?,"
                    + "apelido=?,usuario_codUsuario=? WHERE codAtleta=?";
            comando = conexao.prepareStatement(sql);

            comando.setString(1, atleta.getNomeEmerg());
            comando.setString(2, atleta.getTelEmerg());
            comando.setFloat(3, atleta.getPace());
            comando.setString(4, atleta.getApelido());
            comando.setInt(5, atleta.getCodUsuario());
            comando.setInt(6, atleta.getCodAtleta());
            comando.execute();
//se for null usar if else

            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Atleta atleta) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from inscricao where atleta_codAtleta= " + atleta.getCodAtleta();

            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
            stringSQL = "delete from atleta where codAtleta= " + atleta.getCodAtleta();

            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
            stringSQL = "delete from usuario where codUsuario= " + atleta.getCodUsuario();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Atleta obterAtleta(int codAtleta) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Atleta atleta = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from atleta, usuario  where codAtleta=" + codAtleta + " and usuario.codUsuario=atleta.usuario_codUsuario");
            rs.first();
            atleta = new Atleta(
                    rs.getInt("codAtleta"),
                    rs.getString("nomeEmerg"),
                    rs.getString("telEmerg"),
                    rs.getString("apelido"),
                    rs.getFloat("pace"),
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
            atleta.setCodEndereco(rs.getInt("endereco_codEndereco"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return atleta;
    }

    public static void alterarPace(Atleta atleta) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "UPDATE atleta SET pace=? WHERE codAtleta=?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setFloat(1, atleta.getPace());
            comando.setInt(2, atleta.getCodAtleta());
            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }
}
