package dataTypes;

import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPedido {
    
    private String nickCli;
    private String dirCliente;
    private String mailCli;
    private String nickRes;
    private String nombreRes;
    private DataFecha fecha;
    private int precioT;
    private int idP;
    private Estado estado;
    private Set<DataProducto> productos;
    private DataComentario comentario;
    private DataFecha[]  estados;
       
    public DataPedido(String nick, String dc, String mail, DataFecha f, Estado estado, String nickRes,
            int precioT, int idP, Set<DataProducto> ps, String nombreR, DataComentario coment,DataFecha[] estados){
        
        this.idP = idP;
        this.dirCliente = dc;
        this.nickCli = nick;
        this.nickRes = nickRes;
        this.fecha = f;
        this.estado = estado;
        this.precioT = precioT;
        this.productos = ps;
        this.nombreRes = nombreR;
        this.mailCli = mail;
        this.comentario = coment;
        this.estados = estados;
        
    }
    
    public String getMail(){
        return mailCli;
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
    public DataFecha[] getEstados(){
        return estados;
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
    public String getDirCliente(){
        return dirCliente;
    }
    public String getNombreRes(){
        return nombreRes;
    }
    public String getDireccion(){
        return dirCliente;
    }
    public DataComentario getComentario(){
        return comentario;
    }

}
