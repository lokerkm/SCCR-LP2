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
import modelo.Evento;
import modelo.Kit;
import modelo.Percurso;

public class EventoDAO {

    public static List<Evento> obterEventos() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Evento> eventos = new ArrayList<Evento>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from evento,organizador,endereco "
                    + "where  organizador.codOrganizador = evento.organizador_codOrganizador "
                    + "and  endereco.codEndereco = evento.endereco_codEndereco");
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

                evento.setCodEndereco(rs.getInt("endereco.codEndereco"));
                evento.setCodOrganizador(rs.getInt("organizador.codOrganizador"));
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

    public static void gravar(Evento evento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();

            String sql = "INSERT INTO evento(codEvento, titulo, linkMapa, localLargada,"
                    + " duracao, horaLargada, maxParticipantes, endereco_codEndereco, organizador_codOrganizador)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, evento.getCodEvento());
            comando.setString(2, evento.getTitulo());
            comando.setString(3, evento.getLinkMapa());
            comando.setString(4, evento.getLocalLargada());
            comando.setString(5, evento.getDuracao());
            comando.setString(6, evento.getHoraLargada());
            comando.setInt(7, evento.getMaxParticipantes());
            comando.setInt(8, evento.getCodEndereco());
            comando.setInt(9, evento.getCodOrganizador());
            comando.execute();
            comando.close();

            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Evento evento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {

            conexao = BD.getConexao();
            String sql = "UPDATE evento SET titulo=?,linkMapa=?,"
                    + "localLargada=?,duracao=?,dataEvento=?,horaLargada=?,maxParticipantes=?,"
                    + "endereco_codEndereco=?,organizador_codOrganizador=? WHERE codEvento=?";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, evento.getTitulo());
            comando.setString(2, evento.getLinkMapa());
            comando.setString(3, evento.getLocalLargada());
            comando.setString(4, evento.getDuracao());
            comando.setString(5, evento.getDataEvento());
            comando.setString(6, evento.getHoraLargada());
            comando.setInt(7, evento.getMaxParticipantes());
            comando.setInt(8, evento.getCodEndereco());
            comando.setInt(9, evento.getCodOrganizador());
            comando.setInt(10, evento.getCodEvento());
            comando.execute();

//se for null usar if else
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Evento evento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from evento where codEvento=" + evento.getCodEvento();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Evento obterEvento(int codEvento) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Evento evento = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();

            ResultSet rs = comando.executeQuery("select * from evento,endereco,organizador where codEvento=" + codEvento
                    + " AND evento.endereco_codEndereco = endereco.codEndereco "
                    + "and evento.organizador_codOrganizador = organizador.codOrganizador");
            rs.first();
            evento = new Evento(rs.getInt("codEvento"),
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

            rs = comando.executeQuery("select percurso.codPercurso from percurso,evento where codEvento= " + codEvento
                    + "  AND percurso.evento_codEvento = evento.codEvento");
            ArrayList<Percurso> percursos = new ArrayList<Percurso>();
            while (rs.next()) {
                Percurso percurso = Percurso.obterPercurso(rs.getInt("codPercurso"),codEvento);
                percursos.add(percurso);
            }
            evento.setListaPercursos(percursos);

            rs = comando.executeQuery("select kit.codKit from kit,evento where codEvento= " + codEvento
                    + "  AND kit.evento_codEvento= evento.codEvento");
            ArrayList<Kit> kits = new ArrayList<Kit>();
            while (rs.next()) {
                Kit kit = Kit.obterKit( rs.getInt("codKit"),codEvento);
                kits.add(kit);
            }
            evento.setListaKits(kits);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return evento;
    }
}
