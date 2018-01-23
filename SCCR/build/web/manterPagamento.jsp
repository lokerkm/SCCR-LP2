<%-- 
    Document   : manterPagamento
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
        <title>Manter Pagamento</title>
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
                    <li class="breadcrumb-item"><a href="PesquisaPagamentoController?tipoLogin=${tipoLogin}">Pesquisa de Pagamento</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Manter Pagamento - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <div class="card">
                <div class="card-block">
                    <form  action="ManterPagamentoController?acao=confirmar${operacao}&tipoLogin=${tipoLogin}" method="post" name="frmManterPagamento">
                        <div onload="mudar()" id="tabelaManter">
                            <div class="form-row">
                                <div class="form-group col-md-1">
                                    <label for="codPagamento">Código do Pagamento</label>
                                    <input id="codPagamento" class="form-control"  type="text" name="txtCodPagamento" value="${pagamento.codPagamento}" <c:if test="${operacao != 'Incluir'}"> readonly</c:if>></td>
                                    </div>
                                </div>

                                <div class="form-row" >
                                    <div class="form-group col-md-2">
                                        <label for="tipoPagamento">Forma de pagamento</label>
                                        <select id="tipoPagamento" onchange="mudar()" class="form-control" name="optTipoPagamento"   <c:if test="${operacao == 'Excluir'}"></c:if>>
                                        <option value="cartao"  <c:if test="${pagamento.tipoPagamento == 'cartao'}"> selected</c:if>>Cartão de Crédito</option>  
                                        <option  value="boleto"  <c:if test="${pagamento.tipoPagamento == 'boleto'}"> selected</c:if>>Boleto</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-2">
                                        <label for="valorTotal">Valor Total</label>   
                                        <input id="valorTotal" class="form-control" type="text" id="valor" name="txtValor" value="${pagamento.valorTotal}"  readonly>    
                                </div>
                            </div>



                            <div id="cartao" class="form-row" class="acoesDisponiveis" style="display: none">
                                <div class="form-group col-md-2">
                                    <label for="numeroCartao">Numero Cartão</label>
                                    <input id="numeroCartao" class="form-control" type="text" name="" value="" <c:if test="${operacao == '!Excluir'}">readonly</c:if>></td>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label fo="dataValidade">Data validade</label>
                                        <input id="dataValidade" class="form-control" type="text" name="" value="" <c:if test="${operacao == '!Excluir'}">readonly</c:if>>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label fo="codSeguranca">Codigo de Segurança</label>
                                        <input id="codSeguranca" class="form-control" type="text" name="" value="" <c:if test="${operacao == '!Excluir'}">readonly</c:if>>
                                    </div>
                                </div>
                                <div class="form-row" <c:if test="${operacao == 'Incluir'}"> hidden</c:if>>
                                    <div class="form-group col-md-2">
                                        <label for="statusPagamento">Status do Pagamento </label>
                                    <c:if test="${pagamento.status == false}" > 
                                        <input class="form-control" id="statusPagamento" type="text" name="txtStatusPagamento" value="Não pago" <c:if test="${operacao == 'Incluir'}"> disabled</c:if> <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                    </c:if>
                                    <c:if test="${pagamento.status == true}" > 
                                        <input class="form-control" id="statusPagamento" type="text" name="txtStatusPagamento" value="Pago" <c:if test="${operacao == 'Incluir'}"> disabled</c:if> <c:if test="${operacao != 'Incluir'}"> readonly</c:if>>
                                    </c:if>
                                </div>
                            </div>
                            <input type="hidden" id="txtCodigoBarras" name="txtCodigoBarras" value="${pagamento.codigoBarra}">
                            <div class="form-group row">
                                <div class="col-sm-10"> 
                                    <button type="submit" name="btnConfirmar" class="btn btn-success">Confirmar</button>
                                    <a class ="btn btn-info" href="PesquisaPagamentoController?tipoLogin=${tipoLogin}"> Voltar</a>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div id="boleto" class="acoesDisponiveis" style="display: none">
                        <div class="form-group row col-sm-10">
                            <form  action="ManterPagamentoController?acao=gerarBoleto&tipoLogin=${tipoLogin}" method="post" name="frmGerarBoleto">
                                <input type="hidden" id="txtCodPagamento" name="txtCodPagamento" value="${pagamento.codPagamento}">
                                <input type="hidden" id="txtValorTotal" name="txtValorTotal" value="${pagamento.valorTotal}">
                                <input type="hidden" id="txtCodigoBarras" name="txtCodigoBarras" value="${pagamento.codigoBarra}">
                                <button type="submit" name="btnConfirmar" class="btn btn-warning">Gerar boleto</button></form>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <footer class="footer bg-dark" style=" position: absolute; bottom: 0; width: 100%;" >
            <div class="container">
                <span class="text-muted">Sistema de Controle de Corrida de Rua.</span>
            </div>
        </footer>


        <SCRIPT language="JavaScript">
            document.getElementById("tipoPagamento").onload = mudar();
            document.getElementById("valor").onchange() = calcula();
                    function mudar() {
                        var tipo = document.getElementById("tipoPagamento").value;

                        if (tipo == "cartao") {
                            document.getElementById("cartao").style.display = "block";
                            document.getElementById("boleto").style.display = "none"
                        } else {
                            document.getElementById("cartao").style.display = "none";
                            document.getElementById("boleto").style.display = "block"
                        }
                    }
            function calcula() {
                var codIncricao = document.getElementsByName("optInscricao");
                var codPagamento
            }

        </SCRIPT>        
    </body>
</html>

