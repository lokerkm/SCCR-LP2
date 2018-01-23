<%-- 
    private int codInscricao;
    private Atleta atleta;
    private int numeroPeito;
    private Percurso percurso;
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Manter Inscricão</title>
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
                    <li class="breadcrumb-item active" aria-current="page">Manter Inscrição - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form action="ManterInscricaoController?acao=confirmarIncluirViaEvento&tipoLogin=${tipoLogin}" method="post" name="frmManterInscricaoViaEvento" >
                <div class="card">
                    <div class="card-block">
                        <div class="form-row">
                            <div class="form-group col-md-1">
                                <label for="codInscricao">Código de Inscrição</label>
                                <input class="form-control" id="codInscricao" type="text" name="txtCodInscricao" value="${inscricao.codInscricao}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="evento">Evento</label>
                                    <select class="form-control" id="evento" name="txtCodEvento" readonly>
                                        <option value="${evento.codEvento}" selected>${evento.titulo}</option>  
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-1">
                                <label for="numeroPeito">Numero do peito</label>
                                <input class="form-control" id="numeroPeito" type="text" name="txtNumeroPeito" value="${inscricao.numeroPeito}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-1">
                                    <label for="tamanhoCamisa">Tamanho da Camisa</label>
                                    <input class="form-control" id="tamanhoCamisa" type="text" name="txtTamanhoCamisa" value="${inscricao.tamCamisa}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>


                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="atleta">Atleta</label>
                                    <select class="form-control" id="atleta"name="optAtleta" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                    <c:forEach items="${atletas}" var="atleta">
                                        <option value="${atleta.codAtleta}" <c:if test="${atleta.codAtleta== inscricao.codAtleta}"> selected</c:if>>${atleta.nome}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-2">
                                <label for="chip">Chip</label>
                                <select class="form-control" id="chip"name="optChip" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>    
                                    <c:forEach items="${chips}" var="chip">
                                        <option value="${chip.codChip}" <c:if test="${chip.codChip== inscricao.codChip}"> selected</c:if>>${chip.codChip}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="percurso">Percurso</label>
                                <select class="form-control" id="percurso" name="optPercurso" onLoad="confereEvento()" onChange="confereEvento()" <c:if test="${operacao == 'Excluir'}" > readonly</c:if>>
                                    <c:forEach items="${percursos}" var="percurso">
                                        <option value="${percurso.codPercurso}" <c:if test="${percurso.codPercurso == inscricao.codPercurso} && ${percurso.codEvento == evento.codEvento}"> selected</c:if> >${percurso.nome} </option>
                                    </c:forEach>
                                </select>
                            </div> 
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="kit">Kit</label>
                                <select class="form-control" id="kit" name="optKit" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                    <c:forEach items="${kits}" var="kit">
                                        <option value="${kit.codKit}" <c:if test="${kit.codKit== percurso.codKit}"> selected</c:if>>${kit.descricao}-R$${kit.valorKit}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>


                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="codPagamento">Código Pagamento</label>
                                <input class="form-control" id="txtCodPagamento" type="text" name="txtCodPagamento" value="${inscricao.codPagamento}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                </div>
                            </div>


                            <div class="form-row" <c:if test="${operacao == 'Incluir'}"> hidden</c:if>>
                                <div class="form-group col-md-2">
                                    <label for="retiradaKit">Status da retirada do Kit</label>
                                <c:if test="${inscricao.statusRetirada == false}" > 
                                    <input class="form-control" id="retiradaKit" type="text" name="txtStatusRetirada" value="Não retirado" <c:if test="${operacao == 'Incluir'}"> disabled</c:if> <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                </c:if>
                                <c:if test="${inscricao.statusRetirada == true}" > 
                                    <input class="form-control" id="retiradaKit" type="text" name="txtStatusRetirada" value="Retirado" <c:if test="${operacao == 'Incluir'}"> disabled</c:if> <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-sm-10">
                                <button type="submit" name="btnConfirmar"  id="btnConfirmar" class="btn btn-success">Confirmar</button>
                                <a class ="btn btn-info" href="PesquisaEventoController?tipoLogin=${tipoLogin}"> Voltar</a>
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
