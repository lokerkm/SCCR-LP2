package dao;
//arrumar nome tabela banco de dados

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Kit;

public class KitDAO {

    public static List<Kit> obterKits() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Kit> kits = new ArrayList<Kit>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from kit, evento where kit.evento_codEvento=evento.codEvento");
            while (rs.next()) {
                Kit kit = new Kit(rs.getInt("codKit"),
                        rs.getString("descricao"),
                        rs.getBoolean("camisa"),
                        rs.getString("produtos"),
                        rs.getFloat("valorKit"),
                        null,
                        null);

                kit.setCodEvento(rs.getInt("evento_codEvento"));
                kit.setCodLocalDeRetirada(rs.getInt("local_retirada_codLocal"));
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                kits.add(kit);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return kits;
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

    public static void gravar(Kit kit) throws SQLException, ClassNotFoundException {

        Connection conexao = null;
        try {

            conexao = BD.getConexao();
            String sql = "insert into kit (codKit, descricao, valorKit, produtos, camisa, evento_codEvento, local_retirada_codLocal) "
                    + "values (?,?,?,?,?,?,?)";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, kit.getCodKit());
            comando.setString(2, kit.getDescricao());
            comando.setFloat(3, kit.getValorKit());
            comando.setString(4, kit.getProdutos());
            comando.setBoolean(5, kit.getCamisa());
            comando.setInt(6, kit.getCodEvento());
            comando.setInt(7, kit.getCodLocalDeRetirada());

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }

    }

    public static void alterar(Kit kit) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update kit set  descricao = ?, valorKit = ?, produtos = ?, camisa = ?, local_retirada_codLocal = ? "
                    + "where codKit = ? and evento_codEvento = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, kit.getDescricao());
            comando.setFloat(2, kit.getValorKit());
            comando.setString(3, kit.getProdutos());
            comando.setBoolean(4, kit.getCamisa());

            comando.setInt(5, kit.getCodLocalDeRetirada());

            comando.setInt(6, kit.getCodKit());
            comando.setInt(7, kit.getCodEvento());

            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Kit kit) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;

        try {
            conexao = BD.getConexao();
            stringSQL = "delete from kit where codKit = " + kit.getCodKit();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Kit obterKit(int codKit, int codEvento) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Kit kit = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from kit where codKit = " + codKit + " and evento_codEvento = " + codEvento);
            rs.first();

            kit = new Kit(rs.getInt("codKit"),
                    rs.getString("descricao"),
                    rs.getBoolean("camisa"),
                    rs.getString("produtos"),
                    rs.getFloat("valorKit"),
                    null,
                    null);

            kit.setCodEvento(rs.getInt("evento_codEvento"));
            kit.setCodLocalDeRetirada(rs.getInt("local_retirada_codLocal"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return kit;
    }

}
