package entidades;

import dataTypes.DataPedido;
import dataTypes.DataProducto;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Producto {

    protected String nombre;
    protected String descripcion;
    protected String imagen;
    protected int precio;
    protected Restaurante restaurante;
    protected int tipo;
    
    public static final int Individual = 0;
    public static final int Promocion = 1;
    
    private Map<Integer, PedProd> pedidos;   
    
    // constructor sin imagen
//    public Producto(String nom, String descripcion, Restaurante r){
//        this.nombre = nom;
//        this.descripcion = descripcion;
//       // this.precio = p;
//        pedidos = new HashMap<>();  
//        restaurante = r;
//    }
    
    // constructor con imagen
    public Producto(String nom, String descripcion, Restaurante r, String img){
        this.nombre = nom;
        this.descripcion = descripcion;
       // this.precio = p;
        this.imagen = img;
        pedidos = new HashMap<>();  
        restaurante = r;
    }
    
    public int getTipo(){
        return tipo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public int getPrecio(){
        return precio;
    }
    
    public String getImagen(){
        return imagen;
    }
    
    public abstract boolean getActiva();
    
    public abstract int getDescuento();
    
    public abstract void setActiva(boolean v);
    
    public abstract void setDescuento(int desc);
    
    public void setNombre(String n){
        this.nombre = n;
    }
    
    public void setDescripcion(String d){
        this.descripcion = d;
    }
    
    public void setPrecio(int p){
        this.precio = p;
    }
    
    public void setImagen(String im){
        this.imagen = im;
    }
    
    public Restaurante getRestaurante(){
        return restaurante;
    }
    
//    public void setRestaurante(Restaurante res){
//        this.restaurante = res;
//    }
    
    // agrega un pedido a la coleccion de pedidos a los cuales pertenece el producto
    public void agregarPedido(PedProd PP){
        pedidos.put(PP.getPedido().getId(), PP);
    }
    
    //remueve un pedido a la coleccion de pedidos a los cuales pertenece el producto
    public void borrarPedido(int idp){
        pedidos.remove(idp); 
    }
    
    public DataProducto getInfo(){
        return null;
    }
    
    public Map<Integer, PedProd> getPedidos(){
        return pedidos;
    }
   
    // obtener dataTypes de pedidos   
    public Set<DataPedido> listarPedidos(){   
        Set<DataPedido> sdp = new HashSet<>(); 
        
        for(Map.Entry<Integer, PedProd> entry : pedidos.entrySet()) {
            
            PedProd v = entry.getValue();
            DataPedido dp = v.getPedido().getInfo();

            sdp.add(dp);
        }       
        return sdp; 
    }  
    
//    public void modificarProducto(String nombre, String descripcion, String imagen,int precio, boolean activa, int descuento){
//        this.nombre = nombre;
//        this.descripcion = descripcion;
//        this.imagen = imagen;
//    }
    
}
