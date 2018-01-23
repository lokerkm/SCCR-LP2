package dao;
//arrumar nome tabela banco de dados

import static dao.EventoDAO.fecharConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.DistribuicaoDePontos;
import modelo.Evento;
import modelo.Ranking;

public class RankingDAO {

    public static List<Ranking> obterRankings() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Ranking> rankings = new ArrayList<Ranking>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from ranking");
            while (rs.next()) {
                Ranking ranking = new Ranking(rs.getInt("codRanking"),
                        rs.getString("nome")
                /*ArrayList<Float>) rs.getArray("distribuicaoPontos"*/);
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco

                rankings.add(ranking);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return rankings;
    }

    public static List<Ranking> obterRankingsEvento(int codEvento) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Ranking> rankings = new ArrayList<Ranking>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("SELECT codRanking FROM lista_ranking,ranking WHERE lista_ranking.ranking_codRanking=ranking.codRanking AND lista_ranking.evento_codEvento= " + codEvento);
            while (rs.next()) {
                Ranking ranking = Ranking.obterRanking(rs.getInt("codRanking"));
                rankings.add(ranking);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return rankings;
    }

    public static void gravar(Ranking ranking) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into ranking(codRanking, nome)"
                    + "values (?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, ranking.getCodRanking());
            comando.setString(2, ranking.getNome());

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Ranking ranking) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update ranking set nome = ? where codRanking = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(2, ranking.getCodRanking());
            comando.setString(1, ranking.getNome());

            comando.execute();

            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Ranking ranking) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from distribuicao_pontos where ranking_codRanking= " + ranking.getCodRanking();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
            stringSQL = "delete from ranking where codRanking= " + ranking.getCodRanking();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void excluirEventoDoRanking(int codEvento, int codRanking) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "DELETE FROM `lista_ranking` where ranking_codRanking=" + codRanking + " and evento_codEvento=" + codEvento;
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static void incluirEventoDoRanking(int codEvento, int codRanking) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into lista_ranking(ranking_codRanking, evento_codEvento)"
                    + "values (" + codRanking + " , " + codEvento + ")";
            comando = conexao.prepareStatement(sql);
            comando.execute(sql);
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static Ranking obterRanking(int codRanking) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Ranking ranking = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery(
                    "select * from ranking where codRanking = " + codRanking);
            rs.first();
            ranking = new Ranking(rs.getInt("codRanking"),
                    rs.getString("nome")
            /*ArrayList<Float>) rs.getArray("distribuicaoPontos"*/);
            rs = comando.executeQuery("SELECT * FROM distribuicao_pontos WHERE distribuicao_pontos.ranking_codRanking= " + codRanking);
            ArrayList<DistribuicaoDePontos> distribuicoes = new ArrayList<DistribuicaoDePontos>();
            while (rs.next()) {
                DistribuicaoDePontos distribuicao = DistribuicaoDePontos.obterDistribuicaoDePontos(rs.getInt("codDistribuicaoPontos"), codRanking);
                distribuicoes.add(distribuicao);
            }
            ranking.setDistribuicaoPontos(distribuicoes);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return ranking;
    }

    public static List<DistribuicaoDePontos> obterDistribuicao(int codRanking) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<DistribuicaoDePontos> distribuicaoLista = new ArrayList<DistribuicaoDePontos>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from distribuicao_pontos where ranking_codRanking = " + codRanking);  //colocar um ASC aqui depois
            while (rs.next()) {
                DistribuicaoDePontos distribuicao = new DistribuicaoDePontos(rs.getInt("codDistribuicaoPontos"),
                        rs.getInt("colocacao"),
                        rs.getFloat("pontos"),
                        null);
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                distribuicaoLista.add(distribuicao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return distribuicaoLista;
    }

    public static List<Evento> obterEventosDoRanking(int codRanking) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Evento> eventos = new ArrayList<Evento>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("SELECT * FROM evento,ranking,lista_ranking WHERE "
                    + " evento.codEvento = lista_ranking.evento_codEvento AND "
                    + " ranking.codRanking = lista_ranking.ranking_codRanking AND " + " ranking.codRanking= " + codRanking);
            while (rs.next()) {
                Evento evento = new Evento(rs.getInt("codEvento"),
                        rs.getString("titulo"),
                        rs.getString("linkMapa"),
                        rs.getString("localLargada"),
                        rs.getString("duracao"),
                        rs.getString("dataEvento"),
                        rs.getString("horaLargada"),
                        rs.getInt("maxParticipantes"),
                        null,
                        null);

                evento.setCodEndereco(rs.getInt("endereco_codEndereco"));
                evento.setCodOrganizador(rs.getInt("organizador_codOrganizador"));
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                {
                    eventos.add(evento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return eventos;
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
