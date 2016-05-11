package dataTypes;

import java.io.IOException;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataRestaurante {
    
    private String nombre;
    private String nick;
    private String contrasenia;
    private String direccion;
    private String mail;
    private Set<String> imagenes;
    private Set<DataProducto> promociones;
    private Set<DataProducto> individuales;
    private Set<String> categorias;
    private float puntaje;
    private int visitas;
    
    // dataType de VerInfoRestaurante
    public DataRestaurante(String nom, String nick){
        this.nick = nick;
        this.nombre = nom;
    }
    
    // dataType para el main
    public DataRestaurante(String nom, String nick, String contra, String mail, String dir,
            Set<String> images, Set<DataProducto> individuales, Set<DataProducto> promociones, Set<String> cats,float calificacion, int visitas) throws IOException{
        this.nick = nick;
        this.nombre = nom;
        this.contrasenia = contra;
        this.direccion = dir;
        this.mail = mail;
        this.imagenes = images;
        this.individuales = individuales;
        this.promociones = promociones;
        this.categorias = cats;
        this.puntaje = calificacion;
        this.visitas = visitas;
    }
    
    public Set<String> getCategorias(){
        return categorias;
    }
    
    public String getDireccion(){
        return direccion;
    }
    public String getNick(){
        return nick;
    }
    
    public String getContrasenia(){
        return contrasenia;
    }
    
    public String getMail(){
        return mail;
    }
    public String getNombre(){
        return nombre;
    }
    public Set<String> getImagenes(){
        return imagenes;
    }
    
    public float getPuntaje(){
        return puntaje;
    }
    
    public int getVisitas(){
        return visitas;
    }
            
    public Set<DataProducto> getProductos(int i){
        if (i==1)
            return individuales;
        else if (i==2)
            return promociones;
        else if(i==3){
            Set<DataProducto> aux = individuales;
            aux.addAll(promociones);
            return aux;
        }
        return null;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
}
