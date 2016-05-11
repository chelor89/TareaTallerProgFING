package entidades;

public class PedProd {
    
    private int cantidad;
    private int precio;
    private Pedido pedido;
    private Producto producto;
    
    public PedProd(Pedido pe, int cant, Producto prod){
        this.cantidad = cant;
        this.pedido = pe;
        this.producto = prod;
        this.precio = prod.getPrecio()*cantidad;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public int getPrecio(){
        return precio;
    }
    
    public Pedido getPedido(){
        return pedido;
    }
    
    public Producto getProducto(){
        return producto;
    }
    
}
