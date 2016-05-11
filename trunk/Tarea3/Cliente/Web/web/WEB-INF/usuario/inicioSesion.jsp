<%-- 
    Document   : inicioErroneo
    Created on : 21/09/2014, 08:59:13 PM
    Author     : Marcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio Erroneo :: QuickOrder</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/WEB-INF/template/head.jsp"/>
        <%
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje == null)
                mensaje = "";
        %>
    </head>
    <body>
      <jsp:include page="/WEB-INF/usuario/registro.jsp"/>
        <div id="header" style="margin-bottom:50px">
            <div class="container" id="contenido">
                <div class="row">
                    <div class="col-lg-2">
                        <a href="/casa"><img src="/media/images/logo_negativo.png"></a>
                    </div>
                    <div class="col-lg-5">
                        <form class="form-inline" role="form" method="POST" action="busqueda">
                            <div class="col-lg-6">
                                <div class="input-group">  
                                    <input type="hidden" name="tipo" value="busqueda">
                                    <input type="text" name="restaurante" class="form-control" placeholder="Restaurante o Comida..." style="width: 370px">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"/></button>
                                    </span>
                                </div><!-- /input-group -->
                            </div><!-- /.col-lg-6 -->
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="jumbotron">
            <form role='form' method="POST" action="sesion">
                <div class="form-group">
                    <label for="email"> Usuario:</label>
                    <input type="text" name="nick" id="login" class="form-control" placeholder='Email o Nickname'>
                    <% if (mensaje.equals("U")){ %>
                        <small style="color:red">El usuario ingresado no existe</small>
                    <%}%>
                </div>
                <div class="form-group">
                    <label for="pwd">Contraseña:</label>
                    <input type="password" name="password" id="pwd" class="form-control" placeholder='Contraseña'>
                    <% if (mensaje.equals("C")){ %>
                        <small style="color:red">La contraseña ingresada no es valida</small>
                    <%}%>
                </div>    
                <input type="submit" class="btn btn-default" value="Ingresar">
            </form>
        </div>
      <jsp:include page="/WEB-INF/template/footer.jsp"/>
    </body>
</html>
