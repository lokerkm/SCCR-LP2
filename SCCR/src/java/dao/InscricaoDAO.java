package dao;
//arrumar nome tabela banco de dados

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Inscricao;
import modelo.Pagamento;

public class InscricaoDAO {

    public static List<Inscricao> obterInscricoes() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Inscricao> inscricoes = new ArrayList<Inscricao>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from inscricao");
            while (rs.next()) {
                Inscricao inscricao = new Inscricao(rs.getInt("codInscricao"),
                        rs.getBoolean("statusRetirada"),
                        rs.getString("tam_camisa"),
                        rs.getInt("numeroPeito"),
                        rs.getFloat("precoTotal"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        rs.getString("data_inscricao"));

                inscricao.setCodAtleta(rs.getInt("atleta_codAtleta"));
                inscricao.setCodChip(rs.getInt("chip_codChip"));
                inscricao.setCodKit(rs.getInt("kit_codKit"));
                inscricao.setCodKitEvento(rs.getInt("kit_evento_codEvento"));
                inscricao.setCodPercurso(rs.getInt("percurso_codPercurso"));
                inscricao.setCodPercursoEvento(rs.getInt("percurso_evento_codEvento"));
                inscricao.setCodPagamento(rs.getInt("pagamento_codPagamento"));
                inscricoes.add(inscricao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return inscricoes;
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

    public static void gravar(Inscricao inscricao) throws SQLException, ClassNotFoundException {

        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "INSERT INTO inscricao(codInscricao, tam_camisa, statusRetirada, numeroPeito, precoTotal,"
                    + " data_inscricao, atleta_codAtleta , pagamento_codPagamento,"
                    + " kit_codKit, kit_evento_codEvento, chip_codChip, percurso_codPercurso, percurso_evento_codEvento)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, inscricao.getCodInscricao());
            comando.setString(2, inscricao.getTamCamisa());
            comando.setBoolean(3, inscricao.getStatusRetirada());
            comando.setInt(4, inscricao.getNumeroPeito());
            comando.setFloat(5, inscricao.getPrecoTotal());
            comando.setString(6, inscricao.getDataInscricao());
            comando.setInt(7, inscricao.getCodAtleta());
            comando.setInt(8, inscricao.getCodPagamento());
            comando.setInt(9, inscricao.getCodKit());
            comando.setInt(10, inscricao.getCodKitEvento());
            comando.setInt(11, inscricao.getCodChip());
            comando.setInt(12, inscricao.getCodPercurso());
            comando.setInt(13, inscricao.getCodPercursoEvento());

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }

    }

    public static void alterar(Inscricao inscricao) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "UPDATE inscricao SET tam_camisa=?,statusRetirada=?, numeroPeito=?,precoTotal=?,"
                    + " kit_codKit=?,kit_evento_codEvento=?  "
                    + "WHERE codInscricao=? and percurso_codPercurso=? and percurso_evento_codEvento=?";

            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, inscricao.getTamCamisa());
            comando.setBoolean(2, inscricao.getStatusRetirada());
            comando.setInt(3, inscricao.getNumeroPeito());
            comando.setFloat(4, inscricao.getPrecoTotal());
            // comando.setInt(5, inscricao.getCodPagamento());
            comando.setInt(5, inscricao.getCodKit());
            comando.setInt(6, inscricao.getCodKitEvento());
            comando.setInt(7, inscricao.getCodInscricao());
            comando.setInt(8, inscricao.getCodPercurso());
            comando.setInt(9, inscricao.getCodPercursoEvento());
            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Inscricao inscricao) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;

