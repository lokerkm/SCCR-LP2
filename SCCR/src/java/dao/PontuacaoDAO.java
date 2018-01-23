package dao;
//arrumar nome tabela banco de dados

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Atleta;
import modelo.Pontuacao;

public class PontuacaoDAO {

    public static List<Pontuacao> obterPontuacoes() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Pontuacao> pontuacoes = new ArrayList<Pontuacao>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from pontuacao,atleta,ranking "
                    + "where pontuacao.atleta_codAtleta=atleta.codAtleta "
                    + "and pontuacao.ranking_codRanking=ranking.codRanking");
            while (rs.next()) {
                Pontuacao pontuacao = new Pontuacao(null,
                        null,
                        rs.getInt("codPontuacao"),
                        rs.getFloat("pontos")
                );
                pontuacao.setCodAtleta(rs.getInt("atleta_codAtleta"));
                pontuacao.setCodRanking(rs.getInt("ranking_codRanking"));
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                pontuacoes.add(pontuacao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return pontuacoes;
    }

    public static List<Pontuacao> obterPontuacoesRanking(int codRanking) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Pontuacao> pontuacoes = new ArrayList<Pontuacao>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("SELECT pontuacao.codPontuacao,atleta.codAtleta,SUM(pontuacao.pontos) pontosTotais  "
                    + "FROM pontuacao,atleta,ranking  "
                    + "WHERE pontuacao.atleta_codAtleta = atleta.codAtleta  "
                    + "AND pontuacao.ranking_codRanking = " + codRanking
                    + " AND pontuacao.ranking_codRanking= ranking.codRanking "
                    + "GROUP BY atleta.codAtleta ORDER BY `pontos`  DESC");
            while (rs.next()) {
                Pontuacao pontuacao = new Pontuacao(null,
                        null,
                        rs.getInt("codPontuacao"),
                        rs.getFloat("pontosTotais")
                );
                pontuacao.setCodAtleta(rs.getInt("codAtleta"));
                Atleta atleta = Atleta.obterAtleta(pontuacao.getCodAtleta());
                pontuacao.setAtleta(atleta);
                pontuacao.setCodRanking(codRanking);
                pontuacoes.add(pontuacao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return pontuacoes;
    }

    public static void gravar(Pontuacao pontuacao) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into pontuacao(codPontuacao, pontos, atleta_codAtleta, ranking_codRanking)"
                    + " values (?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, pontuacao.getCodPontuacao());
            comando.setFloat(2, pontuacao.getPontos());
            comando.setInt(3, pontuacao.getCodAtleta());
            comando.setInt(4, pontuacao.getCodRanking());

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Pontuacao pontuacao) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update pontuacao set pontos = ?,  atleta_codAtleta = ? where codPontuacao = ? and ranking_codRanking = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(3, pontuacao.getCodPontuacao());
            comando.setFloat(1, pontuacao.getPontos());
            comando.setInt(2, pontuacao.getCodAtleta());
            comando.setInt(4, pontuacao.getCodRanking());
            comando.execute();

            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Pontuacao pontuacao) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from pontuacao where codPontuacao= " + pontuacao.getCodPontuacao() + " and ranking_codRanking = " + pontuacao.getCodRanking();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Pontuacao obterPontuacao(int codPontuacao, int codRanking) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Pontuacao pontuacao = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery(
                    "select * from pontuacao where codPontuacao = " + codPontuacao + " and ranking_codRanking= " + codRanking);
            rs.first();
            pontuacao = new Pontuacao(null,
                    null,
                    rs.getInt("codPontuacao"),
                    rs.getFloat("pontos")
            );
            pontuacao.setCodAtleta(rs.getInt("atleta_codAtleta"));
            pontuacao.setCodRanking(rs.getInt("ranking_codRanking"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return pontuacao;
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

}
