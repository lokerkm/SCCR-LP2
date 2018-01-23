<%-- 
    Document   : manterEventoDoRanking
    Created on : 02/01/2018, 17:44:03
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
        <title>Manter Evento</title>
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
                    <li class="breadcrumb-item"><a href="PesquisaEventoController?tipoLogin=${tipoLogin}">Pesquisa de Evento</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Manter Evento Do Ranking ${codRanking}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form action="ManterRankingController?acao=confirmarIncluirEventoDoRanking&tipoLogin=${tipoLogin}&codRanking=${codRanking}" method="post" name="frmManterEvento" >
                <div class="card">
                    <div class="card-block">



                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="evento">Evento</label>
                                <select class="form-control" id="evento" name="optEvento">
                                    <c:forEach items="${eventos}" var="evento">
                                        <option value="${evento.codEvento}">${evento.titulo}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-sm-10">
                                <button type="submit" name="btnConfirmar" class="btn btn-success">Confirmar</button>
                                <a class ="btn btn-info" href="PesquisaEventoDoRankingController?tipoLogin=${tipoLogin}&codRanking=${codRanking}"> Voltar</a>
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