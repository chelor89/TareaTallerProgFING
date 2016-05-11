package dataTypes;

import static entidades.Imagenes.leerImgURL;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class DataCliente {
    
    private String nick;
    private String mail;
    private String nombre;
    private String direccion;
    private String apellido;
    private BufferedImage imagen;
    private DataFecha fechaNacimiento;
    private Set<DataPedido> pedidos;
    private String contra;
    
    
    // dataType para listar y para el DataPedidos de DataProducto en VerInfoProducto
    public DataCliente(String nick, String mail){
        this.nick = nick;
        this.mail = mail;
    }
    
    // dataType para el main en VerInfoCliente
    public DataCliente(String nick, String mail, String nombre, String dir, 
            String apellido, DataFecha fechaN, String imag, Set<DataPedido> dp, String contra) {
        this.nick = nick;
        this.mail = mail;
        this.nombre = nombre;
        this.direccion = dir;
        this.apellido = apellido;
        
        File imgF = new File(imag);
        this.imagen = leerImgURL(imag);
        
        
        this.fechaNacimiento = fechaN;  
        this.pedidos = dp;
        this.contra = contra;
    }
    
    public String getNick(){
        return nick;
    }
    public String getMail(){
        return mail;
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
    public BufferedImage getImagen(){
        return imagen;
    }
    public DataFecha getFecha(){
        return fechaNacimiento;
    }
    public Set<DataPedido> getPedidos(){
        return pedidos;
    }     
    
    public String getContra(){
        return contra;
    }

    
}
