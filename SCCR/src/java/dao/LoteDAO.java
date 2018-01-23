package dao;
//arrumar nome tabela banco de dados

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Lote;
import modelo.Evento;

public class LoteDAO {

    public static List<Lote> obterLotes() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Lote> lotes = new ArrayList<Lote>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from lote, evento where lote.evento_codEvento = evento.codEvento");
            while (rs.next()) {

                Lote lote = new Lote(rs.getInt("codLote"),
                        rs.getString("dataInicial"),
                        rs.getString("dataFinal"),
                        rs.getFloat("valor"),
                        null);
                
                lote.setCodEvento(rs.getInt("evento_codEvento"));

                lotes.add(lote);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return lotes;
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

    public static void gravar(Lote lote) throws SQLException, ClassNotFoundException {
        Connection conexao = null;

        try {

            conexao = BD.getConexao();
            String sql = "INSERT INTO lote(codLote, dataInicial, dataFinal, valor, evento_codEvento)"
                    + " values (?,?,?,?,?)";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, lote.getCodLote());
            comando.setString(2, lote.getDataInicial());
            comando.setString(3, lote.getDataFinal());
            comando.setFloat(4, lote.getValor());
            comando.setInt(5, lote.getCodEvento());

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Lote lote) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update lote set  dataInicial = ?, dataFinal = ?, valor = ?"
                    + "where codLote = ? and evento_codEvento = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, lote.getDataInicial());
            comando.setString(2, lote.getDataFinal());
            comando.setFloat(3, lote.getValor());
            comando.setInt(4, lote.getCodLote());
            comando.setInt(5, lote.getCodEvento());
            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Lote lote) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;

        try {
            conexao = BD.getConexao();
            stringSQL = "delete from lote where codLote = " + lote.getCodLote() + " and evento_codEvento = " + lote.getCodEvento();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Lote obterLote(int codLote, int codEvento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Lote lote = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from lote where codLote = " + codLote + " and evento_codEvento = " + codEvento);
            rs.first();

            lote = new Lote(rs.getInt("codLote"),
                    rs.getString("dataInicial"),
                    rs.getString("dataFinal"),
                    rs.getFloat("valor"),
                    null);

            lote.setCodEvento(codEvento);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return lote;
    }

}
