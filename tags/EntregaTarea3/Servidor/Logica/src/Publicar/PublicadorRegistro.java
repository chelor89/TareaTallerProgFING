/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Publicar;

import Fabrica.Fabrica;
import dataTypes.DataFecha;
import entidades.ImagenesServidor;
import static entidades.ImagenesServidor.decodeToImage;
import interfaces.IControladorClientes;
import java.awt.image.BufferedImage;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Mati
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class PublicadorRegistro {

    //obtencion del controlador de clientes

    private Endpoint endpoint = null;
    Fabrica F = Fabrica.getInstance();
    private IControladorClientes cc = F.getIControladorClientes();

    //Constructor

    public PublicadorRegistro() {
    }

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar() {
         
            endpoint = Endpoint.publish("http://" + ImagenesServidor.urlWS + "/publicadorRegistro", this);

    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    //operaciones a agregar que esten en el servlet   

    @WebMethod
    public Boolean seleccionarCliente(String nick) {
        return cc.seleccionarCliente(nick);
    }

    @WebMethod
    public Boolean darDeAltaCliente(String nick, String mail, String nombre, String dir, String apellido, int dia, int mes, int año, String contrasenia,String imagen) {
        DataFecha fechaN = new DataFecha(año, mes, dia);
        BufferedImage img = decodeToImage(imagen);
        return cc.altaCliente(nick, mail, contrasenia, nombre, dir, apellido, fechaN ,img);
    }
    
    @WebMethod
    public void addRegistroAcceso(String IP, String URL, String SO, String navegador){
        cc.addRegistroAcceso(IP,URL,SO,navegador);
    }
    

}
