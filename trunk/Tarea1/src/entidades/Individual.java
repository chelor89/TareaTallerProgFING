package entidades;

import dataTypes.DataPedido;
import dataTypes.DataProducto;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Individual extends Producto {
    
    private Map<String,CantInd> promociones;
   
    
    public Individual(String nom, String descripcion, int p, Restaurante r, String img){
        super(nom, descripcion, r, img);
        this.precio = p;
        this.tipo = Individual;
        this.promociones = new HashMap<>(); 
    }
    
    public void agregarPromoAInd(CantInd CI){
        promociones.put(CI.getPromocion().getNombre(), CI);
    }
    
    @Override
    public boolean getActiva(){
        return false;
    }
    
    @Override
    public int getDescuento(){
        return 0;
    }
 // (String nom, String desc, int precio, String imagen, Set<DataPedido> pedidos)
    @Override
    public DataProducto getInfo(){
        Set<DataPedido> sdp = new HashSet<>(); 
     
        for(Map.Entry<Integer, PedProd> entry : this.getPedidos().entrySet()) {
            int k = entry.getKey();
            PedProd v = entry.getValue();            
            
            sdp.add(v.getPedido().getInfo());
        }       
        
        return new DataProducto(nombre, descripcion, precio, imagen, sdp);
    }
    
//    @Override
//    public void modificarProducto(String nombre, String descripcion, String imagen, int precio, boolean activa, int descuento){
//        this.precio = precio;
//    }

    @Override
    public void setActiva(boolean v) {
   }

    @Override
    public void setDescuento(int desc) {
   }

    
}
