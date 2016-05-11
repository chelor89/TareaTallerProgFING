package interfaces;

import dataTypes.DataCliente;
import dataTypes.DataFecha;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import dataTypes.Estado;
import java.util.Set;

public interface IControladorPedidos {
    
    public void altaPedido();
    
    public void altaPedido(DataFecha df);
    
    public void agregarProducto(int cant);
    
    public void actualizarEstadoPedido(Estado es);
    
    public void cancelarPedidoM();
    
    public Set<DataCliente> listarClientes();
    
    public Set<String> listarCategorias();
        
    public Set<DataPedido> listarPedidos();
    
    public Set<DataProducto> listarProductosDelRestaurante(int tipo);
    
    public Set<DataRestaurante> listarRestaurantesCategoria();
    
    public DataPedido seleccionarPedido(int id);
    
    public void seleccionarCategoria(String cat);
    
    public DataPedido verInfoPedido();
    
    public DataPedido verInfoPedidoGenerado();
    
    public void SeleccionarRestaurante(String nombreRes);
    
    public void SeleccionarProducto(String nombrePro);
    
    public void SeleccionarCliente(String nick);
    
    public void AgregarPedidoACliente();
}
