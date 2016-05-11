<%@page import="publicar.DataCliente"%>
<%@page import="com.quickorder.model.EstadoSesion"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.quickorder.controllers.Sesion"%>


<!--<head>-->


<!--    Modal de Registro de Usuarios   -->
<jsp:include page="/WEB-INF/usuario/registro.jsp"/>

<!--</head>-->

<!--<body>-->
<div id="header">
    <div class="container" id="contenido">
        <div class="row">
            <div class="col-lg-2">
                <a href="/casa"><img src="/media/images/logo_negativo.png"></a>
            </div>
            <div class="col-lg-10">
                <div class="row">
                    <%
                        DataCliente cli;
                        try {
                            cli = Sesion.getInfoClienteLogueado(request);
                        } catch (Exception ex) {
                            cli = null;
                        }

                        if (request.getSession().getAttribute("estado_sesion").equals(EstadoSesion.LOGIN_CORRECTO)) {
                    %>

                    <div class="col-lg-6 pull-right text-right">
                        <img src="/imagenes?tipo=Perfil" alt="imagenPerfil" style="height:100px;width: auto;max-width: 120px;display: block;margin-left: 8px" class="img-thumbnail pull-right">

                        <span class="blanco"> Logueado como </span> <a href="/perfil?tipo=verPerfil"><%= cli.getNombre()%></a>
                        <span style="display:block" class="blanco"><%= cli.getMail()%></span><a style="display:block" href="/sesion?tipo=cerrar">Cerrar Sesion</a>
                    </div>
                </div>
                <% } else if (request.getSession().getAttribute("estado_sesion").equals(EstadoSesion.NO_LOGIN)) {%>
                <div class="col-lg-7 pull-right">
                    <form class="form-inline pull-right" role="form" method="POST" action="sesion">    
                        <div class="form-group">
                            <input type="text" name="nick" class="form-control" placeholder="Email o Nickname">
                        </div>
                        <div class="form-group ">
                            <input type="password" name="password" class="form-control" placeholder="Contraseña">
                        </div>
                        <input type="hidden" name="paginaEnvio" value="http://localhost:8084/WEB-INF/principal/paginaPrincipal.jsp">
                        <div class="form-group">
                            <input type="submit" class="btn btn-danger form-control" value="Ingresar">
                        </div>
                    </form>
                    <button id="mReg" onclick="borrar()" class="btn btn-default btn-sm pull-right" data-toggle="modal" data-target="#myModal" style="margin-top:0.5%;display:inline">Registrarse</button>
                </div>
            </div>

            <% }%>

            <div class="row">
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
</div>
</div>
<!--</body>-->
<script>
    function borrar(){
       setTimeout( function() {  $('#registrationForm').data('bootstrapValidator').resetForm(true); }, 4000); 
    }
</script>