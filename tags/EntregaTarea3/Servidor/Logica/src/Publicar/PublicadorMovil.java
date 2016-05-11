/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publicar;

import Fabrica.Fabrica;
import dataTypes.DataFecha;
import dataTypes.DataPedido;
import dataTypes.DataRestaurante;
import dataTypes.Estado;
import entidades.ImagenesServidor;
import interfaces.IControladorPedidos;
import interfaces.IControladorRestaurantes;
import java.net.UnknownHostException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

/**
 *
 * @author ut603269
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class PublicadorMovil {

    private Endpoint endpoint = null;
    Fabrica F = Fabrica.getInstance();
    private IControladorRestaurantes cr = F.getIControladorRestaurantes();
    private IControladorPedidos cp = F.getIControladorPedidos();
    private String restoLogueado;
    public PublicadorMovil() {
    }
    

    
    @WebMethod(exclude = true)
    public void publicar() throws UnknownHostException {
        endpoint = Endpoint.publish("http://" + ImagenesServidor.urlWS + "/publicadorMovil", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public boolean existeRestaurante(String ident) {
        return cr.seleccionarRestaurante(ident);
    }

    @WebMethod
    public DataRestaurante getRestaurante(String ident) {
        cr.seleccionarRestaurante(ident);
        return cr.verInfoRestauranteM();
    }

    @WebMethod
    public DataPedido[] getPedidos() {        
        return cr.listarPedidosRestaurante(restoLogueado).toArray(new DataPedido[cr.listarPedidosRestaurante(restoLogueado).size()]);
    }
    @WebMethod
    public void ActualizarEstadoPedido(int pedido, Estado estado, DataFecha df) {        
        cp.seleccionarPedido(pedido);    
        cp.actualizarEstadoPedido(estado, df);
    }
    @WebMethod
    public void setRestoLogueado(String ident) {        
        restoLogueado = ident;
    }
    @WebMethod
    public DataPedido getPedido(int id) {        
        return cp.seleccionarPedido(id);
    }
}
