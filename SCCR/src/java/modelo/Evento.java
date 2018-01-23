package modelo;

import dao.EventoDAO;
import dao.EventoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Evento {

    private int codEvento;
    private String titulo;

    private String linkMapa;
    private String localLargada;
    private String duracao;
    private String dataEvento;
    private String horaLargada;
    private int maxParticipantes;
    private Organizador organizador;
    private Endereco endereco;
    private ArrayList<Kit> listaKits;
    private ArrayList<Percurso> listaPercursos;
    private ArrayList<Ranking> listaRankings;
    private ArrayList<Lote> listaLotes;

    private int codOrganizador;
    private int codEndereco;
    private ArrayList listaCodKits;
    private ArrayList listaCodPercursos;
    private ArrayList listaCodRankings;
    private ArrayList listaCodLotes;

    public Evento(int codEvento, String titulo, String linkMapa, String localLargada, String duracao, String dataEvento, String horaLargada, int maxParticipantes, Organizador organizador, Endereco endereco, ArrayList<Kit> listaKits, ArrayList<Percurso> listaPercursos, ArrayList<Ranking> listaRankings, ArrayList<Lote> listaLotes) {
        this.codEvento = codEvento;
        this.titulo = titulo;
        this.linkMapa = linkMapa;
        this.localLargada = localLargada;
        this.duracao = duracao;
        this.dataEvento = dataEvento;
        this.horaLargada = horaLargada;
        this.maxParticipantes = maxParticipantes;
        this.organizador = organizador;
        this.endereco = endereco;
        this.listaKits = listaKits;
        this.listaPercursos = listaPercursos;
        this.listaRankings = listaRankings;
        this.listaLotes = listaLotes;
    }

    public Evento(int codEvento, String titulo, String linkMapa, String localLargada, String duracao, String dataEvento, String horaLargada, int maxParticipantes, Organizador organizador, Endereco endereco) {
        this.codEvento = codEvento;
        this.titulo = titulo;
        this.linkMapa = linkMapa;
        this.localLargada = localLargada;
        this.duracao = duracao;
        this.dataEvento = dataEvento;
        this.horaLargada = horaLargada;
        this.maxParticipantes = maxParticipantes;
        this.organizador = organizador;
        this.endereco = endereco;
    }

    



    public ArrayList<Kit> getListaKits() {
        return listaKits;
    }

    public void setListaKits(ArrayList<Kit> listaKits) {
        this.listaKits = listaKits;
    }

    public ArrayList<Percurso> getListaPercursos() {
        return listaPercursos;
    }

    public void setListaPercursos(ArrayList<Percurso> listaPercursos) {
        this.listaPercursos = listaPercursos;
    }

   
    public ArrayList getListaCodKits() {
        return listaCodKits;
    }

    public void setListaCodKits(ArrayList listaCodKits) {
        this.listaCodKits = listaCodKits;
    }

    public ArrayList getListaCodPercursos() {
        return listaCodPercursos;
    }

    public void setListaCodPercursos(ArrayList listaCodPercursos) {
        this.listaCodPercursos = listaCodPercursos;
    }

    public ArrayList<Ranking> getListaRankings() {
        return listaRankings;
    }

    public void setListaRankings(ArrayList<Ranking> listaRankings) {
        this.listaRankings = listaRankings;
    }

    public ArrayList<Lote> getListaLotes() {
        return listaLotes;
    }

    public void setListaLotes(ArrayList<Lote> listaLotes) {
        this.listaLotes = listaLotes;
    }

    public ArrayList getListaCodRankings() {
        return listaCodRankings;
    }

    public void setListaCodRankings(ArrayList listaCodRankings) {
        this.listaCodRankings = listaCodRankings;
    }

    public ArrayList getListaCodLotes() {
        return listaCodLotes;
    }

    public void setListaCodLotes(ArrayList listaCodLotes) {
        this.listaCodLotes = listaCodLotes;
    }

    

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }

    public int getCodOrganizador() {
        return codOrganizador;
    }

    public void setCodOrganizador(int codOrganizador) {
        this.codOrganizador = codOrganizador;
    }

    public int getCodEvento() {
        return codEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getLocalLargada() {
        return localLargada;
    }

    public void setLocalLargada(String localLargada) {
        this.localLargada = localLargada;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getHoraLargada() {
        return horaLargada;
    }

    public void setHoraLargada(String horaLargada) {
        this.horaLargada = horaLargada;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
    }

    public String getLinkMapa() {
        return linkMapa;
    }

    public void setLinkMapa(String linkMapa) {
        this.linkMapa = linkMapa;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static List<Evento> obterEventos()
            throws ClassNotFoundException {
        return EventoDAO.obterEventos();
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        EventoDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        EventoDAO.alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        EventoDAO.excluir(this);
    }

    public static Evento obterEvento(int codEvento) throws ClassNotFoundException {
        return EventoDAO.obterEvento(codEvento);
    }
}
