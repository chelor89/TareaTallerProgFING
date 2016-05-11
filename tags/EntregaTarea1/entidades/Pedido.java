package entidades;

import dataTypes.DataFecha;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.Estado;
import static dataTypes.Estado.Preparacion;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Pedido {

    private int Id;
    private int precioT;
    private Estado estado;
    private DataFecha fecha;
    private Cliente cliente;
  //  private Restaurante restaurante;
   
    private Map<String, PedProd> productos;
    
    public Pedido(int id, Cliente c){
        Date d = new Date();
        SimpleDateFormat formA = new SimpleDateFormat("yyyy");
        SimpleDateFormat formM = new SimpleDateFormat("MM");
        SimpleDateFormat formD = new SimpleDateFormat("dd");
        SimpleDateFormat formH = new SimpleDateFormat("hh");
        SimpleDateFormat formMM = new SimpleDateFormat("mm");
        this.fecha = new DataFecha(Integer.parseInt(formA.format(d)), Integer.parseInt(formM.format(d)),
                Integer.parseInt(formD.format(d)), Integer.parseInt(formH.format(d)), Integer.parseInt(formMM.format(d))); 
        
        this.estado = Preparacion;
        this.Id = id;
        productos = new HashMap<>();
        this.precioT = 0;
        this.cliente = c;
    }
    
    // pedido con fecha predeterminada
    public Pedido(int id, Cliente c, DataFecha df){
        
        this.estado = Preparacion;
        this.Id = id;
        this.fecha = df;
        productos = new HashMap<>();
        this.precioT = 0;
        this.cliente = c;
    }
    
    public DataFecha getFecha(){
        return fecha;
    }
    
    public int getId(){
        return Id;
    }
    
    public int getPrecioT(){
        return precioT;
    }
    
    public Estado getEstado(){
        return estado;
    }
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public Restaurante getRestaurante(){
        Entry<String, PedProd> entry = productos.entrySet().iterator().next(); 
        return(entry.getValue().getProducto().getRestaurante());     
        
    }
         
    // agregar individual
    public void agregarProducto(int cant, Individual ind){
        PedProd PP = new PedProd(this, cant, ind);
        
        // agrego el producto al conjunto de productos que conforman el pedido
        productos.put(PP.getProducto().getNombre(), PP);
        precioT += PP.getPrecio();
         
        // agrego el pedido al conjunto de pedidos a los que pertence el producto
        ind.agregarPedido(PP);        
    }
    
    // agregar promocion
    public void agregarProducto(int cant, Promocion promo){
        PedProd PP = new PedProd(this, cant, promo);
        
        // agrego el producto al conjunto de productos que conforman el pedido
        productos.put(PP.getProducto().getNombre(), PP);
        precioT += PP.getPrecio();
         
        // agrega el pedido al conjunto de pedidos a los que pertence la promocion
        promo.agregarPedido(PP);
        // hace lo propio para cada individual contenido en la promocion
//        Map<String, CantInd> inds = promo.getIndividuales();
//        for(Entry<String, CantInd> entry : inds.entrySet()) {
//            String k = entry.getKey();
//            CantInd v = entry.getValue();
// 
//            this.agregarProducto(v.getCantidad(), v.getIndividual());
//        }    
    }
    
    public PedProd getPedProd(String key){
        return productos.get(key);
    }
    
    // obtener productos
    public Set<DataProducto> listarProductos(){        
        Set<DataProducto> sdp = new HashSet<>(); 
        
        for(Entry<String, PedProd> entry : productos.entrySet()) {
            DataProducto dp;
            PedProd v = entry.getValue();
            Producto p = v.getProducto();
            Restaurante r = p.getRestaurante();
            if(p.getTipo() == Producto.Individual)
                dp = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(), 
                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), false,  -1, -1, -1); 
            else    
                dp = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(), 
                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), p.getActiva(),  p.getDescuento(),
                        -1, -1);
                
            sdp.add(dp);
        } 
        return sdp;        
    }
    public void CancelarPedido(){
        for(Entry<String, PedProd> entry : productos.entrySet()) {
           
            PedProd v = entry.getValue();            
            v.getProducto().borrarPedido(Id);
                 
        }       
    }
    
    public void setEstado(Estado es){
        estado = es;
    }
    
    public DataPedido getInfo(){
        Set<DataProducto> sdp = new HashSet<>();
        
        for(Entry<String, PedProd> entry : productos.entrySet()) {
            String k = entry.getKey();
            PedProd v = entry.getValue();
            
            sdp.add(new DataProducto(
                    v.getProducto().getNombre(),
                    v.getProducto().getTipo(),
                  v.getProducto().getPrecio(),
                    v.getCantidad(), v.getPrecio()));
        //    (String nick, String mail, DataFecha f, Estado estado, String nickRes, int precioT, int idP, Set<DataProducto> ps
        }
        return new DataPedido(
                cliente.getNickName(), 
                cliente.getMail(),
                this.fecha,
                estado, 
                this.getRestaurante().getNickName(),
                precioT,
                this.Id,
                sdp,
                this.getRestaurante().getNombre());
    }
        
    public DataPedido getInfoPedidoGenerado(){
        Set<DataProducto> sdp = new HashSet<>();
        
        for(Entry<String, PedProd> entry : productos.entrySet()) {
            String k = entry.getKey();
            PedProd v = entry.getValue();
            
        sdp.add(new DataProducto(v.getProducto().getNombre(), v.getProducto().getTipo(),
                v.getPrecio(), v.getCantidad()));
        }
        
        return (new DataPedido(cliente.getNickName(), cliente.getMail(), fecha, estado,
                this.getRestaurante().getNickName(), precioT, Id, sdp,this.getRestaurante().getNombre()));
    }
}