        try {
            conexao = BD.getConexao();
            stringSQL = "delete from inscricao where codInscricao=" + inscricao.getCodInscricao() + " and percurso_codPercurso=" + inscricao.getCodPercurso() + " and percurso_evento_codEvento=" + inscricao.getCodPercursoEvento();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Inscricao obterInscricao(int codInscricao, int codPercurso, int codEvento) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Inscricao inscricao = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from inscricao where codInscricao =" + codInscricao + " and percurso_codPercurso=" + codPercurso + " and percurso_evento_codEvento=" + codEvento);
            rs.first();

            inscricao = new Inscricao(rs.getInt("codInscricao"),
                    rs.getBoolean("statusRetirada"),
                    rs.getString("tam_camisa"),
                    rs.getInt("numeroPeito"),
                    rs.getFloat("precoTotal"),
                    null,
                    null,
                    null,
                    null,
                    null,
                    rs.getString("data_inscricao"));

            inscricao.setCodAtleta(rs.getInt("atleta_codAtleta"));
            inscricao.setCodChip(rs.getInt("chip_codChip"));
            inscricao.setCodKit(rs.getInt("kit_codKit"));
            inscricao.setCodKitEvento(rs.getInt("kit_evento_codEvento"));
            inscricao.setCodPercurso(rs.getInt("percurso_codPercurso"));
            inscricao.setCodPercursoEvento(rs.getInt("percurso_evento_codEvento"));
            inscricao.setCodPagamento(rs.getInt("pagamento_codPagamento"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return inscricao;
    }

    public static Inscricao obterInscricaoSimularAtleta(int codChip, int codEvento, int codAtleta) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Inscricao inscricao = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("SELECT * FROM inscricao WHERE inscricao.atleta_codAtleta= " + codAtleta + " AND inscricao.chip_codChip= " + codChip + " AND inscricao.percurso_evento_codEvento= " + codEvento);
            rs.first();

            inscricao = new Inscricao(rs.getInt("codInscricao"),
                    rs.getBoolean("statusRetirada"),
                    rs.getString("tam_camisa"),
                    rs.getInt("numeroPeito"),
                    rs.getFloat("precoTotal"),
                    null,
                    null,
                    null,
                    null,
                    null,
                    rs.getString("data_inscricao"));

            inscricao.setCodAtleta(rs.getInt("atleta_codAtleta"));
            inscricao.setCodChip(rs.getInt("chip_codChip"));
            inscricao.setCodKit(rs.getInt("kit_codKit"));
            inscricao.setCodKitEvento(rs.getInt("kit_evento_codEvento"));
            inscricao.setCodPercurso(rs.getInt("percurso_codPercurso"));
            inscricao.setCodPercursoEvento(rs.getInt("percurso_evento_codEvento"));
            inscricao.setCodPagamento(rs.getInt("pagamento_codPagamento"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return inscricao;
    }

    public static Inscricao obterInscricaoKit(int codInscricao, int codEvento) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Inscricao inscricao = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from inscricao where inscricao.codInscricao =" + codInscricao + " and inscricao.kit_evento_codEvento = " + codEvento);
            rs.first();

            inscricao = new Inscricao(rs.getInt("codInscricao"),
                    rs.getBoolean("statusRetirada"),
                    rs.getString("tam_camisa"),
                    rs.getInt("numeroPeito"),
                    rs.getFloat("precoTotal"),
                    null,
                    null,
                    null,
                    null,
                    null,
                    rs.getString("data_inscricao"));

            inscricao.setCodAtleta(rs.getInt("atleta_codAtleta"));
            inscricao.setCodChip(rs.getInt("chip_codChip"));
            inscricao.setCodKit(rs.getInt("kit_codKit"));
            inscricao.setCodKitEvento(rs.getInt("kit_evento_codEvento"));
            inscricao.setCodPercurso(rs.getInt("percurso_codPercurso"));
            inscricao.setCodPercursoEvento(rs.getInt("percurso_evento_codEvento"));
            inscricao.setCodPagamento(rs.getInt("pagamento_codPagamento"));
            inscricao.setPagamento(Pagamento.obterPagamento(inscricao.getCodPagamento()));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return inscricao;
    }

    public static Inscricao obterInscricaoPagamento(int codPagamento) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Inscricao inscricao = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from inscricao where inscricao.pagamento_codPagamento = " + codPagamento);
            rs.first();

            inscricao = new Inscricao(rs.getInt("codInscricao"),
                    rs.getBoolean("statusRetirada"),
                    rs.getString("tam_camisa"),
                    rs.getInt("numeroPeito"),
                    rs.getFloat("precoTotal"),
                    null,
                    null,
                    null,
                    null,
                    null,
                    rs.getString("data_inscricao"));

            inscricao.setCodAtleta(rs.getInt("atleta_codAtleta"));
            inscricao.setCodChip(rs.getInt("chip_codChip"));
            inscricao.setCodKit(rs.getInt("kit_codKit"));
            inscricao.setCodKitEvento(rs.getInt("kit_evento_codEvento"));
            inscricao.setCodPercurso(rs.getInt("percurso_codPercurso"));
            inscricao.setCodPercursoEvento(rs.getInt("percurso_evento_codEvento"));
            inscricao.setCodPagamento(rs.getInt("pagamento_codPagamento"));
            inscricao.setPagamento(Pagamento.obterPagamento(inscricao.getCodPagamento()));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return inscricao;
    }

}
