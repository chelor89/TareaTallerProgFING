package entidades;

import dataTypes.DataCantInd;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Promocion extends Producto {
 
    private boolean activa;
    private int descuento;
    
    private Map<String, CantInd> individuales;
    
    
    // constructor con imagen
    public Promocion(String nom, String descripcion, String img, int desc, Restaurante r){
        super(nom, descripcion, r, img);
        this.precio = 0;
        this.tipo = Promocion;
        this.descuento = desc;
        activa = true;        
        individuales = new HashMap<>();
    }
    
    public Map<String, CantInd> getIndividuales(){
        return individuales;
    }
    
    @Override
    public boolean getActiva(){
        return activa;
    }
    
    @Override
    public int getDescuento(){
        return descuento;
    }
    
//    @Override
//    public void modificarProducto(String nombre, String descripcion, String imagen,int precio, boolean activa, int descuento){
//        this.activa = activa;
//        this.descuento = descuento;
//    }
    
    @Override
    public void setActiva(boolean v){
        this.activa = v;
    }
    
    @Override
    public void setDescuento(int desc){
        this.descuento = desc;
    }
    
    // agrego el individual al conjunto de indivuales que conforman la promocion
    public void agregarIndAPromo(CantInd CI){
        float  p = 1 -((float) this.descuento) / 100;                
        this.precio = (int) (this.precio + (CI.getCantidad() * CI.getIndividual().getPrecio() * p));
        individuales.put(CI.getIndividual().getNombre(), CI);
    } 
    
//    public CantInd obtenerIndividual(String key){
//        return individuales.get(key);
//    }
    
    // obtener dataTypes de individuales dentro de promocion
//    public Set<DataProducto> listarIndividuales(){        
//        Set<DataProducto> sdp = new HashSet<>(); 
//        
//        for(Map.Entry<String, CantInd> entry : individuales.entrySet()) {
//            String k = entry.getKey();
//            CantInd v = entry.getValue();
//            
//            Producto p = v.getPromocion();
//            Restaurante r = p.getRestaurante();
//               
//            DataProducto dp = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(), 
//                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), p.getActiva(),  p.getDescuento(),
//                        v.getCantidad(), v.getCantidad()*p.getPrecio());
//            sdp.add(dp);
//        }       
//        return sdp;        
//    }
    public Set<DataCantInd> listarCantInd(){        
        Set<DataCantInd> sdci = new HashSet<>(); 
        
        for(Map.Entry<String, CantInd> entry : individuales.entrySet()) {
            String k = entry.getKey();
            CantInd v = entry.getValue();
            
            Producto p = v.getPromocion();
            Restaurante r = p.getRestaurante();
            
            DataProducto dp = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(), 
                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), p.getActiva(),  p.getDescuento(),
                        v.getCantidad(), v.getCantidad()*p.getPrecio());
                    
            DataCantInd dci = new DataCantInd(v.getCantidad(), v.getIndividual().getInfo());
            sdci.add(dci);
        }       
        return sdci;        
    }
        @Override
    public DataProducto getInfo(){
        Set<DataPedido> sdp = new HashSet<>(); 
//        int p = 0;
//        for(Map.Entry<Integer, PedProd> entry : this.getPedidos().entrySet()) {
//            
//            PedProd v = entry.getValue();            
//            
//            sdp.add(v.getPedido().getInfo());
//        }       
         // public DataProducto(String nom, String desc, int precio, int tipo, String nombreRes, 
           // String nickRes, String mailRes, String imagen, boolean activada, int descuento, int cant, int precioXL)
        return new DataProducto(nombre, descripcion, precio, tipo, this.getRestaurante().getNombre(), 
                this.getRestaurante().getNickName(), this.getRestaurante().getMail(), imagen, activa, descuento, 0, 0, this.listarCantInd(), this.listarPedidos());
    }
}
    
