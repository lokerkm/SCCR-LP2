/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.ChipDAO;
import java.sql.SQLException;
import java.util.List;

public class Chip {

    private int codChip;
    private String tipoChip;
    private float distancia;

    public Chip(int codChip, String tipoChip, float distancia) {
        this.codChip = codChip;
        this.tipoChip = tipoChip;
        this.distancia = distancia;
    }

    public int getCodChip() {
        return codChip;
    }

    public void setCodChip(int codChip) {
        this.codChip = codChip;
    }

    public String getTipoChip() {
        return tipoChip;
    }

    public void setTipoChip(String tipoChip) {
        this.tipoChip = tipoChip;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

   public static List<Chip> obterChips()
            throws ClassNotFoundException {
        return ChipDAO.obterChips();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        ChipDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        ChipDAO.alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        ChipDAO.excluir(this);
    }

    public static Chip obterChip(int codChip) throws ClassNotFoundException {
        return ChipDAO.obterChip(codChip);
    }
    
}
