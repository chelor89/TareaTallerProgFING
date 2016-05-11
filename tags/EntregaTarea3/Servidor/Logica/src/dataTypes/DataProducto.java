package dataTypes;


import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataProducto {
    
    private String nombre;
    private String nombreRes;
    private String mailRes;
    private String nickRes;
    private int tipo;
    private int precio;
    private String descripcion;
    private String imagen;
    private boolean activada;
    private int descuento;
    private int cantidad;
    private Set<DataCantInd> individuales;
    private Set<DataPedido> pedidos;
    

    // dataType para listar en GenerarPedido VerInfoProducto y ActualizarEstadoProducto y para DataRestaurante en VerInfoRestaurante
    public DataProducto(String nom, String desc, int precio, int tipo, String nombreRes, 
            String nickRes, String mailRes, String image, boolean activada, int descuento, int cant, int precioXL)
    {
        this.nickRes = nickRes;
        this.nombreRes = nombreRes;
        this.mailRes = mailRes;
        this.nombre = nom;
        this.descripcion = desc;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = image;
        this.activada = activada;
        this.descuento = descuento;
        this.cantidad = cant;
        this.pedidos = new HashSet<>();
        this.individuales = new HashSet<>();
    }
    
    public DataProducto(String nom, String desc, int precio, int tipo, String nombreRes, 
            String nickRes, String mailRes, String image, boolean activada, int descuento, int cant, int precioXL,
            Set<DataCantInd> inds,  Set<DataPedido> peds) 
    {
        this.nickRes = nickRes;
        this.nombreRes = nombreRes;
        this.mailRes = mailRes;
        this.nombre = nom;
        this.descripcion = desc;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = image;
        this.activada = activada;
        this.descuento = descuento;
        this.cantidad = cant;
        this.pedidos = peds;
        this.individuales = inds;
       
        
    }  
    
    // dataType para VerInfoPedido
    public DataProducto(String nom, int tipo, int precio, int cantidad, int precioXLV){
        this.nombre = nom;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = null;
    }
    
    // dataType para el DataProducto
    public DataProducto(String nom, int tipo, int precio, int cantidad){
        this.nombre = nom;
        this.tipo = tipo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = null;
    }    
    
    // dataType para VerInfoProducto Individual, para DataCantInd y para ActualizarProducto Individual
    public DataProducto(String nom, String desc, int precio, String image, Set<DataPedido> pedidos){
        this.nombre = nom;
        this.descripcion = desc;
        this.precio = precio;
        this.imagen = image;
        this.pedidos = pedidos;
      
    }
    
    // dataType para VerInfoProducto Promocion
    public DataProducto(String nom, String desc, int precio, String image, boolean activada,
            Set<DataPedido> pedidos, Set<DataCantInd> individuales){
        this.nombre = nom;
        this.descripcion = desc;
        this.precio = precio;
        this.imagen = image;
        this.activada = activada;
        this.pedidos = pedidos;
        this.individuales = individuales;
        
    }
    
    // dataType para ActualizarProducto Promocion
    public DataProducto(String nom, String desc, String image, boolean activada, int descuento){
        this.nombre = nom;
        this.descripcion = desc;
        this.imagen = image;
        this.activada = activada;
        this.descuento = descuento;
    }
    
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
