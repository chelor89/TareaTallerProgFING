/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publicar;

import Fabrica.Fabrica;
import dataTypes.DataCliente;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import entidades.ImagenesServidor;
import interfaces.IControladorClientes;
import interfaces.IControladorPedidos;
import interfaces.IControladorRestaurantes;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Marcelo
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class PublicadorPerfil {
    
    private Endpoint endpoint = null;
    Fabrica F = Fabrica.getInstance();
    private IControladorClientes cc = F.getIControladorClientes();
    private IControladorRestaurantes cr = F.getIControladorRestaurantes();
    private IControladorPedidos cp = F.getIControladorPedidos();
    
    public PublicadorPerfil(){}
    
    @WebMethod(exclude = true)
    public void publicar(){

            endpoint = Endpoint.publish("http://" + ImagenesServidor.urlWS + "/publicadorPerfil", this);

    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod
    public void agregarComentario(int numeroId, String textoComentario, int puntaje){
        cp.seleccionarPedido(numeroId);
        cp.AgregarComentarioAPedido(textoComentario, puntaje);
    }
    
  
    
    @WebMethod
    public int obtenerCantidad(int n){
        cp.seleccionarPedido(n);
        DataPedido dp = cp.verInfoPedido();
        int cant = dp.getProductos().size();
        return cant;
    }
    
    @WebMethod
    public DataProducto[] getProductos(int n, int cant){
        cp.seleccionarPedido(n);
        DataPedido dp = cp.verInfoPedido();
        DataProducto[] dpArray = dp.getProductos().toArray(new DataProducto[cant]);
        return dpArray;
    }
      
    @WebMethod
    public int getPrecioTotal(int n){
        cp.seleccionarPedido(n);
        DataPedido dp = cp.verInfoPedido();
        return dp.getPrecioTotal();
    }
    
    @WebMethod
    public boolean existeCliente(String nick){
        return cc.seleccionarCliente(nick);
    }
    
    @WebMethod
    public DataCliente getCliente(String nick){
        cc.seleccionarCliente(nick);
        return cc.verInfoCliente();
    }
}
