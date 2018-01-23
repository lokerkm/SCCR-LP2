
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html ng-app="testApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Pesquisa Distribuição de Pontos</title>
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
                        <li class="breadcrumb-item active" aria-current="page">Pesquisa Distribuição de Pontos</li>
                    </ol>
                </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container" style="margin: 20px auto;">
            <input class="form-control"  id="buscaNome" ng-model="search.colocacao" placeholder="Pesquise por colocacao" style="margin-bottom: 7px;"/>
            <table class="table" id="tabelaPesquisa">

                <thead class="thead-dark">
                    <tr>
                        <th scope="co1">Codigo Distribuicao de Pontos</th>
                        <th scope="co1">Colocação</th>
                        <th scope="co1">Pontos</th>
                        <th scope="co1">Codigo Ranking</th>
                        <th scope="co1">Ação</th>
                    </tr> 
                </thead>
                <tbody>
                    <tr ng-repeat="distribuicao in distribuicao | filter:search:strict">
                        <td>{{distribuicao.codDistribuicaoDePontos}}</td>
                        <td>{{distribuicao.colocacao}}</td>
                        <td>{{distribuicao.pontos}}</td>
                        <td>{{distribuicao.codRanking}}</td>
                        <td >
                            <a class="btn btn-warning" href="ManterDistribuicaoDePontosController?acao=prepararEditar&tipoLogin=${tipoLogin}&codDistribuicaoDePontos={{distribuicao.codDistribuicaoDePontos}}&codRanking={{distribuicao.codRanking}}"/>Editar</a>
                            <a class="btn btn-danger" href="ManterDistribuicaoDePontosController?acao=prepararExcluir&tipoLogin=${tipoLogin}&codDistribuicaoDePontos={{distribuicao.codDistribuicaoDePontos}}&codRanking={{distribuicao.codRanking}}"/>Excluir</a>
                        </td>
                    </tr> 
                </tbody>
            </table>
        <form action="ManterDistribuicaoDePontosController?acao=prepararIncluir&tipoLogin=${tipoLogin}" method="post">
            <input class ="btn btn-success" type="submit" name="btnIncluir" value="Incluir"/>
            <a class="btn btn-info"href="PaginaAcessoController?tipoLogin=${tipoLogin}">Voltar</a>
        </form>
        </div>

        <footer class="footer bg-dark" style=" position: absolute; bottom: 0; width: 100%;" >
            <div class="container">
                <span class="text-muted">Sistema de Controle de Corrida de Rua.</span>
            </div>
        </footer>


        <script>
            var testApp = angular.module('testApp', []);
            testApp.controller('testController', function ($scope, $http) {
                $scope.getRequest = function () {
                    $http.get("http://localhost:8080/SCCR/api/distribuicao")
                            .then(function successCallback(response) {

                                $scope.distribuicao = response.data;
                            }, function errorCallback(response) {
                                console.log("Unable to perform get request");
                            });
                };
            });
        </script>

    </body>
</html>
