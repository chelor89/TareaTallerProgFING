
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ListIterator"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="dataTypes.DataRestaurante"%>
<%@page import="interfaces.IControladorRestaurantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <jsp:include page="/WEB-INF/template/head.jsp"/> 

        <title>Pagina Principal :: QuickOrder</title>


    </head>
    <body>
        <jsp:include page="/WEB-INF/template/header.jsp"/>        
        <div class="container wrapper">
            <nav class="navbar navbar-default" role="navigation" style="margin-top:3%">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">
                            <%
                                IControladorRestaurantes cr = (IControladorRestaurantes) request.getSession().getAttribute("controladorRestaurantes");
                                Set<DataRestaurante> restos = (Set<DataRestaurante>) request.getSession().getAttribute("restaurantes");%>
                            <%=restos.size()%>
                            restaurantes encontrados</a>
                    </div>
                    
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">

                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Ordenar por<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="/busqueda?tipo=busqueda&orden=mejor_puntuados&restaurante=">Mejor puntuados(descendente)</a></li>
                                    <li><a href="/busqueda?tipo=busqueda&orden=alfabeticamente&restaurante=">Alfabeticamente(a-z)</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>

            <div class="row">
                <div class="col-lg-3">
                    <div class="list-group">
                        <%LinkedList< String> cats = (LinkedList<String>) request.getSession().getAttribute("10categorias");
                            ListIterator<String> it = cats.listIterator();
                            int i = 0;
                            while (it.hasNext()
                                    && (i < 10)) {
                                i++;
                                String cat = it.next();%>

                        <a href="/busqueda?categoria=<%=cat%>&tipo=categoria" class="list-group-item">
                            <%= cat%>
                            <span class="badge">
                                <% cr.seleccionarCategoria(cat);%>
                                <%= cr.listarRestaurantesCategoria().size()%>
                            </span>  
                        </a>
                        <%}%>
                        <a class="list-group-item" data-toggle="modal" data-target="#modalCategorias" style="cursor:pointer" >
                            Ver mas... 
                            <span class="glyphicon glyphicon-chevron-right"></span> 
                        </a>
                    </div>
                </div>
                        
                        
                <div class="col-lg-6">

                    <ul class="media-list">
                        <%
                            for (DataRestaurante dr : restos) {
                                
                                Iterator<String> iti;
                                iti = dr.getCategorias().iterator();
                                String catsresto = "";
                                while (iti.hasNext()) {
                                    catsresto = catsresto + ", " + iti.next();
                                }
                                catsresto = catsresto.substring(2);
                                catsresto = catsresto + ".";
                        %>
                        <div class="panel panel-default">
                            <li class="media">

                                <a class="pull-left" href="/restaurantes?restaurante=<%=dr.getNick()%>&tipo=pagRestaurante">
                                    <img class="media-img" src="/imagenes?tipo=Restaurante&restaurante=<%= dr.getNick()%>&indice=0" width="64" height="64" alt="">
                                </a>

                                <div class="media-body">    

                                    <span class="pull-right" style="margin: 1%">     
                                        <% for (i = 0; i < dr.getPuntaje(); i++) {%>

                                        <img src="/media/images/star.png" title="Puntaje promedio: <%=dr.getPuntaje()%>">
                                        <%}%>
                                        <%for (i = 0; i < 5 - dr.getPuntaje(); i++) {%>

                                        <img src="/media/images/star_grey.png" title="Puntaje promedio: <%=dr.getPuntaje()%>">
                                        <%}%>         
                                    </span>
                                    
                                    <a href="/restaurantes?restaurante=<%=dr.getNick()%>&tipo=pagRestaurante">
                                        <h4 class="media-heading"> <%= dr.getNombre()%> </h4> 
                                    </a>
                                    <%= dr.getDireccion()%> <br>
                                    <%= catsresto%>
                                </div>
                            </li> 
                        </div><%}%>
                    </ul>

                </div>
            </div> 
        </div>
        <jsp:include page="/WEB-INF/template/footer.jsp"/>
    </body>
    <div id="modalCategorias" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 style="margin-left:40%" id="myModalLabel">Categorías</h3>
                </div>
                <div class="modal-body">
                    <%
                        Set<String> cats2 = (Set<String>) request.getSession().getAttribute("categorias");
                        for (String cat : cats2) {%>

                    <div class="col-lg-4">

                        <a href="/busqueda?categoria=<%=cat%>&tipo=categoria">
                            <%=cat%>  
                            <span class="badge pull-right">
                                <% cr.seleccionarCategoria(cat);%>
                                <%=cr.listarRestaurantesCategoria().size()%> 
                            </span>  
                        </a>  
                    </div>  

                    <%}%>  
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>
    </div>
                    
                    
</html>


