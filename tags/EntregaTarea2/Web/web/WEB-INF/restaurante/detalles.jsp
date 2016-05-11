<%-- 
    Document   : detalles
    Created on : 21/09/2014, 09:30:06 PM
    Author     : Marcelo
--%>

<%@page import="interfaces.IControladorRestaurantes"%>
<%@page import="dataTypes.DataPedido"%>
<%@page import="dataTypes.DataComentario"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.Iterator"%>
<%@page import="entidades.Producto"%>
<%@page import="dataTypes.DataProducto"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Set"%>
<%@page import="dataTypes.DataRestaurante"%>
<%@page import="com.quickorder.controllers.Login"%>
<%@page import="dataTypes.DataCliente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/WEB-INF/template/head.jsp"/>
        <title>Detalles de Restaurantes :: QuickOrder</title>
        <script src="path/to/javascripts/bootstrap-rating-input.min.js" type="text/javascript"></script>   
        <%DataRestaurante dr = (DataRestaurante) request.getAttribute("restaurante");%>
        <script>

            var cantProds;
            
            $(document).ready(function(){  
                
                var nom;
                
                $("#pedido").on('click',"tr.fila", function() {
                    nom = $(this).contents().filter(".nombre").text();
                    getProducto(nom);
                });
                
                //Selecciona un producto
                $(".datosProd").click(function(){
                    nom = $(this).contents().filter(".nombre").text();
                    getProducto(nom);
                });
                
                    //Agrega producto al pedido
                    $("#agregar").click(function(){
                        //agrego el producto seleccionado con su cantidad
                        addProducto(nom,$("#cantidad").find(":selected").text());
                    }); 

                    //Quita un producto del pedido
                    $("#quitar").click(function(){
                        removeProducto(nom);
                    });
                

                
                //Envia la data
                $("#pedir").click(function(){
                    if(cantProds !== 0){
                        var req = new XMLHttpRequest();
                        req.open("GET","restaurantes?tipo=generarPedido",true);
                        req.send();
                        req.onreadystatechange=function(){
                            if (req.readyState===4 && req.status===200){
                                getPedido();
                                alert("Su pedido se ha registrado con éxito");
                            }
                        };
                    }else alert("Debe seleccionar algun producto!");
                });
            });
            
            //Genera el modal del producto seleccionado
            function generarMod(prod){
                    var pre = $("<span class='lead pull-right'></span>").text("$" + prod.precio);
                    var nom = $("<span id='name' class='lead'></span>").text(prod.nombre);
                    $("#mod").empty();
                    $("#cantidad").prop('selectedIndex',0);
                    $("#mod").append(nom, pre);
                    
                    //La imagen del producto seleccionado al servidor
                    $("#imProd").attr("src","\imagenes?tipo=Producto&restaurante=<%= dr.getNick() %>&producto=" + prod.nombre);
                    
                    //Pregunto si ya fue seleccionado el producto y cambio el boton del modal
                    if(!containsProducto(prod.nombre)){
                          $("#agregar").text("Agregar Producto");
                          $("#quitar").hide();
                    }else {$("#agregar").text("Actualizar Cantidad");
                           $("#quitar").show();
                          };
                    $(".selecCant").modal("show");
                };
                
            
            //Refresca la lista de productos en el pedido
            function refresh(prods){
                $("#pedido").empty();
                $("#total").empty();
                var total = 0;
                cantProds = prods.length;
                for(i = 0; i < prods.length; i++){
                        total = total + (prods[i].precio*prods[i].cantidad);
                        var pre = $("<td></td>").append("$" + (prods[i].precio*prods[i].cantidad));
                        var nom = $("<td class='nombre'></td>").append(prods[i].nombre);
                        var cant = $("<td></td>").append(prods[i].cantidad);
                        $("#pedido").append($("<tr class='fila'></tr>").append(cant,nom,pre));
                }
                $("#total").text("$" + total);
            };
            
            
            function getProducto(nombre){
                var req = new XMLHttpRequest();
                req.open("GET","restaurantes?tipo=getProducto&producto=" + nombre,true);
                req.send();
                req.onreadystatechange=function(){
                    if (req.readyState===4 && req.status===200){
                        generarMod(JSON.parse(req.responseText));
                    }
                };
            };
            
            
            function addProducto( nombre, cantidad){
                var req = new XMLHttpRequest();
                req.open("GET","restaurantes?tipo=addProducto&producto=" + nombre + "&cantidad=" + cantidad,true);
                req.send();
                req.onreadystatechange=function(){
                    if (req.readyState===4 && req.status===200){
                        getPedido();
                    }
                }; 
            };
            
            function removeProducto(nombre){
                var req = new XMLHttpRequest();
                req.open("GET","restaurantes?tipo=removeProducto&producto=" + nombre,true);
                req.send();
                req.onreadystatechange=function(){
                    if (req.readyState===4 && req.status===200){
                        getPedido();
                    }
                }; 
            };
            
            function containsProducto(nombre){
                var req = new XMLHttpRequest();
                req.open("GET","restaurantes?tipo=containsProducto&producto=" + nombre,false);
                req.send();
                var jsonText = req.responseText;
                return JSON.parse(jsonText);
            };
            
            function getPedido(){
                var req = new XMLHttpRequest();
                req.open("GET","restaurantes?tipo=getPedido",true);
                req.send();
                req.onreadystatechange=function(){
                    if (req.readyState===4 && req.status===200){
                        var jsonText = req.responseText;
                        refresh(JSON.parse(jsonText));
                    }
                }; 
            };
            
            
            
            
        
        </script>
        <!-- Small modal -->

            <div class="modal fade selecCant" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
              <div class="modal-dialog modal-md">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title" style="display:inline">Seleccionar Cantidad</h3>
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    </div>
                    <div  class="modal-body">
                        <div class="row">
                            <div class="col-lg-6">
                                <img id='imProd'  alt="imagen del producto" class="img-thumbnail">
                                
                            </div>
                            <div class="col-lg-6">
                                <div class="row">
                                    <div class="col-lg-12" id="mod">
                                     <!--el contenido lo manda el jscript-->                                   
                                    </div>
                                    <div class="col-lg-5">
                                        <select id="cantidad" class="form-control" style="margin-top: 8%">
                                            <%for (int i=1; i<=20; i++){%>
                                            <option><%=i%></option>
                                            <%}%>                                   
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="quitar" style="display:none" type="button" class="btn btn-danger" data-dismiss="modal">Quitar</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id='agregar'>Agregar</button>
                    </div>
                </div>
              </div>
            </div>
        
        <%  DataCliente cli = Login.getInfoClienteLogueado(request);
            String Direccion;
            if (cli != null) 
                 Direccion = cli.getDireccion();
            else Direccion = "<strong> Cliente no Logueado</strong>";
            
            
        %>
        

    </head>
    
    <body onload="getPedido();">
       <jsp:include page="/WEB-INF/template/header.jsp"/>
       <% int j = 0 ;%> 
       
       
       <div class="container wrapper" >
           <div class="row">
               <div class="col-lg-8">
                   
                    <h1 id='nomRes' ><%= dr.getNombre() %>  <%for ( j = 0; j < dr.getPuntaje(); j++) {%>

                                        <img src="/media/images/star.png" title="Puntaje promedio: <%=dr.getPuntaje()%>">
                                        <%}%>    <% for ( j = 0; j < 5 - dr.getPuntaje(); j++) {%>

                                        <img src="/media/images/star_grey.png" title="Puntaje promedio: <%=dr.getPuntaje()%>">
                                        <%}%></h1> 
                    
                    <ul class="nav nav-tabs" style="margin-bottom: 3%" role="tablist">
                        <li class="active"><a href="#menu" role="tab" data-toggle="tab">Menú</a></li>
                        <li><a href="#informacion" role="tab" data-toggle="tab">Información</a></li>
                        <li><a href="#comentarios" role="tab" data-toggle="tab">Comentarios</a></li>
                    </ul>  
                    <div class="tab-content container-fluid">
                        <div class="tab-pane fade active in" id="menu">
                            <%
                                
                                Set<DataProducto> promos = dr.getProductos(2);
                                if(!promos.isEmpty()){
                            %>
                            <div class="row">
                                <h1>Promociones</h1>
                                    <%
                                        for (DataProducto p:promos){
                                    %>
                                    <div class="col-lg-6" style="padding: 0.6%">
                                        <a id="<%= p.getNombre()%>" style="cursor: pointer" class="list-group-item datosProd">
                                            <div class="pull-right"><span class="lead">$</span><span class="lead precio"><%= p.getPrecioUnitario()%></span></div>
                                            <h4 class="list-group-item-heading nombre"><%= p.getNombre()%></h4>
                                            <p class="list-group-item-text"><%= p.getDescripcion()%></p>
                                        </a>
                                    </div>
                                    <%};%>
                                
                            </div>
                            <% 
                                }    
                                Set<DataProducto> ind = dr.getProductos(1);
                                if (!ind.isEmpty()){
                            %>        
                              
                            <div class="row">
                                <h1>Individuales</h1>
                                    <%
                                        
                                        for (DataProducto i:ind){
                                    %>
                                    <div class="col-lg-6" style="padding: 0.6%">
                                        <a  style="cursor: pointer" class="list-group-item datosProd">
                                            <div class="pull-right"><span class="lead">$</span><span class="lead precio"><%= i.getPrecioUnitario()%></span></div>
                                            <h4 class="list-group-item-heading nombre"><%= i.getNombre()%></h4>
                                            <p class="list-group-item-text"><%= i.getDescripcion()%></p>
                                        </a>
                                    </div>
                                <%}%>
                            </div>
                        <%}%>
                        </div>
                        <div class="tab-pane fade" id="informacion">
                            <div class="row">
                                <div class="col-lg-12">
                                    <table class="table table-striped" >
                                        <tr>
                                            <td>Nombre:</td>
                                            <td><%= dr.getNombre() %></td> 
                                        </tr>
                                        <tr>
                                            <td>Nickname:</td>
                                            <td><%= dr.getNick() %></td>
                                        </tr>
                                        <tr>
                                            <td>Direccion:</td>
                                            <td><%= dr.getDireccion() %></td>
                                        </tr>
                                        <tr>
                                            <td>Email:</td> 
                                            <td><%= dr.getMail() %></td>
                                        </tr>
                                    </table>
                                    <div id="carousel-example-generic" class="carousel slide col-lg-5" data-ride="carousel">
                                       
                                        <ol class="carousel-indicators">
                                            <!-- Indicators -->
                                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                        <% for (int i=1;i<dr.getImagenes().length;i++){ %>
                                            <li data-target="#carousel-example-generic" data-slide-to="<%=Integer.toString(i)%>"></li>
                                        <%}%>
                                        </ol>
                                        
                                        <!-- Wrapper for slides -->
                                        <div class="carousel-inner">
                                            <div class="item active">
                                                <img src= "imagenes?tipo=Restaurante&restaurante=<%= dr.getNick() %>&indice=0" alt="imagen0">
                                                <div class="carousel-caption"></div>
                                            </div>
                                        <% for (int i=1;i < dr.getImagenes().length;i++){ %>
                                            <div class="item">
                                                <img src= "imagenes?tipo=Restaurante&restaurante=<%= dr.getNick() %>&indice=<%=Integer.toString(i)%>" alt="imagen<%=Integer.toString(i)%>">
                                                <div class="carousel-caption"></div>
                                            </div>
                                        <%}%>
                                        </div>

                                        <!-- Controls -->
                                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                          <span class="glyphicon glyphicon-chevron-left"></span>
                                        </a>
                                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                          <span class="glyphicon glyphicon-chevron-right"></span>
                                        </a>
                                    </div>    
                                </div>
                            </div>
                        </div> 
                        
                            <div class="tab-pane fade" id="comentarios">
                            <% 
                            
                            IControladorRestaurantes cr = (IControladorRestaurantes) request.getSession().getAttribute("controladorRestaurantes");                    
                            Set<DataPedido> pedidos = cr.listarPedidosRestaurante(dr.getNick()); 
                            %>
                            <div class="row">
                                <div class="col-lg-12">
                                    <table class="table table-striped" >                                
                                        
                                         <thead>
                                            <tr>
                                                <th>Cliente</th>
                                                <th>Descripcion</th>
                                                <th>Comentario</th>
                                                <th>Puntaje</th>
                                                <th>Fecha</th>
                                            </tr>
                                         </thead>
                                        <% 
                                        if (pedidos.size() == 4) {
                                        }
                                        for (DataPedido pedido : pedidos) {
                                            DataComentario comentario = pedido.getComentario();                                    
                                        %>
                                            <% if (comentario != null) { %>
                                                <tr>
                                                    
                                                    <td> <%=pedido.getNickCliente() %>  </td>
                                                    
                                                    <td>  <%=comentario.getInfoPedido() %> </td>
                                                    
                                                    <td>  <%=comentario.getContenido()%> </td>
                                                    
                                                    <td colspan="3"> <%for ( j = 0; j < comentario.getPuntaje(); j++) {%>

                                                            <img src="/media/images/star.png" title="Puntaje promedio: <%=comentario.getPuntaje()%>">
                                                        <%}%>    
                                                        <% for ( j = 0; j < 5 - comentario.getPuntaje(); j++) {%>

                                                            <img src="/media/images/star_grey.png" title="Puntaje promedio: <%=comentario.getPuntaje()%>">
                                                        <%}%> </td>
                                                    
                                                    
                                                    <td> <%=comentario.getFecha()%> </td>
                                            </tr>                               
                                            <% } %>
                                        <% } %>
                                    </table>
                                </div>
                            </div>                            
                        </div>
                        
                    </div>
               </div>
               <div class="col-lg-4">
                   <div class="container-fluid" style="margin-top: 5%"> <!--style="position: fixed; width: 33.33333333%; margin-top: 2%"-->
                       <div class="row">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                   <h3 class="panel-title">Mi Pedido</h3>
                                </div>
                                    <div class="panel-body" >
                                        <table class="table table-hover table-condensed prodSelec" id="tabla">
                                            <thead>
                                              <tr>
                                                <th>Cantidad</th>
                                                <th>Producto</th>
                                                <th>Precio</th>
                                              </tr>
                                            </thead>
                                            <tbody id="pedido" style="overflow: auto">
                                              <!--el contenido lo genera el script-->
                                            </tbody>
                                          </table>
                                    </div>
                                <div class="panel-footer"> Delivery Para:<%=Direccion%>  </div>
                                <div class="panel-body">
                                    <span>Total:  </span><span class="lead" id="total">$0</span>
                                </div>
                                <% if (cli != null){ %>
                                <div class="panel-footer"><button id="pedir" type="button" class="btn btn-primary center-block">Realizar pedido</button></div>
                                <%}else{%>
                                <form action="/login">
                                    <div class="panel-footer"><button type="submit" class="btn btn-danger center-block">Iniciar Sesión</button></div>
                                </form>
                                <%}%>
                            </div>
                        </div>
                   </div>
                </div>
           </div>            
        </div>
                            
    <jsp:include page="/WEB-INF/template/footer.jsp"/>
    </body>
</html>
