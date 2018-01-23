
<%-- 
    Document   : pesquisaEvento
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
        <title>Pesquisa Evento</title>
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
                    <li class="breadcrumb-item active" aria-current="page">Pesquisa de Evento</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container" style="margin: 20px auto;">
            <input class="form-control" id="buscaNome" ng-model="search.titulo" placeholder="Pesquise por nome" style="margin-bottom: 7px;" />
            <table class="table" id="tabelaPesquisa">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Codigo Evento</th>
                        <th scope="col">Titulo</th>
                        <th scope="col">Data</th>
                        <th scope="col">Ação</th>
                    </tr>
                </thead>

                <tbody>
                    <tr ng-repeat="evento in eventos|filter:search:strict">
                        <td>{{evento.codEvento}}</td>
                        <td>{{evento.titulo}}</td>
                        <td>{{evento.dataEvento}}</td>
                        <td ><div style="float: left" <c:if test="${tipoLogin=='atleta'}">hidden</c:if>>
                                <a class="btn btn-warning"<c:if test="${tipoLogin!='atleta'}"> href="ManterEventoController?acao=prepararEditar&tipoLogin=${tipoLogin}&codEvento={{evento.codEvento}}&codEndereco={{evento.codEndereco}}"</c:if>/>Editar</a>
                            </div>  <div style="float: center" <c:if test="${tipoLogin!='administrador'}">hidden</c:if>>  
                                <a class="btn btn-danger"<c:if test="${tipoLogin!='atleta'}"> href="ManterEventoController?acao=prepararExcluir&tipoLogin=${tipoLogin}&codEvento={{evento.codEvento}}&codEndereco={{evento.codEndereco}}"</c:if>/>Excluir</a>
                                </div>
                                <div <c:if test="${tipoLogin!='atleta'}">hidden</c:if>>  
                                <a class="btn btn-outline-success" href="ManterInscricaoController?acao=prepararIncluirViaEvento&tipoLogin=${tipoLogin}&codEvento={{evento.codEvento}}"/>Inscrever</a>
                                </div>
                            </td>

                        </tr>
                    </tbody>
                </table>
                <form action="ManterEventoController?acao=prepararIncluir&tipoLogin=${tipoLogin}" method="post">
                <div style="float: left" <c:if test="${tipoLogin=='atleta'}"> hidden</c:if>>
                        <input class ="btn btn-success" type="submit" name="btnIncluir" value="Incluir"/>
                    </div>
                    <div style="float: center">
                        <a class="btn btn-info"href="PaginaAcessoController?tipoLogin=${tipoLogin}">Voltar</a></div>
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
                            $http.get("http://localhost:8080/SCCR/api/eventos")
                                    .then(function successCallback(response) {

                                        $scope.eventos = response.data;
                                    }, function errorCallback(response) {
                                        console.log("Unable to perform get request");
                                    });
                        };
                    });
        </script>
    </body>
</html>
