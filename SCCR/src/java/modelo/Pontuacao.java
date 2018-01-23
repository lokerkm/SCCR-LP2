package modelo;

import dao.PontuacaoDAO;
import java.sql.SQLException;
import java.util.List;

public class Pontuacao {

    private Atleta atleta;
    private Ranking ranking;
    private float pontos;
    private int codPontuacao;

    private int codAtleta;
    private int codRanking;

    public Pontuacao(Atleta atleta, Ranking ranking, int codPontuacao, float pontos) {
        this.atleta = atleta;
        this.ranking = ranking;
        this.pontos = pontos;
        this.codPontuacao = codPontuacao;

    }

    public int getCodPontuacao() {
        return codPontuacao;
    }

    public void setCodPontuacao(int codPontuacao) {
        this.codPontuacao = codPontuacao;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public float getPontos() {
        return pontos;
    }

    public void setPontos(float pontos) {
        this.pontos = pontos;
    }

    public int getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(int codAtleta) {
        this.codAtleta = codAtleta;
    }

    public int getCodRanking() {
        return codRanking;
    }

    public void setCodRanking(int codRanking) {
        this.codRanking = codRanking;
    }

    public static List<Pontuacao> obterPontuacoes()
            throws ClassNotFoundException {
        return PontuacaoDAO.obterPontuacoes();
    }

    public static List<Pontuacao> obterPontuacoesRanking(int codRanking)
            throws ClassNotFoundException {
        return PontuacaoDAO.obterPontuacoesRanking(codRanking);
    }

    public void gravar() throws SQLException,
            ClassNotFoundException {
        PontuacaoDAO.gravar(this);
    }

    public void alterar() throws SQLException,
            ClassNotFoundException {
        PontuacaoDAO.alterar(this);
    }

    public void excluir() throws SQLException,
            ClassNotFoundException {
        PontuacaoDAO.excluir(this);
    }

    public static Pontuacao obterPontuacao(int codPontuacao, int codRanking) throws ClassNotFoundException {
        return PontuacaoDAO.obterPontuacao(codPontuacao, codRanking);
    }
}
