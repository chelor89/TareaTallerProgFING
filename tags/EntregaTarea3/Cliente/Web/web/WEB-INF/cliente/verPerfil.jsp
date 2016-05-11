<%@page import="publicar.DataFecha"%>
<%@page import="publicar.Estado"%>
<%@page import="java.util.List"%>
<%@page import="publicar.DataPedido"%>
<%@page import="publicar.DataCliente"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.quickorder.model.EstadoSesion"%>
<%@page import="com.quickorder.controllers.Sesion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="/media/js/ratingsys.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VerPerfil :: QuickOrder</title>
        <jsp:include page="/WEB-INF/template/head.jsp"/>
        <script type="text/javascript" src="/media/js/ratingsys.js"></script>
        
        <div class="modal fade" id="ModalComentario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
               <div class="modal-content">
                 <div id="modalHeader" class="modal-header">
                     <div class="row">
                        <div class="col-md-8"><h4>Ingrese su Comentario: </h4></div>
                        <div class="col-md-4"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></div>
                    </div>
                   
                   </div>
                   <form id="commentForm" class="form-horizontal registerForm" role="form" method="POST" action="perfil">    
                        <div id="body" class="modal-body">
                            <div>
                                <img id="1" src="/media/images/star_grey.png" title="1 Estrella" 
                                     onclick="rateIt(this)" onmouseover="on(this)" onmouseout="off(this)">
                                <img id="2" src="/media/images/star_grey.png" title="2 Estrellas"
                                     onclick="rateIt(this)" onmouseover="on(this)" onmouseout="off(this)">
                                <img id="3" src="/media/images/star_grey.png" title="3 Estrellas"
                                     onclick="rateIt(this)" onmouseover="on(this)" onmouseout="off(this)">
                                <img id="4" src="/media/images/star_grey.png" title="4 Estrellas"
                                     onclick="rateIt(this)" onmouseover="on(this)" onmouseout="off(this)">
                                <img id="5" src="/media/images/star_grey.png" title="5 Estrellas"
                                     onclick="rateIt(this)" onmouseover="on(this)" onmouseout="off(this)">
                            </div>
                            <br/>
                            <div class="form-group col-lg-12">
                                <textarea id="textoC" name="comentario" type="text" class="form-control" rows="3" placeholder="Comentario"></textarea>
                            </div>
                            <br/>
                            <div class="row">
                                <div class="col-lg-3">
                                    <button id="ingresarCom" type="submit" class="btn btn-default form-control pull-right botonIngresar">Ingresar</button>
                                </div>
                            </div>
                        </div>
                      <input id="ester" type="hidden" name="estrellitaestrellitadondeestas" value="">
                      <input type="hidden" name="tipo" value="comentario">
                      <input id="idPedido" type="hidden" name="numeroId" value="">  
                    </form>
               </div>
            </div>
        </div>
    
        <script>
            $(document).ready(function() {
                
                var area = $("#textoC");
                $(area).attr("disabled", "disabled");
                var button = $("#ingresarCom");
                $(button).attr("disabled", "disabled");
                
                $('#commentForm').bootstrapValidator({
                    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        comentario: {
                            message: 'El comentario no es válido',
                            validators: {
                                notEmpty: {
                                    message: 'Este campo no puede estar vacio'
                                }
                            }
                        }
                    }
                });
            });    
                 
                
