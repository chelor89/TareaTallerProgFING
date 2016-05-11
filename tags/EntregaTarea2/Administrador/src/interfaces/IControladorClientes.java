package interfaces;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Set;

public interface IControladorClientes {
    
    public boolean altaCliente(String nick, String mail, String nombre, String dir, String apellido, DataFecha fechaN, String contrasenia, BufferedImage imagen);
    
    public Set<DataCliente> listarClientes();
    
    public boolean seleccionarCliente(String nick);
    
    public DataCliente verInfoCliente();
    
}
