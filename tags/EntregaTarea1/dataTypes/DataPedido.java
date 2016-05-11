package dataTypes;

import java.util.Date;
import java.util.Set;

public class DataPedido {
    
    private String nickCli;
    private String mail;
    private String nickRes;
    private String nombreRes;
    private DataFecha fecha;
    private int precioT;
    private int idP;
    private Estado estado;
    private Set<DataProducto> productos;
    private DataCliente cliente;
       
    // dataType para listar en VerInfoPedido, ActualizarPedido y CancelarPedido
    public DataPedido(String nick, String mail, DataFecha f, Estado estado, String nickRes,
            int precioT, int idP, Set<DataProducto> ps, String nombreR){
        
        this.idP = idP;
        this.nickCli = nick;
        this.nickRes = nickRes;
        this.fecha = f;
        this.estado = estado;
        this.precioT = precioT;
        this.productos = ps;
        this.nombreRes = nombreR;
        this.mail = mail;
        
    }
            
//    public DataPedido(int id, String nickC, String nickR, DataFecha f, Estado estado){
//        this.idP = id;
//        this.nickCli = nickC;
//        this.nickRes = nickR;
//        this.fecha = f;
//        this.estado = estado;
//    }
//    
//    // dataType de DataCliente en VerInfoCliente
//    public DataPedido(int id, String nickR, DataFecha f){
//        this.idP = id;
//        this.nickRes = nickR;
//        this.fecha = f;
//    }
//    
//    
//    // dataType para el main en VerInfoPedido
//    public DataPedido(String nickC, Estado estado, String nickRes, int precioT, Set<DataProducto> ps){
//        this.nickCli = nickC;
//        this.nickRes = nickRes;
//        this.estado = estado;
//        this.precioT = precioT;
//        this.productos = ps;
//    }
    
//    // dataType para el DataProducto en VerInfoProducto
//    public DataPedido(DataCliente dc, int precio, DataFecha f){
//        this.cliente = dc;
//        this.fecha = f;
//        this.precioT = precio;
//    }
    
    public String getMail(){
        return mail;
    }
    public int getId(){
        return idP;
    }
    public int getPrecioTotal(){
        return precioT;
    }
    public Estado getEstado(){
        return estado;
    }
    public DataFecha getFecha(){
        return fecha;
    }
    public Set<DataProducto> getProductos(){
        return productos;
    }  
    public String getNickRestaurante(){
        return nickRes;
    }
    public String getNickCliente(){
        return nickCli;
    }
    public DataCliente getCliente(){
        return cliente;
    }
    public String getNombreRes(){
        return nombreRes;
    }
}
