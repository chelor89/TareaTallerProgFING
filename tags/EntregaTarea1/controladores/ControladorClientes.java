package controladores;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import entidades.Cliente;
import entidades.Pedido;
import interfaces.IControladorClientes;
import java.util.Set;
import manejadores.ManejadorClientes;

public class ControladorClientes implements IControladorClientes {
    
    private Cliente clienteActual;
    private Pedido pedidoActual;
    
    
    public ControladorClientes(){
            super();
    }

    @Override
    public boolean altaCliente(String nick, String mail, String nombre, String dir, String apellido, DataFecha fechaN, String imagen) {
        ManejadorClientes mc = ManejadorClientes.getInstance();
        if(mc.existeCliente(nick, mail))
            return true;
        mc.agregarCliente(new Cliente(nick, nombre, dir, mail, apellido, fechaN, imagen));
        return false;    
    }

    @Override
    public Set<DataCliente> listarClientes() {
        ManejadorClientes MC = ManejadorClientes.getInstance();
        return(MC.listarClientes());      
    }

    @Override
    public void seleccionarCliente(String nick) {
        ManejadorClientes MC = ManejadorClientes.getInstance();
        clienteActual = MC.getCliente(nick);
    }

    @Override 
    public DataCliente verInfoCliente() {
        return(clienteActual.getInfo());
    }
 
}
