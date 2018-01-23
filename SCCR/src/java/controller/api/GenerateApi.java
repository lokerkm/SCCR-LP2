/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.api;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import modelo.Administrador;
import modelo.Atleta;
import modelo.Chip;
import modelo.DistribuicaoDePontos;
import modelo.Endereco;
import modelo.Evento;
import modelo.Inscricao;
import modelo.Kit;
import modelo.LocalDeRetirada;
import modelo.Lote;
import modelo.Organizador;
import modelo.Pagamento;
import modelo.Percurso;
import modelo.Pontuacao;
import modelo.Ranking;

/**
 *
 * @author kevin
 */
public class GenerateApi {

    public static String serialize(String object) throws Exception {
        HashMap stringClasse = new HashMap<>();
        stringClasse.put("Evento", Evento.obterEventos());
        stringClasse.put("Atleta", Atleta.obterAtletas());
        stringClasse.put("Organizador", Organizador.obterOrganizadores() );
        stringClasse.put("Administrador", Administrador.obterAdministrador());
        stringClasse.put("Chip", Chip.obterChips());
        stringClasse.put("Pagamento", Pagamento.obterPagamento());
        stringClasse.put("Kit", Kit.obterKits());
        stringClasse.put("Ranking", Ranking.obterRankings());
        stringClasse.put("Pontuacao", Pontuacao.obterPontuacoes());
        stringClasse.put("Percurso", Percurso.obterPercursos());
        stringClasse.put("LocalDeRetirada", LocalDeRetirada.obterLocaisDeRetirada());
        stringClasse.put("Inscricao", Inscricao.obterInscricoes());
        stringClasse.put("Endereco", Endereco.obterEnderecos());
        stringClasse.put("Lote", Lote.obterLotes());
        stringClasse.put("Distribuicao", DistribuicaoDePontos.obterDistribuicaoDePontos());
        List<Object> jsonReturn = (List<Object>) stringClasse.get(object);
        return new Gson().toJson(jsonReturn);
    }
}
