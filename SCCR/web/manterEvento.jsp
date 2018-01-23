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
                    <li class="breadcrumb-item active" aria-current="page">Manter Evento - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form action="ManterEventoController?acao=confirmar${operacao}&tipoLogin=${tipoLogin}" method="post" name="frmManterEvento" >
                <div class="card">
                    <div class="card-block">
                        <div class="form-row">
                            <div class="form-group col-md-1">
                                <label for="codEvento">Código do Evento</label>
                                <input class="form-control" id="codEvento" type="text" name="txtCodEvento" value="${evento.codEvento}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="titulo">Titulo</label>
                                    <input class="form-control" id="titulo" type="text" name="txtTituloEvento" value="${evento.titulo}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="organizador">Organizador</label>
                                    <select class="form-control" id="organizador" name="optOrganizador" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                                    <c:forEach items="${organizadores}" var="organizador">
                                        <option value="${organizador.codOrganizador}" <c:if test="${organizador.codOrganizador == evento.codOrganizador}"> selected</c:if>>${organizador.nome}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-2">
                                <label for="dataEvento">Data Evento</label>
                                <input class="form-control" id="dataEvento" type="text" name="txtDataEvento" value="${evento.dataEvento}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="horaLargada">Hora da Largada</label>
                                    <input class="form-control" id="horaLargada" type="text" name="txtHoraLargada" value="${evento.horaLargada}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-1">
                                    <label for="duracao">Duração</label>
                                    <input class="form-control" id="duracao" type="text" name="txtDuracaoEvento" value="${evento.duracao}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>


                            <div class="form-row">
                                <div class="form-group col-md-1">
                                    <label for="codEndereco">Código do Endereço</label>
                                    <input class="form-control" id="codEndereco" type="text" name="txtCodEndereco" value="${endereco.codEndereco}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="cep">Cep</label>
                                    <input class="form-control" id="cep" type="text" name="txtCepEndereco" value="${endereco.cep}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>

                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-1">
                                    <label for="uf">UF</label>
                                    <input class="form-control" id="uf" type="text" name="txtUfEndereco" value="${endereco.uf}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="cidade">Cidade</label>
                                    <input class="form-control" id="cidade" type="text" name="txtCidade" value="${endereco.cidade}"  <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="bairro">Bairro</label>
                                    <input class="form-control" id="bairro" type="text" name="txtBairro" value="${endereco.bairro}"  <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="logradouro">Logradouro</label>
                                    <input class="form-control" id="logradouro" type="text" name="txtLogradouro" value="${endereco.logradouro}"  <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-1">
                                    <label for="numero">Numero</label>
                                    <input class="form-control" id="numero" type="text" name="txtNumeroLogradouro" value="${endereco.numeroLogradouro}"  <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="complemento">Complemento</label>
                                    <input class="form-control" id="complemento" type="text" name="txtComplementoLogradouro" value="${endereco.complementoLogradouro}"  <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="localLargada">Local de Largada</label>
                                    <input class="form-control" id="localLargada" type="text" name="txtLocalLargada" value="${evento.localLargada}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="linkMapa">Link do Mapa</label>
                                    <input class="form-control" id="linkMapa" type="text" name="txtLinkGoogleMpas" value="${evento.linkMapa}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-1">
                                    <label for="maxParticipantes">Número máximo de participantes</label>
                                    <input class="form-control" id="maxParticipantes" type="text" name="txtMaxParicipantes" value="${evento.maxParticipantes}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-10">
                                    <button type="submit" name="btnConfirmar" class="btn btn-success">Confirmar</button>
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
