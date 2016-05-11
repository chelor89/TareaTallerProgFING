package controladores;

import dataTypes.DataPedido;
import dataTypes.DataProducto;
import dataTypes.DataRestaurante;
import entidades.Categoria;
import entidades.Individual;
import entidades.Pedido;
import entidades.Producto;
import entidades.Promocion;
import entidades.Restaurante;
import interfaces.IControladorRestaurantes;
import java.util.HashSet;
import java.util.Set;
import manejadores.ManejadorRestaurantes;

public class ControladorRestaurantes implements IControladorRestaurantes {
    
    private Pedido pedidoActual;
    private Categoria categoriaActual;
    private Restaurante restauranteActual;
    private Producto productoActual;
 
    public ControladorRestaurantes(){
        super();
    } 

    @Override
    public void actualizarIndividual(String nombre, String descripcion, String imagen, int precio) {
        restauranteActual.getIndividuales().remove(productoActual.getNombre());
        productoActual.setNombre(nombre);
        productoActual.setDescripcion(descripcion);
        productoActual.setImagen(imagen);
        productoActual.setPrecio(precio);
        restauranteActual.getIndividuales().put(nombre, (Individual) productoActual);
    }

    @Override
    public void actualizarPromocion(String nombre, String descripcion, String imagen, Boolean activar, int descuento) {
        restauranteActual.getPromociones().remove(productoActual.getNombre());
        productoActual.setNombre(nombre);
        productoActual.setDescripcion(descripcion);
        productoActual.setImagen(imagen);
        productoActual.setDescuento(descuento);
        productoActual.setActiva(activar);
        restauranteActual.getPromociones().put(nombre, (Promocion) productoActual);
    }

    @Override
    public void agregarCategoriasARestaurante() {
        restauranteActual.agregarCategoria(categoriaActual);
        categoriaActual.agregarRestaurante(restauranteActual);
    }

    @Override
    public void agregarIndividualAPromocion(String nombreInd, int cantidad) {
        restauranteActual.agregarIndividualAPromocion(nombreInd,cantidad, (Promocion) productoActual);
        
    }

    @Override
    public boolean altaCategoria(String nombreCat) {
        ManejadorRestaurantes mR = ManejadorRestaurantes.getInstance();
        if (!mR.existeCategoria(nombreCat)) {
          Categoria c = new Categoria(nombreCat);
          mR.agregarCategoria(c);
          return false;
        }
        return true;  
    }

    @Override
    public boolean altaIndividual(String nickRest, String nombre, String descripcion, int precio, String imagen) {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
        Restaurante R;
        R = MR.getRestaurante(nickRest);
        if (R.existeProducto(nombre)){
            return true;
        }
        R.crearIndividual(nombre, descripcion, precio, imagen);
        restauranteActual = R;
        return false;
    }

    @Override
    public boolean altaRestaurante(String nick, String mail, String nombre, String dir, Set<String> imagenes) {
     ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        if(mr.existeRestaurant(nick, mail))
            return true;
        restauranteActual = new Restaurante(nick, nombre, dir, mail, imagenes); 
        
        mr.agregarRestaurante(restauranteActual);
        return false;  
    }

    @Override
    public boolean altaPromocion(String nickRest, String nombre, String descripcion, int descuento, int precio, String imagen) {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
        Restaurante R;
        R = MR.getRestaurante(nickRest);
        if (R.existeProducto(nombre)){
            return true;
        }
        R.crearPromocion(nombre, descripcion, descuento, precio, imagen); 
        restauranteActual = R;
        return false;
    }

    @Override
    public Set<String> listarCategorias() {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
        return (MR.listarCategorias());
    }

    @Override
    public Set<DataProducto> listarProductos() {
       ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
       Set<DataRestaurante>  restaurantes = MR.listarRestaurantes();
       Set<DataProducto> productos = new HashSet<>();
       for (DataRestaurante dr : restaurantes){
           Restaurante resto = MR.getRestaurante(dr.getNick());
           productos.addAll(resto.listarProductos(3));    
       }
       return productos;  
    }

    @Override
    public void seleccionarCategoria(String nombreCat) {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();        
        categoriaActual = MR.getCategoria(nombreCat);
    }

    @Override
    public DataProducto seleccionarProducto(String nombreProd, String nickRest) {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();
        Restaurante r  = MR.getRestaurante(nickRest);
        Producto p;
        DataProducto DP;
        if (r.existeIndividual(nombreProd)){ 
            p = r.getIndividual(nombreProd); 
            DP = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(), 
                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), false,  -1, -1, -1); 
        }
        else{
            p = r.getPromocion(nombreProd);
            DP = new DataProducto(p.getNombre(), p.getDescripcion(), p.getPrecio(), p.getTipo(), 
                    r.getNombre(), r.getNickName(), r.getMail(), p.getImagen(), p.getActiva(),  p.getDescuento(),
                        -1, -1);
        }
        productoActual = p;
        restauranteActual = r;
        return DP;
    }

    @Override
    public void seleccionarRestaurante(String nick) {
        ManejadorRestaurantes MR = ManejadorRestaurantes.getInstance();    
        
        restauranteActual = MR.getRestaurante(nick);
    }

    @Override
    public DataRestaurante verInfoRestauranteM() {      
        return(restauranteActual.getInfo());
    }

    @Override
    public Set<DataRestaurante> listarRestaurantesCategoria() {
        return (categoriaActual.listarRestaurantes());        
    }

    @Override
    public void seleccionarProductoDeRestaurante(String nombre) {
        if (restauranteActual.getIndividuales().containsKey(nombre))
            productoActual = restauranteActual.getIndividual(nombre);
        else
            productoActual = restauranteActual.getPromocion(nombre);
    }

    @Override
    public Set<DataPedido> mostrarPedidosQueLoContienen() {
        return productoActual.listarPedidos();
    }

    @Override
    public DataProducto verInfoProducto() {
        return(productoActual.getInfo());
        
    }

    @Override
    public boolean existeRestaurante(String k) {
        ManejadorRestaurantes mr = ManejadorRestaurantes.getInstance();
        return mr.existeRestaurantPorNick(k);
    }

    @Override
    public boolean existeProductoEnRestaurante(String key) {
        return restauranteActual.existeProducto(key);
    }

    
}
