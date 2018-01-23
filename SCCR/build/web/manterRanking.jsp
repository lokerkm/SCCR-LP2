<%-- 
    Document   : manterRanking
    Created on : 17/10/2017, 09:30:15
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
        <title>Manter Ranking</title>

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
                    <li class="breadcrumb-item active" aria-current="page">Manter Ranking - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form action="ManterRankingController?acao=confirmar${operacao}&tipoLogin=${tipoLogin}" method="post" name="frmManterRanking">
                <div class="card">
                    <div class="card-block"> <div class="form-row">
                            <div class="form-group col-md-1">
                                <label for="codRanking">Código do Ranking</label>
                                <input id="codPagamento" class="form-control" type="text" name="txtCodRanking" value="${ranking.codRanking}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="nome">Nome</label>
                                    <input id="nome" class="form-control" type="text" name="txtRanking" value="${ranking.nome}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>

                        <%-- Tenho que ver como vai ser isso em formulario bootstrap, se vai mudar ou o que vai fazer--%>
                        <div class="form-group row" <c:if test="${operacao != 'Incluir'}"> hidden</c:if>>
                                <label class="col-sm-2 col-form-label">Posição   </label>
                                <label class="col-sm-2 col-form-label">   Pontuação</label>
                            </div>

                            <div class="form-group row" <c:if test="${operacao != 'Incluir'}"> hidden</c:if>>
                                <label class="col-sm-2 col-form-label">1.</label>
                                <div class="col-sm-2">
                                    <input class="form-control"  placeholder="Pontuação" type="text" name="txtPosicao0" value="${distribuicao0.pontos}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>

                            <div class="form-group row" <c:if test="${operacao != 'Incluir'}"> hidden</c:if>>
                                <label class="col-sm-2 col-form-label">2.</label>
                                <div class="col-sm-2">
                                    <input class="form-control"  placeholder="Pontuação" type="text" name="txtPosicao1" value="${distribuicao1.pontos}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>

                            <div class="form-group row" <c:if test="${operacao != 'Incluir'}"> hidden</c:if>>
                                <label class="col-sm-2 col-form-label">3.</label>
                                <div class="col-sm-2">
                                    <input class="form-control"  placeholder="Pontuação" type="text" name="txtPosicao2" value="${distribuicao2.pontos}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>



                            <thead class="thead-dark" <c:if test="${operacao == 'Incluir'}"> hidden</c:if> >    
                        <table class="table" id="tabelaPesquisa" <c:if test="${operacao == 'Incluir'}"> hidden</c:if>>
                                <tr>

                                    <th>Colocacao </th>
                                    <th>Pontos </th>


                                </tr>
                                </thead>  
                                <tbody>
                                <c:forEach items="${distribuicao}" var="distribuicao">
                                    <tr>

                                        <td><c:out value="${distribuicao.colocacao}" /></td>
                                        <td><c:out value="${distribuicao.pontos}" /></td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <div class="form-group row">
                            <div class="col-sm-10">
                                <a class ="btn btn-warning" href="PesquisaEventoDoRankingController?tipoLogin=${tipoLogin}&codRanking=${ranking.codRanking}" <c:if test="${operacao != 'Editar'}"> hidden</c:if>>Manter Eventos</a>
                                <a class ="btn btn-warning" href="PesquisaDistribuicaoDoRankingController?tipoLogin=${tipoLogin}&codRanking=${ranking.codRanking}" <c:if test="${operacao != 'Editar'}"> hidden</c:if>>Editar Distribuição de Pontos</a>
                                <button type="submit" name="btnConfirmar" class="btn btn-success">Confirmar</button>
                                <a class ="btn btn-info" href="PesquisaRankingController?tipoLogin=${tipoLogin}"> Voltar</a>
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


        <SCRIPT language="JavaScript">

        </SCRIPT>        
    </body>
</html>

