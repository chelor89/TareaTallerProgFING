package dataTypes;

import static entidades.Imagenes.leerImgURL;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;

public class DataRestaurante {
    
    private String nombre;
    private String nick;
    private String direccion;
    private String mail;
    private BufferedImage[] imagenes;
    private Set<DataProducto> promociones;
    private Set<DataProducto> individuales;
    private Set<String> categorias;
    private float puntajeProm;
    
    // dataType de VerInfoRestaurante
    public DataRestaurante(String nom, String nick){
        this.nick = nick;
        this.nombre = nom;
    }
    
    // dataType para el main
    public DataRestaurante(String nom, String nick, String mail, String dir,
            Set<String> images, Set<DataProducto> individuales, Set<DataProducto> promociones, Set<String> cats,float calificacion) throws IOException{
        this.nick = nick;
        this.nombre = nom;
        this.direccion = dir;
        this.mail = mail;
        
        BufferedImage[] imgsBuff = new BufferedImage[images.size()];
        int i=0;
        for (String img: images){
            imgsBuff[i] = leerImgURL(img);
            i++;
        }
        
        this.imagenes = imgsBuff;
        this.individuales = individuales;
        this.promociones = promociones;
        this.categorias = cats;
        this.puntajeProm = calificacion;
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
    public String getMail(){
        return mail;
    }
    public String getNombre(){
        return nombre;
    }
    public BufferedImage[] getImagenes(){
        return imagenes;
    }
    
    public float getPuntaje(){
        return puntajeProm;
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
