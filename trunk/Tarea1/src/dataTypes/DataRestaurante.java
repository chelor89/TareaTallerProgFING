package dataTypes;

import java.util.Set;

public class DataRestaurante {
    
    private String nombre;
    private String nick;
    private String direccion;
    private String mail;
    private Set<String> imagenes;
    private Set<DataProducto> productos;
    
    // dataType de VerInfoRestaurante
    public DataRestaurante(String nom, String nick){
        this.nick = nick;
        this.nombre = nom;
    }
    
    // dataType para el main
    public DataRestaurante(String nom, String nick, String mail, String dir,
            Set<String> imagenes, Set<DataProducto> productos){
        this.nick = nick;
        this.nombre = nom;
        this.direccion = dir;
        this.mail = mail;
        this.imagenes = imagenes;
        this.productos = productos;
    }
    
    public String getDireccion(){
        return mail;
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
    public Set<String> getImagenes(){
        return imagenes;
    }
    public Set<DataProducto> getProductos(){
        return productos;
    }
    @Override
    public String toString(){
        return nombre;
    }
}
