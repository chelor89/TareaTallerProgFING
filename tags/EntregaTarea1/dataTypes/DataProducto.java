package dataTypes;


import java.util.HashSet;
import java.util.Set;

public class DataProducto {
    
    private String nombre;
    private String nombreRes;
    private String mailRes;
    private String nickRes;
    private int tipo;
    private int precio;
    private int precioXLineaDeVenta;
    private String descripcion;
    private String imagen;
    private boolean activada;
    private int descuento;
    private int cantidad;
    private Set<DataCantInd> individuales;
    private Set<DataPedido> pedidos;
    

    // dataType para listar en GenerarPedido VerInfoProducto y ActualizarEstadoProducto y para DataRestaurante en VerInfoRestaurante
    public DataProducto(String nom, String desc, int precio, int tipo, String nombreRes, 
            String nickRes, String mailRes, String imagen, boolean activada, int descuento, int cant, int precioXL)
    {
        this.nickRes = nickRes;
        this.nombreRes = nombreRes;
        this.mailRes = mailRes;
        this.nombre = nom;
        this.descripcion = desc;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = imagen;
        this.activada = activada;
        this.descuento = descuento;
        this.cantidad = cant;
        this.pedidos = new HashSet<>();
        this.individuales = new HashSet<>();
        this.precioXLineaDeVenta = precioXL;
        
        
    }
    public DataProducto(String nom, String desc, int precio, int tipo, String nombreRes, 
            String nickRes, String mailRes, String imagen, boolean activada, int descuento, int cant, int precioXL,
            Set<DataCantInd> inds,  Set<DataPedido> peds)
    {
        this.nickRes = nickRes;
        this.nombreRes = nombreRes;
        this.mailRes = mailRes;
        this.nombre = nom;
        this.descripcion = desc;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = imagen;
        this.activada = activada;
        this.descuento = descuento;
        this.cantidad = cant;
        this.pedidos = peds;
        this.individuales = inds;
        this.precioXLineaDeVenta = precioXL;
       
        
    }  
//    public DataProducto(String nom, String nombreRes, String mailRes){
//        this.nombre = nom;
//        this.nombreRes = nombreRes;
//        this.mailRes = mailRes;
//        this.precio = precio;
//     
//        this.precio = -1;
//        this.imagen = "";
//        this.activada = false;
//        this.pedidos = new HashSet<>();
//        this.individuales = new HashSet<>();
//    }
    
//    public DataProducto(String nom, String nombreRes, String mailRes, String nickRes){
//        this.nombre = nom;
//        this.nombreRes = nombreRes;
//        this.mailRes = mailRes;
//        this.nickRes = nickRes;
//        this.imagen = "";
//    }
    
    // dataType para VerInfoPedido
    public DataProducto(String nom, int tipo, int precio, int cantidad, int precioXLV){
        this.nombre = nom;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.precioXLineaDeVenta = precioXLV;
        this.imagen = "";
    }
    
    // dataType para el DataProducto
    public DataProducto(String nom, int tipo, int precio, int cantidad){
        this.nombre = nom;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = "";
    }    
    
    // dataType para VerInfoProducto Individual, para DataCantInd y para ActualizarProducto Individual
    public DataProducto(String nom, String desc, int precio, String imagen, Set<DataPedido> pedidos){
        this.nombre = nom;
        this.descripcion = desc;
        this.precio = precio;
        this.imagen = imagen;
        this.pedidos = pedidos;
      
    }
    
    // dataType para VerInfoProducto Promocion
    public DataProducto(String nom, String desc, int precio, String imagen, boolean activada,
            Set<DataPedido> pedidos, Set<DataCantInd> individuales){
        this.nombre = nom;
        this.descripcion = desc;
        this.precio = precio;
        this.imagen = imagen;
        this.activada = activada;
        this.pedidos = pedidos;
        this.individuales = individuales;
        
    }
    
    // dataType para ActualizarProducto Promocion
    public DataProducto(String nom, String desc, String imagen, boolean activada, int descuento){
        this.nombre = nom;
        this.descripcion = desc;
        this.imagen = imagen;
        this.activada = activada;
        this.descuento = descuento;
    }
    
    // dataType para ActualizarProducto Individual
//    public DataProducto(String nom, String desc, int precio, String imagen){
//        this.nombre = nom;
//        this.descripcion = desc;
//        this.imagen = imagen;
//        this.precio = precio;
//    }
    
    public String getNombre(){
        return nombre;
    }
    public String getNombreRes(){
        return nombreRes;
    }
    public String getMailRes(){
        return mailRes;
    }
    public String getNickRes(){
        return nickRes;
    }
    public int getTipo(){
        return tipo;
    }
    public int getPrecioUnitario(){
        return precio;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getImagen(){
        return imagen;
    }
    public boolean getAcivada(){
        return activada;
    }
    public int getDescuento(){
        return descuento;
    }
    public int getCantidad(){
        return cantidad;
    }
    public Set<DataCantInd> getIndividuales(){
        return individuales;
    }
    public Set<DataPedido> getPedidos(){
        return pedidos;
    }
    public String getNickRestaurante(){
        return nickRes;
        
    }
}
