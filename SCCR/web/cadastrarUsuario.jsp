<%-- 
    Document   : telaCadastrar
    Created on : 12/12/2017, 09:42:03
    Author     : Aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <style>
            
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #eee;
            }
            select{
                margin-bottom: 10px;
                font-size: 12px;
                text-align: center;
            }
            .container-center{
                margin: 5rem auto;
                width: 15rem;
            }

        </style>



    </head>
    <body>
        <form action="CadastrarController" method="post" name="frmCadastrar">
            <div id="fullscreen_bg" class="fullscreen_bg" action="CadastrarController" method="post" name="frmCadastrar">
                <div class="container-center">
                    <form class="form-signin">
                        <select class="form-control" name="tipoCadastro">
                            <option value="atleta">Atleta</option>
                            <option value="organizador">Organizador</option>
                        </select>
                        <button class="btn btn-lg btn-primary btn-block" type="submit" value="confirmar" name="confirma">
                            Continuar Cadastro
                        </button>
                    </form>
                </div>
            </div>
        </form>

        

    </body>
</html>
