package dataTypes;

import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataCliente {
    
    private String nick;
    private String mail;
    private String contrasenia;
    private String nombre;
    private String direccion;
    private String apellido;
    private String imagen;
    private DataFecha fechaNacimiento;
    private Set<DataPedido> pedidos;
    
    
    // dataType para listar y para el DataPedidos de DataProducto en VerInfoProducto
    public DataCliente(String nick, String mail){
        this.nick = nick;
        this.mail = mail;
    }
    
    // dataType para el main en VerInfoCliente
    public DataCliente(String nick, String mail, String contra, String nombre, String dir, 
            String apellido, DataFecha fechaN, String imag, Set<DataPedido> dp) {
        this.nick = nick;
        this.mail = mail;
        this.contrasenia = contra;
        this.nombre = nombre;
        this.direccion = dir;
        this.apellido = apellido;
        this.imagen = imag;
        
        
        this.fechaNacimiento = fechaN;  
        this.pedidos = dp;
    }
    
    public String getNick(){
        return nick;
    }
    public String getMail(){
        return mail;
    }
    
    public String getContraseña(){
        return contrasenia;
    }
    
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getImagen(){
        return imagen;
    }
    public DataFecha getFecha(){
        return fechaNacimiento;
    }
    public Set<DataPedido> getPedidos(){
        return pedidos;
    }     

    
}
