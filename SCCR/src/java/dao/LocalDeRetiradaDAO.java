package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.LocalDeRetirada;

public class LocalDeRetiradaDAO {

    public static List<LocalDeRetirada> obterLocaisDeRetirada() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<LocalDeRetirada> locaisDeRetirada = new ArrayList<LocalDeRetirada>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from local_retirada,endereco where local_retirada.endereco_codEndereco=endereco.codEndereco");
            while (rs.next()) {
                LocalDeRetirada localDeRetirada = new LocalDeRetirada(rs.getInt("codLocal"),
                        rs.getString("horaInicial"),
                        rs.getString("horaFinal"),
                        rs.getString("dataInicial"),
                        rs.getString("dataFinal"),
                        null,
                        rs.getString("linkMapa")
                );
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                localDeRetirada.setCodEndereco(rs.getInt("codEndereco"));
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                locaisDeRetirada.add(localDeRetirada);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return locaisDeRetirada;
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

    public static void gravar(LocalDeRetirada localDeRetirada) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {

            conexao = BD.getConexao();
            String sql = "insert into local_retirada(codLocal, horaInicial, horaFinal, dataInicial, dataFinal, endereco_codEndereco, linkMapa ) "
                    + "values (?,?,?,?,?,?,?)";

            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, localDeRetirada.getCodLocalDeRetirada());
            comando.setString(2, localDeRetirada.getHoraInicial());
            comando.setString(3, localDeRetirada.getHoraFinal());
            comando.setString(4, localDeRetirada.getDataInicial());
            comando.setString(5, localDeRetirada.getDataFinal());
            comando.setInt(6, localDeRetirada.getCodEndereco());
            comando.setString(7, localDeRetirada.getLinkMapa());

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(LocalDeRetirada localDeRetirada) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update local_retirada set horaInicial = ?, horaFinal = ?, dataInicial = ?, dataFinal = ?,  endereco_codEndereco = ?, linkMapa = ?"
                    + " where codLocal = ?";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, localDeRetirada.getHoraInicial());
            comando.setString(2, localDeRetirada.getHoraFinal());
            comando.setString(3, localDeRetirada.getDataInicial());
            comando.setString(4, localDeRetirada.getDataFinal());
            comando.setInt(5, localDeRetirada.getCodEndereco());
            comando.setString(6, localDeRetirada.getLinkMapa());

            comando.setInt(7, localDeRetirada.getCodLocalDeRetirada());
            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(LocalDeRetirada localDeRetirada) throws SQLException, ClassNotFoundException {

        Connection conexao = null;
        Statement comando = null;
        String stringSQL;

        try {
            conexao = BD.getConexao();
            stringSQL = "delete from local_retirada where codLocal = " + localDeRetirada.getCodLocalDeRetirada();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static LocalDeRetirada obterLocalDeRetirada(int codLocalDeRetirada) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        LocalDeRetirada localDeRetirada = null;

        try {

            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from local_retirada where codLocal = " + codLocalDeRetirada);
            rs.first();

            localDeRetirada = new LocalDeRetirada(rs.getInt("codLocal"),
                        rs.getString("horaInicial"),
                        rs.getString("horaFinal"),
                        rs.getString("dataInicial"),
                        rs.getString("dataFinal"),
                        null,
                        rs.getString("linkMapa"));

            localDeRetirada.setCodEndereco(rs.getInt("endereco_codEndereco"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return localDeRetirada;
    }

}
