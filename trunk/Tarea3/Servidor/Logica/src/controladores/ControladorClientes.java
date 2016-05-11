package controladores;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import entidades.Cliente;
import entidades.Pedido;
import entidades.RegistroAcceso;
import interfaces.IControladorClientes;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import manejadores.ManejadorClientes;
import manejadores.ManejadorRegistros;
import dataTypes.DataRegistroAcceso;

public class ControladorClientes implements IControladorClientes {
    
    private Cliente clienteActual;
    private Pedido pedidoActual;
    
    
    public ControladorClientes(){
            super();
    }

    @Override
    public boolean altaCliente(String nick, String mail, String contra, String nombre, String dir, String apellido, DataFecha fechaN, BufferedImage imagen) {
        ManejadorClientes mc = ManejadorClientes.getInstance();
        if(mc.existeCliente(nick, mail))
            return true;
        try {
            mc.agregarCliente(new Cliente(nick, nombre, contra, dir, mail, apellido, fechaN, imagen));
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
    
    @Override
    public void addRegistroAcceso(String IP, String URL, String SO, String navegador){
        ManejadorRegistros mr = ManejadorRegistros.getInstance();
        RegistroAcceso R = new RegistroAcceso(IP,URL,SO,navegador);
        mr.addRegistro(R);
    }
    
    @Override
    public List<DataRegistroAcceso> getRegistrosAcceso(){
        ManejadorRegistros mr = ManejadorRegistros.getInstance();
        List<RegistroAcceso> lr = mr.getRegistros();
        List<DataRegistroAcceso> ret = new ArrayList();
        for (RegistroAcceso r : lr){
            DataRegistroAcceso dr = new DataRegistroAcceso(r.getIP(),r.getURL(),r.getSO(),r.getNavegador());
            ret.add(dr);
        }
        return ret;
    }
 
}
