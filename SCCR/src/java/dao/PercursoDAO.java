package dao;
//arrumar nome tabela banco de dados

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Percurso;

public class PercursoDAO {

    public static List<Percurso> obterPercursos() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Percurso> percursos = new ArrayList<Percurso>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from percurso, evento where percurso.evento_codEvento = evento.codEvento");
            while (rs.next()) {
                Percurso percurso = new Percurso(rs.getInt("codPercurso"),
                        rs.getString("nome"),
                        rs.getFloat("quilometragem"),
                        rs.getString("itinerario"),
                        null);

                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                percurso.setCodEvento(rs.getInt("codEvento"));
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                percursos.add(percurso);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return percursos;
    }

    public static void gravar(Percurso percurso) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "INSERT INTO percurso(codPercurso, nome, quilometragem, itinerario, evento_codEvento) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, percurso.getCodPercurso());
            comando.setString(2, percurso.getNome());
            comando.setFloat(3, percurso.getQuilometragem());
            comando.setString(4, percurso.getItinerario());
            comando.setFloat(5, percurso.getCodEvento());
            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Percurso percurso) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update percurso set nome = ?,  quilometragem= ?, itinerario = ? where codPercurso = ? and evento_codEvento = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, percurso.getNome());
            comando.setFloat(2, percurso.getQuilometragem());
            comando.setString(3, percurso.getItinerario());
            comando.setInt(4, percurso.getCodPercurso());
            comando.setInt(5, percurso.getCodEvento());
            comando.execute();

            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Percurso percurso) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from percurso where codPercurso = " + percurso.getCodPercurso() + " and evento_codEvento = " + percurso.getCodEvento();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Percurso obterPercurso(int codPercurso, int codEvento) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Percurso percurso = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from percurso  where codPercurso=" + codPercurso + " and evento_codEvento=" + codEvento);
            rs.first();
            percurso = new Percurso(rs.getInt("codPercurso"),
                    rs.getString("nome"),
                    rs.getFloat("quilometragem"),
                    rs.getString("itinerario"),
                    null);

            percurso.setCodEvento(rs.getInt("evento_codEvento"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return percurso;
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
