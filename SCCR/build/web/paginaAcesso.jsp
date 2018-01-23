
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina Acesso - ${tipoLogin}</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <style>
            html{
                position: relative;
                min-height: 100%;
            }
            .card {
                font-size: 1em;
                overflow: hidden;
                padding: 0;
                border: none;
                border-radius: .28571429rem;
                box-shadow: 0 1px 3px 0 #d4d4d5, 0 0 0 1px #d4d4d5;
                background: gainsboro;
            }

            .card-block {
                font-size: 1em;
                position: relative;
                margin: 0;
                padding: 1em;
                border: none;
                border-top: 1px solid rgba(34, 36, 38, .1);
                box-shadow: none;
            }

            .text-bold {
                font-weight: 600;
            }

        </style>
    </head>

    <body>
        <nav class="navbar navbar-dark bg-dark navbar-fixed-top" >
            <div class="navbar-header">
                <ol class="breadcrumb" style="margin: auto;">
                    <li class="breadcrumb-item active" aria-current="page">Página do ${tipoLogin}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>


        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin != 'administrador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Admistrador</h5>
                                <a href="PesquisaAdministradorController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin == 'organizador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Atleta</h5>
                                <a href="PesquisaAtletaController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin == 'atleta'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Organizador</h5>
                                <a href="PesquisaOrganizadorController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4">
                    <div class="card">
                        <div class="card-block">
                            <h5 class="text-bold">Evento</h5>
                            <a href="PesquisaEventoController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4">
                    <div class="card">
                        <div class="card-block">
                            <h5 class="text-bold">Percurso</h5>
                            <a href="PesquisaPercursoController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin == 'atleta'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Lote</h5>
                                <a  href="PesquisaLoteController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin == 'atleta'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Local de Retirada</h5>
                                <a  href="PesquisaLocalDeRetiradaController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin == ''}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Kit</h5>
                                <a href="PesquisaKitController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin == 'atleta'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Chip</h5>
                                <a href="PesquisaChipController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin != 'administrador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Inscricao</h5>
                                <a href="PesquisaInscricaoController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin == 'organizador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Pagamento</h5>
                                <a href="PesquisaPagamentoController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin != 'administrador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Confirmar Pagamento</h5>
                                <a href="ManterPagamentoController?acao=prepararConfirmarPagamento&tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin != 'administrador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Confirmar retirada do Kit</h5>
                                <a href="ManterInscricaoController?acao=prepararConfirmarRetiradaKit&tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin != 'administrador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Endereço</h5>
                                <a href="PesquisaEnderecoController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4">
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Ranking</h5>
                                <a href="PesquisaRankingController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <%-- <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin != 'administrador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Distruibuição de Pontos</h5>
                                <a href="PesquisaDistribuicaoDePontosController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>--%>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin != 'administrador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Simular resultado do Atleta</h5>
                                <a href="ManterAtletaController?acao=prepararSimularResultado&tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-md-4 col-lg-3 mt-4" <c:if test="${tipoLogin != 'administrador'}"> hidden </c:if>>
                        <div class="card">
                            <div class="card-block">
                                <h5 class="text-bold">Pontuação</h5>
                                <a href="PesquisaPontuacaoController?tipoLogin=${tipoLogin}" class="btn btn-info float-right btn-sm">Manter</a>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <footer class="footer bg-dark " style=" position: absolute; bottom: 0; width:100%;">
            <div class="container">
                <span class="text-muted">Sistema de Controle de Corrida de Rua.</span>
            </div>
        </footer>


    </body>
</html>
