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
    private DataComentario comentario;
       
    public DataPedido(String nick, String mail, DataFecha f, Estado estado, String nickRes,
            int precioT, int idP, Set<DataProducto> ps, String nombreR, DataComentario coment){
        
        this.idP = idP;
        this.nickCli = nick;
        this.nickRes = nickRes;
        this.fecha = f;
        this.estado = estado;
        this.precioT = precioT;
        this.productos = ps;
        this.nombreRes = nombreR;
        this.mail = mail;
        this.comentario = coment;
        
    }
    
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
    public DataComentario getComentario(){
        return comentario;
    }

}
