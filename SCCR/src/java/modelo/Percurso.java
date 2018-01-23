package modelo;

import dao.PercursoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Percurso {

    private int codPercurso;
    private String nome;
    private float quilometragem;
    private String itinerario;
    private Evento evento;
   private ArrayList<Inscricao> listaInscricoes;

    private int codEvento;
    private ArrayList listaCodInscricao;

    public ArrayList<Inscricao> getListaInscricoes() {
        return listaInscricoes;
    }

    public void setListaInscricoes(ArrayList<Inscricao> listaInscricoes) {
        this.listaInscricoes = listaInscricoes;
    }

    public ArrayList getCodInscricao() {
        return listaCodInscricao;
    }

    public void setCodInscricao(ArrayList codInscricao) {
        this.listaCodInscricao = codInscricao;
    }

    public Percurso(int codPercurso, String nome, float quilometragem, String itinerario, Evento evento) {
        this.codPercurso = codPercurso;
        this.nome = nome;
        this.quilometragem = quilometragem;
        this.itinerario = itinerario;
        this.evento = evento;
    }

    public int getCodPercurso() {
        return codPercurso;
    }

    public void setCodPercurso(int codPercurso) {
        this.codPercurso = codPercurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(float quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getItinerario() {
        return itinerario;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public int getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(int codEvento) {
        this.codEvento = codEvento;
    }

    public static List<Percurso> obterPercursos()
            throws ClassNotFoundException {
        return PercursoDAO.obterPercursos();
    }

    public void gravar() throws SQLException,
            ClassNotFoundException {
        PercursoDAO.gravar(this);
    }

    public void alterar() throws SQLException,
            ClassNotFoundException {
        PercursoDAO.alterar(this);
    }

    public void excluir() throws SQLException,
            ClassNotFoundException {
        PercursoDAO.excluir(this);
    }

    public static Percurso obterPercurso(int codPercurso, int codEvento) throws ClassNotFoundException {
        return PercursoDAO.obterPercurso(codPercurso, codEvento);
    }
}
