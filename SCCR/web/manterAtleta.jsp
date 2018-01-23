<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <title>Manter Atleta</title>
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
                    <li class="breadcrumb-item"><a href="PesquisaAtletaController?tipoLogin=${tipoLogin}">Pesquisa de Atleta</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Manter Atleta - ${operacao}</li>
                </ol>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-outline-danger my-2 my-sm-0" href="index.jsp"> Logout</a></li>
            </ul>
        </nav>
        <div class="container">
            <form action="ManterAtletaController?acao=confirmar${operacao}&tipoLogin=${tipoLogin}" method="post" name="frmManterAtleta" id="frmManterAtleta" >
                <div class="card">
                    <div class="card-block">
                        <div class="form-row">
                            <div class="form-group col-md-1">
                                <label for="codAtleta">Codigo do Atleta:</label>
                                <input class="form-control" id="codAtleta" type="text" name="txtCodAtleta" value="${atleta.codAtleta}" <c:if test="${operacao != 'Incluir'}"> readonly class = "form-control-plaintext" </c:if>>
                                </div>
                                <div class="form-group col-md-1">
                                    <label for="codUsuario">Codigo de Usuario:</label>
                                    <input class="form-control" id="codUsuario" type="text" name="txtCodUsuario" value="${atleta.codUsuario}" <c:if test="${operacao != 'Incluir'}"> readonly class = "form-control-plaintext"</c:if>>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="nome">Nome</label>
                                    <input class="form-control" id="nome" type="text" name="txtNomeAtleta" value="${atleta.nome}" <c:if test="${operacao == 'Excluir'}"> readonly class = "form-control-plaintext"</c:if>></td>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="apelido">Apelido</label>
                                    <input class="form-control" id="apelido" type="text" name="txtApelidoAtleta" value="${atleta.apelido}" <c:if test="${operacao == 'Excluir'}"> readonly class = "form-control-plaintext"</c:if>></td>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="cpf">CPF</label>
                                    <input class="form-control" id="cpf"type="text" name="txtCpf" value="${atleta.cpf}" <c:if test="${operacao == 'Excluir'}"> readonly class = "form-control-plaintext"</c:if>>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="identidade">Indentidade / RG</label>
                                    <input class="form-control" id="identidade" type="text" name="txtIdentidade" value="${atleta.rg}" <c:if test="${operacao == 'Excluir'}"> readonly class = "form-control-plaintext"</c:if>>
                                </div>
                                <div class="form-group col-md-2">
                                    <label for="dataNascimento">Data de nascimento</label>
                                    <input class="form-control" id="dataNascimento" placeholder="DD/MM/AAAA" type="text" name="txtDataNascimento" value="${atleta.dataNascimento}" <c:if test="${operacao == 'Excluir'}"> readonly class = "form-control-plaintext"</c:if>>
                                </div>
                            </div>

                            <div class ="form-row">
                                <div class="form-group col-md-2">
                                    <label for="sexo">Sexo</label>
                                    <select id="sexo" class="form-control" name="optSexoAtleta" <c:if test="${operacao == 'Excluir'}"> disabled</c:if>>
                                    <option value="false" <c:if test="${atleta.sexo == false}"> selected</c:if>>masculino</option>
                                    <option value="true" <c:if test="${atleta.sexo == true}"> selected</c:if>>feminino</option>
                                    </select>
                                </div>
                            </div>




                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="telefoneFixo">Telefone Fixo</label>
                                    <input id="telefoneFixo" class="form-control" type="text" name="txtTelefoneFixo" value="${atleta.telFixo}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                                <div class="form-group col-md-2">
                                    <label fo="telefoneCelular">Telefone Celular</label>
                                    <input id="telefoneCelular" class="form-control" type="text" name="txtTelefoneCelular" value="${atleta.telCel}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-2">
                                    <label for="nomeContatoEmergencia">Nome do contato de emergência</label>
                                    <input id="nomeContatoEmergencia" class="form-control" type="text" name="txtNomeContatoEmergencia" value="${atleta.nomeEmerg}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>

                                </div>
                                <div class="form-group col-md-2">
                                    <label for="telContatoEmergencia">Telefone do contato de emergência</label>
                                    <input id="telContatoEmergencia" class="form-control" type="text" name="txtTelefoneContatoEmergencia" value="${atleta.telEmerg}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>

                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="email">E-mail:</label>
                                    <td><input id="email" class="form-control" type="email" name="txtEmail" value="${atleta.email}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>></td>

                                </div>
                                <div class="form-group col-md-3" <c:if test="${operacao == 'Excluir'}"> hidden</c:if>>
                                    <label for="confirmaEmail">Confirmar E-mail:</label>
                                    <td><input id="confirmaEmail" class="form-control" type="email" name="txtEmail" value="${atleta.email}" ></td>

                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <label for="senha">Senha</label>
                                    <input id="senha" class="form-control" type="password" name="txtSenha"  value="${atleta.senha}" <c:if test="${operacao == 'Excluir'}"> readonly</c:if>>

                                </div>
                                <div class="form-group col-md-3" <c:if test="${operacao == 'Excluir'}"> hidden</c:if>>
                                    <label for="confirmSenha">Confirmação senha</label>
                                    <input id="confirmaSenha" class="form-control" type="password" name="txtConfirmaSenha" id="txtConfirmaSenhaAtleta" value="${atleta.senha}">

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




                            <div class="form-group row">
                                <div class="col-sm-10">
                                    <button type="button" name="btnConfirmar" class="btn btn-success" onClick="validarForm()">Confirmar</button>
                                    <a class ="btn btn-info" href="PesquisaAtletaController?tipoLogin=${tipoLogin}&cadastro=${cadastro}"> Voltar</a>
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
            function validarForm() {
                senha = document.getElementById('senha').value;
                confirmaSenha = document.getElementById('confirmaSenha').value;
                if (senha != confirmaSenha) {
                    alert("SENHAS DIFERENTES!\nFAVOR DIGITAR SENHAS IGUAIS");
                } else {
                    email = document.getElementById('email').value;
                    confirmaEmail = document.getElementById('confirmaEmail').value;
                    if (email != confirmaEmail) {
                        alert("EMAILS DIFERENTES!\nFAVOR DIGITAR EMAILS IGUAIS");
                    } else {
                        document.getElementById("frmManterAtleta").submit();
                    }
                }
            }
        </script>
    </body>
</html>
