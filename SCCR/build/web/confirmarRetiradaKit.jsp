<%-- 
    Document   : confirmarRetiradaKit
    Created on : 22/11/2017, 20:59:56
    Author     : sothz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar retirada Kit</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

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
                    <li class="breadcrumb-item active" aria-current="page">Confirmar retirada do Kit</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form form action="ManterInscricaoController?acao=confirmarRetiradaKit&tipoLogin=${tipoLogin}" method="post" name="frmConfirmarRetiradaKit" >
                <div class="card">
                    <div class="card-block">
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="evento">Qual Evento?</label>
                                <select class="form-control" id="evento" name="optEvento">
                                    <c:forEach items="${eventos}" var="evento">
                                        <option value="${evento.codEvento}">${evento.titulo}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="codInscricao">Digite o código da Inscricao</label>
                                <input class="form-control" id="codInscricao" type="text" name="txtCodInscricao">
                            </div>
                        </div>

                        <div class="form-row" <c:if test="${operacao != 'Preparar'}"> hidden</c:if>>
                                <div class="form-group col-md-2">
                                    <label for="retiradaKit">Status da retirada do Kit</label>
                                    <input class="form-control" id="retiradaKit" type="text" name="txtStatusRetirada" value="${inscricao.statusRetirada}" readonly>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-sm-10">
                                <button type="submit" name="btnConfirmar" class="btn btn-success">Confirmar</button>
                                <a class="btn btn-info"href="PaginaAcessoController?tipoLogin=${tipoLogin}">Voltar</a>
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
