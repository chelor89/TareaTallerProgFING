package interfaces;

import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import java.util.Set;

public interface IControladorRestaurantes {
    
    public void actualizarIndividual(String nombre, String descripcion, String imagen, int precio);
    
    public void actualizarPromocion(String nombre, String descripcion, String imagen, Boolean activar, int descuento);
    
    public void agregarCategoriasARestaurante();
    
    public void agregarIndividualAPromocion(String nombreInd, int cantidad);
    
    public boolean altaCategoria(String nombreCat);
    
    public boolean altaIndividual(String nickRest, String nombre, String desctripcion, int precio, String imagen);
    
    public boolean altaRestaurante(String nick, String mail, String nombre, String dir, Set<String> imagenes);
    
    public boolean altaPromocion(String nickRest, String nombre, String descripcion, int descuento, int precio, String imagen);
    
    public Set<String> listarCategorias();
    
    public Set<DataProducto> listarProductos();
    
    public void seleccionarCategoria(String nombreCat);
    
    public DataProducto seleccionarProducto(String nombre, String nickRest);
    
    public void seleccionarProductoDeRestaurante(String nombre);
    
    public void seleccionarRestaurante(String nick);
    
    public DataRestaurante verInfoRestauranteM();
    
    public Set<DataRestaurante> listarRestaurantesCategoria();
    
    public Set<DataPedido> mostrarPedidosQueLoContienen();
    
    public DataProducto verInfoProducto();
    
    public boolean existeRestaurante(String k);
    
    public boolean existeProductoEnRestaurante(String key);
}
