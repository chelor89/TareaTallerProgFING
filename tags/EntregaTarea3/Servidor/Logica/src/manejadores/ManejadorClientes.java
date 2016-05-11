package manejadores;

import dataTypes.DataCliente;
import entidades.Cliente;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManejadorClientes {
    
    private Map<String, Cliente> clientes;
    private Map<String, Cliente> clientespormail;
    
    private ManejadorClientes() {
        clientes = new HashMap<>();
        clientespormail= new HashMap<>();
    }
    
    public static ManejadorClientes getInstance() {
        return ManejadorClientesHolder.INSTANCE;
    }
    
    private static class ManejadorClientesHolder {

        private static final ManejadorClientes INSTANCE = new ManejadorClientes();
    }
    
    public void agregarCliente(Cliente c){
        clientes.put(c.getNickName(), c); 
        clientespormail.put(c.getMail(), c);  
    }
    
    public void borrarCliente(Cliente c){
        clientes.remove(c.getNickName());
        clientespormail.remove(c.getMail());
    }
    
    public Cliente getCliente(String key){
        Cliente aux = clientes.get(key);
        if (aux == null)
            aux = clientespormail.get(key);
        return aux;
    }
    
    public boolean existeCliente(String key, String mail){
        return clientes.containsKey(key) || clientespormail.containsKey(mail);
    }
    
    // obtener productos
    public Set<DataCliente> listarClientes(){        
        Set<DataCliente> sdc = new HashSet<>(); 
        
        for(Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            String k = entry.getKey();
            Cliente v = entry.getValue();
            
            DataCliente dc = new DataCliente(v.getNickName(), v.getMail()); 
            sdc.add(dc);
        }       
        return sdc;        
    }
    
}
