<%-- 
    Document   : telaLogar
    Created on : 12/12/2017, 09:42:03
    Author     : Aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logar</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>


        <style>
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #eee;
            }
            .fullscreen_bg {
                position: fixed;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                background-size: cover;
                background-position: 50% 50%;
            }
            .form-signin {
                max-width: 300px;
                padding: 15px;
                margin: 0 auto;
            }
            .form-signin .form-signin-heading, .form-signin {
                margin-bottom: 10px;
            }
            .form-signin .form-control {
                position: relative;
                
                height: auto;
                padding: 10px;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
            }
            .form-signin .form-control:focus {
                z-index: 2;
            }
            .form-signin input[type="text"] {
                margin-bottom: -1px;
                border-bottom-left-radius: 0;
                border-bottom-right-radius: 0;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: none;
                border-left-style: solid;
                border-color: #000;
            }
            .form-signin input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
                border-top-style: none;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-color: #000;
            }
            .form-signin-heading {
                color: #fff;
                text-align: center;
                text-shadow: 0 2px 2px rgba(0,0,0,0.5);
            }
            .form-signin select{
                margin-bottom: 10px;
                font-size: 12px;
                text-align: center;
                
            }

        </style>


    </head>
    <body>

        <div id="fullscreen_bg" class="fullscreen_bg" action="LogarController" method="post" name="frmLogar">
            <div class="container">
                <form class="form-signin" action="LogarController" method="post" name="frmLogar" >
                    <h1 class="form-signin-heading text-muted">Login</h1>
                    <input type="text" class="form-control" placeholder="Endereço de E-mail" required="" autofocus="" name="txtLogin">
                    <input type="password" class="form-control" placeholder="Senha" required="" name="txtSenha">
                    <select class="form-control" name="tipoLogin">
                        <option value="administrador">Administrador</option>
                        <option value="atleta">Atleta</option>
                        <option value="organizador">Organizador</option>
                    </select>
                    <button class="btn btn-lg btn-primary btn-block" type="submit" value="confirmar" name="confirma">
                        Sign In
                    </button>
                    <a href="cadastrarUsuario.jsp">Não Possui cadastro? Clique aqui!</a>
                </form>
            </div>
        </div>

    </body>
</html>
