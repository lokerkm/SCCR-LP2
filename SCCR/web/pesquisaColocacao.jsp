
<%-- 
    Document   : pesquisaPontuacao
    Created on : 10/10/2017, 08:50:52
    Author     : Aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html ng-app="testApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Pesquisa Colocação</title>
        <script src="angular.min.js"></script>
        <style>
            html{
                position: relative;
                min-height: 100%;
            }
        </style>
    </head>
    <body ng-controller="testController" ng-init="getRequest()">
        <nav class="navbar navbar-dark bg-dark navbar-fixed-top">

            <div class="navbar-header">
                <ol class="breadcrumb" style="margin: auto;">
                    <li class="breadcrumb-item"><a href="PaginaAcessoController?tipoLogin=${tipoLogin}">Página do ${tipoLogin}</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Pesquisa Ranking</li>
                    <li class="breadcrumb-item active" aria-current="page">Pesquisa Colocação</li>
                    <li class="breadcrumb-item active" aria-current="page">${ranking.nome}</li>
                </ol>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container" style="margin: 20px auto;">
            <input class="form-control"  style="margin-bottom: 7px;" id="buscaNome" ng-model="search.codPontuacao" placeholder="Pesquise por código" />
            <table class="table" id="tabelaPesquisa">
                <thead class="thead-dark">
                    <tr>
                        <th scope="co1">Atleta</th>
                        <th scope="co1">Codígo Atleta</th>
                        <th scope="co1">Pontos</th>

                    </tr> 
                </thead>
                <tbody>
                    <c:forEach items="${pontuacoes}" var="pontuacao">
                        <tr><c:forEach  items="${atletas}" var="atleta">
                            <c:if test="${pontuacao.codAtleta== atleta.codAtleta}"><td>${atleta.nome}</td></c:if>
                    </c:forEach>
                    <td>${pontuacao.codAtleta}</td>
                    <td>${pontuacao.pontos}</td></tr>
                </c:forEach>
                </tbody>
            </table>
            <a class="btn btn-info"href="PesquisaRankingController?tipoLogin=${tipoLogin}">Voltar</a>

        </div>

        <footer class="footer bg-dark" style=" position: absolute; bottom: 0; width: 100%;" >
            <div class="container">
                <span class="text-muted">Sistema de Controle de Corrida de Rua.</span>
            </div>
        </footer>



    </body>
</html>
