/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publicar;

import Fabrica.Fabrica;
import dataTypes.DataCliente;
import dataTypes.DataFecha;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import dataTypes.Estado;
import entidades.ImagenesServidor;
import interfaces.IControladorPedidos;
import interfaces.IControladorRestaurantes;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import static javax.jws.WebParam.Mode.INOUT;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Marcelo
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class PublicadorRestaurantes {
    
    private Endpoint endpoint = null;
    Fabrica F = Fabrica.getInstance();
    private IControladorRestaurantes cr = F.getIControladorRestaurantes();
    private IControladorPedidos cp = F.getIControladorPedidos();
    
    public PublicadorRestaurantes(){}
    
    @WebMethod(exclude = true)
    public void publicar(){

            endpoint = Endpoint.publish("http://" + ImagenesServidor.urlWS + "/publicadorRestaurantes", this);

      
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod 
    public DataRestaurante getRestauranteCR(String restaurante){
        cr.seleccionarRestaurante(restaurante);
        return cr.verInfoRestauranteM();
    }
    
    @WebMethod 
    public DataPedido[] getPedidosRestaurante(String restaurante){
        Set<DataPedido> sdp = cr.listarPedidosRestaurante(restaurante);
        DataPedido[] dp = new DataPedido[sdp.size()];
        int i = 0;
        for (DataPedido d : sdp){
            dp[i] = d;
            i++;
        }
        return dp;
    }    
    
    @WebMethod 
    public DataProducto getProductoCR(String producto){
        cr.seleccionarProductoDeRestaurante(producto);
        return cr.verInfoProducto();
    }
    
    @WebMethod 
    public void generarPedido(String restaurante, String[] prodsNom, int[] prodsCant, String cliente){
        cp.SeleccionarCliente(cliente);
        cp.altaPedido();
        cp.SeleccionarRestaurante(restaurante);
        cp.AgregarPedidoACliente();
        for (int i = 0; i < prodsNom.length; i++){
            cp.SeleccionarProducto(prodsNom[i]);
            cp.agregarProducto(prodsCant[i]);
        }
        cp.actualizarEstadoPedido(Estado.Preparacion, null);
    }
    
    @WebMethod 
    public void addVisitaRestaurante(String restaurante){
        cr.addVisitaRestaurante(restaurante);
    }
}
