package dao;
//arrumar nome tabela banco de dados

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Pagamento;

public class PagamentoDAO {

    public static List<Pagamento> obterPagamentos() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Pagamento> pagamentos = new ArrayList<Pagamento>();
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from pagamento");
            while (rs.next()) {
                Pagamento pagamento = new Pagamento(rs.getInt("codPagamento"),
                        rs.getFloat("valorTotal"),
                        rs.getBoolean("status"),
                        rs.getString("tipoPagamento"),
                        rs.getString("codigoBarra"));
                //colocar chave estrangeria na classe e null na criaçao aqui em cima
                //curso.setMatriculaProfessorCoordenador(rs.getInt("professorCoordenador")); exemplo do marco
                pagamentos.add(pagamento);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return pagamentos;
    }

    public static void gravar(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "insert into pagamento(codPagamento, valorTotal, status, tipoPagamento, codigoBarra)"
                    + "values (?,?,?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(1, pagamento.getCodPagamento());
            comando.setFloat(2, pagamento.getValorTotal());
            comando.setBoolean(3, pagamento.isStatus());
            comando.setString(4, pagamento.getTipoPagamento());
            comando.setString(5, pagamento.getCodigoBarra());
            comando.execute();
            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update pagamento set valorTotal = ?, status = ?, codigoBarra = ?, tipoPagamento =?  where codPagamento = ?";

            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setInt(5, pagamento.getCodPagamento());
            comando.setFloat(1, pagamento.getValorTotal());
            comando.setBoolean(2, pagamento.isStatus());
            comando.setString(3, pagamento.getCodigoBarra());
            comando.setString(4, pagamento.getTipoPagamento());

            comando.execute();

            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Pagamento pagamento) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from pagamento where codPagamento= " + pagamento.getCodPagamento();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);
        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Pagamento obterPagamento(int codPagamento) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Pagamento pagamento = null;

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery(
                    "select * from pagamento where codPagamento = " + codPagamento);
            rs.first();
            pagamento = new Pagamento(rs.getInt("codPagamento"),
                    rs.getFloat("valorTotal"),
                    rs.getBoolean("status"),
                    rs.getString("tipoPagamento"),
                    rs.getString("codigoBarra"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return pagamento;
    }

    public static void alterarStatus(int codigoBarra) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();
            String sql = "update pagamento set status = true where codigoBarra = " + codigoBarra;

            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.execute();

            comando.close();
            conexao.close();

        } catch (SQLException e) {
            throw e;
        }
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
