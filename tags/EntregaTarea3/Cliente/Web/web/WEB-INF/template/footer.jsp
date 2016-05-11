
<%@page import="publicar.DataRestauranteArray"%>
<%@page import="publicar.DataRestaurante"%>
<%@page import="java.util.List"%>
<%-- 
    Document   : footer
    Created on : 21/09/2014, 11:24:53 PM
    Author     : Marcelo
--%>


<div class="footer">
    <footer class="site">

        <div class="btn-group dropup" >
            <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown"style="margin-top:25%">
                Restaurante <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <%               
                    List<DataRestaurante> restos = (List<DataRestaurante>) request.getSession().getAttribute("restaurantes");
                    for (DataRestaurante res : restos) {
                %> 
                <li><a href="/restaurantes?restaurante=<%=res.getNick()%>&tipo=pagRestaurante"><%= res.getNombre()%></a></li> 
                    <%}%> 
            </ul>
        </div>


        <div class="section links">
            <div class="header">QuickOrder</div>
            <div class="first link">
                <a  href="https://www.facebook.com/fabirydel?fref=ts" target="_blank">Fabian Rydel</a><br>
                <a  href="https://www.facebook.com/FedeSC2?fref=ts" target="_blank">Federico Gonzalez</a><br>          
                <a  href="https://www.facebook.com/chelor89?fref=ts" target="_blank">Marcelo Rodríguez</a><br>
                <a  href="https://www.facebook.com/matias.cabrera.399?fref=ts" target="_blank">Matias Cabrera</a><br>
            </div>
        </div>
        <div class="section links">
            <div class="header">Herramientas</div>
            <div class="first link">
                <a href="/perfil?tipo=verPerfil">Mi Cuenta</a><br>
                <a data-toggle="modal" data-target="#myModal">Registro</a>
            </div>
        </div>
        <div class="section connect">
            <div class="header">Mantenete Conectado</div>
            <div class="body">
                <table>
                    <tbody><tr>
                            <td width="50px" align="center">
                                <a href="https://twitter.com" target="_blank"><img src="/media/images/twitter.png"></a>
                            </td>
                            <td width="50px" align="center">
                                <a href="https://www.facebook.com" target="_blank"><img src="/media/images/facebook.png"></a>
                            </td>
                            <td width="50px" align="center">
                                <a href="http://www.youtube.com" target="_blank"><img src="/media/images/youtube.png"></a>
                            </td>
                        </tr>
                    </tbody></table>
            </div>
        </div>
    </footer>
</div>
