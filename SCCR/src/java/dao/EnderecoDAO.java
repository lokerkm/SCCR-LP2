package dao;
//arrumar nome tabela banco de dados

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Endereco;

public class EnderecoDAO {

    public static List<Endereco> obterEnderecos() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Endereco> enderecos = new ArrayList<Endereco>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from endereco");
            while (rs.next()) {
                Endereco endereco = new Endereco(rs.getInt("codEndereco"),
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("numeroLogradouro"),
                        rs.getString("complementoLogradouro"),
                        rs.getString("bairro"));
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getString("professorCoordenador")); exemplo do marco
                enderecos.add(endereco);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return enderecos;
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

    public static void gravar(Endereco endereco) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "INSERT INTO endereco(codEndereco, cep, logradouro, cidade, numeroLogradouro, complementoLogradouro, uf, bairro)"
                    + " VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando = conexao.prepareStatement(sql);
            comando.setInt(1, endereco.getCodEndereco());
            comando.setString(2, endereco.getCep());
            comando.setString(3, endereco.getLogradouro());
            comando.setString(4, endereco.getCidade());
            comando.setString(5, endereco.getNumeroLogradouro());
            comando.setString(6, endereco.getComplementoLogradouro());
            comando.setString(7, endereco.getUf());
            comando.setString(8, endereco.getBairro());

            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Endereco endereco) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "UPDATE endereco SET cep=?,"
                    + "logradouro=?,cidade=?,"
                    + "numeroLogradouro=?,complementoLogradouro=?,uf=?,bairro=? WHERE codEndereco=?";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, endereco.getCep());
            comando.setString(2, endereco.getLogradouro());
            comando.setString(3, endereco.getCidade());
            comando.setString(4, endereco.getNumeroLogradouro());
            comando.setString(5, endereco.getComplementoLogradouro());
            comando.setString(6, endereco.getUf());
            comando.setString(7, endereco.getBairro());
            comando.setInt(8, endereco.getCodEndereco());
            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Endereco endereco) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();

            stringSQL = "delete from endereco where codEndereco = " + endereco.getCodEndereco();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Endereco obterEndereco(int codEndereco) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Endereco endereco = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from endereco where codEndereco=" + codEndereco);
            rs.first();
            endereco = new Endereco(rs.getInt("codEndereco"),
                    rs.getString("cep"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("uf"),
                    rs.getString("numeroLogradouro"),
                    rs.getString("complementoLogradouro"),
                    rs.getString("bairro"));
            System.out.println(endereco.getCep());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return endereco;
    }
}