//           });            
        </script>
        
        <div class="modal fade" id="ModalDetalles" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
               <div class="modal-content">
                 <div id="modalHeaderDetalles" class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                 </div>
                 <div id="body" class="modal-body">
                    <!-- <h4>Productos ordenados: </h4> -->
                    <table class="table table-hover">
                        <thead>
                          <tr>
                            <th>Nombre</th>
                            <th>Tipo</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Subtotal</th>
                          </tr>
                        </thead>
                        <tbody id="producto">

                        </tbody>
                      </table>
                    </div>
                   <div id="footerDetalles" class="modal-footer">
                       
                   </div>
               </div>
            </div>
        </div>
                               
    <script>
            <%
                DataCliente cli;
                try {
                    cli = Sesion.getInfoClienteLogueado(request);
                } catch (Exception ex) {
                    cli = null;
                }
            %>
        $(document).ready(function(){
            
                $(".botonComentar").click(function(){
                       var idP = $(this).parent().parent().contents().filter("#num").text();
                       $("#idPedido").attr("value",idP);
                });  
            
                $(".datosPed").click(abc);
                    
                function abc(){    
                    var ped = {
                               numero:$(this).parent().parent().contents().filter("#num").text(), 
                               estado:$(this).parent().parent().contents().filter("#estado").text(), 
                               nickres:$(this).parent().parent().contents().filter("#res").text() 
                              };
                    var num = $("<h4></h4>").text("Detalles del Pedido Numero " + ped.numero);
                    var es = $("<h4></h4>").text(ped.estado);
                    var nr = $("<p></p>").text("Restaurante: " + ped.nickres);
                    $("#modalHeaderDetalles").empty();
                    $("#modalHeaderDetalles").append(num);
                    $("#modalHeaderDetalles").append(es);
                    $("#modalHeaderDetalles").append(nr);
                    
                    $("#producto").empty();
                  
                    $.get('perfil',{numPedido: ped.numero,tipo:"obtenerCantidadProdsPed"}, function(cantidad){
                      
                    for(i = 0; i < cantidad; i++){
                            
                        $.get('perfil',{indice : i, numPedido : ped.numero,tipo:"obtenerDatosProd"}, function(nombreP){      
                            var res = nombreP.split("^#^");  

                            var nom = $("<td></td>").append(res[0]);
                            var tip = $("<td></td>").append(res[1]);
                            var prec = $("<td></td>").append(res[2]);
                            var canti = $("<td></td>").append(res[3]);
                            var subt = $("<td></td>").append(res[4]);
                            $("#producto").append($("<tr></tr>").append(nom, tip, prec, canti, subt));
                        });
                    }
                    
                    $("#footerDetalles").empty();
                    
                    $.get('perfil',{numPedido: ped.numero, totalPrecio: "true", indice : i,tipo:"obtenerPrecioPedido"}, function(totalP){
                        var tot = $("<h4></h4>").text("Total: " + totalP);
                        
                        $("#footerDetalles").append(tot);
                    });
                });    
            }
        });
    </script>
                 
     
    </head>
    <body>
        <jsp:include page="/WEB-INF/template/header.jsp"/>
        <div class="container wrapper">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#perfil" role="tab" data-toggle="tab">Mi perfil</a></li>
                <li><a href="#pedidos" role="tab" data-toggle="tab">Mis pedidos</a></li>
            </ul>
            <div class="tab-content pull-left">
                <div class="tab-pane active fade in container" id="perfil">
                    <br/>
                    <div class="row">
                        <div class="col-lg-3 col-lg-offset-3">
                            <img src="/imagenes?tipo=Perfil" class="img-rounded" style="width:100%;height:100%" alt="No hay foto, cabron">
                        </div>
                        <div class="col-lg-6">
                            <table class="table table-hover" style="width:50%">
                                <tr>
                                    <td>Nickname</td>
                                    <td><%= cli.getNick()%></td>
                                </tr>
                                <tr>
                                    <td>Correo</td>
                                    <td><%= cli.getMail()%></td>
                                </tr>
                                <tr>
                                    <td>Tipo</td>
                                    <td>Cliente</td>
                                </tr>
                                <tr>
                                    <td>Nombre</td> 
                                    <td><%= cli.getNombre()%></td
                                </tr>
                                <tr>
                                    <td>Apellido</td> 
                                    <td><%= cli.getApellido()%></td>
                                </tr>
                                <tr>
                                    <td>Fecha de Nacimiento</td> 
                                    <% DataFecha nac = cli.getFechaNacimiento(); %>
                                    <td><%= nac.getDia()%>/<%= nac.getMes()%>/<%= nac.getAnio()%></td>
                                </tr>
                            </table>
                        </div>
                    </div>


                </div>
                <div class="tab-pane fade in container" id="pedidos">
                    <br/>
                    <table class="table table-striped table-bordered">
                        <th>Numero</th>
                        <th>Fecha</th>
                        <th>Restaurante</th>
                        <th>Estado</th>
                        <th>Total</th>
                        <th>Detalles</th>
                        <%
                          List<DataPedido> sdp = cli.getPedidos();
                          Iterator<DataPedido> it = sdp.iterator();
                          while (it.hasNext()) {
                              DataPedido dp = it.next(); %>
                               <tr>
                                  <td id="num"><%=dp.getIdP()%></td>
                                  <% DataFecha fped = dp.getFecha(); %>
                                  <td id="fecha"><%= fped.getDia()%>/<%= fped.getMes()%>/<%= fped.getAnio()%></td> 
                                  <td id="res"><%=dp.getNickRes()%></td>
                                  <td id="estado"><%=dp.getEstado()%></td>
                                  <td id="precioT"><%="$" + dp.getPrecioT()%></td>
                                  <td>
                                      <button type="button" style="display: inline" class="btn btn-link datosPed" data-toggle="modal" data-target="#ModalDetalles">Detalles </button>
                                      <br/>
                                      <%if(dp.getEstado() == Estado.fromValue("Recibido") && dp.getComentario() == null){%>
                                      <button type="button" style="display: inline" class="btn btn-link botonComentar" data-toggle="modal" data-target="#ModalComentario">Comentar</button>
                                      <%}%>
                                  </td>
                              </tr>
                          <% } %>
                    </table>
                </div>
            </div>
        </div>
      <jsp:include page="/WEB-INF/template/footer.jsp"/>
    </body>
</html>
