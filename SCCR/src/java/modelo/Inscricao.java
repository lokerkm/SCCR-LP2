package modelo;

import dao.InscricaoDAO;
import java.sql.SQLException;
import java.util.List;

public class Inscricao {

    private int codInscricao;
    private Boolean statusRetirada;
    private String tamCamisa;
    private int numeroPeito;
    private float precoTotal;
    private Chip chip;
    private Atleta atleta;
    private Percurso percurso;
    private Pagamento pagamento;
    private Kit kit;
    private String dataInscricao;

    private int codChip;
    private int codAtleta;
    private int codPercurso;
    private int codPercursoEvento;
    private int codPagamento;
    private int codKit;
    private int codKitEvento;

    public Inscricao(int codInscricao, Boolean statusRetirada, String tamCamisa, int numeroPeito, float precoTotal, Chip chip, Atleta atleta, Percurso percurso, Pagamento pagamento, Kit kit, String dataInscricao) {
        this.codInscricao = codInscricao;
        this.statusRetirada = statusRetirada;
        this.tamCamisa = tamCamisa;
        this.numeroPeito = numeroPeito;
        this.precoTotal = precoTotal;
        this.chip = chip;
        this.atleta = atleta;
        this.percurso = percurso;
        this.pagamento = pagamento;
        this.kit = kit;
        this.dataInscricao = dataInscricao;
    }

    public Inscricao(int codInscricao, Boolean statusRetirada, String tamCamisa, int numeroPeito, Chip chip, Atleta atleta, Percurso percurso, Pagamento pagamento, Kit kit, String dataInscricao) {
        this.codInscricao = codInscricao;
        this.statusRetirada = statusRetirada;
        this.tamCamisa = tamCamisa;
        this.numeroPeito = numeroPeito;
        this.chip = chip;
        this.atleta = atleta;
        this.percurso = percurso;
        this.pagamento = pagamento;
        this.kit = kit;
        this.dataInscricao = dataInscricao;
    }

    public Kit getKit() {
        return kit;
    }

    public String getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(String dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public Chip getChip() {
        return chip;
    }

    public int getCodPercursoEvento() {
        return codPercursoEvento;
    }

    public void setCodPercursoEvento(int codPercursoEvento) {
        this.codPercursoEvento = codPercursoEvento;
    }

    public int getCodEventoKit() {
        return codKitEvento;
    }

    public void setCodEventoKit(int codKitEvento) {
        this.codKitEvento = codKitEvento;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public int getCodKit() {
        return codKit;
    }

    public int getCodKitEvento() {
        return codKitEvento;
    }

    public void setCodKitEvento(int codKitEvento) {
        this.codKitEvento = codKitEvento;
    }

    public void setCodKit(int codKit) {
        this.codKit = codKit;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public String getTamCamisa() {
        return tamCamisa;
    }

    public void setTamCamisa(String tamCamisa) {
        this.tamCamisa = tamCamisa;
    }

    public Boolean getStatusRetirada() {
        return statusRetirada;
    }

    public void setStatusRetirada(Boolean statusRetirada) {
        this.statusRetirada = statusRetirada;
    }

    public int getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(int codPagamento) {
        this.codPagamento = codPagamento;
    }

    public void setCodPercurso(int codPercurso) {
        this.codPercurso = codPercurso;
    }

    public int getCodPercurso() {
        return codPercurso;
    }

    public void setMatriculaAtleta(int matriculaAtleta) {
        this.codAtleta = matriculaAtleta;
    }

    public int getCodInscricao() {
        return codInscricao;
    }

    public void setCodInscricao(int codInscricao) {
        this.codInscricao = codInscricao;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public int getNumeroPeito() {
        return numeroPeito;
    }

    public void setNumeroPeito(int numeroPeito) {
        this.numeroPeito = numeroPeito;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public int getCodChip() {
        return codChip;
    }

    public void setCodChip(int codChip) {
        this.codChip = codChip;
    }

    public Percurso getPercurso() {
        return percurso;
    }

    public void setEvento(Percurso percurso) {
        this.percurso = percurso;
    }

    public int getCodAtleta() {
        return codAtleta;
    }

    public void setCodAtleta(int codAtleta) {
        this.codAtleta = codAtleta;
    }

    public static List<Inscricao> obterInscricoes()
            throws ClassNotFoundException {
        return InscricaoDAO.obterInscricoes();
    }

    public static Inscricao obterInscricao(int codInscricao, int codPercurso, int codEvento)
            throws ClassNotFoundException {
        return InscricaoDAO.obterInscricao(codInscricao, codPercurso, codEvento);
    }

    public static Inscricao obterInscricaoSimularAtleta(int codChip, int codEvento, int codAtleta)
            throws ClassNotFoundException {
        return InscricaoDAO.obterInscricaoSimularAtleta(codChip, codEvento, codAtleta);
    }

    public static Inscricao obterInscricaoKit(int codInscricao, int codEvento)
            throws ClassNotFoundException {
        return InscricaoDAO.obterInscricaoKit(codInscricao, codEvento);
    }

    public static Inscricao obterInscricaoPagamento(int codPagamento)
            throws ClassNotFoundException {
        return InscricaoDAO.obterInscricaoPagamento(codPagamento);
    }

    public void gravar() throws SQLException, ClassNotFoundException {
        InscricaoDAO.gravar(this);
    }

    public void alterar() throws SQLException, ClassNotFoundException {
        InscricaoDAO.alterar(this);
    }

    public void excluir() throws SQLException, ClassNotFoundException {
        InscricaoDAO.excluir(this);
    }

    public Float calcularValorTotal(int codKit, int codEvento) throws ClassNotFoundException, SQLException {
        float preco;
        Kit kitPreco = Kit.obterKit(codKit, codEvento);
        Lote lote = Lote.obterLote(1, codEvento);//Improvisei o 1, falta pegar a data atualizada do computador e comparar com a do lote
        preco = kitPreco.getPreco() + lote.getValor();
        return preco;
    }
}
