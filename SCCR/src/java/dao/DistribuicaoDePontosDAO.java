package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.DistribuicaoDePontos;

public class DistribuicaoDePontosDAO {

    public static List<DistribuicaoDePontos> obterDistribuicaoDePontos() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<DistribuicaoDePontos> distribuicaoPontos = new ArrayList<DistribuicaoDePontos>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from distribuicao_pontos,ranking where distribuicao_pontos.ranking_codRanking= ranking.codRanking");
            while (rs.next()) {
                DistribuicaoDePontos distribuicaoDePontos = new DistribuicaoDePontos(rs.getInt("codDistribuicaoPontos"),
                        rs.getInt("colocacao"),
                        rs.getFloat("pontos"),
                        null);
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                distribuicaoPontos.add(distribuicaoDePontos);
                distribuicaoDePontos.setCodRanking(rs.getInt("ranking_codRanking"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return distribuicaoPontos;
    }

    public static void gravar(DistribuicaoDePontos distribuicaoPontos) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into distribuicao_pontos(codDistribuicaoPontos, colocacao, pontos, ranking_codRanking) "
                    + "values (?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, distribuicaoPontos.getCodDistribuicaoDePontos());
            comando.setInt(2, distribuicaoPontos.getColocacao());
            comando.setFloat(3, distribuicaoPontos.getPontos());
            comando.setInt(4, distribuicaoPontos.getCodRanking());
            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(DistribuicaoDePontos distribuicaoPontos) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update distribuicao_pontos set colocacao = ?,  pontos = ? where codDistribuicaoPontos = ? and ranking_codRanking = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(3, distribuicaoPontos.getCodDistribuicaoDePontos()); //eu tava aqui
            comando.setInt(4,distribuicaoPontos.getCodRanking());
            comando.setInt(1, distribuicaoPontos.getColocacao());
            comando.setFloat(2, distribuicaoPontos.getPontos());
            
            comando.execute();

            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(DistribuicaoDePontos distribuicaoPontos) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from distribuicao_pontos where codDistribuicaoPontos= " + distribuicaoPontos.getCodDistribuicaoDePontos()+ " AND ranking_codRanking= " + distribuicaoPontos.getCodRanking();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }
   
    public static void excluirDoRanking(int codRanking) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from distribuicao_pontos where ranking_codRanking= " + codRanking;
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static DistribuicaoDePontos obterDistribuicaoDePontos(int codDistribuicaoPontos, int codRanking) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        DistribuicaoDePontos distribuicaoPontos = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery(
                    "select * from distribuicao_pontos where codDistribuicaoPontos = " + codDistribuicaoPontos + " and ranking_codRanking = "+ codRanking);
            rs.first();
            distribuicaoPontos = new DistribuicaoDePontos(rs.getInt("codDistribuicaoPontos"),
                    rs.getInt("colocacao"),
                    rs.getFloat("pontos"),
                    null);
            
            distribuicaoPontos.setCodRanking(rs.getInt("ranking_codRanking"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return distribuicaoPontos;
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
