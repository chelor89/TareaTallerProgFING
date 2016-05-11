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
    
    public void actualizarEstadoPedido(Estado es, DataFecha df);
    
    public void cancelarPedidoM();
    
    public Set<DataCliente> listarClientes();
    
    public Set<String> listarCategorias();
        
    public Set<DataPedido> listarPedidos();
    
    public Set<DataProducto> listarProductosDelRestaurante(int tipo);
    
    public Set<DataRestaurante> listarRestaurantesCategoria();
    
    public DataPedido seleccionarPedido(int id);
    
    public boolean seleccionarCategoria(String cat);
    
    public DataPedido verInfoPedido();
    
    public DataPedido verInfoPedidoGenerado();
    
    public boolean SeleccionarRestaurante(String nombreRes);
    
    public boolean SeleccionarProducto(String nombrePro);
    
    public boolean SeleccionarCliente(String nick);
    
    public void AgregarPedidoACliente();
    
    public void AgregarComentarioAPedido(String contenido, int puntaje);
    
    public void AgregarComentarioAPedido(String contenido, int puntaje, DataFecha fecha);
}
