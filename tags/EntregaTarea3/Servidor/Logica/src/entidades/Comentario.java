package entidades;

import dataTypes.DataFecha;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comentario {
    
    private String contenido;
    private int puntaje;
    private DataFecha fecha;
    private Pedido pedido;
 
    public Comentario(String cont, int p){
        Date d = new Date();
        SimpleDateFormat formA = new SimpleDateFormat("yyyy");
        SimpleDateFormat formM = new SimpleDateFormat("MM");
        SimpleDateFormat formD = new SimpleDateFormat("dd");
        SimpleDateFormat formH = new SimpleDateFormat("hh");
        SimpleDateFormat formMM = new SimpleDateFormat("mm");
        this.fecha = new DataFecha(Integer.parseInt(formA.format(d)), Integer.parseInt(formM.format(d)),
                Integer.parseInt(formD.format(d)), Integer.parseInt(formH.format(d)), Integer.parseInt(formMM.format(d))); 
        this.contenido = cont;
        this.puntaje = p;
    }
    
    public Comentario(String cont, int p, DataFecha fecha){
        this.fecha = fecha;
        this.contenido = cont;
        this.puntaje = p;
    }
    
    public int getPuntaje(){
        return puntaje;
    }
    
    public String getContenido(){
        return contenido;
    }
    
    public DataFecha getFecha(){
        return fecha;
    }
    
    public Pedido getPedido(){
        return pedido;
    }
    
    public void setPedido(Pedido p){
        this.pedido = p;
    }
        
}
