/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Chip;

/**
 *
 * @author sothz
 */
public class ChipDAO {

    public static List<Chip> obterChips() throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;

        List<Chip> chips = new ArrayList<Chip>();

        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("select * from chip ");
            while (rs.next()) {
                Chip chip = new Chip(rs.getInt("codChip"),
                        rs.getString("tipoChip"),
                        rs.getFloat("distancia"));
                chips.add(chip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return chips;
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

    public static void gravar(Chip Chip) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {
            conexao = BD.getConexao();

            String sql = "INSERT INTO Chip(codChip, tipoChip, distancia)"
                    + " VALUES (?,?,?)";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setInt(1, Chip.getCodChip());
            comando.setString(2, Chip.getTipoChip());
            comando.setFloat(3, Chip.getDistancia());
            comando.execute();
            comando.close();

            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void alterar(Chip Chip) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        try {

            conexao = BD.getConexao();
            String sql = "UPDATE Chip SET tipoChip= ?, distancia= ? WHERE codChip= ?";
            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, Chip.getTipoChip());
            comando.setFloat(2, Chip.getDistancia());
            comando.setInt(3, Chip.getCodChip());
            comando.execute();
            comando.close();
            conexao.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public static void excluir(Chip chip) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        String stringSQL;
        try {
            conexao = BD.getConexao();
            stringSQL = "delete from Chip where codChip=" + chip.getCodChip();
            comando = conexao.prepareStatement(stringSQL);
            comando.execute(stringSQL);

        } catch (SQLException e) {
            throw e;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public static Chip obterChip(int codChip) throws ClassNotFoundException {
        Connection conexao = null;
        Statement comando = null;
        Chip Chip = null;
        try {
            conexao = BD.getConexao();
            comando = conexao.createStatement();

            ResultSet rs = comando.executeQuery("select * from Chip where codChip=" + codChip);
            rs.first();
            Chip = new Chip(rs.getInt("codChip"),
                    rs.getString("tipoChip"),
                    rs.getFloat("distancia"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharConexao(conexao, comando);
        }
        return Chip;
    }

}
