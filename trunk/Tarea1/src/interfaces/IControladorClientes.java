package interfaces;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import java.util.Set;

public interface IControladorClientes {
    
    public boolean altaCliente(String nick, String mail, String nombre, String dir, String apellido, DataFecha fechaN, String imagen);
    
    public Set<DataCliente> listarClientes();
    
    public void seleccionarCliente(String nick);
    
    public DataCliente verInfoCliente();
    
}
