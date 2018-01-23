<%-- 
    Document   : manterPercurso
    Created on : 17/10/2017, 21:11:36
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
        <title>Manter Percurso</title>
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
                    <li class="breadcrumb-item"><a href="PesquisaPercursoController?tipoLogin=${tipoLogin}">Pesquisa de Percurso</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Manter Percurso - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form action="ManterPercursoController?acao=confirmar${operacao}&tipoLogin=${tipoLogin}" method="post" name="frmManterPercurso">
                <div class="card">
                    <div class="card-block"> 
                        <table id="tabelaManter">
                            <div class="form-row">
                                <div class="form-group col-md-1">
                                    <label for="codPercurso">Código do Percurso</label>
                                    <input id="codPercurso" class="form-control" type="text" name="txtCodPercurso" value="${percurso.codPercurso}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-2">
                                        <label for="nome">Nome</label>
                                        <input id="nome" class="form-control" type="text" name="txtPercurso" value="${percurso.nome}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="intinerario">Itinerario</label>
                                        <input id="quilometragem" class="form-control" type="text" name="txtItinerario" value="${percurso.itinerario}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-2">
                                        <label for="quilometragem">Quilometragem</label>
                                        <input id="quilometragem" class="form-control" type="text" name="txtQuilometragemPercurso" value="${percurso.quilometragem}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                    </div>
                                </div>
                                <div class="form-row" <c:if test="${operacao != 'Incluir'}"> hidden</c:if>>
                                    <div class="form-group col-md-3">
                                        <label for="evento">Evento</label>
                                        <select class="form-control" id="evento" name="optEvento" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                        <c:forEach items="${eventos}" var="evento">
                                            <option value="${evento.codEvento}" <c:if test="${percurso.codEvento== evento.codEvento}"> selected</c:if>>${evento.titulo}</option>  
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-10">
                                    <button type="submit" name="btnConfirmar" class="btn btn-success">Confirmar</button>
                                    <a class ="btn btn-info" href="PesquisaPercursoController?tipoLogin=${tipoLogin}"> Voltar</a>
                                </div>
                            </div>
                        </table>
                    </div>
                </div>
        </div>
                                                            
        <footer class="footer bg-dark" style=" position: absolute; bottom: 0; width: 100%;" >
            <div class="container">
                <span class="text-muted">Sistema de Controle de Corrida de Rua.</span>
            </div>
        </footer>


    </body>
</html>

