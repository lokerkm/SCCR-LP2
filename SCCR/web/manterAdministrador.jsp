<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manter Administrador</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <style>
            html{
                position: relative;
                min-height: 100%;
            }
            .card{
                margin-top: 40px;
                margin-bottom: 40px;
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
                    <li class="breadcrumb-item"><a href="PesquisaAdministradorController?tipoLogin=${tipoLogin}">Pesquisa de Admistrador</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Manter Administrador - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form action="ManterAdministradorController?acao=confirmar${operacao}&tipoLogin=${tipoLogin}" method="post" name="frmManterAdministrador" id="frmManterAdministrador" >
                <div class="card">
                    <div class="card-block">

                        <div class ="form-row">
                            <div class = "form-group col-md-1">
                                <label for="inputCodAdmin">Código do Administrador</label>
                                <input class="form-control" id="inputCodAdmin" type="text" name="txtCodAdministrador" value="${administrador.codAdministrador}" <c:if test="${operacao != 'Incluir'}"> readonly class = "form-control-plaintext"</c:if>>
                                </div>  
                            </div>
                            <div class ="form-row">
                                <div class = "form-group col-md-4">
                                    <label for="loginAdmin">Login</label>
                                    <input class="form-control" id="loginAdmin" type="text" name="txtLoginAdministrador" value="${administrador.login}" <c:if test="${operacao == 'Excluir'}"> readonly class = "form-control-plaintext"</c:if>>
                                </div>
                            </div>

                            <div class ="form-row">
                                <div class = "form-group col-md-2">
                                    <label for="loginAdmin">Senha</label>
                                    <input class="form-control" type="password" name="txtSenhaAdministrador" id="txtSenhaAdministrador" value="${administrador.senha}" <c:if test="${operacao == 'Excluir'}"> readonly class = "form-control-plaintext"</c:if>>
                                </div>
                                <div class = "form-group col-md-2" <c:if test="${operacao == 'Excluir'}"> hidden</c:if>>
                                    <label for="loginAdmin">Confirmar Senha</label>
                                    <input class= "form-control" type="password" name="txtConfirmaSenhaAdministrador" id="txtConfirmaSenhaAdministrador" value="${administrador.senha}" >
                            </div>
                        </div>
                        <div class="form-group row ">
                            <div class="col-sm-10">
                                <button type="button" name="btnConfirmar" class="btn btn-success" onClick="validarSenha()">Confirmar</button>
                                <a class ="btn btn-info" href="PesquisaAdministradorController?tipoLogin=${tipoLogin}"> Voltar</a>
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
        <script>
            function validarSenha() {
                senha = document.getElementById('txtSenhaAdministrador').value;
                confirmaSenha = document.getElementById('txtConfirmaSenhaAdministrador').value;
                if (senha != confirmaSenha) {
                    alert("SENHAS DIFERENTES!\nFAVOR DIGITAR SENHAS IGUAIS");
                } else {
                    document.getElementById("frmManterAdministrador").submit();
                }
            }
        </script>
    </body>
</html>
