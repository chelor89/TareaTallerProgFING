package manejadores;

import dataTypes.DataComentario;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import entidades.Cliente;
import entidades.Pedido;
import entidades.Restaurante;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ManejadorPedidos {
        
    private Map<Integer, Pedido> pedidos;
    
    private ManejadorPedidos() {       
        pedidos = new HashMap<>();      
    }
    
    public static ManejadorPedidos getInstance() {
        return ManejadorPedidosHolder.INSTANCE;
    }
    
    private static class ManejadorPedidosHolder {

        private static final ManejadorPedidos INSTANCE = new ManejadorPedidos();
    }
    
    public void agregarPedido(Pedido p){
        pedidos.put(p.getId(), p);        
    }
    
    public void borrarPedido(Pedido p){
        pedidos.remove(p.getId());
    }
    
    public Pedido obtenerPedido(int key){
        return pedidos.get(key);
    }
    
    public Map<Integer, Pedido> getPedidos(){
        return pedidos;
    }
 
    // obtener pedidos   
    public Set<DataPedido> listarPedidos(){   
        Set<DataPedido> sdp = new HashSet<>(); 
        
        for(Map.Entry<Integer, Pedido> entry : pedidos.entrySet()) {
            
            Pedido v = entry.getValue();
            Restaurante r = v.getRestaurante();
            Cliente c = v.getCliente();
            
            String prods = "";
            Set<DataProducto> ps = v.listarProductos();
            for(DataProducto p : ps)
              prods = prods + " " + p.getNombre();
            
           // DataComentario com = new DataComentario(v.getComentario().getContenido(), v.getComentario().getPuntaje(),
            //        v.getComentario().getFecha(), prods);
            DataComentario com = v.getInfo().getComentario();
            DataPedido dp = new DataPedido(c.getNickName(), c.getMail(), v.getFecha(), v.getEstado(),
                    r.getNickName(), v.getPrecioT(), v.getId(), v.listarProductos(), r.getNombre(), com); 
            sdp.add(dp);
        }       
        return sdp; 
    }       
    
}
