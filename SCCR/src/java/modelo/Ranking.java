package modelo;

import dao.RankingDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private int codRanking;
    private String nome;
    private ArrayList<DistribuicaoDePontos> distribuicaoPontos;
    private int codDistribuicaoPontos;

    private ArrayList<Pontuacao> listaPontuacoes;

    private ArrayList listaCodPontuacoes;

    public Ranking(int codRanking, String nome/*, ArrayList<DistribuicaoDePontos> distribuicaoPontos*/) {
        this.codRanking = codRanking;
        this.nome = nome;
        //this.distribuicaoPontos = distribuicaoPontos;
    }

    public ArrayList<Pontuacao> getListaPontuacoes() {
        return listaPontuacoes;
    }

    public void setListaPontuacoes(ArrayList<Pontuacao> listaPontuacoes) {
        this.listaPontuacoes = listaPontuacoes;
    }

    public ArrayList getListaCodPontuacoes() {
        return listaCodPontuacoes;
    }

    public void setListaCodPontuacoes(ArrayList listaCodPontuacoes) {
        this.listaCodPontuacoes = listaCodPontuacoes;
    }

    public int getCodDistribuicaoPontos() {
        return codDistribuicaoPontos;
    }

    public void setCodDistribuicaoPontos(int codDistribuicaoPontos) {
        this.codDistribuicaoPontos = codDistribuicaoPontos;
    }

    public int getCodRanking() {
        return codRanking;
    }

    public void setCodRanking(int codRanking) {
        this.codRanking = codRanking;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<DistribuicaoDePontos> getDistribuicaoPontos() {
        return distribuicaoPontos;
    }

    public void setDistribuicaoPontos(ArrayList<DistribuicaoDePontos> distribuicaoPontos) {
        this.distribuicaoPontos = distribuicaoPontos;
    }

    public static List<Ranking> obterRankings()
            throws ClassNotFoundException {
        return RankingDAO.obterRankings();
    }

    public static List<Ranking> obterRankingsEvento(int codEvento)
            throws ClassNotFoundException {
        return RankingDAO.obterRankingsEvento(codEvento);
    }

    public void gravar() throws SQLException,
            ClassNotFoundException {
        RankingDAO.gravar(this);
    }

    public void alterar() throws SQLException,
            ClassNotFoundException {
        RankingDAO.alterar(this);
    }

    public void excluir() throws SQLException,
            ClassNotFoundException {
        RankingDAO.excluir(this);
    }

    public static void excluirEventoDoRaking(int codEvento, int codRanking) throws SQLException,
            ClassNotFoundException {
        RankingDAO.excluirEventoDoRanking(codEvento, codRanking);
    }

    public static void incluirEventoDoRaking(int codEvento, int codRanking) throws SQLException,
            ClassNotFoundException {
        RankingDAO.incluirEventoDoRanking(codEvento, codRanking);
    }

    public static Ranking obterRanking(int codRanking) throws ClassNotFoundException {
        return RankingDAO.obterRanking(codRanking);
    }

    public static List<DistribuicaoDePontos> obterDistribuicao(int codRanking) throws ClassNotFoundException {
        return RankingDAO.obterDistribuicao(codRanking);
    }

    public static List<Evento> obterEventosDoRanking(int codRanking) throws ClassNotFoundException {
        return RankingDAO.obterEventosDoRanking(codRanking);
    }
}
