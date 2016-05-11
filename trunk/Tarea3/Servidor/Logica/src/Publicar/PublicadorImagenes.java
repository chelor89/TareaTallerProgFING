/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publicar;

import Fabrica.Fabrica;
import dataTypes.DataRestaurante;
import entidades.ImagenesServidor;
import static entidades.ImagenesServidor.encodeToString;
import static entidades.ImagenesServidor.leerImgURL;
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
public class PublicadorImagenes {
    
    private Endpoint endpoint = null;
    Fabrica F = Fabrica.getInstance();
    private IControladorRestaurantes cr = F.getIControladorRestaurantes();
    private IControladorPedidos cp = F.getIControladorPedidos();
    
    public PublicadorImagenes(){}
    
    @WebMethod(exclude = true)
    public void publicar(){
        
            endpoint = Endpoint.publish("http://" + ImagenesServidor.urlWS + "/publicadorImagenes", this);

    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
        
    @WebMethod 
    public String getImagenPerfil(String imagen){
        return encodeToString(leerImgURL(imagen), "png");
    }
    
    @WebMethod 
    public String getImagenProducto(String restaurante, String producto){
        cr.seleccionarRestaurante(restaurante);
        cr.seleccionarProductoDeRestaurante(producto);
        return encodeToString(leerImgURL(cr.verInfoProducto().getImagen()), "png");
    }
    
    @WebMethod 
    public String[] getImagenesRestaurante(String restaurante){
        cr.seleccionarRestaurante(restaurante);
        DataRestaurante dr = cr.verInfoRestauranteM();
        String[] imgs = new String[dr.getImagenes().size()];
        int i=0;
        for (String img: dr.getImagenes()){
            imgs[i] = encodeToString(leerImgURL(img), "png");
            i++;
        }
        return imgs;
    }
    
}
