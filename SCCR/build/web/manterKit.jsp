<%-- 

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Manter Kit</title>
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
                    <li class="breadcrumb-item"><a href="PesquisaKitController?tipoLogin=${tipoLogin}">Pesquisa de Kit</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Manter Kit - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>

        <div class="container">
            <form action="ManterKitController?acao=confirmar${operacao}&tipoLogin=${tipoLogin}" method="post" name="frmManterKit">
                <div class="card">
                    <div class="card-block">
                        <div class="form-row">
                            <div class="form-group col-md-1">
                                <label for="codKit">Código</label>
                                <input class="form-control" id="codKit" type="text" name="txtCodKit" value="${kit.codKit}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>    
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-1">
                                    <label for="descricao">Descrição</label>
                                    <input class="form-control" id="descricao"type="text" name="txtDescricao" value="${kit.descricao}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-1">
                                    <label for="camisa">Camisa:</label>
                                    <select class="form-control" id="camisa" name="optCamisa" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                                    <option value=false <c:if test="${kit.camisa == false}"> selected</c:if>>Sem camisa</option>
                                    <option value=true <c:if test="${kit.camisa== true}"> selected</c:if>>Com camisa</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-1">
                                    <label for="produtos">Produtos</label>
                                    <input class="form-control" id="produtos" type="text" name="txtProdutos" value="${kit.produtos}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
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
                                <label for="evento">Evento</label>
                                <select class="form-control" id="evento" name="optEvento" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                    <c:forEach items="${eventos}" var="evento">
                                        <option value="${evento.codEvento}" <c:if test="${inscricao.codPercursoEvento== evento.codEvento}"> selected</c:if>>${evento.titulo} - ${evento.dataEvento}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="localRetirada">Local de Retirada:</label>
                                <select class="form-control" id="localRetirada" name="optLocalDeRetirada" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                    <c:forEach items="${localDeRetiradas}" var="localDeRetirada">
                                        <option value="${localDeRetirada.codLocalDeRetirada}" <c:if test="${kit.codLocalDeRetirada == localDeRetirada.codLocalDeRetirada}"> selected</c:if>>${localDeRetirada.linkMapa}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-1">
                                <label for="precoKit">Preço do Kit</label>
                                <input class="form-control" id="precoKit" type="text" name="txtValorKit" value="${kit.valorKit}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-10">
                                    <button type="submit" name="btnConfirmar" class="btn btn-success">Confirmar</button>
                                    <a class ="btn btn-info" href="PesquisaKitController?tipoLogin=${tipoLogin}"> Voltar</a>
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
