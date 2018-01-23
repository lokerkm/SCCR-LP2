<%-- 
    Document   : manterPontuacao
    Created on : 17/10/2017, 10:12:45
    Author     : kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Manter Distribuicao de Pontos</title>
        <style>
            html{
                position: relative;
                min-height: 100%;
            }
            .card{
                margin-top: 40px;
                background: rgba(255, 255, 255, 0.8);
                box-shadow: rgba(0, 0, 0, 0.3) 20px 20px 20px;

            }
            .card-block{
                font-size: 1em;
                position: relative;
                margin: 0;
                padding: 1em;
                border: none;
                border-top: 1px solid rgba(34, 36, 38, .1);
                box-shadow: none;
            }

        </style>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-dark navbar-fixed-top">
            <div class="navbar-header">
                <ol class="breadcrumb" style="margin: auto;">
                    <li class="breadcrumb-item"><a href="PaginaAcessoController?tipoLogin=${tipoLogin}">Página do ${tipoLogin}</a></li>
                    <li class="breadcrumb-item"><a href="PesquisaRankingController?tipoLogin=${tipoLogin}">Pesquisa de Ranking</a></li>
                    <li class="breadcrumb-item"><a href="ManterRankingController?acao=prepararEditar&tipoLogin=${tipoLogin}&codRanking=${ranking.codRanking}">Manter Ranking</a></li>
                    <li class="breadcrumb-item"><a href="PesquisaDistribuicaoDoRankingController?tipoLogin=${tipoLogin}&codRanking=${codRanking}">Pesquisa de Distribuição de Pontos</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Manter Distribuicao de Pontos - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form action="ManterDistribuicaoDePontosController?acao=confirmar${operacao}&tipoLogin=${tipoLogin}&codRanking=${codRanking}" method="post" name="frmManterDistribuicaoDePontos">
                <div class="card">
                    <div class="card-block"> <div class="form-row">
                            <form action="ManterChipController?acao=confirmar${operacao}" method="post" name="frmManterChip" >
                                <div class="form-group col-md-1">
                                    <label for="codDistribuicao">Código da Distribuição de Pontos</label>
                                    <input class="form-control" id="codDistribuicao" type="text" name="txtCodDistribuicaoDePontos" value="${distribuicao.codDistribuicaoDePontos}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                    </div>
                            </div>
                            <div class ="form-row">
                                <div class="form-group col-md-4">
                                    <label for="ranking">Ranking</label>
                                    <select id="ranking" class="form-control" name="optRanking" readonly>
                                    <c:forEach items="${rankings}" var="ranking">
                                        <option value="${ranking.codRanking}" <c:if test="${codRanking == ranking.codRanking}"> selected</c:if>>${ranking.nome}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-2">
                                <label for="colocacao">Colocação</label>
                                <input class="form-control" id="colocacao" type="text" name="txtColocacao" value="${distribuicao.colocacao}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="pontos">Pontos</label>
                                    <input class="form-control" id="pontos" type="text" name="txtPontos" value="${distribuicao.pontos}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-10">
                                    <button type="submit" name="btnConfirmar" class="btn btn-success">Confirmar</button>
                                    <a class ="btn btn-info" href="PesquisaDistribuicaoDoRankingController?tipoLogin=${tipoLogin}&codRanking=${codRanking}"> Voltar</a>
                            </div>
                        </div>  
                    </div>
                </div>
            </form>
        </div>                            
        <footer class="footer bg-dark" style=" position: absolute; bottom: 0; width: 100%;" >
            <div class="container">
                <span class="text-muted">Sistema de Controle de Corrida de Rua.</span>
            </div>
        </footer>


    </body>
</html>

