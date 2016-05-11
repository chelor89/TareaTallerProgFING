package interfaces;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import dataTypes.DataRegistroAcceso;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

public interface IControladorClientes {
    
    public boolean altaCliente(String nick, String mail, String contra, String nombre, String dir, String apellido, DataFecha fechaN, BufferedImage imagen);
    
    public Set<DataCliente> listarClientes();
    
    public boolean seleccionarCliente(String nick);
    
    public DataCliente verInfoCliente();
    
    public void addRegistroAcceso(String IP, String URL, String SO, String navegador);
    
    public List<DataRegistroAcceso> getRegistrosAcceso();
    
}
