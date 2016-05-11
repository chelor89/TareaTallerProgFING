package interfaces;

import dataTypes.DataComentario;
import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import java.awt.image.BufferedImage;
import java.util.Set;

public interface IControladorRestaurantes {
    
    public Set<DataPedido> listarPedidosRestaurante(String nickResto);
    
    public void actualizarIndividual(String nombre, String descripcion, BufferedImage imagen, int precio);
    
    public void actualizarPromocion(String nombre, String descripcion, BufferedImage imagen, Boolean activar, int descuento);
    
    public void agregarCategoriasARestaurante();
    
    public void agregarIndividualAPromocion(String nombreInd, int cantidad);
    
    public boolean altaCategoria(String nombreCat);
    
    public boolean altaIndividual(String nickRest, String nombre, String desctripcion, int precio, BufferedImage imagen);
    
    public boolean altaRestaurante(String nick, String mail, String contra, String nombre, String dir, Set<BufferedImage> imagenes);
    
    public boolean altaPromocion(String nickRest, String nombre, String descripcion, int descuento, int precio, BufferedImage imagen);
    
    public Set<String> listarCategorias();
    
    public Set<DataProducto> listarProductos();
    
    public Set<DataRestaurante> listarRestaurantes();
    
    public void seleccionarCategoria(String nombreCat);
    
    public DataProducto seleccionarProducto(String nombre, String nickRest);
    
    public boolean seleccionarProductoDeRestaurante(String nombre);
    
    public boolean seleccionarRestaurante(String key);
    
    public DataRestaurante verInfoRestauranteM();
    
    public Set<DataRestaurante> listarRestaurantesCategoria();
    
    public Set<DataPedido> mostrarPedidosQueLoContienen();
    
    public DataProducto verInfoProducto();
    
    public boolean existeRestaurante(String k);
    
    public boolean existeProductoEnRestaurante(String key);
    
    public Set<DataComentario> listarComentarios();
    
    public void addVisitaRestaurante(String restaurante);
    
}
