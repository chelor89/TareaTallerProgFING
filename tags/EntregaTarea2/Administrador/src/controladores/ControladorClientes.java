package controladores;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import entidades.Cliente;
import entidades.Pedido;
import interfaces.IControladorClientes;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejadorClientes;

public class ControladorClientes implements IControladorClientes {
    
    private Cliente clienteActual;
    private Pedido pedidoActual;
    
    
    public ControladorClientes(){
            super();
    }

    @Override
    public boolean altaCliente(String nick, String mail, String nombre, String dir, String apellido, DataFecha fechaN, String contrasenia, BufferedImage imagen) {
        ManejadorClientes mc = ManejadorClientes.getInstance();
        if(mc.existeCliente(nick, mail))
            return true;
        try {
            mc.agregarCliente(new Cliente(nick, nombre, dir, mail, apellido, fechaN, contrasenia, imagen));
        } catch (IOException ex) {
            Logger.getLogger(ControladorClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Set<DataCliente> listarClientes() {
        ManejadorClientes MC = ManejadorClientes.getInstance();
        return(MC.listarClientes());      
    }

    @Override
    public boolean seleccionarCliente(String nick) {
        ManejadorClientes MC = ManejadorClientes.getInstance();
        clienteActual = MC.getCliente(nick);
        return clienteActual != null;
    }

    @Override 
    public DataCliente verInfoCliente() {
        try {
            return(clienteActual.getInfo());
        } catch (IOException ex) {
            Logger.getLogger(ControladorClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
 
}
