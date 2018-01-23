package modelo;

import dao.DistribuicaoDePontosDAO;
import java.sql.SQLException;
import java.util.List;


public class DistribuicaoDePontos {
    private int codDistribuicaoDePontos;
    private int colocacao;
    private float pontos;
    private Ranking ranking;
    private int codRanking;

    public DistribuicaoDePontos(int codDistribuicaoPontos, int colocacao, float pontos, Ranking ranking) {
        this.codDistribuicaoDePontos = codDistribuicaoPontos;
        this.colocacao = colocacao;
        this.pontos = pontos;
        this.ranking = ranking;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public int getCodRanking() {
        return codRanking;
    }

    public void setCodRanking(int codRanking) {
        this.codRanking = codRanking;
    }

    public int getCodDistribuicaoDePontos() {
        return codDistribuicaoDePontos;
    }

    public void setCodDistribuicaoDePontos(int codDistribuicaoDePontos) {
        this.codDistribuicaoDePontos = codDistribuicaoDePontos;
    }

    public int getColocacao() {
        return colocacao;
    }

    public void setColocacao(int colocacao) {
        this.colocacao = colocacao;
    }

    public float getPontos() {
        return pontos;
    }

    public void setPontos(float pontos) {
        this.pontos = pontos;
    }
    
     public static List<DistribuicaoDePontos> obterDistribuicaoDePontos()
            throws ClassNotFoundException {
        return DistribuicaoDePontosDAO.obterDistribuicaoDePontos();
    }
    public void gravar() throws SQLException,
            ClassNotFoundException {
        DistribuicaoDePontosDAO.gravar(this);
    }

    public void alterar() throws SQLException,
            ClassNotFoundException {
        DistribuicaoDePontosDAO.alterar(this);
    }

    public void excluir() throws SQLException,
            ClassNotFoundException {
        DistribuicaoDePontosDAO.excluir(this);
    }
    public static void excluirDoRanking(int codRanking) throws SQLException,
            ClassNotFoundException {
        DistribuicaoDePontosDAO.excluirDoRanking(codRanking);
    }

    public static DistribuicaoDePontos obterDistribuicaoDePontos(int codDistribuicaoPontos, int codRanking) throws ClassNotFoundException {
        return DistribuicaoDePontosDAO.obterDistribuicaoDePontos(codDistribuicaoPontos, codRanking);
    }


}
