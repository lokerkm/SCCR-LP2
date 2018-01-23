<%-- 
    Document   : pesquisaEventoDoRanking
    Created on : 02/01/2018, 00:15:32
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
        <title>Pesquisa Evento Do Ranking ${codRanking}</title>

        <style>
            html{
                position: relative;
                min-height: 100%;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-dark navbar-fixed-top">
            <div class="navbar-header">
                <ol class="breadcrumb" style="margin: auto;">
                    <li class="breadcrumb-item"><a href="PaginaAcessoController?tipoLogin=${tipoLogin}">Página do ${tipoLogin}</a></li>
                    <li class="breadcrumb-item"><a href="PesquisaRankingController?tipoLogin=${tipoLogin}">Pesquisa de Ranking</a></li>
                    <li class="breadcrumb-item"><a href="ManterRankingController?acao=prepararEditar&tipoLogin=${tipoLogin}&codRanking=${codRanking}">Manter Ranking</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Pesquisa Evento Do Ranking ${codRanking}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container" style="margin: 20px auto;">




           
            <table class="table" id="tabelaPesquisa" <c:if test="${operacao == 'Incluir'}"> hidden</c:if>>
                 <thead class="thead-dark" <c:if test="${operacao == 'Incluir'}"> hidden</c:if> >    
                    <tr>

                        <th>Código </th>
                        <th>Nome</th>
                        <th>Ação </th>


                    </tr>
                    </thead>  
                    <tbody>
                    <c:forEach items="${eventos}" var="evento">
                        <tr>

                            <td><c:out value="${evento.codEvento}" /></td>
                            <td><c:out value="${evento.titulo}" /></td>
                            <td >
                                
                                <a class="btn btn-danger"<c:if test="${tipoLogin!='atleta'}"> href="ManterRankingController?acao=confirmarExcluirEventoDoRanking&tipoLogin=${tipoLogin}&codEvento=${evento.codEvento}&codRanking=${codRanking}"</c:if>/>Excluir</a>
                                </td>

                            </tr>
                    </c:forEach>
                </tbody>
            </table>                    





            <form action="ManterRankingController?acao=prepararIncluirEventoDoRanking&tipoLogin=${tipoLogin}&codRanking=${codRanking}" method="post">
                    <input class ="btn btn-success" type="submit" name="btnIncluir" value="Incluir"/>
                    <a class="btn btn-info"href="ManterRankingController?acao=prepararEditar&tipoLogin=${tipoLogin}&codRanking=${codRanking}">Voltar</a>
            </form>




        </div>
        <footer class="footer bg-dark" style=" position: absolute; bottom: 0; width: 100%;" >
            <div class="container">
                <span class="text-muted">Sistema de Controle de Corrida de Rua.</span>
            </div>
        </footer>



    </body>
</html>

