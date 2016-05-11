package dataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataComentario {
    
    private String contenido;
    private int puntaje;
    private DataFecha fecha;
    private String infoPedido;
    
    public DataComentario(String c, int p, DataFecha f, String ip){
        this.contenido = c;
        this.puntaje = p;
        this.fecha = f;
        this.infoPedido = ip;
    }
    
    public String getContenido(){
        return contenido;
    }
    
    public int getPuntaje(){
        return puntaje;
    }
    
    public DataFecha getFecha(){
        return fecha;
    }
    
    public String getInfoPedido(){
        return infoPedido;
    }
    
}
