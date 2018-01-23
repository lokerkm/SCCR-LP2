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
        <title>Manter Pontuacao</title>
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
                    <li class="breadcrumb-item"><a href="PesquisaPontuacaoController?tipoLogin=${tipoLogin}">Pesquisa de Pontuação</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Manter Pontuação - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form action="ManterPontuacaoController?acao=confirmar${operacao}&tipoLogin=${tipoLogin}" method="post" name="frmManterPontuacao">
                <div class="card">
                    <div class="card-block"> 
                        <table id="tabelaManter">
                            <div class="form-row">
                                <div class="form-group col-md-1">
                                    <label for="codPontuacao">Código do Pontuação</label>
                                    <input id="codPontuacao" class="form-control" type="text" name="txtCodPontuacao" value="${pontuacao.codPontuacao}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-3">
                                        <label for="atleta">Atleta</label>
                                        <select id="atleta" class="form-control" <select name="optAtleta" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                                            <c:forEach items="${atletas}" var="atleta">
                                                <option value="${atleta.codAtleta}" <c:if test="${pontuacao.codAtleta== atleta.codAtleta}"> selected</c:if>>${atleta.nome}</option>  
                                            </c:forEach>
                                        </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="ranking">Ranking</label>
                                    <select id="ranking" class="form-control" name="optRanking" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                        <c:forEach items="${rankings}" var="ranking">
                                            <option value="${ranking.codRanking}" <c:if test="${pontuacao.codRanking== ranking.codRanking}"> selected</c:if>>${ranking.nome}</option>  
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="pontos">Pontos</label>
                                    <input id="pontos" class="form-control" type="text" name="txtPontuacao" value="${pontuacao.pontos}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <button type="submit" name="btnConfirmar" class="btn btn-success">Confirmar</button>
                                        <a class ="btn btn-info" href="PesquisaPontuacaoController?tipoLogin=${tipoLogin}"> Voltar</a>
                                </div>
                            </div>
                        </table>
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
<!--

            function campoNumerico(valor)
            {
                var caracteresValidos = "0123456789";
                var ehNumero = true;
                var umCaracter;
                for (i = 0; i < valor.length && ehNumero == true; i++)
                {
                    umCaracter = valor.charAt(i);
                    if (caracteresValidos.indexOf(umCaracter) == -1)
                    {
                        ehNumero = false;
                    }
                }
                return ehNumero;
            }

            function validarFormulario(form) {
                var mensagem;
                mensagem = "";
                if (form.txtCodCurso.value == "") {
                    mensagem = mensagem + "Informe o Código do Curso\n";
                }
                if (form.txtNomeCurso.value == "") {
                    mensagem = mensagem + "Informe o Nome do Curso\n";
                }
                if (form.txtTotalPeriodos.value == "") {
                    mensagem = mensagem + "Informe o Total de Períodos\n";
                }
                if (form.txtCargaHoraria.value == "") {
                    mensagem = mensagem + "Informe a Carga Horária\n";
                }
                if (!campoNumerico(form.txtCodCurso.value)) {
                    mensagem = mensagem + "Código do Curso deve ser numérico\n";
                }
                if (!campoNumerico(form.txtTotalPeriodos.value)) {
                    mensagem = mensagem + "Total de Períodos deve ser numérico\n";
                }
                if (!campoNumerico(form.txtCargaHoraria.value)) {
                    mensagem = mensagem + "Carga Horária deve ser numérica\n";
                }
                if (mensagem == "") {
                    return true;
                } else {
                    alert(mensagem);
                    return false;
                }
            }
            //-->
        </SCRIPT>        
    </body>
</html>

